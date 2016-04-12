package com.yingying.searchapp;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import java.io.IOException;

public class ForgetPassword extends AppCompatActivity implements UEconfirm.UEListener, SQAconfirm.SQAListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

    }

    //This gets called by ueconfirm when the user clicks the button
    @Override
    public void CheckUE(String username, String email) {
        SQAconfirm QuestionPortion = (SQAconfirm) getSupportFragmentManager().findFragmentById(R.id.fragment2);
        try {
            hashmap hm = new hashmap("Userpass.csv", "Restaurant.csv");
            if (hm.profile.containsKey(username) && email.equalsIgnoreCase(hm.profile.get(username).getEmail())) {
                QuestionPortion.setSecurityQuestion(hm.profile.get(username).getSecurityQuestion(), hm.profile.get(username).getUsername());
            }
        } catch (IOException e) {
            System.out.println("Error:" + e.getMessage());
        }
    }

    @Override
    public void CheckSA(String securityAnswer, String username) {
        ReturnPassword returnPassword = (ReturnPassword) getSupportFragmentManager().findFragmentById(R.id.fragment3);
        try {
            hashmap hm = new hashmap("Userpass.csv", "Restaurant.csv");
            if (hm.profile.get(username).getSecurityAnswer().equalsIgnoreCase(securityAnswer)) {
                returnPassword.setPassword(hm.profile.get(username).getPassword());
            }
        } catch (IOException e) {
            System.out.println("Error:" + e.getMessage());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
      //?  getMenuInflater().inflate(R.menu.menu_forget_password, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
       // if (id == R.id.action_settings) {
        //    return true;
       // }
//
        return super.onOptionsItemSelected(item);
    }
}
