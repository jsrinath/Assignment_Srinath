package com.rabo;

import org.junit.jupiter.api.Test;

import com.rabo.model.Person;
import com.rabo.model.Record;

public class RecordTest {
	  @Test
	    public void testPerson() {
	 
	        PojoTestUtils.validateAccessors(Record.class);
	    }
}
