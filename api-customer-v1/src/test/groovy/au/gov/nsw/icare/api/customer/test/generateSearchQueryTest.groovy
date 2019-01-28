package au.gov.nsw.icare.api.customer.test

import static org.junit.Assert.*
//import au.gov.nsw.icare.api.customer.utils.*

import java.util.Properties

import org.junit.After

import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test

import com.mulesoft.weave.parser.ActualParserPosition

import groovy.json.JsonOutput;
import groovy.json.JsonSlurper
import groovy.xml.XmlUtil
import groovy.util.slurpersupport.*;

class GenerateSearchQueryTest {

	final String scriptPath = "src/main/resources/queries/generate-search-query.groovy"
	GroovyShell shell
	Binding binding
	
	@Before
    void setUp() {
        binding = new Binding()
		binding.flowVars = [:]
		binding.SF_CONTACTS_TABLE_NAME = "Contact"
		binding.SF_ACCOUNTS_TABLE_NAME = "Account"
		binding.SF_ACCOUNT_CONTACT_RELATION_TABLE = "AccountContactRelation"
		binding.flowVars['queryLimit'] = 100
        shell = new GroovyShell(binding)
    }
 
    @After
    void tearDown() {

    }
 
    @Test
    void testHappyCaseContactSearch() {
		binding.flowVars.searchType = "contacts"
		binding.flowVars.metadataOnly = false
		binding.flowVars.data = new JsonSlurper().parseText('{ "attributes": { "params": { }, "criteria": { "firstName": "John" } } }')
		binding.flowVars.attributes = binding.flowVars.data.attributes
		
		runScript(scriptPath)
		
		assert(binding.flowVars.salesforceQuery != null)
		assert(binding.flowVars.salesforceQuery.indexOf('SELECT') >= 0)
		assert(binding.flowVars.salesforceQuery.indexOf('FirstName') >= 0)
		assert(binding.flowVars.salesforceQuery.indexOf('FROM') >= 0)
		assert(binding.flowVars.salesforceQuery.indexOf('ORDER BY') == -1)
		assert(binding.flowVars.salesforceQuery.indexOf('LIMIT 100') >= 0)
		assert(binding.flowVars.salesforceQuery.indexOf('OFFSET') == -1)
		assert(binding.flowVars.salesforceQuery.indexOf('WHERE FirstName = \'John\'') >= 0)
    }
	
	@Test
	void testHappyCaseAccountSearchWithOrderBy() {
		binding.flowVars.searchType = "accounts"
		binding.flowVars.metadataOnly = false
		binding.flowVars.data = new JsonSlurper().parseText(stringFromFile("src/test/resources/mocks/search-account-test-request-simple.json")).data
		binding.flowVars.attributes = binding.flowVars.data.attributes
		binding.flowVars.sfPersonaId = "XYZ123"
		
		runScript(scriptPath)
		
		assert(binding.flowVars.salesforceQuery != null)
		assert(binding.flowVars.salesforceQuery.indexOf('SELECT') >= 0)
		assert(binding.flowVars.salesforceQuery.indexOf('FROM') >= 0)
		assert(binding.flowVars.salesforceQuery.indexOf('Trading_Name__c = \'Acme Trading Ltd\'') >= 0)
		assert(binding.flowVars.salesforceQuery.indexOf('RecordTypeId = \'XYZ123\'') >= 0) 
		assert(binding.flowVars.salesforceQuery.indexOf('ORDER BY Version_Number__c DESC, Name DESC') > 0)
		assert(binding.flowVars.salesforceQuery.indexOf('LIMIT 100') >= -1)
		assert(binding.flowVars.salesforceQuery.indexOf('OFFSET') == -1)
	}
	
	@Test
	void testHappyCaseContactSearchWithParamsAndAccountRelationship() {
		binding.flowVars.searchType = "contacts"
		binding.flowVars.metadataOnly = false
		binding.flowVars.data = new JsonSlurper().parseText(stringFromFile("src/test/resources/mocks/search-contact-test-request-with-accountid.json")).data
		binding.flowVars.attributes = binding.flowVars.data.attributes
		binding.flowVars.sfPersonaId = "XYZ123"
		
		runScript(scriptPath)
		
		def searchString = "FROM AccountContactRelation WHERE AccountId = 'AccId1234' AND ContactId IN (SELECT Id FROM Contact WHERE RecordTypeId = 'XYZ123' AND Salutation = 'Mr' AND FirstName = 'John' AND LastName = 'Smith') ORDER BY Contact.Version_Number__c DESC, Contact.FirstName DESC LIMIT 10"
		assert(binding.flowVars.salesforceQuery.indexOf(searchString) >= 0)
	}
	
	@Test
	void testHappyCaseContactSearchWithParamsAndAccountRelationshipMetadataOnly() {
		binding.flowVars.searchType = "contacts"
		binding.flowVars.metadataOnly = true
		binding.flowVars.data = new JsonSlurper().parseText(stringFromFile("src/test/resources/mocks/search-contact-test-request-with-accountid.json")).data
		binding.flowVars.attributes = binding.flowVars.data.attributes
		binding.flowVars.sfPersonaId = "XYZ123"
		
		runScript(scriptPath)
		
		assert(binding.flowVars.salesforceQuery == "SELECT COUNT(ContactId) FROM AccountContactRelation WHERE AccountId = 'AccId1234' AND ContactId IN (SELECT Id FROM Contact WHERE RecordTypeId = 'XYZ123' AND Salutation = 'Mr' AND FirstName = 'John' AND LastName = 'Smith')")
	}
	
	@Test
	void testHappyCaseContactSearchWithIdCriteria() {
		binding.flowVars.searchType = "contacts"
		binding.flowVars.metadataOnly = false
		binding.flowVars.data = new JsonSlurper().parseText(stringFromFile("src/test/resources/mocks/search-contact-test-request-by-id.json")).data
		binding.flowVars.attributes = binding.flowVars.data.attributes
		binding.flowVars.sfPersonaId = null
		
		runScript(scriptPath)
		
		assert(binding.flowVars.salesforceQuery.indexOf("FROM Contact WHERE Id = '0030l00000Cwk0AAAR' LIMIT 100") != -1)
	}
	
	def rootCause(Exception e) {
		Throwable t = e;
		while (t.cause != null) t = t.cause;
		t
	}
	
	def runScript(path) {
		try {
			shell.evaluate(new File(path))
		} catch (Exception e) {
			def cause = rootCause(e)
			def line = cause.stackTrace
			println "Line $line: $cause.message"
			throw e
		}
	}
	
	def stringFromFile(path) {
		File file = new File(path)
		return file.text
	}

}
