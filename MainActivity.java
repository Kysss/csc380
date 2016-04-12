package com.yingying.searchapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
