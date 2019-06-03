package com.rabo;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.bind.JAXBException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.rabo.service.FileExtractorServiceImpl;

@SpringBootTest
public class FileExtractorServiceImplTest {
@Autowired
private FileExtractorServiceImpl fileExtractorService;
@Test
void testextractCSVRecords() {
	File file = new File("records.csv");
	try {
		fileExtractorService.extractCSVRecords(file );
	} catch (IOException e) {
		fail(e);
	}
}

@Test
void testextractFileNotFoundCSVRecords() {
	File file = new File("12.csv");
	try {
		fileExtractorService.extractCSVRecords(file );
	} catch (FileNotFoundException e) {
		assertTrue(true);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		fail(e);
	}
}

@Test
void testextractCSVRecordsPerson() {
	File file = new File("issues.csv");
	try {
		fileExtractorService.extractCSVRecordsPerson(file);
	} catch (IOException e) {
		fail(e);
	}
}

@Test
void testextractXMLRecords() {
	File file = new File("records.xml");
	try {
		fileExtractorService.extractXMLRecords(file);
	} catch (JAXBException e) {
		fail(e);
	}
}

@Test
void testextractFileNotFoundCSVRecordsPerson() {
	File file = new File("12.xml");
	try {
		fileExtractorService.extractCSVRecordsPerson(file );
	} catch (FileNotFoundException e) {
		assertTrue(true);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		fail(e);
	}
}
}
