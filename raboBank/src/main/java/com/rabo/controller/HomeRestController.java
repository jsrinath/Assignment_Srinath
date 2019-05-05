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

import com.rabo.model.Person;
import com.rabo.service.fileExtractorServiceImpl;

@RestController
public class HomeRestController {
	@Autowired
	fileExtractorServiceImpl fileExtractor;
	
	@PostMapping("/csvUpload") // //new annotation since 4.3
	@ResponseStatus(HttpStatus.OK)
	public List<Person> singleFileUpload(@RequestParam("file") MultipartFile file){
		System.out.println("file uploaded");
		File csvFile = new File(file.getOriginalFilename());
		try {
			csvFile.createNewFile();
			FileOutputStream fos = new FileOutputStream(csvFile);
			fos.write(file.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Person> personList =fileExtractor.extractCSVRecordsPerson(csvFile);
		return personList;
	}
}
