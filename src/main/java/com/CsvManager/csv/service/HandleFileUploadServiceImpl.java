package com.CsvManager.csv.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.CsvManager.csv.dao.YourEntityDao;
import com.CsvManager.csv.entity.YourEntity;

@Service
public class HandleFileUploadServiceImpl implements HandleFileUploadService {
	
	@Autowired
	private YourEntityDao yourEntityDao;
	private final int BATCH_SIZE = 100;
	List<YourEntity> yourEntities = new ArrayList<>();
	
	private YourEntity parseLine(String line, YourEntity entityClass) throws Exception {
	    String[] columns = line.split(",");
	    Map<String, String> columnsValue = new HashMap<>();
	    // assuming first column is column1, second column is column2 and so on
	    for (int i = 0; i < columns.length; i++) {
	        //assuming column names are in format column_i
	        columnsValue.put("column_" + i, columns[i]);
	    }
	    YourEntity entity = entityClass.getClass().getConstructor().newInstance();
	    entity.setColumns(columnsValue);
//	    for (Field field : entityClass.getClass().getDeclaredFields()) {
//	        if (columnsValue.containsKey(field.getName())) {
//	            boolean accessible = field.isAccessible();
//	            field.setAccessible(true);
//	            field.set(entity, columnsValue.get(field.getName()));
//	            field.setAccessible(accessible);
//	        }
//	    }
	    return entity;
	}
	
	@Override
	public List<YourEntity> FileUpload(MultipartFile file) throws Exception {
		    try {
//		    	Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
//		        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader);
//		        for (CSVRecord record : records) {
//		            YourEntity yourEntity = new YourEntity();
//		            Map<String, String> columns = new HashMap<>();
//		            for (String column : record.toMap().keySet()) {
//		                columns.put(column, record.get(column));
//		            }
//		            yourEntity.setColumns(columns);
//		            yourEntities.add(yourEntity);
//		        }
//		        for(YourEntity yourEntity : yourEntities){
//		            this.yourEntityDao.save(yourEntity);
//		        }
//		    	
		    	 InputStream is = file.getInputStream();
		    	    BufferedReader br = new BufferedReader(new InputStreamReader(is));
		    	    String line;
		    	    YourEntity entityclass = new YourEntity();
		    	    int count = 0;
		    	    while ((line = br.readLine()) != null) {
		    	        // code to parse the line and create a new YourEntity object
		    	        YourEntity yourEntity = parseLine(line, entityclass);
		    	        yourEntities.add(yourEntity);
		    	        count++;
		    	        if (count % BATCH_SIZE == 0) {
		    	            // save the batch of entities to the database
		    	            this.yourEntityDao.saveAll(yourEntities);
		    	            yourEntities.clear();
		    	        }
		    	    }
		    	    // save remaining entities
		    	    if (!yourEntities.isEmpty()) {
		    	        this.yourEntityDao.saveAll(yourEntities);
		    	    }
		    	
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		    
		    return yourEntities;
	}

}
