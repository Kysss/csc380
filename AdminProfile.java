/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author KimBrian
 */
public class AdminProfile extends Profile {

   private HashMap restaurants = new HashMap(); //should = the hashmap after parsing the restaurant database
   private HashMap usersAccount = new HashMap(); //should = the hashmap after parsing the user database.
    private String accountType = "Admin";

    public AdminProfile(String fn, String ln, String u) {
        super(fn, ln, u);
    }

    public String getAccountType() {
        return accountType;
    }
    public void deleteUser(){
        
    }
    
    public void deleteRes(){
        
    }
    public void setUserPassword(){
        
    }
    public void deleteUserRating(){
        
    }
    
    public void setResName(){
        
    }
    public void setResContact(){
        
    }
    
    public void setResAddress(){
        
    }
 
    
}
