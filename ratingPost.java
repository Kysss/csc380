
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Yingying Xia
 */
public class ratingPost {

    String title;
    Double rating;
    String content;
    String postdate;

    public ratingPost(String title, Double Rating, String content) {
        this.title = title;
        this.rating = Rating;
        this.content = content;

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        
       this.postdate = dateFormat.format(date);
       //System.out.println(this.postdate);
        //save this specific rating somewhere saveReview
    }
}
