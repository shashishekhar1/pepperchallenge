package au.gov.nsw.icare.api.customer.test

import static org.junit.Assert.*
import au.gov.nsw.icare.api.customer.utils.SoqlQuery
import au.gov.nsw.icare.api.customer.utils.SoqlQueryOrdering

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

class SoqlQueryTest {

	@Before
	void setUp() {
		
	}
 
	@After
	void tearDown() {

	}
 
	@Test
	void selectFromWhereSimpleTest() {
		SoqlQuery query = new SoqlQuery()
		query.addSelectCriterion("FirstName")
		query.addSelectCriterion("LastName")
		query.addFromTable("Contacts")
		query.addWhereCriterion("FirstName", "Adam")
		
		def result = query.getQueryString()
		
		assert(result == 'SELECT FirstName, LastName FROM Contacts WHERE FirstName = \'Adam\'')
	}
	
	@Test
	void selectFromWhereOrderByLimitOffsetSimpleTest() {
		SoqlQuery query = new SoqlQuery()
		
		query.addSelectCriterion("FirstName")
		query.addFromTable("Contacts")
		query.addWhereCriterion("FirstName", "Adam")
		query.addOrderByCriterion("FirstName", SoqlQueryOrdering.DESC)
		query.setLimit(10)
		query.setOffset(5)
		
		def result = query.getQueryString()
		
		assert(result == 'SELECT FirstName FROM Contacts WHERE FirstName = \'Adam\' ORDER BY FirstName DESC LIMIT 10 OFFSET 5')
	}
	
	@Test
	void selectFromWhereOrderByLimitOffsetFullTest() {
		SoqlQuery query = new SoqlQuery()
		
		query.addSelectCriterion("FirstName")
		query.addSelectCriterion("LastName")
		query.addSelectCriterion("Birthdate")
		query.addSelectCriterion("Registered")
		query.addSelectCriterion("Version")
		
		query.addFromTable("Contacts")
		
		query.addWhereCriterion("FirstName", "Adam")
		query.addWhereDateCriterion("Birthdate", "2000-01-01")
		query.addWhereCriterion("Registered", false)
		query.addWhereCriterion("Version", 7.0d)
		
		query.addOrderByCriterion("FirstName", SoqlQueryOrdering.ASC)
		query.addOrderByCriterion("Birthdate", SoqlQueryOrdering.DESC)
		
		def result = query.getQueryString()
		
		def desiredResult = 'SELECT FirstName, LastName, Birthdate, Registered, Version ' +
			'FROM Contacts ' +
			'WHERE FirstName = \'Adam\' AND Registered = false AND Version = 7.0 AND Birthdate = 2000-01-01 ORDER BY FirstName ASC, Birthdate DESC'
		
		assert(result == desiredResult)
	}
}
