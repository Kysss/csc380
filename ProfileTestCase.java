/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Profiles;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
/**
 *
 * @author KimBrian
 */
public class ProfileTestCase extends TestCase{
    
        String firstName = null;
        String lastName = null;
        String accountType;
        String userName = null;
        
        @Test
    public void testSetFirstName(){
        AdminProfile p = new AdminProfile(firstName, lastName, userName);
        //Test First Name Assignment
        String fName = "Kim Brian";
        p.setFirstname(fName);
        assertEquals("Kim Brian",p.getFirstname() );
    }
    public void testSetLastName (){
        AdminProfile p = new AdminProfile(firstName, lastName, userName);
        //Test Last Name Assignment
        String lName = "Fadul";
        p.setLastname(lName);
        assertEquals("Fadul",p.getLastname() );
    }
    public void testSetUserName (){
        AdminProfile p = new AdminProfile(firstName, lastName, userName);
        //Test User Name Assignment
        String uName = "kimfadul";
        p.setUsername(uName);
        assertEquals("kimfadul",p.getUsername());
    }
    public void testGetAccountType (){
        AdminProfile p = new AdminProfile(firstName, lastName, userName);
        //Test Get Account Type
        assertEquals("Admin",p.getAccountType() );
    }
}




