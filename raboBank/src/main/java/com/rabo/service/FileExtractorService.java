package com.rabo.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.xml.bind.JAXBException;

import com.rabo.model.Person;
import com.rabo.model.Record;

public interface FileExtractorService {
	public List<Record> extractCSVRecords(File file)  throws IOException; 
	public List<Record> extractXMLRecords(File file)  throws  JAXBException;
	public List<Person> extractCSVRecordsPerson(File file)  throws IOException;
}
