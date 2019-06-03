package com.rabo.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.rabo.common.RaboConstants;
import com.rabo.model.Person;
import com.rabo.service.FileExtractorServiceImpl;

@RestController
public class HomeRestController {
    private static final Logger logger = LoggerFactory.getLogger(HomeRestController.class);

	@Autowired
	FileExtractorServiceImpl fileExtractor;
	
	@PostMapping("/csvUpload") // //new annotation since 4.3
	@ResponseStatus(HttpStatus.OK)
	public List<Person> singleFileUpload(@RequestParam("file") MultipartFile file){
		List<Person> personList = null;
		if (file==null || file.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Please select a file");
		}
		if (file.getContentType()!=null &&  !file.getContentType().equalsIgnoreCase(RaboConstants.FILE_TYPE_CSV.getValue())
				&&  !file.getContentType().equalsIgnoreCase(RaboConstants.FILE_Text_CSV.getValue())) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Only CSV File is allowed");
		}
		File csvFile = new File(file.getOriginalFilename());
		try(FileOutputStream fos = new FileOutputStream(csvFile);) {
			boolean isFilecreated = csvFile.createNewFile();
			logger.info("File created:{0}",isFilecreated);
			fos.write(file.getBytes());
			 personList =fileExtractor.extractCSVRecordsPerson(csvFile);
		} catch (IOException e) {
			logger.error(RaboConstants.ERROR.getValue(), e);
		}
		return personList;
	}
}
