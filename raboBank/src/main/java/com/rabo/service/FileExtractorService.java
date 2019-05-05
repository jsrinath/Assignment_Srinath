package com.rabo.service;

import java.io.File;
import java.util.List;

import com.rabo.model.*;

public interface FileExtractorService {
	public List<Record> extractCSVRecords(File file);
	public List<Record> extractXMLRecords(File file);
	public List<Person> extractCSVRecordsPerson(File file);
}
