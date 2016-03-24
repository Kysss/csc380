
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Yingying Xia
 */
public class Restaurant {

    String rName;
    String rAddress;
    String rContact;
    Double rAverageRating;
    
    //  Set ratingList = new HashSet();
    Set<String> rType = new HashSet();
    
    ArrayList ratingList = new ArrayList();

    public Restaurant(String name) {
        this.rName = name;
    }

    public void setName(String newName) {
        this.rName = newName;
    }

    public void setAddress(String address) {
        this.rAddress = address;
    }

    public void setContact(String contact) {
        this.rContact = contact;
    }

    public void setType(String type) {
        Set<String> currentType = this.rType;
        currentType.add(type);
    }

    public void removeType(String type) {
        Set<String> currentType = this.rType;
        currentType.remove(type);
    }

    public void addRating(Double rate) {
       this.ratingList.add(rate);
       this.rAverageRating = this.getAverageRating();
       //System.out.println(ratingList);
     
    }

   // NEED TO REORGANIZE AND TEST THIS SO THAT THE ARRAY DO NOT HAVE AN EMPTY SPOT WHEN AN ENTRY IS REMOVED.
    public void removeRating(Double rate) {
       this.ratingList.remove(rate);
       this.rAverageRating = this.getAverageRating();
     //   System.out.println(ratingList);
      
    }

    public String getName() {
        return rName;
    }

    public String getAddress() {
        return rAddress;
    }

    public String getContact() {
        return rContact;
    }

    public Double getAverageRating() {
        Double sum = 0.0;
        
        if(ratingList.isEmpty()){
            return 0.0;
        }else{
        for (int i = 0; i < this.ratingList.size(); i++) {
            Double current = Double.parseDouble(ratingList.get(i).toString());
            
            sum = sum + current;
            rAverageRating = sum/ratingList.size();
            
        }
       // System.out.println("Average rating" + this.rAverageRating);
        return rAverageRating;
    }
    }
    public Set<String> getType() {
        return rType;
    }
}
