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
public class UserProfile extends Profile {

    private String accountType = "User";
    private HashMap<String, String> hm = new HashMap<>();

    public UserProfile(String fn, String ln, String u) {
        super(fn, ln, u);
    }

    public String getAccountType() {
        return accountType;
    }

    public String signUp(String fn, String ln, String u, String p) {
        String accepted = "Sign up finished";
        boolean b = false;
        boolean userExists = hm.containsKey(u);
        do {
            if (userExists == true) {
                System.out.println("User already exists");
                b = false;
            } else {
                super.setFirstname(fn);
                super.setLastname(ln);
                super.setPassword(p);
                super.setUsername(u);
                hm.put(super.getUsername(), super.getPassword());
                b = true;
            }
        } while (b = false);
        return accepted;
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
    public boolean getHashMap (){
        if (hm.isEmpty()) {
            return false;
        }
        else return true;
    }
}
