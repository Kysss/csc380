package com.yingying.searchapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class search extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
    //    String username = getIntent().getStringExtra("Username");
        TextView usernameDisplay = (TextView)findViewById(R.id.usernamePrint);
      //  usernameDisplay.setText(username);
    }
}
