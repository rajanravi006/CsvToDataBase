package com.CsvManager.csv.entity;

import java.util.Map;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "your_entity_table")
public class YourEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ElementCollection
    @CollectionTable(name = "your_entity_table_columns", joinColumns = @JoinColumn(name = "your_entity_id"))
    @MapKeyColumn(name = "column_name")
    @Column(name = "column_value")
    private Map<String, String> columns;

    // getters and setters for the class properties
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Map<String, String> getColumns() {
        return columns;
    }

    public void setColumns(Map<String, String> columns) {
        this.columns = columns;
    }
}