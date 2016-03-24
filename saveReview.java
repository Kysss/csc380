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
public class saveReview {
        public boolean test;
        public boolean check;
        public boolean check1;
    public saveReview(String pathcheck,String path, String user, String pass, String restaurant, String review, String rating) throws IOException{
    // Pathcheck is to make sure the User and Pass are valid
    // path is where the review is going to get saved to
    // Check to make sure the User doesn't exist
        //test is for testing purposes
        this.test = false;
        //check is to make sure the User and Pass exists
        this.check = false;
        
        //check1 is to make sure the same person doesn't review the same restaurant
        this.check1 = true;
       
        CSVReader csvReader = new CSVReader(new FileReader(pathcheck));
        
        String[] row = null;
        
        //making sure user and pass match
        while((row = csvReader.readNext())!=null){

            
            if(row[0].equals(user)){
                if(row[1].equals(pass))
                this.check = true;
            }
            
            
        }
        
        if(this.check== false){
            System.out.println("Username or Password does not exist");
        }
        csvReader.close();
        
        
        // Check to make sure that there is only 1 review per restaurant
        csvReader = new CSVReader(new FileReader(path));
        while((row = csvReader.readNext()) !=null && check == true){
            if(row[0].equals(user)){
                if(row[1].equals(restaurant)){
                    System.out.println("You can only write one review per resturant");
                    check1 = false;
                }
            }
        }
        csvReader.close();
        
        if(this.check && this.check1){
            CSVWriter csvwriter = new CSVWriter(new FileWriter(path, true)); 
            String [] info = (user+ ","+ restaurant +"," + review + "," + rating ).split(",");
            csvwriter.writeNext(info);
            test = true;
            csvwriter.close();
        }
        
        
    }
}
