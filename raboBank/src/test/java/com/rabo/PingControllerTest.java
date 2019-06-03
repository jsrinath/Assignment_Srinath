package com.rabo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URL;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class PingControllerTest {

    // bind the above RANDOM_PORT
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testCSVUploadHome() throws Exception {

        ResponseEntity<String> response = restTemplate.getForEntity(
			new URL("http://localhost:" + port + "/home").toString(), String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());

    }
    
    @Test
    public void testfindCount() throws Exception {

        ResponseEntity<String> response = restTemplate.getForEntity(
			new URL("http://localhost:" + port + "/findCount").toString(), String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());

    }
    
    @Test
    public void testuploadStatus() throws Exception {

        ResponseEntity<String> response = restTemplate.getForEntity(
			new URL("http://localhost:" + port + "/uploadStatus").toString(), String.class);
        assertEquals(HttpStatus.OK , response.getStatusCode());

    }
    
    

}