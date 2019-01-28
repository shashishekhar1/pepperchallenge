package au.gov.nsw.icare.api.customer.test

import au.gov.nsw.icare.api.customer.mapping.PersonaNameMapper

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

class PersonaMapperTest {

	PersonaNameMapper mapper
	PersonaNameMapper blankMapper

	@Before
	public void setUp() throws Exception {
		mapper = new PersonaNameMapper()
		blankMapper = new PersonaNameMapper()
		
		mapper.addMapping("ID123", "Contact", "Involved_Party", "Involved Party")
		mapper.addMapping("ID456", "Contact", "Provider_Contact", "Provider Contact")
		mapper.addMapping("ID789", "Account", "Involved_Party", "Involved Party")
		mapper.addMapping("IDABC", "Account", "Provider_Account", "Provider Account")
	}

	@Test
	public void mapperShouldBeInitialised() {
		assertTrue(mapper.isInitialised())
	}
	
	@Test
	public void mapperShouldNotBeInitialised() {
		assertFalse(blankMapper.isInitialised())
	}
	
/* Start */
	@Test
	public void testGetMappingForId() {
		def m = mapper.getMappingForId("ID789")
		assertTrue(m.id == "ID789")
		assertTrue(m.type == "Account")
		assertTrue(m.developerName == "Involved_Party")
		assertTrue(m.name == "Involved Party")
	}
	
	// Get Developer Name for ID
	@Test
	public void testGetDeveloperNameForId() {
		assertTrue(mapper.getDeveloperNameForId("ID456") == "Provider_Contact")
	}
	
	// Get Name for ID
	@Test
	public void testGetNameForId()  {
		assertTrue(mapper.getNameForId("ID789") == "Involved Party")
	}
	
	// Get ID for Developer Name + Type
	@Test
	public void testGetIdForDeveloperName()  {
		assertTrue(mapper.getIdForDeveloperName("Involved_Party", "Contact") == "ID123")
		assertTrue(mapper.getIdForDeveloperName("Involved_Party", "Account") == "ID789")
	}
  
	@Test
	// Get Name for Developer Name + Type
	public void testGetNameForDeveloperName()  {
		assertTrue(mapper.getNameForDeveloperName("Involved_Party", "Contact") == "Involved Party")
		assertTrue(mapper.getNameForDeveloperName("Involved_Party", "Account") == "Involved Party")
	}
	
	@Test
	// Get Developer Name for Name + Type
	public void testGetDeveloperNameForName()  {
		assertTrue(mapper.getDeveloperNameForName("Involved Party", "Contact") == "Involved_Party")
		assertTrue(mapper.getDeveloperNameForName("Involved Party", "Account") == "Involved_Party")
	}

	@After
	void after() {
		return
	}

}
