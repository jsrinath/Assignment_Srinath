package com.rabo;

import org.junit.jupiter.api.Test;

import com.rabo.model.Person;
import com.rabo.model.Records;

public class RecordsTest {
	  @Test
	    public void testPerson() {
	 
	        PojoTestUtils.validateAccessors(Records.class);
	    }
}
