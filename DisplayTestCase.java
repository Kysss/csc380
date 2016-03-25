
import junit.framework.TestCase;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.HashMap;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Yingying Xia
 */
public class DisplayTestCase extends TestCase {

    @Test
    public void testDisplayRanking() {
        HashMap<String, Restaurant> rList = new HashMap();
        Restaurant a = new Restaurant("Domino's");
        Restaurant b = new Restaurant("Subway");
        Restaurant c = new Restaurant("Pizza Hut");
        Restaurant d = new Restaurant("Kampai");
        Restaurant e = new Restaurant("Kimya Subway");
        a.addRating(1.5);
        b.addRating(3.0);
        c.addRating(4.0);
        d.addRating(3.5);
        e.addRating(3.5);
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
        ResSearch searchname = new ResSearch(rList);
        HashMap<String, Restaurant> r = searchname.searchByName("Subway");         //should return subway and kimya subway
        ResSearch searchtype = new ResSearch(rList);
        HashMap<String, Restaurant> t = searchtype.searchByType("Chinese");         ///should return Domino's, Subway,and Kampai

        DisplayResRank rank = new DisplayResRank(r);
        Restaurant[] rs = rank.displayLowToHighRanking(r);           //should be subway,kimya subway.
        Restaurant[] qs = rank.displayHighToLowRanking(r);           //should be kimya, subway
        assertEquals("Subway", rs[0].rName);
        assertEquals("Kimya Subway", rs[1].rName);

        assertEquals("Kimya Subway", qs[0].rName);
        assertEquals("Subway", qs[1].rName);
        DisplayResRank rank2 = new DisplayResRank(t);
        Restaurant[] rs2 = rank2.displayLowToHighRanking(t);        //should be domino's,subway,kampai
        Restaurant[] qs2 = rank2.displayHighToLowRanking(t);        //should be kampai, subway, domino's

        assertEquals("Domino's", rs2[0].rName);
        assertEquals("Subway", rs2[1].rName);
        assertEquals("Kampai", rs2[2].rName);

        assertEquals("Kampai", qs2[0].rName);
        assertEquals("Subway", qs2[1].rName);
        assertEquals("Domino's", qs2[2].rName);
    }
}
