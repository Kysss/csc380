package com.yingying.searchapp;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

public class ForgetPassword extends AppCompatActivity implements UEconfirm.UEListener, SQAconfirm.SQAListener {


    Context CTX = this;
    Boolean checkStatus;
    String name;
    String question;
    String answer;
    String pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        checkStatus = false;

    }

    //This gets called by ueconfirm when the user clicks the button
    @Override
    public void CheckUE(String username, String email) {
        SQAconfirm QuestionPortion = (SQAconfirm) getSupportFragmentManager().findFragmentById(R.id.fragment2);
      //  try {
      //      hashmap hm = new hashmap("Userpass.csv", "Restaurant.csv");
      //      if (hm.profile.containsKey(username) && email.equalsIgnoreCase(hm.profile.get(username).getEmail())) {
      //          QuestionPortion.setSecurityQuestion(hm.profile.get(username).getSecurityQuestion(), hm.profile.get(username).getUsername());
      //      }
      //  } catch (IOException e) {
      //      System.out.println("Error:" + e.getMessage());
       // }

        DatabseOperations DOP = new DatabseOperations(CTX);
        name= null;
        question = null;
        answer = null;
        pass= null;
        Cursor CR = DOP.getInformation(DOP);
        CR.moveToFirst();
        checkStatus = false;

        while(!CR.isAfterLast()){
            if(username.equals(CR.getString(0))&& email.equals(CR.getString(4))){
                pass = CR.getString(1);
                name = CR.getString(0);
                question = CR.getString(5);
                answer = CR.getString(6);

                checkStatus = true;
                break;
            }else{
                CR.moveToNext();
            }

        }

        if(checkStatus == true){
            Toast.makeText(getBaseContext(), "Please answer the security question below", Toast.LENGTH_LONG).show();
            QuestionPortion.setSecurityQuestion(question, name);
        }else if (checkStatus == false){
            Toast.makeText(getBaseContext(),"Sorry, your provided information do not match.",Toast.LENGTH_LONG).show();

        }

    }

    @Override
    public void CheckSA(String securityAnswer, String username) {
        ReturnPassword returnPassword = (ReturnPassword) getSupportFragmentManager().findFragmentById(R.id.fragment3);
       // try {
      //      hashmap hm = new hashmap("Userpass.csv", "Restaurant.csv");
       //     if (hm.profile.get(username).getSecurityAnswer().equalsIgnoreCase(securityAnswer)) {
      //          returnPassword.setPassword(hm.profile.get(username).getPassword());
      //      }
      //  } catch (IOException e) {
      //      System.out.println("Error:" + e.getMessage());
      //  }

        if(answer.equalsIgnoreCase(securityAnswer)){
            returnPassword.setPassword(pass);
        }else{
            Toast.makeText(getBaseContext(),"Sorry.Please try again.",Toast.LENGTH_LONG).show();
            finish();
            Intent forgetIntent = new Intent(ForgetPassword.this, ForgetPassword.class);
           ForgetPassword.this.startActivity(forgetIntent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_forget_password, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
//
        return super.onOptionsItemSelected(item);
    }
}
