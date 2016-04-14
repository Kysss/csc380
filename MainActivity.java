package com.yingying.searchapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Context CTX = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView forget = (TextView) findViewById(R.id.forgetPasswordLink);

        //restaurant database initiate
        RestaurantDatabaseOperations RDB = new RestaurantDatabaseOperations(CTX);
        RDB.putResInformation(RDB, "Domino's", "60 SW 9th St, Oswego, NY 13126","(315) 343-7250","10:30AM–12AM","3.2","American");
        RDB.putResInformation(RDB,"Kiyomi","311 W Seneca St, Oswego, NY 13126","(315) 343-8889","11AM–10PM","3.3","Japanese,Chinese");

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
