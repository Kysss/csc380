package com.yingying.searchapp;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ProfilePage extends AppCompatActivity {

    static String carryUsername;
    static String carryUserEmail;
    static String carryResName;
    static String carryClickedUsername;
    ArrayList<ratingPost> reviewHistoryList;
    Context CTX = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            carryUsername = extras.getString("accountUsername");
            carryUserEmail = extras.getString("accountEmail");
            carryResName = extras.getString("RestaurantName");
            carryClickedUsername = extras.getString("clickedUsername");
        }
        TextView usernameDisplay = (TextView)findViewById(R.id.usernameDisplay);
        TextView accountTypeDisplay = (TextView)findViewById(R.id.accountTypeDisplay);
        ListView ratingHistoryList = (ListView)findViewById(R.id.ratingHistoryList);

        usernameDisplay.setText(carryClickedUsername);

        if(getProfileTypeByName(carryClickedUsername)!=null && getProfileTypeByName(carryClickedUsername).equalsIgnoreCase("Admin")) {
            accountTypeDisplay.setText("Admin");
        }else{
            accountTypeDisplay.setText("User");
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        reviewHistoryList= new ArrayList<ratingPost>();
        loadReviews();
        ProfileHistoryAdapter ProfileHistoryAdapter = new ProfileHistoryAdapter(this, reviewHistoryList);
        ratingHistoryList.setAdapter(ProfileHistoryAdapter);
    }


    public String getProfileTypeByName(String username) {
        DatabseOperations DB = new DatabseOperations(CTX);
        String type = null;
        Cursor cr = DB.getInformation(DB);
        cr.moveToFirst();
        while (!cr.isAfterLast()) {
            if (cr.getString(0) != null && cr.getString(0).equalsIgnoreCase(carryClickedUsername)) {
                type = cr.getString(7);
                break;

            }
        cr.moveToNext();
    }
      return type;
    }

    public void loadReviews(){
        RatingDatabaseOperations RDB = new RatingDatabaseOperations(CTX);
        Cursor CR = RDB.getInformation(RDB);
        CR.moveToFirst();
        while(!CR.isAfterLast()){
            if(CR.getString(2)!=null && CR.getString(2).equalsIgnoreCase(carryClickedUsername)){
                reviewHistoryList.add(new ratingPost(CR.getString(0),CR.getString(1),CR.getString(2),CR.getString(3),CR.getString(4),CR.getString(5)));

            }
            CR.moveToNext();
        }
    }

}
