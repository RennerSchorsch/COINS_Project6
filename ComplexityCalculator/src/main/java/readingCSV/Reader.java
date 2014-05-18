/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package readingCSV;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Georg
 */
public class Reader {
    
    public List<String> txtReader(File file) throws IOException, NullPointerException {
        
        BufferedReader input = new BufferedReader(new FileReader(file));
        String zeile;
        List<String> content = new ArrayList<String>();
        
	while ((zeile = input.readLine()) != null) {
		content.add(zeile);
	}
        
        if(content.isEmpty()){
            throw new NullPointerException();
        } else {
            return content;
        }
    }
    
}
