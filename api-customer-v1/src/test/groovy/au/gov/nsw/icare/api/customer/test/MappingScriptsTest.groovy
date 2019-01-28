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

class MappingScriptsTest {

	final String contactDomainToSfScriptPath = "src/main/resources/mappings/map-domain-contact-to-sf-object.groovy"
	final String contactDomainToSfInputPath = "src/test/resources/mocks/create-contact-test-request-partial.json"
	
	GroovyShell shell
	Binding binding
	
	@Before
    void setUp() {
        binding = new Binding()
		binding.flowVars = [:]
		binding.payload = null
        shell = new GroovyShell(binding)
    }
 
    @After
    void tearDown() {

    }
 
    @Test
    void testHappyCaseContactMapToSf() {
		binding.flowVars.attributes = new JsonSlurper().parseText(stringFromFile(contactDomainToSfInputPath)).data.attributes
 
		def payload = runScript(contactDomainToSfScriptPath)
		
		assert(payload != null)
		assert(payload instanceof List)
		assert(payload.size() == 1)
		assert(payload[0] instanceof Map)
		
		// [ABN__c:ABN34252, Agree_to_SMS_Notification__c:false, Birthdate:Tue Feb 02 11:00:00 AEDT 1982, Contracted_Provider__c:false, Description:Lorem Ipsum, Email:johns@example.org, FirstName:John, Former_Name__c:Johnathon, Gender__c:Male, Home_Phone_Country__c:Australia (61), Home_Phone_Extension__c:02, HomePhone:98423012, null:123 Sample St, LastName:Smith, MailingCity:Sydney, MailingCountry:Australia, MailingStreet:A Large Building, MailingPostalCode:2000, MailingState:NSW, Marital_Status__c:Married, Mobile_Phone_Country__c:Australia (61), Mobile_Phone_Extension__c:null, MobilePhone:0470330990, OtherCity:Sydney, OtherCountry:Australia, OtherStreet:6 Apple St, OtherPostalCode:2000, OtherState:NSW, RecordTypeId:Provider_Contact, Registered_for_GST__c:true, Salutation:Mr, Secondary_Email__c:johns_2005@msn.com, Version_Number__c:6.0, XDR_Tax_File_Number__c:XDR3456789, mailingStreet:A Large Building
       // 123 Sample St, otherStreet:6 Apple St]
		assert(payload[0].ABN__c == "ABN34252")
		assert(payload[0].Home_Phone_Extension__c == "02")
		//assert(payload[0].Version_Number__c == 6.0)
		assert(payload[0].Birthdate instanceof Date)
		
		// Check address has been handled correctly
		assert(payload[0].MailingStreet == "A Large Building\n123 Sample St")
		
		// Check there are no field name mapping errors
		assert(payload[0].findAll { k, v -> k == "null" }.size() == 0)
    }
	
	def rootCause(Exception e) {
		Throwable t = e;
		while (t.cause != null) t = t.cause;
		t
	}
	
	def runScript(path) {
		def result = null
		
		try {
			result = shell.evaluate(new File(path))
		} catch (Exception e) {
			def cause = rootCause(e)
			def line = cause.stackTrace
			println "Line $line: $cause.message"
			throw e
		}
		
		return result
	}
	
	def stringFromFile(path) {
		File file = new File(path)
		return file.text
	}

}
