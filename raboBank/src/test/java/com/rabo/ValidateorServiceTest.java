package com.rabo;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.rabo.model.Record;
import com.rabo.service.ValidateorService;
import com.rabo.service.ValidateorServiceImpl;

@SpringBootTest
public class ValidateorServiceTest {
 @Autowired	
  ValidateorService validService;
  @Test
  public void testGet() {
		List<Record> records = new ArrayList<>();
		Record rcd = new Record();
		rcd.setAccountNumber("1232");
		rcd.setReference(1232);
		records.add(rcd);
		Record rcd1 = new Record();
		rcd1.setAccountNumber("1232");
		rcd1.setReference(1232);
		records.add(rcd1);
		System.out.println("Testsss"+validService!=null);
		assertEquals(2	,validService.findDuplicateRecords(records).size());
    }
}
