package com.rabo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.rabo.model.Person;

@SpringBootTest
public class PersonTest {

	  @Test
	    public void testPerson() {
	 
	        PojoTestUtils.validateAccessors(Person.class);
	    }
}
