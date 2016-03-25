/*
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

    public String userOption = null;
    public Restaurant[] displayranking;
    public HashMap<String, Restaurant> rList = new HashMap();

    //passing in the *searched/filtered* Hashmap into Display
    DisplayResRank(HashMap<String, Restaurant> rList) {
    //    this.userOption = userSelect;
        this.rList = rList;
        //default ranking from high to low
    /*    if (this.userOption.equalsIgnoreCase("default")) {
            this.displayHighToLowRanking(rList);
        } else if (this.userOption.equalsIgnoreCase("Lowest")) {
            this.displayLowToHighRanking(rList);
        } else {
            System.out.println("Sorry, please select from either default(high to low) or from low to high");
        }*/

    }

    //from low to high
    //returns an ordered(base on restaurants' average ranking) array from low to high
    public Restaurant[] displayLowToHighRanking(HashMap<String, Restaurant> rankingList) {
        HashMap<String, Restaurant> rList = rankingList;
        // System.out.println(rList);
        // System.out.println(rList.size());
        Restaurant[] r = new Restaurant[rList.size()];
        int currentSize = 0;
        for (String i : rList.keySet()) {
            //  System.out.println(i);
            Double rate = rList.get(i).rAverageRating;
            if (currentSize == 0) {
                r[0] = rList.get(i);
                currentSize++;
                //   System.out.println("nothing here. add successfully");

            } else {
                for (int j = currentSize - 1; j >= 0; j--) {
                    Double f = r[j].rAverageRating;
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
                //   System.out.println("something was already there.adding");
                currentSize++;
                //   System.out.println("currentSize" + currentSize);
            }
        }
       /// System.out.println("From low to high");
        //   for (Restaurant r1 : r) {
           //     System.out.println(r1.rName + r1.rAverageRating);
        //  }

        return r;
    }

    //from high to low -- default display
    //returns an ordered(base on restaurants' average ranking) array from high to low. 
    public Restaurant[] displayHighToLowRanking(HashMap<String, Restaurant> rankingList) {
        HashMap<String, Restaurant> rList = rankingList;
        // System.out.println(rList);
        //   System.out.println(rList.size());
        Restaurant[] r = new Restaurant[rList.size()];
        int currentSize = 0;
        for (String i : rList.keySet()) {
            //     System.out.println(i);
            Double rate = rList.get(i).rAverageRating;
            if (currentSize == 0) {
                r[0] = rList.get(i);
                currentSize++;
                //      System.out.println("nothing here. add successfully");
            } else {
                for (int j = currentSize - 1; j >= 0; j--) {
                    Double f = r[j].rAverageRating;
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
                //       System.out.println("something was already there.adding");
                currentSize++;
                //        System.out.println("currentSize" + currentSize);
            }
        }
      //  System.out.println("From High to Low");
      //     for (Restaurant r1 : r) {
     //          System.out.println(r1.rName + r1.rAverageRating);
       //   }
        return r;
    }
}
