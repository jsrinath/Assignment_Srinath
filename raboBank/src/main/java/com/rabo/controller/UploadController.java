package com.rabo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rabo.common.raboConstants;
import com.rabo.model.Record;
import com.rabo.service.FileExtractorService;
import com.rabo.service.ValidateorService;
import com.rabo.service.ValidateorServiceImpl;
import com.rabo.service.fileExtractorServiceImpl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
public class UploadController {

	//Save the uploaded file to this folder
	private static String UPLOADED_FOLDER = "H://temp//";
	@Autowired
	fileExtractorServiceImpl fileExtractor;
	@Autowired 
	ValidateorServiceImpl validator;
	@GetMapping("/")
	public String index() {
		return "upload";
	}
	@GetMapping("/findCount")
	public String indexTest() {
		return "index";
	}

	@PostMapping("/upload") // //new annotation since 4.3
	public String singleFileUpload(@RequestParam("file") MultipartFile file,
			RedirectAttributes redirectAttributes) {
		File csvFile;
		List<Record> rcd = null;
		List<Record> dupRcds = null;
		List<Record> endblRcds = null;
		if (file.isEmpty()) {
			redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
			return "redirect:uploadStatus";
		}
		if(!file.getContentType().equalsIgnoreCase(raboConstants.FILE_TYPE_CSV)
				&& !file.getContentType().equalsIgnoreCase(raboConstants.FILE_TYPE_XML)){
			redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
			return "redirect:uploadStatus";
		}
		try {
			csvFile = new File(file.getOriginalFilename());
			csvFile.createNewFile();
			FileOutputStream fos = new FileOutputStream(csvFile);
			fos.write(file.getBytes());
			if(file.getContentType().equalsIgnoreCase(raboConstants.FILE_TYPE_CSV)){
				rcd = fileExtractor.extractCSVRecords(csvFile);
			}else {
				rcd = fileExtractor.extractXMLRecords(csvFile);
			}
			// Get the file and save it somewhere
			dupRcds =validator.findDuplicateRecords(rcd);
			endblRcds =validator.findErrorEndBalRecrd(rcd);
			System.out.println(dupRcds.size());
			redirectAttributes.addFlashAttribute("duplicateRecords", dupRcds);
			redirectAttributes.addFlashAttribute("endBalancerecords", endblRcds);
			redirectAttributes.addFlashAttribute("success",
					"You successfully uploaded '" + file.getOriginalFilename() + "'");

		} catch (IOException e) {
			redirectAttributes.addFlashAttribute("message", e.getCause().getMessage());
			return "redirect:uploadStatus";
		}

		return "redirect:/uploadStatus";
	}

	@GetMapping("/uploadStatus")
	public String uploadStatus() {
		return "uploadStatus";
	}

}