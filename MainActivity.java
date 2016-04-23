package com.yingying.searchapp;

import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    Context CTX = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView forget = (TextView) findViewById(R.id.forgetPasswordLink);

        //restaurant database initiate
        RestaurantDatabaseOperations RDB = new RestaurantDatabaseOperations(CTX);
      //  RDB.putResInformation(RDB, "Domino's", "60 SW 9th St, Oswego, NY 13126","(315) 343-7250","10:30AM–12AM","3.2","American");
       // RDB.putResInformation(RDB, "Kiyomi", "311 W Seneca St, Oswego, NY 13126", "(315) 343-8889", "11AM–10PM", "3.3", "Japanese,Chinese");

        //rating databse initiate
        RatingDatabaseOperations RateDB = new RatingDatabaseOperations(CTX);
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        String reviewDate = dateFormat.format(date);

   //     RateDB.putInformation(RateDB,"Kiyomi",reviewDate,"yxia","yxia@oswego,edu","4.5","Good service.");

        DatabseOperations DO = new DatabseOperations(CTX);
       // DO.deleteUser(DO,"yxia","yxia@oswego.edu");
        DO.updateInformation(DO,"yxia","testing","hello");


        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent forgetIntent = new Intent(MainActivity.this, ForgetPassword.class);
                MainActivity.this.startActivity(forgetIntent);
            }
        });

    }

    public void onButtonClick(View v){
        if(v.getId()==R.id.signInButton){
            Intent i = new Intent(MainActivity.this, sign_In.class);
            startActivity(i);
        }
    }

    public void onButtonClick2(View v){
        if(v.getId()==R.id.signUpButton){
            Intent i = new Intent(MainActivity.this, sign_Up.class);
            startActivity(i);
        }
    }





}
