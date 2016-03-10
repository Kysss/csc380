
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
    Set<String> rType = new HashSet();

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

    public void removeType(String type){
        Set<String> currentType = this.rType;
        currentType.remove(type);
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
        return rAverageRating;
    }

    public Set<String> getType() {
        return rType;
    }
}
