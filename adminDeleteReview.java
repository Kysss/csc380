package com.yingying.searchapp;

import android.app.AlertDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.HashMap;

public class adminDeleteReview extends AppCompatActivity{
    static String carryUsername;
    static String carryUserEmail;
    static String carryResName;
    static String carryClickedUsername;
    ArrayList<ratingPost> reviewHistoryList;
    Context CTX = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_delete_review);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            carryUsername = extras.getString("accountUsername");
            carryUserEmail = extras.getString("accountEmail");
            carryResName = extras.getString("RestaurantName");
            carryClickedUsername = extras.getString("clickedUsername");
        }

        ListView ratingHistoryList = (ListView)findViewById(R.id.ratingList);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(adminDeleteReview.this,myHomepage.class);
                i.putExtra("accountUsername",carryUsername);
                i.putExtra("accountEmail", carryUserEmail);
               adminDeleteReview.this.startActivity(i);
            }
        });
        reviewHistoryList= new ArrayList<ratingPost>();
        loadReviews();
        adminDeleteAdapter adminDeleteAdapter = new adminDeleteAdapter(this, reviewHistoryList);
        ratingHistoryList.setAdapter(adminDeleteAdapter);
        ratingHistoryList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(CTX);
                builder.setMessage("Are you sure you want to delete " + reviewHistoryList.get(position).postUserName + "'s review?");
                builder.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                RatingDatabaseOperations rdo = new RatingDatabaseOperations(CTX);
                                rdo.deleteReview(rdo, reviewHistoryList.get(position).resName, reviewHistoryList.get(position).getPostUserName(),
                                        reviewHistoryList.get(position).getPostDate());
                                Restaurant r = getRestaurantByName(reviewHistoryList.get(position).resName);
                                String newAverage = calculateAverageRating(r.getName());
                                RestaurantDatabaseOperations restaurantDatabaseOperations = new RestaurantDatabaseOperations(CTX);
                                restaurantDatabaseOperations.updateRestaurantAverageRating(restaurantDatabaseOperations,r.getName(),
                                        r.getContact(),newAverage);

                                finish();
                                Intent i = new Intent(adminDeleteReview.this,adminDeleteReview.class);
                                i.putExtra("accountUsername",carryUsername);
                                i.putExtra("accountEmail", carryUserEmail);
                              //  i.putExtra("RestaurantName", reviewHistoryList.get(position).getChildList().get(position).getText());
                                adminDeleteReview.this.startActivity(i);
                            }
                        });

                builder.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder.create();
                alert11.show();

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


    public Restaurant getRestaurantByName(String res) {
        Restaurant r=null;
        RestaurantDatabaseOperations ResDOP = new RestaurantDatabaseOperations(CTX);
        Cursor cr = ResDOP.getInformation(ResDOP);
        cr.moveToFirst();
        while (!cr.isAfterLast()) {
            if (cr.getString(0) != null && cr.getString(0).toLowerCase().equalsIgnoreCase(res)) {
                r = new Restaurant(cr.getString(0),cr.getString(1),cr.getString(2),cr.getString(3),cr.getString(4),cr.getString(5));
                break;
            }else{
                r = null;
            }
            cr.moveToNext();
        }
        return r;
    }



    public void loadReviews(){
        RatingDatabaseOperations RDB = new RatingDatabaseOperations(CTX);
        Cursor CR = RDB.getInformation(RDB);
        CR.moveToFirst();
        while(!CR.isAfterLast()){
            reviewHistoryList.add(new ratingPost(CR.getString(0),CR.getString(1),CR.getString(2),CR.getString(3),CR.getString(4),CR.getString(5)));
            CR.moveToNext();
        }
    }
}
