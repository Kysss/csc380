/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yingying.searchapp;


import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Reeves
 */
public class saveUser {
    public boolean check;
    
    public saveUser(String path, String user, String pass) throws IOException{
        // Check to make sure the User doesn't exist
        this.check = true;
        
       
        CSVReader csvReader = new CSVReader(new FileReader(path));
        String[] row = null;
        while((row = csvReader.readNext())!=null){

            // if there is a username that already exists
            if(row[0].equals(user)){
                System.out.println("Username Already saved to file");
                this.check = false;
            }
        }
        csvReader.close();
        if(this.check){
            CSVWriter csvwriter = new CSVWriter(new FileWriter(path, true)); 
            String [] info = (user+ ","+ pass).split(",");
            csvwriter.writeNext(info);
            csvwriter.close();
        }
        
        
    }
}
