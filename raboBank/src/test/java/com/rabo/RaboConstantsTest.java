package com.rabo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.rabo.common.RaboConstants;

public class RaboConstantsTest {

	@Test
	public void testMSG(){
		assertEquals("message", RaboConstants.MSG.getValue());
	}

	
	@Test
	public void testERROR(){
		assertEquals("Exception", RaboConstants.ERROR.getValue());
	}
	
	@Test
	public void testREDIRECT(){
		assertEquals("redirect:uploadStatus", RaboConstants.REDIRECT.getValue());
	}

	@Test
	public void testUNSUPPORTED_FILEFORMAT(){
		assertEquals("Only csv or xml file allowed!!", RaboConstants.UNSUPPORTED_FILEFORMAT.getValue());
	}


	@Test
	public void testFILE_TYPE_XML(){
		assertEquals("text/xml", RaboConstants.FILE_TYPE_XML.getValue());
	}

	@Test
	public void testFILE_TYPE_CSV(){
		assertEquals("application/vnd.ms-excel", RaboConstants.FILE_TYPE_CSV.getValue());
	}



	
	
}
