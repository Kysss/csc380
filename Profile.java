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
public abstract class Profile {
private String firstName;
private String lastName;
private String userName;
private String password;
    
public Profile (String fn, String ln, String u) {
    firstName = fn;
    lastName = ln;
    userName = u;
}
    //Making profile info
public void setFirstname (String fn){
    this.firstName = fn;
}
public void setLastname (String ln) {
    this.lastName = ln;
}
public void setUsername (String u){
    this.userName = u;
}
    //Viewing profile Informations
public String getFirstname () {
    return firstName;
}
public String getLastname () {
    return lastName;
}
public String getUsername () {
    return userName;
}
public abstract String getAccountType();
}
