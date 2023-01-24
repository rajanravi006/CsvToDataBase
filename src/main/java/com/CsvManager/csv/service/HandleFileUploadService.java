package com.CsvManager.csv.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.CsvManager.csv.entity.YourEntity;

@Service
public interface HandleFileUploadService {
	public List<YourEntity> FileUpload(MultipartFile file) throws Exception;
}
