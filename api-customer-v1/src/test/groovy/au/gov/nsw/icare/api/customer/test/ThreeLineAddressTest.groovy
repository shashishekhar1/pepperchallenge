package au.gov.nsw.icare.api.customer.test

import org.junit.Before
import org.junit.Test
import static org.junit.Assert.*

import au.gov.nsw.icare.api.customer.utils.ThreeLineAddress
import au.gov.nsw.icare.api.customer.exceptions.InvalidAttributeValueException

class ThreeLineAddressTest {
	
	@Before
	public void setUp() throws Exception {
		
	}
	
	@Test
	public void oneLineToThree_EmptyString() {
		boolean exceptionOccurred = false
		def result = new ThreeLineAddress("")
				
		assertTrue(result.line1 == null)
		assertTrue(result.line2 == null)
		assertTrue(result.line3 == null)
	}
	
	@Test
	public void oneLineToThree_OneLineString() {
		def result = new ThreeLineAddress("123 George Street")
		assertTrue(result.line1 == "123 George Street")
		assertTrue(result.line2 == null)
		assertTrue(result.line3 == null)
	}
	
	@Test
	public void oneLineToThree_TwoLineString() {
		def result = new ThreeLineAddress("The Big Tower\n123 George Street")
		assertTrue(result.line1 == "The Big Tower")
		assertTrue(result.line2 == "123 George Street")
		assertTrue(result.line3 == null)
	}
	
	@Test
	public void oneLineToThree_ThreeLineString() {
		def result = new ThreeLineAddress("The Penthouse\nThe Big Tower\n123 George Street")
		assertTrue(result.line1 == "The Penthouse")
		assertTrue(result.line2 == "The Big Tower")
		assertTrue(result.line3 == "123 George Street")
	}
	
	@Test
	public void oneLineToThree_LineOneTooLongString() {
		boolean exceptionOccurred = false
		try {
			new ThreeLineAddress("This Penthouse Is So Ridiculously Oppulent That Its Name Does Not Fit In a Stnadard Address Format\nThe Big Tower\n123 George Street")
		} catch (InvalidAttributeValueException e) {
			assertTrue(e.getErrors()[0].code == "ADDRESS_LINE_TOO_LONG")
			exceptionOccurred = true
		}
		
		assertTrue(exceptionOccurred)
	}
	
	@Test
	public void oneLineToThree_LinesOneAndTwoTooLongString() {
		boolean exceptionOccurred = false
		try {
			new ThreeLineAddress("This Penthouse Is So Ridiculously Oppulent That Its Name Does Not Fit In a Stnadard Address Format\nThe Big Tower Near The Park That Has The Statue Of the Magnificent Inventor That Created All of The Things\n123 George Street")
		} catch (InvalidAttributeValueException e) {
			assertTrue(e.getErrors()[0].code == "ADDRESS_LINE_TOO_LONG")
			exceptionOccurred = true
		}
		
		assertTrue(exceptionOccurred)
	}
	
	@Test
	public void oneLineToThree_TooManyLines() {
		boolean exceptionOccurred = false
		try {
			new ThreeLineAddress("One\nTwo\nThree\nFour")
		} catch (InvalidAttributeValueException e) {
			assertTrue(e.getErrors()[0].code == "ADDRESS_CONTAINS_TOO_MANY_LINES")
			exceptionOccurred = true
		}
		
		assertTrue(exceptionOccurred)
	}
	
	@Test
	public void oneLineToThree_WindowsFormatNewLines() {
		boolean exceptionOccurred = false
		try {
			new ThreeLineAddress("The Residence\r\n123 George Street")
		} catch (InvalidAttributeValueException e) {
			assertTrue(e.getErrors()[0].code == "ADDRESS_NEWLINE_NOT_UNIX_FORMAT")
			exceptionOccurred = true
		}
		
		assertTrue(exceptionOccurred)
	}
}