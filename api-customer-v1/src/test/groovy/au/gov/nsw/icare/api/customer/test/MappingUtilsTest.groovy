package au.gov.nsw.icare.api.customer.test

import au.gov.nsw.icare.api.customer.utils.*


import static org.junit.Assert.*

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

class MappingUtilsTest {

	@Before
	public void setUp() throws Exception {
		
	}

	@Test
	public void convertThreeLineAddressToOneLineAllNull() {
		assert(MappingUtils.convertThreeLineAddressToOneLine(null, null, null) == null)
	}
	
	@Test
	public void convertThreeLineAddressToOneLineAllBlank() {
		ThreeLineAddress a = new ThreeLineAddress(null, null, null)
		assert(MappingUtils.convertThreeLineAddressToOneLine("", "", "") == null)
	}
	
	@Test
	public void convertThreeLineAddressToOneLineOnlyLine1TheRestNull() {
		assert(MappingUtils.convertThreeLineAddressToOneLine("123 George St", null, null) == "123 George St")
	}
	
	@Test
	public void convertThreeLineAddressToOneLineOnlyLine1AndLine2TheRestNull() {
		assert(MappingUtils.convertThreeLineAddressToOneLine("The Grand", "123 George St", null) == "The Grand\n123 George St")
	}
	
	@Test
	public void convertThreeLineAddressToOneLineOnlyLine1TheRestEmptyString() {
		assert(MappingUtils.convertThreeLineAddressToOneLine("123 George St", "", "") == "123 George St")
	}
	
	@Test
	public void convertThreeLineAddressToOneLineOnlyLine1AndLine2TheRestEmptyString() {
		assert(MappingUtils.convertThreeLineAddressToOneLine("The Grand", "123 George St", "") == "The Grand\n123 George St")
	}
	
	@Test
	public void mapAttributeFromDomainToSfIfNotNullTest() {
		def attributes = [ 
			"firstName": "Barney",
			"lastName": null,
			"description" : "",
			"address": [
				"street": "5 Stone Way",
				"suburb": null,
				"postalCode": ""
			]
		]
		
		def mapping = [
			"firstName": "GivenName",
			"lastName": "Surname",
			"password": "Passphrase",
			"description": "Notes",
			"address.street": "MailingStreet",
			"address.suburb": "MailingSuburb",
			"address.postalCode": "MailingPostalcode"
		]
		
		def mappedObj = MappingUtils.mapAttributeFromDomainToSfIfNotNull(attributes, mapping)
		
		println mappedObj.toString()
		
		// Check first level keys
		assert(mappedObj.GivenName == "Barney")
		assertFalse(mappedObj.containsKey("Surname"))
		assert(mappedObj.containsKey("Notes"))
		assert(mappedObj.Notes == null)
		
		// Check sub keys
		assert(mappedObj.MailingStreet == "5 Stone Way")
		assertFalse(mappedObj.containsKey("MailingSuburb"))
		assert(mappedObj.containsKey("MailingPostalcode"))
		assert(mappedObj.MailingPostalcode == null)		
	}

	@Test
	void checkForInvalidAttributesHappyCase() {
		def validAttributesMap = [
			"name",
			"gender",
			"phone.number"
		]
		
		def requestAttributesMap = [
			"name": "Zeus",
			"gender": "female",
			"phone": [
				"number": "12345"
			]
		]
		
		def result = MappingUtils.checkForInvalidAttributes(validAttributesMap, requestAttributesMap)
		
		assert(result.size() == 0)
	}
	
	@Test
	void convertFieldMapToDotNotationHappyCase() {
		def dotNotationList = [
			"name",
			"gender",
			"phone.number"
		]
		
		def fieldMap = [
			"name": "Zeus",
			"gender": "female",
			"phone": [
				"number": "12345"
			]
		]
		
		def result = MappingUtils.convertFieldMapToDotNotationList(fieldMap)
		assert(result == dotNotationList)
	}
	
	
	@After
	void after() {
		return
	}

}
