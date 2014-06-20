/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.coinsproject6.complexitycalculator.readingCSV;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author Georg
 */
public class Reader {
    
    private static final Logger logger = Logger.getLogger(Reader.class);
    
    /**
     * 
     * @param file
     * @return
     * @throws IOException 
     */
    public List<String> txtReader(File file) throws IOException {
        
        BufferedReader input = new BufferedReader(new FileReader(file));
        String zeile;
        List<String> content = new ArrayList<String>();
        
	while ((zeile = input.readLine()) != null) {
		content.add(zeile);
	}
        
        if(content.isEmpty()){
            logger.error("No data could read from the file. (" + file.getName() + ")");
            throw new IOException();                 
        } else {
            return content;
        }
    }
    
    /**
     * 
     * @param file
     * @return
     * @throws IOException 
     */
    public String rowReader(File file) throws IOException {
        
        BufferedReader input = new BufferedReader(new FileReader(file));
        String zeile;
        String content = "";
        
        while((zeile = input.readLine()) != null){
            content = content + " " + zeile;
        }
        
        if(content.isEmpty()){
        logger.error("No data could read from the file. (" + file.getName() + ")");
            throw new IOException();                 
        } else {
            return content;
        }
        
    }
    
}
