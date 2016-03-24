/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.Scanner;

/**
 *
 * @author KimBrian
 */
public class UserProfile extends Profile {

    private String accountType = "User";

    public UserProfile(String fn, String ln, String u) {
        super(fn, ln, u);
    }

    public String getAccountType() {
        return accountType;
    }

}
