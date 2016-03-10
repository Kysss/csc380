/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Yingying Xia
 */
import junit.framework.TestCase;
//import org.junit.Before;
import org.junit.Test;

public class RestaurantTestCase extends TestCase {

    @Test
    public void testSetName() {
        String name = "Domino";
        Restaurant r = new Restaurant(name);
        String newName = "Domino's";
        r.setName(newName);
        //  assertEquals("This should return false", "Domino", r.rName); //expecting false
        assertEquals("Setting the name as Domino's", "Domino's", r.rName); //should return true
    }

    @Test
    public void testSetAddress() {
        String address = "60 SW 9th St, Oswego, NY 13126";
        Restaurant r = new Restaurant("Domino's");
        // assertNotNull(r.rAddress); //empty restaurant address; should return false
        r.setAddress(address);
        assertNotNull(r.rAddress); //restaurant address not null; should return true
        assertEquals("60 SW 9th St, Oswego, NY 13126", address); //should return true
        //  assertEquals("60 SW 9th St, Oswego, NY 1312", address); //should return false
    }

    @Test
    public void testSetContact() {
        String contact = "(315) 343-7250";
        Restaurant r = new Restaurant("Domino's");
        //assertNotNull(r.rContact); //should return false
        r.setContact(contact);
        assertNotNull(r.rContact);
    }

    @Test
    public void testSetType() {
        Restaurant r = new Restaurant("Domino's");
        r.setType("Thai");  //tests
        r.setType("Thai");  //would not produce duplicates

        r.setType("Chinese");
        assertNotNull(r.rType); //should return true;
       // assertNull(r.rType); //should return false;
        assertEquals(true, r.rType.contains("Thai"));
        assertEquals(true, r.rType.contains("Chinese"));
      //   assertEquals(true,r.rType.contains("Ba")); //should return false

    }
    
    @Test
    public void testRemoveType(){
        Restaurant r = new Restaurant("Domino's");
        r.setType("Thai");
        r.setType("Chinese");
        r.removeType("Chinese");
        assertEquals(true,r.rType.contains("Thai")); //should return true
    //    assertEquals(true,r.rType.contains("Chinese")); //should fail 
    }

    @Test
    public void testSetUp() {
        String name = "Domino's";
        String address = "60 SW 9th St, Oswego, NY 13126";
        String contact = "(315) 343-7250";
        Restaurant r = new Restaurant("Domino");
        assertEquals("Domino", r.getName());
        r.setName(name);
        r.setAddress(address);
        r.setContact(contact);
        assertEquals("Domino's", r.getName());
        assertEquals("60 SW 9th St, Oswego, NY 13126", r.getAddress());
        assertEquals("(315) 343-7250", r.getContact());
        assertNull(r.getAverageRating());
    }

}
