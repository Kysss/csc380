/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csv;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Reeves
 */
public class saveRestaurant {
    boolean check = false;

    public saveRestaurant(String path,Profile p, String name, String address, String contact, String rating) throws FileNotFoundException, IOException{
       
        if(p.getAccountType().equals("Admin")){
            CSVReader csvReader = new CSVReader(new FileReader(path));
            CSVWriter csvwriter = new CSVWriter(new FileWriter(path, true)); 
            String [] info = (name+ ","+ address + ","+ contact + ","+ rating).split(",");
            csvwriter.writeNext(info);
            csvwriter.close();
            check = true;
        }
        
        
    }
}
