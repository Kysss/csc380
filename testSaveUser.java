/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csv;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Reeves
 */
public class testSaveUser {
    public testSaveUser() throws IOException{
        //For test to pass both test must come back false then true
        
        boolean test = false;
       
        String path = "/Users/Reeves/Desktop/test.csv";
        CSVReader csvReader = new CSVReader(new FileReader(path));
        
        //Clear the test.csv file and add one user and pass
        CSVWriter csvwriter = new CSVWriter(new FileWriter(path)); 
        String [] info = ("Reeves"+ ","+ "csc380").split(",");
            csvwriter.writeNext(info);
            csvwriter.close();
            
            
            
            
        // First test make sure Reeves cannot be added because it already exists on the csv
        // should return a print statement saying Username is taken 
        // Should return false because it already exists
            
        System.out.println(new saveUser("/Users/Reeves/Desktop/test.csv","Reeves", "Helloworld").check);
        
        
        // Second check is making sure that a User that is not on the sheet is being added
        //Should return true showing that the User is being added to the csv
        
        System.out.println(new saveUser("/Users/Reeves/Desktop/test.csv","Reeves1", "HelloWorld").check);
        
        //Third test is to try to reAdd the new_User
        //Should return false and print statment explaining what is wrong
        
        System.out.println(new saveUser("/Users/Reeves/Desktop/test.csv","Reeves1", "hsdkfsd").check);
        

    }
    
        
    
        
}
