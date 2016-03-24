/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csv;

import com.opencsv.CSVWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Reeves
 */
public class hashmapTest {
    public hashmapTest() throws IOException{
        
        String path = "/Users/Reeves/Desktop/test.csv";
        // Clears the entire test .csv except for what is about to get added to it
        CSVWriter csvwriter = new CSVWriter(new FileWriter(path)); 
        String [] info = ("Reeves"+ "," + "helloworld"+ "," +"Matt"+ "," + "Reeves").split(",");
        csvwriter.writeNext(info);
        csvwriter.close();
        
        // right now the only thing on the .csv is "Reeves","helloworld","Matt","Reeves"
        // this is the format that would be created if you used the createUser method 
        // User,Pass,Fn,Ln
        
        //Test1 is going to show that this information sucessfully gets added to the hashmap and information can be retrieved from it.
        System.out.println("Test1");
        hashmap hm = new hashmap(path,path);
        System.out.println("Last Name: " + hm.profile.get("Reeves").getLastname());
        System.out.println("Account type: "+ hm.profile.get("Reeves").getAccountType());
        System.out.println("First Name: " + hm.profile.get("Reeves").getFirstname());
        
        // We again need to clear the test page for Test2
        
        csvwriter = new CSVWriter(new FileWriter(path)); 
        info = ("Domino's"+ "," + "6 iroquois drive"+ "," +"867-5309"+ "," + "5").split(",");
        csvwriter.writeNext(info);
        csvwriter.close();
        
        // So now the test.cvs only has 
        // "Doino's","6 iroquois drive","867-5309","5"
        // Now we again run the hashmap.java to make sure it creates a hm with a key "Domino's" with all the info
        
        System.out.println("Test2");
        hm = new hashmap(path,path);
        System.out.println("Address: " + hm.restaurant.get("Domino's").getAddress());
        System.out.println("Rating: " + hm.restaurant.get("Domino's").getAverageRating());
        System.out.println("Contact: " + hm.restaurant.get("Domino's").getContact());
        
        
    }
}
