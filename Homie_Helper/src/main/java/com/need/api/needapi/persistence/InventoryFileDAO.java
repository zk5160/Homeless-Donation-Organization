package com.need.api.needapi.persistence;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Implements the functionality for JSON file-based persistence for Inventory
 * 
 * @author Team 7G
 */
public class InventoryFileDAO extends NeedFileDAO { 

    /**
     * Creates a Inventory File Data Access Object
     * 
     * @param filename Filename to read from and write to
     * @param objectMapper Provides JSON Object to/from Java Object serialization and deserialization
     * 
     * @throws IOException when file cannot be accessed or read from
     */
    public InventoryFileDAO(@Value("${inventory.file}") String filename, ObjectMapper objectMapper) throws IOException {
        super(filename, objectMapper);
        //TODO Auto-generated constructor stub
    }
    
}