package com.rabo.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rabo.common.RaboConstants;
import com.rabo.model.Record;
import com.rabo.service.ValidateorServiceImpl;
import com.rabo.service.FileExtractorServiceImpl;

@Controller
public class UploadController {

	private static final Logger logger = LoggerFactory.getLogger(UploadController.class);

	//Save the uploaded file to this folder
	@Autowired
	FileExtractorServiceImpl fileExtractor;
	@Autowired 
	ValidateorServiceImpl validator;
	@GetMapping("/home")
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
		if (file == null || file.isEmpty()) {
			redirectAttributes.addFlashAttribute(RaboConstants.MSG.getValue(), "Please select a file to upload");
			return RaboConstants.REDIRECT.getValue();
		}
		if( file.getContentType()!=null  && !file.getContentType().equalsIgnoreCase(RaboConstants.FILE_TYPE_CSV.getValue())
				&& !file.getContentType().equalsIgnoreCase(RaboConstants.FILE_Text_CSV.getValue())             
				&& !file.getContentType().equalsIgnoreCase(RaboConstants.FILE_TYPE_XML.getValue())
			   && !file.getContentType().equalsIgnoreCase(RaboConstants.FILE_APP_XML.getValue())	){
			redirectAttributes.addFlashAttribute(RaboConstants.MSG.getValue(), RaboConstants.UNSUPPORTED_FILEFORMAT.getValue());
			return RaboConstants.REDIRECT.getValue();
		}
		if(fileExtractor!=null && file.getOriginalFilename()!=null && !file.getOriginalFilename().isEmpty()) {
			return fileService(file, redirectAttributes);
		}
		return RaboConstants.REDIRECT.getValue();
	}
	public String fileService(MultipartFile file,RedirectAttributes redirectAttributes) {
		List<Record> rcd = null;
		List<Record> dupRcds = null;
		List<Record> endblRcds = null;
		File csvFile = new File(file.getOriginalFilename());
		try {
			boolean isFIle = csvFile.createNewFile();
			logger.info("FIle created {0}",isFIle);
			
		} catch (IOException e1) {
			logger.error("Error",e1);
		}
		try(FileOutputStream fos = new FileOutputStream(csvFile)) {
			fos.write(file.getBytes());
			if(file.getContentType()!=null && !file.getContentType().isEmpty() && (file.getContentType().equalsIgnoreCase(RaboConstants.FILE_TYPE_CSV.getValue())
					||file.getContentType().equalsIgnoreCase(RaboConstants.FILE_Text_CSV.getValue() ))){
				rcd = fileExtractor.extractCSVRecords(csvFile);
			}else {
				rcd = fileExtractor.extractXMLRecords(csvFile);
			}
			// Get the file and save it somewhere
			dupRcds =validator.findDuplicateRecords(rcd);
			endblRcds =validator.findErrorEndBalRecrd(rcd);
			redirectAttributes.addFlashAttribute("duplicateRecords", dupRcds);
			redirectAttributes.addFlashAttribute("endBalancerecords", endblRcds);
			redirectAttributes.addFlashAttribute("success",
					"You have successfully uploaded '" + file.getOriginalFilename() + "'");

		} catch (IOException e) {
			redirectAttributes.addFlashAttribute(RaboConstants.MSG.getValue(), e.getCause().getMessage());
		} catch (JAXBException e) {
			redirectAttributes.addFlashAttribute(RaboConstants.MSG.getValue(), e.getCause().getMessage());
		}catch(java.lang.NumberFormatException e) {
			redirectAttributes.addFlashAttribute(RaboConstants.MSG.getValue(), "Please enter valid number" + e.getCause().getMessage());
		}
		catch (Exception e) {
			redirectAttributes.addFlashAttribute(RaboConstants.MSG.getValue(), e.getCause().getMessage());
		}
		return RaboConstants.REDIRECT.getValue();
	}
	@GetMapping("/uploadStatus")
	public String uploadStatus() {
		return "uploadStatus";
	}

}