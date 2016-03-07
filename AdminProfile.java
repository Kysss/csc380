/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Profiles;

/**
 *
 * @author KimBrian
 */
public class AdminProfile extends Profile{
    private String accountType = "Admin";
    public AdminProfile(String fn, String ln, String u) {
        super(fn, ln, u);
    }
    public String getAccountType() {
        return accountType;
    }
}
