package com.rabo.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.rabo.common.raboConstants;
import com.rabo.model.Person;
import com.rabo.service.fileExtractorServiceImpl;

@RestController
public class HomeRestController {
	@Autowired
	fileExtractorServiceImpl fileExtractor;
	
	@PostMapping("/csvUpload") // //new annotation since 4.3
	@ResponseStatus(HttpStatus.OK)
	public List<Person> singleFileUpload(@RequestParam("file") MultipartFile file){
		List<Person> personList;
		if (file.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Please select a file");
		}
		if (!file.getContentType().equalsIgnoreCase(raboConstants.FILE_TYPE_CSV)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Only CSV File is allowed");
		}
		File csvFile = new File(file.getOriginalFilename());
		try {
			csvFile.createNewFile();
			FileOutputStream fos = new FileOutputStream(csvFile);
			fos.write(file.getBytes());
			 personList =fileExtractor.extractCSVRecordsPerson(csvFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getCause().getMessage(), e);
		}
		
		return personList;
	}
}
