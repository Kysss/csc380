package com.yingying.searchapp;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class addNewReview extends AppCompatActivity {

    String carryResName;
    String carryUsername;
    String carryUserEmail;
    Context CTX = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_review);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            carryUsername = extras.getString("accountUsername");
            carryUserEmail = extras.getString("accountEmail");
            carryResName = extras.getString("RestaurantName");
        }


        final TextView resName = (TextView) findViewById(R.id.rname);
        resName.setText(carryResName);

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(addNewReview.this,myHomepage.class);
                i.putExtra("accountUsername",carryUsername);
                i.putExtra("accountEmail", carryUserEmail);
                addNewReview.this.startActivity(i);
            }
        });

        final RatingBar rb = (RatingBar) findViewById(R.id.ratingBar2);
        final EditText comment = (EditText) findViewById(R.id.commentContent);

        Button postCommentButton = (Button) findViewById(R.id.submitReviewButton);
        postCommentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Date date = new Date();
                String reviewDate = dateFormat.format(date);
                ratingPost rp = new ratingPost(carryResName, reviewDate, carryUsername, carryUserEmail, Float.toString(rb.getRating()),
                        comment.getText().toString());

                RatingDatabaseOperations RatingDB = new RatingDatabaseOperations(CTX);
                RatingDB.putInformation(RatingDB, rp.getResName(), rp.getPostDate(), carryUsername, carryUserEmail,
                        rp.getRating(), rp.getReview());
                Toast.makeText(getBaseContext(), "Review posted successfully", Toast.LENGTH_LONG).show();

                Restaurant thisRes = getRestaurantByName(carryResName);
                String newAverage = calculateAverageRating(carryResName);

//                updateAverageRating(new RestaurantDatabaseOperations(CTX),thisRes,newAverage);
                finish();
                Intent i = new Intent(addNewReview.this, RestaurantPage.class);
                i.putExtra("accountUsername", carryUsername);
                i.putExtra("accountEmail", carryUserEmail);
                i.putExtra("RestaurantName", carryResName);
                addNewReview.this.startActivity(i);

            }
        });


    }


    private String calculateAverageRating(String resName) {
        //going through ratingDatabase
        String newAverage;
        RatingDatabaseOperations RatingDOP = new RatingDatabaseOperations(CTX);
        Cursor cr = RatingDOP.getInformation(RatingDOP);
        cr.moveToFirst();

        float sum = 0;
        int count = 0;
        while (!cr.isAfterLast()) {
            if (cr.getString(0) != null && cr.getString(0).equalsIgnoreCase(resName)) {
                Float rating = Float.parseFloat(cr.getString(4));
                sum = sum + rating;

                count = count + 1;
            }
            cr.moveToNext();
        }
        if(count!=0) {
            float average = sum / count;
            newAverage = Float.toString(average);
        }else{
            newAverage = Float.toString(0);
        }
        return newAverage;
    }


  //  public void updateAverageRating(RestaurantDatabaseOperations rdop, Restaurant r, String newAverage) {
  //      SQLiteDatabase SQ = rdop.getWritableDatabase();
   //     ContentValues cv = new ContentValues();
    //    //r.setAverage(newAverage);
     //   cv.put(RestaurantTableData.RestaurantTableInfo.RES_NAME, r.getName());
       // cv.put(RestaurantTableData.RestaurantTableInfo.RES_ADDRESS, r.getAddress());
       // cv.put(RestaurantTableData.RestaurantTableInfo.RES_CONTACT, r.getContact());
       // cv.put(RestaurantTableData.RestaurantTableInfo.RES_OPERATION_HOURS, r.getHours());
        //   cv.put(RestaurantTableData.RestaurantTableInfo.RES_AVERAGE_RATING,r.getAverage());
       // cv.put(RestaurantTableData.RestaurantTableInfo.RES_TYPES, r.getType());
       // SQ.update(RestaurantTableData.RestaurantTableInfo.RES_TABLE_NAME, cv, RestaurantTableData.RestaurantTableInfo.RES_AVERAGE_RATING
        //        + "= ?", new String[]{String.valueOf(newAverage)});
        //SQ.close();
   // }


    public Restaurant getRestaurantByName(String res) {
        Restaurant r = null;
        RestaurantDatabaseOperations ResDOP = new RestaurantDatabaseOperations(CTX);
        Cursor cr = ResDOP.getInformation(ResDOP);
        cr.moveToFirst();
        while (!cr.isAfterLast()) {
            if (cr.getString(0) != null && cr.getString(0).equalsIgnoreCase(res)) {
                r = new Restaurant(cr.getString(0),cr.getString(1),cr.getString(2),cr.getString(3),cr.getString(4),cr.getString(5));

            }else{
                r = null;
            }
            cr.moveToNext();
        }
        return r;
    }

}