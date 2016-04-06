package com.kimbrian.csc380;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.Scanner;

/**
 *
 * @author KimBrian
 */
public abstract class Profile {

    private String firstName;
    private String lastName;
    private String email;
    private String userName;
    private String password;
    private String securityQuestion;
    private String securityAnswer;
 public ratingPost[] ratingHistory;
 public int ratingPostCount = 0;
    public Profile(String fn, String ln, String e, String u, String p, String sq, String sa) {
        firstName = fn;
        lastName = ln;
        email = e;
        userName = u;
        password = p;
        securityQuestion= sq;
        securityAnswer= sa;
    }
    //Making profile info

    public void setFirstname(String fn) {
        this.firstName = fn;
    }

    public void setLastname(String ln) {
        this.lastName = ln;
    }

    public void setEmail (String e){
        this.email = e;
    }

    public void setUsername(String u) {
        this.userName = u;
    }

    public void setPassword(String p) {
        this.password = p;
    }

    public void setSecurityQuestion (String sq) {
        this.securityQuestion = sq;
    }

    public void setSecurityAnswer (String sa) {
        this.securityAnswer = sa;
    }
    //Viewing profile Informations

    public String getFirstname() {
        return firstName;
    }

    public String getLastname() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return userName;
    }

    public abstract String getAccountType();

    public String getPassword() {
        return password;
    }

    public String getSecurityQuestion(){
        return securityQuestion;
    }

    public String getSecurityAnswer(){
        return securityAnswer;
    }
    
    public void addRatingPost(String title, Double rating, String content){
       ratingPost rp  = new ratingPost(title,rating,content);
       this.ratingHistory[ratingPostCount] = rp;
       ratingPostCount ++;
    }
    
    public ratingPost[] inspectRatingHistory(){
        return this.ratingHistory;
    }
            

}
