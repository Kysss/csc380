/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author KimBrian
 */
public class AdminProfile extends Profile {

    private String accountType = "Admin";
    private HashMap<String, String> hm = new HashMap<>();
  

    public AdminProfile(String fn, String ln, String u) {
        super(fn, ln, u);
        hm.put("YingYing", "Xia");
        hm.put("KimBrian", "Fadul");
        hm.put("Matt", "Reeves");
    }

    public String getAccountType() {
        return accountType;
    }

    public String logIn(String u, String p) {
        String accepted = "Logged In";
        boolean b = false;
        boolean userExists = hm.containsKey(u);
        do {
            if (userExists == true) {
                String passwordCheck = hm.get(u);
                if (passwordCheck.equals(p)) {
                    b = true;
                    return accepted;
                } else {
                    b = false;
                    System.out.println("Log in failed");
                }
            }
        } while (b = false);
        return accepted;
    }

    public boolean getHashMap() {
        if (hm.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public void addRestaurant(String path, Profile p, String name, String address, String contact, String rating) throws IOException {

        new saveRestaurant(path, this, name, address, contact, rating);
        System.out.println("Restaurant " + name + " added succesfully." );
    }
    
    

}
