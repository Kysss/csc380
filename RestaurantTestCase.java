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
        //      assertEquals("This should return false", "Domino", r.rName); //expecting false
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
    public void testRemoveType() {
        Restaurant r = new Restaurant("Domino's");
        r.setType("Thai");
        r.setType("Chinese");
        r.removeType("Chinese");
        assertEquals(true, r.rType.contains("Thai")); //should return true
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

    }

    @Test
    public void testAddRating() {
        Double rating = 3.55;
        Restaurant r = new Restaurant("Domino's");
        r.addRating(rating);
        
        assertNotNull(r.ratingList);
        assertEquals(true,r.ratingList.contains(3.55));
     //   assertEquals(true,r.ratingList.contains(3.56)); //should return false
    }
    
    @Test
    public void testRemoveRating(){
        Double rating = 3.9;
        Double rating2 = 4.0;
        Restaurant r = new Restaurant ("Domino's");
        r.addRating(rating);
        r.addRating(rating2);
        assertNotNull(r.ratingList);
        assertEquals(true,r.ratingList.contains(3.9));
        assertEquals(true,r.ratingList.contains(4.0));
        r.removeRating(3.9);
       // assertEquals(true,r.ratingList.contains(3.9)); //should return false
        assertEquals(false,r.ratingList.contains(3.9));
        assertEquals(true,r.ratingList.contains(4.0));
        //assertEquals(4.0,r.rAverageRating);
    }
    
    public void testAverageRating(){
        Double rating = 3.0;
        Double rating2 = 6.0;
        Double rating3 = 6.0;
        Restaurant r = new Restaurant ("Domino's");
        r.addRating(rating);
        r.addRating(rating2);
        r.addRating(rating3);
        assertEquals(5.0,r.getAverageRating());
        r.removeRating(rating3);
        assertEquals(4.5,r.getAverageRating());
        r.removeRating(rating);
        assertEquals(6.0,r.getAverageRating());
       //System.out.println("get average rating test" + r.getAverageRating());
        r.removeRating(rating2);
        assertEquals(0.0,r.getAverageRating());
    }
}
