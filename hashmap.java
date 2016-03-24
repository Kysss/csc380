/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csv;

import com.opencsv.CSVReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/**
 *
 * @author Reeves
 */
public class hashmap {
        public HashMap<String,Restaurant> restaurant = new HashMap<String,Restaurant>();
        public HashMap<String,Profile> profile = new HashMap<String,Profile>();
    public hashmap(String profPath, String restPath) throws FileNotFoundException, IOException{
        
        
        
        CSVReader csvReader = new CSVReader(new FileReader(profPath));
        String[] row = null;
        while((row = csvReader.readNext())!=null){
                Profile p = new UserProfile(row[0],row[2],row[3]);
                p.setFirstname(row[2]);
                p.setLastname(row[3]);
                p.setUsername(row[0]);
                p.setPassword(row[1]);
                profile.put(row[0], p);
                
            }
        csvReader.close();
        
        
        csvReader = new CSVReader(new FileReader(restPath));
        
        while((row=csvReader.readNext())!= null){
            Restaurant r = new Restaurant(row[0]);
            r.setAddress(row[1]);
            r.setContact(row[2]);
            r.rAverageRating = Double.parseDouble(row[3]);
            restaurant.put(row[0], r);
        }
        
       csvReader.close();
    }
       
}

