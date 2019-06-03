package com.rabo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.rabo.model.Record;
import com.rabo.service.ValidateorServiceImpl;

@SpringBootTest
public class ValidateorServiceMockTest {
@Autowired
private ValidateorServiceImpl validateorServiceImpl;

@DisplayName("Test Mock helloService + helloRepository")
@Test
void testfindDuplicateRecords() {
	List<Record> records = new ArrayList<>();
	Record rcd = new Record();
	rcd.setAccountNumber("1232");
	rcd.setReference(1232);
	records.add(rcd);
	Record rcd1 = new Record();
	rcd1.setAccountNumber("1232");
	rcd1.setReference(1232);
	records.add(rcd1);
    assertEquals(2, validateorServiceImpl.findDuplicateRecords(records).size());
}

@Test
void testfindErrorEndBalRecrd() {
	List<Record> records = new ArrayList<>();
	Record rcd = new Record();
	rcd.setAccountNumber("111");
	rcd.setReference(111);
    rcd.setStartBalance(100);
    rcd.setMutation(1);
    rcd.setEndBalance(101);
	records.add(rcd);
	Record rcd1 = new Record();
	rcd1.setAccountNumber("1232");
	rcd1.setReference(1232);
	rcd1.setStartBalance(50);
	rcd1.setMutation(1);
	rcd1.setEndBalance(51);
	records.add(rcd1);
    assertEquals(0, validateorServiceImpl.findErrorEndBalRecrd(records).size());
}

}
