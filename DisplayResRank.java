package com.yingying.searchapp;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Yingying Xia
 */
public class DisplayResRank {

  //  public String userOption = null;
  //  public Restaurant[] displayranking;
    public HashMap<String, Restaurant> rList = new HashMap();

    //passing in the *searched/filtered* Hashmap into Display
    DisplayResRank(HashMap<String, Restaurant> rList) {
       this.rList = rList;
    }

    //from low to high
    //returns an ordered(base on restaurants' average ranking) array from low to high
    public Restaurant[] displayLowToHighRanking(HashMap<String, Restaurant> rankingList) {
        HashMap<String, Restaurant> rList = rankingList;
        Restaurant[] r = new Restaurant[rList.size()];
       int currentSize = 0;
        for (String i : rList.keySet()) {
              System.out.println(i);
            float rate = Float.parseFloat(rList.get(i).rAverageRating);
            if (currentSize == 0) {
                r[0] = rList.get(i);
                currentSize++;
          } else {
               for (int j = currentSize - 1; j >= 0; j--) {
                    float f = Float.parseFloat(r[j].rAverageRating);
                   if (rate <= f && j != 0) {
                   r[j + 1] = r[j];
                 } else if (rate <= f && j == 0) {
                     r[j + 1] = r[j];
                   r[j] = rList.get(i);
                 break;
                 } else {
                 r[j + 1] = rList.get(i);
                    break;
                  }
            }
               currentSize++;
           }
      }
        return r;
    }

    //from high to low -- default display
    //returns an ordered(base on restaurants' average ranking) array from high to low. 
    public Restaurant[] displayHighToLowRanking(HashMap<String, Restaurant> rankingList) {
       HashMap<String, Restaurant> rList = rankingList;

        Restaurant[] r = new Restaurant[rList.size()];
        int currentSize = 0;
        for (String i : rList.keySet()) {
                 System.out.println(i);
           float rate = Float.parseFloat(rList.get(i).rAverageRating);
            if (currentSize == 0) {
               r[0] = rList.get(i);
                currentSize++;
                    //  System.out.println("nothing here. add successfully");
           } else {
               for (int j = currentSize - 1; j >= 0; j--) {
                  float f = Float.parseFloat(r[j].rAverageRating);
                    if (rate >= f && j != 0) {
                     r[j + 1] = r[j];
                    } else if (rate >= f && j == 0) {
                      r[j + 1] = r[j];
                       r[j] = rList.get(i);
                     break;
                   } else {
                     r[j + 1] = rList.get(i);
                     break;
                 }
           }
               currentSize++;
         }
      }
     return r;
    }
}
