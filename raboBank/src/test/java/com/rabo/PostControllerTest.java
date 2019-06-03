package com.rabo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.rabo.controller.UploadController;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class PostControllerTest {
    private MockMvc mockMvc;

	 @Autowired
	 private TestRestTemplate restTemplate;
	    
		 @Test
		 public void testissuesUpload() {
			 LinkedMultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("file", new ClassPathResource("issues.csv"));
			 HttpHeaders headers = new HttpHeaders();
			 headers.setContentType(MediaType.MULTIPART_FORM_DATA);

			 HttpEntity<LinkedMultiValueMap<String, Object>> requestEntity = new    HttpEntity<LinkedMultiValueMap<String, Object>>(
			                     map, headers);
			 ResponseEntity<String> result = restTemplate.exchange("/csvUpload", HttpMethod.POST, requestEntity,
			                     String.class);
			 assertEquals(HttpStatus.OK, result.getStatusCode());
		 }
		 
		 @Test
		 public void testxmlUpload() {
			 LinkedMultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("file", new ClassPathResource("records.xml"));
			 HttpHeaders headers = new HttpHeaders();
			 headers.setContentType(MediaType.MULTIPART_FORM_DATA);

			 HttpEntity<LinkedMultiValueMap<String, Object>> requestEntity = new    HttpEntity<LinkedMultiValueMap<String, Object>>(
			                     map, headers);
			 ResponseEntity<String> result = restTemplate.exchange("/upload", HttpMethod.POST, requestEntity,
			                     String.class);
			 assertEquals(HttpStatus.FOUND, result.getStatusCode());
		 }
	    
		 @Test
		 public void testUplwrongFmtload() {
			 LinkedMultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("file", new ClassPathResource("records.txt"));
			 HttpHeaders headers = new HttpHeaders();
			 headers.setContentType(MediaType.MULTIPART_FORM_DATA);

			 HttpEntity<LinkedMultiValueMap<String, Object>> requestEntity = new    HttpEntity<LinkedMultiValueMap<String, Object>>(
			                     map, headers);
			 ResponseEntity<String> result = restTemplate.exchange("/upload", HttpMethod.POST, requestEntity,
			                     String.class);
			 assertEquals(HttpStatus.FOUND, result.getStatusCode());
		 }
	    
		 
		 @Test
		 public void testEmpytFileload() {
			 LinkedMultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("file", new ClassPathResource("rcdEmpty.txt"));
			 HttpHeaders headers = new HttpHeaders();
			 headers.setContentType(MediaType.MULTIPART_FORM_DATA);

			 HttpEntity<LinkedMultiValueMap<String, Object>> requestEntity = new    HttpEntity<LinkedMultiValueMap<String, Object>>(
			                     map, headers);
			 ResponseEntity<String> result = restTemplate.exchange("/upload", HttpMethod.POST, requestEntity,
			                     String.class);
			 assertEquals(HttpStatus.FOUND, result.getStatusCode());
		 }
		 
		   
				 @Test
				 public void testUplInvldFmtload() {
					 LinkedMultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
					map.add("file", new ClassPathResource("invldrecords.csv"));
					 HttpHeaders headers = new HttpHeaders();
					 headers.setContentType(MediaType.MULTIPART_FORM_DATA);

					 HttpEntity<LinkedMultiValueMap<String, Object>> requestEntity = new    HttpEntity<LinkedMultiValueMap<String, Object>>(
					                     map, headers);
					 ResponseEntity<String> result = restTemplate.exchange("/upload", HttpMethod.POST, requestEntity,
					                     String.class);
					 assertEquals(HttpStatus.FOUND, result.getStatusCode());
				 }
			    
			    
				 @Test
				 public void testUplInvldcsvFmtload() {
					 LinkedMultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
					map.add("file", new ClassPathResource("invldrecords.csv"));
					 HttpHeaders headers = new HttpHeaders();
					 headers.setContentType(MediaType.MULTIPART_FORM_DATA);

					 HttpEntity<LinkedMultiValueMap<String, Object>> requestEntity = new    HttpEntity<LinkedMultiValueMap<String, Object>>(
					                     map, headers);
					 ResponseEntity<String> result = restTemplate.exchange("/csvUpload", HttpMethod.POST, requestEntity,
					                     String.class);
					 assertEquals(HttpStatus.OK, result.getStatusCode());
				 }
			    
			    
				 @Test
				 public void testcsvwrngfmtUpload() {
					 LinkedMultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
					map.add("file", new ClassPathResource("records.csv"));
					 HttpHeaders headers = new HttpHeaders();
					 headers.setContentType(MediaType.MULTIPART_FORM_DATA);

					 HttpEntity<LinkedMultiValueMap<String, Object>> requestEntity = new    HttpEntity<LinkedMultiValueMap<String, Object>>(
					                     map, headers);
					 ResponseEntity<String> result = restTemplate.exchange("/csvUpload", HttpMethod.POST, requestEntity,
					                     String.class);
					 assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());
				 }
	    
	    
	    
	 @Test
	 public void testcsvUpload() {
		 LinkedMultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
		map.add("file", new ClassPathResource("records.csv"));
		 HttpHeaders headers = new HttpHeaders();
		 headers.setContentType(MediaType.MULTIPART_FORM_DATA);

		 HttpEntity<LinkedMultiValueMap<String, Object>> requestEntity = new    HttpEntity<LinkedMultiValueMap<String, Object>>(
		                     map, headers);
		 ResponseEntity<String> result = restTemplate.exchange("/upload", HttpMethod.POST, requestEntity,
		                     String.class);
		 assertEquals(HttpStatus.FOUND, result.getStatusCode());
	 }
	 
	 
	 @Test
	 public void testXMlUpload() {
		 LinkedMultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
		map.add("file", new ClassPathResource("records.xml"));
		 HttpHeaders headers = new HttpHeaders();
		 headers.setContentType(MediaType.MULTIPART_FORM_DATA);

		 HttpEntity<LinkedMultiValueMap<String, Object>> requestEntity = new    HttpEntity<LinkedMultiValueMap<String, Object>>(
		                     map, headers);
		 ResponseEntity<String> result = restTemplate.exchange("/upload", HttpMethod.POST, requestEntity,
		                     String.class);
		 assertEquals(HttpStatus.FOUND, result.getStatusCode());
	 }
}
