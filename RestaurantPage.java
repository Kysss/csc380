package com.yingying.searchapp;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;

public class RestaurantPage extends AppCompatActivity {

    Context CTX = this;
    static String carryResName;
    static String carryUsername;
    static String carryUserEmail;
    ArrayList<ratingPost> reviewList;
    Restaurant clickedRes;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurant_page);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            carryUsername = extras.getString("accountUsername");
            carryUserEmail = extras.getString("accountEmail");
            carryResName = extras.getString("RestaurantName");
        }

        RestaurantDatabaseOperations RDOP = new RestaurantDatabaseOperations(CTX);
        Cursor cr = RDOP.getInformation(RDOP);
        cr.moveToFirst();

        //res name = 0,
        //res address = 1
        //res contact = 2
        //res hours = 3
        //res rating = 4
        //res type =5

        while (!cr.isAfterLast()) {
            if (cr.getString(0) != null && cr.getString(0).equalsIgnoreCase(carryResName)) {
                clickedRes = new Restaurant(cr.getString(0), cr.getString(1), cr.getString(2),
                        cr.getString(3), cr.getString(4), cr.getString(5));
                break;
            } else {
                cr.moveToNext();
            }
        }

        TextView ResName = (TextView) findViewById(R.id.restaurantName);
        ResName.setText(carryResName);

        TextView ResAddress = (TextView) findViewById(R.id.restaurantAddress);
        ResAddress.setText(clickedRes.getAddress());

        TextView ResContact = (TextView) findViewById(R.id.restaurantContact);
        ResContact.setText(clickedRes.getContact());

        RatingBar ResRating = (RatingBar) findViewById(R.id.ratingBar);
        ResRating.setRating(Float.parseFloat(clickedRes.getAverage()));

        TextView ResType = (TextView) findViewById(R.id.restaurantTypes);
        ResType.setText(clickedRes.getType());

        TextView ResHours = (TextView) findViewById(R.id.restaurantHours);
        ResHours.setText(clickedRes.getHours());


        Button backToSearch = (Button)findViewById(R.id.backToSearch);
        backToSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RestaurantPage.this,MainSearch.class);
                i.putExtra("accountUsername",carryUsername);
                i.putExtra("accountEmail", carryUserEmail);
                RestaurantPage.this.startActivity(i);
            }
        });

        Button addNewReview = (Button)findViewById(R.id.addReviewButton);
        addNewReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addReviewPage = new Intent(RestaurantPage.this, addNewReview.class);
                addReviewPage.putExtra("accountUsername",carryUsername);
                addReviewPage.putExtra("accountEmail",carryUserEmail);
                addReviewPage.putExtra("RestaurantName",carryResName);
                RestaurantPage.this.startActivity(addReviewPage);
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View view) {
                Intent i = new Intent(RestaurantPage.this,myHomepage.class);
                i.putExtra("accountUsername",carryUsername);
                i.putExtra("accountEmail", carryUserEmail);
                RestaurantPage.this.startActivity(i);
            }
        });


        reviewList = new ArrayList<ratingPost>();
        loadDatabase();

        ListView myList = (ListView) findViewById(R.id.reviewListView);
        ReviewListAdapter reviewListAdapter = new ReviewListAdapter(this, reviewList);
        myList.setAdapter(reviewListAdapter);
        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent i = new Intent(CTX, ProfilePage.class);
                i.putExtra("RestaurantName", reviewList.get(position).getResName());
                i.putExtra("accountUsername", RestaurantPage.carryUsername);
                i.putExtra("accountEmail", RestaurantPage.carryUserEmail);
                Toast.makeText(CTX, reviewList.get(position).resName, Toast.LENGTH_LONG).show();
                CTX.startActivity(i);




            }
        });


        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    private void loadDatabase() {
        RatingDatabaseOperations RatingDOP = new RatingDatabaseOperations(CTX);
        Cursor cr = RatingDOP.getInformation(RatingDOP);
        cr.moveToFirst();

        while (!cr.isAfterLast()) {
            if (cr.getString(0) != null && cr.getString(0).equalsIgnoreCase(carryResName)) {
                ratingPost rp = new ratingPost(cr.getString(0), cr.getString(1), cr.getString(2), cr.getString(3), cr.getString(4),cr.getString(5));
                reviewList.add(rp);
            }
            cr.moveToNext();
        }

    }

    private String calculateAverageRating(String resName){
        //going through ratingDatabase
        RatingDatabaseOperations RatingDOP = new RatingDatabaseOperations(CTX);
        Cursor cr = RatingDOP.getInformation(RatingDOP);
        cr.moveToFirst();

        float sum = 0;
        int count = 0;
        while(!cr.isAfterLast()){
            if(cr.getString(0)!=null && cr.getString(0).equalsIgnoreCase(resName)){
                Float rating = Float.parseFloat(cr.getString(4));
                sum = sum + rating;
                count++;
            }
            cr.moveToNext();
        }

        float average = sum/count;
        String newAverage = Float.toString(average);
        //RestaurantDatabaseOperations ResDOP = new RestaurantDatabaseOperations(CTX);
        //ResDOP.updateAverageRating(ResDOP,);
        return newAverage;
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "RestaurantPage Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.yingying.searchapp/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "RestaurantPage Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.yingying.searchapp/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
