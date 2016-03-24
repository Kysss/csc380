/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csv;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
//import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Reeves
 */
public class testSaveReview {
    
    public testSaveReview() throws IOException{
        String pathcheck = "/Users/Reeves/Desktop/Userpass.csv";
        String path = "/Users/Reeves/Desktop/test.csv";
        boolean test = false;
       
       
        CSVReader csvReader = new CSVReader(new FileReader(path));
        
        //Clear the test.csv file and add one Review for testing purposes
        CSVWriter csvwriter = new CSVWriter(new FileWriter(path)); 
        String [] info = ("Reeves,Mario's,good,5").split(",");
        csvwriter.writeNext(info);
        csvwriter.close();
        
        
        //Test one is trying to add another review to the same restaurant
        // We will Use the Username and password Reeves,helloworld because it's already saved to Userpass.csv
        // We should get a printline saying you can only write one review per person
        //test should be false
        System.out.println("Test1");
       
        System.out.println(new saveReview(pathcheck,path,"Reeves","helloworld","Mario's","good","5").test);
        
        //Test two is making sure that it checks the username and password properly 
        //test should be false
        System.out.println("Test2");
        
        System.out.println(new saveReview(pathcheck,path,"Reeves","helloworlskdd","Mario's","good","5").test);
        
        // Test three is making sure a user can add a review to a different restaurant
        //Test should be true
        System.out.println("Test 3");
        
        System.out.println(new saveReview(pathcheck,path,"Reeves","helloworld","Luigi's","Okay","3").test);
        
        //Test 4 is making sure that if Reeves tries to make another review for luigis it gets stopped because it failed the test
        //Test should be false
        
        System.out.println(new saveReview(pathcheck,path,"Reeves","helloworld","Luigi's","Okay","3").test);
        
    }
}
