package com.CsvManager.csv.controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.CsvManager.csv.entity.YourEntity;
import com.CsvManager.csv.service.HandleFileUploadService;

@RestController
public class HandleFileUpload {
	@Autowired
	private HandleFileUploadService handleFileUploadService;
	
	@PostMapping("/upload")
	public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) throws Exception {
		List<YourEntity> list = new ArrayList<>();
	    list = this.handleFileUploadService.FileUpload(file);
		if(list.isEmpty())
			return ResponseEntity.ok("error");
		return ResponseEntity.ok("File Uploaded Sucessfully"); //changed return type
	}
}
