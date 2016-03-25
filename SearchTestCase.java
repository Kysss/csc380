
import java.util.HashMap;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Yingying Xia
 */
public class SearchTestCase extends TestCase {

    @Test
    public void testSearchByName() {
        HashMap<String, Restaurant> rList = new HashMap();
        Restaurant a = new Restaurant("Domino's");
        Restaurant b = new Restaurant("Subway");
        Restaurant c = new Restaurant("Pizza Hut");
        Restaurant d = new Restaurant("Kampai");
        Restaurant e = new Restaurant("Kimya Subway");

        rList.put(a.rName, a);
        rList.put(b.rName, b);
        rList.put(c.rName, c);
        rList.put(d.rName, d);
        rList.put(e.rName, e);
        ResSearch searchname = new ResSearch(rList);
        HashMap<String, Restaurant> r = searchname.searchByName("Subway");        //should return subway and kimya subway.

        /*  for (Restaurant s : r.values()) {
         System.out.println(s.rName);
            
         }*/
        assertEquals(true, r.keySet().contains("Subway"));
        assertEquals(true, r.keySet().contains("Kimya Subway"));
    }

    public void testSearchByType() {
        HashMap<String, Restaurant> rList = new HashMap();
        Restaurant a = new Restaurant("Domino's");
        Restaurant b = new Restaurant("Subway");
        Restaurant c = new Restaurant("Pizza Hut");
        Restaurant d = new Restaurant("Kampai");
        Restaurant e = new Restaurant("Kimya Subway");
        a.setType("Chinese");
        a.setType("Thai");
        b.setType("Chinese");
        b.setType("Vietnam");
        c.setType("Japanese");
        d.setType("Chinese");
        d.setType("American");
        e.setType("American");
        rList.put(a.rName, a);
        rList.put(b.rName, b);
        rList.put(c.rName, c);
        rList.put(d.rName, d);
        rList.put(e.rName, e);
        ResSearch searchtype = new ResSearch(rList);
        HashMap<String, Restaurant> t = searchtype.searchByType("Chinese");   //should return Domino's, Subway,and Kampai
       /* for (Restaurant s : t.values()) {
            System.out.println(s.rName + s.rType);
        }*/
        
        assertEquals(true, t.keySet().contains("Domino's"));
        assertEquals(true, t.keySet().contains("Kampai"));
        assertEquals(true, t.keySet().contains("Subway"));
       // assertEquals(true, t.keySet().contains("jahjdf")); //this should return false
       
    }
}
