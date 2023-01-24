package com.CsvManager.csv.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.CsvManager.csv.entity.YourEntity;

@Repository
public interface YourEntityDao extends JpaRepository<YourEntity, Long> {
	
}
