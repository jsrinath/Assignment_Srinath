package com.rabo.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import javax.xml.bind.JAXBException;

import com.rabo.model.*;

public interface FileExtractorService {
	public List<Record> extractCSVRecords(File file)  throws FileNotFoundException; 
	public List<Record> extractXMLRecords(File file)  throws  JAXBException;
	public List<Person> extractCSVRecordsPerson(File file)  throws FileNotFoundException;
}
