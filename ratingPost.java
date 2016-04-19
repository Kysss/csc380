package com.yingying.searchapp;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Yingying Xia
 */
public class ratingPost {

    String resName;
    String postdate;
    String postUserName;
    String rating;
    String content;
    String postEmail;


    public ratingPost(String resName, String postDate, String username,String useremail, String Rating, String content) {
        this.resName = resName;
        this.postdate = postDate;
        this.postUserName=username;
        this.postEmail = useremail;
        this.rating = Rating;
        this.content = content;


       //System.out.println(this.postdate);
        //save this specific rating somewhere saveReview
    }


    public String getResName(){
        return this.resName;
    }
    public String getPostDate(){
        return this.postdate;
    }
    public String getPostUserEmail(){
        return this.postEmail;
    }
    public String getPostUserName(){
        return this.postUserName;
    }
    public String getRating(){
        return this.rating;
    }
    public String getReview(){
        return this.content;
    }

}
