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
public class TestSaveRestaurant {
    public TestSaveRestaurant() throws IOException{
        
        String path = "/Users/Reeves/Desktop/test.csv";
        Profile up = new UserProfile("Matt","Reeves","Reeves12");
        Profile ap = new AdminProfile("Matt","Reeves", "Reeves12");
        String name = "Mario's";
        String address = "20 Bantry Road";
        String contact = "538-5091";
        String rating = "2.434234";
        
        //Start off by clearing out the test.csv accept for one restaurant
        CSVWriter csvwriter = new CSVWriter(new FileWriter(path)); 
        String [] info = ("Domino's"+ "," + "6 iroquois drive"+ "," +"867-5309"+ "," + "5").split(",");
        csvwriter.writeNext(info);
        csvwriter.close();
        
        
        // Test 1 makes sure that only an admin can add a restaurant to the Restaurant.csv database
        //Should return false because a User is trying to add a restaurant 
        System.out.println(new saveRestaurant(path,up,name,address,contact,rating).check);
        
        // Test 2 makes sure that an Admin has priviledge to add to the database
        //Should return true
        //SHould print out Contact
        System.out.println(new saveRestaurant(path,ap,name,address,contact,rating).check);
        System.out.println(new hashmap(path,path).restaurant.get(name).getContact());
        
    }
    
}
