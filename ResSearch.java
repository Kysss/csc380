package com.yingying.searchapp;

import java.util.HashMap;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Yingying Xia
 */
public class ResSearch {

    HashMap<String,Restaurant> resDatabase = new HashMap(); //should be the complete parsed restaurant database here.
    HashMap<String,Restaurant> searchResult;
    //user search preference - by res name or type
    // passing in the **parsed**hashmap of the entire restaurant database
    
    public ResSearch(HashMap<String, Restaurant> hm) {
        resDatabase = hm;
      /*  if(preference.equalsIgnoreCase("name")){
            System.out.println("Please enter the restaurant name you wish to search.");
            Scanner scanner = new Scanner(System.in);
            String s = scanner.nextLine();
            searchResult = this.searchByName(s);
        }else if(preference.equalsIgnoreCase("type")){
            System.out.println("Please enter the restaurant type you wish to search.");
            Scanner scanner = new Scanner(System.in);
            String s = scanner.nextLine();
            searchResult = this.searchByType(s);
        }else{
            System.out.println("Sorry. Currently we only support searching by restaurant names and types.");
        }
     */
    }
    
    
    
    public HashMap<String, Restaurant> searchByName(String resName){
      searchResult= new HashMap();
        for (Restaurant r : this.resDatabase.values()) {
           
            if(r.rName.equalsIgnoreCase(resName)|| r.rName.contains(resName)){
                searchResult.put(r.rName, r);
            }
        }
      
        return searchResult;
         //then pass searchResult to DisplayResRank according to user preference again.
    }
    
   // public HashMap<String, Restaurant> searchByType(String type){
    //   searchResult = new HashMap(); //create an empty hashmap for storing searched results.
     //   for (Restaurant r : this.resDatabase.values()) {
      //      if(r.rType.contains(type)){
       //         searchResult.put(r.rName, r);
        //    }
        //}
     //   for (Restaurant s : searchResult.values()) {
     //   }
     //  return searchResult;
       // return save;  //then pass this to DisplayResRank according to user preference again.
   // }
   
}
