package com.yingying.searchapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class sign_Up extends AppCompatActivity {
    EditText fName, lName, eMail, username, password, retypePassword, securityQ, securityA;
    String userfirstName, userLastName, userEmail, userName, userPassword, confirmPassword, securityQuestion, securityAnswer;
    String userType = "User";

    Button SUN;
    Context ctx = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign__up);
        fName = (EditText) findViewById(R.id.fn);
        lName = (EditText) findViewById(R.id.ln);
        eMail = (EditText) findViewById(R.id.e);
        username = (EditText) findViewById(R.id.un);
        password = (EditText) findViewById(R.id.p);
        retypePassword = (EditText) findViewById(R.id.rp);
        securityQ = (EditText) findViewById(R.id.sq);
        securityA = (EditText) findViewById(R.id.sa);
        SUN = (Button) findViewById(R.id.signUpNowButton);
        SUN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userfirstName = fName.getText().toString();
                userLastName = lName.getText().toString();
                userEmail = eMail.getText().toString();
                userName = username.getText().toString();
                userPassword = password.getText().toString();
                confirmPassword = retypePassword.getText().toString();
                securityQuestion = securityQ.getText().toString();
                securityAnswer = securityA.getText().toString();
                DatabseOperations DB = new DatabseOperations(ctx);

                if (!(userPassword.equals(confirmPassword))) {
                    Toast.makeText(getBaseContext(), "Passwords are not matching.", Toast.LENGTH_LONG).show();
                    password.setText("");
                    retypePassword.setText("");

                }else if(DB.existUsername(DB,userName)==true && DB.existEmailname(DB,userEmail)==true){
                    Toast.makeText(getBaseContext(), "Username and email address already associated with an account.Please try again.", Toast.LENGTH_LONG).show();
                    username.setText("");
                    eMail.setText("");
                }else if (DB.existUsername(DB,userName)==true) {
                    Toast.makeText(getBaseContext(), "Username already exist. Please try again..", Toast.LENGTH_LONG).show();
                    username.setText("");
                }else if(DB.existEmailname(DB,userEmail)==true){
                    Toast.makeText(getBaseContext(), "This email has already been associated with an account.", Toast.LENGTH_LONG).show();
                    eMail.setText("");
                } else {
                    DB = new DatabseOperations(ctx);
                    DB.putInformation(DB, userName, userPassword, userLastName, userfirstName, userEmail, securityQuestion, securityAnswer, userType);
                    //send Email here
                    String e = "You have successfully registered a new account. Your user name is: "+userName + " ,and your email is:" +
                                  userEmail+ ". If you have any question, please send an email to yxia@oswego.edu. Thank you.";
                   // sendEmail(e);






                    Toast.makeText(getBaseContext(), "Registration success! You can login now.", Toast.LENGTH_LONG).show();

                    finish();
                    Intent i = new Intent(sign_Up.this, sign_In.class);
                    startActivity(i);

                }


            }
        });
    }



    public void sendEmail(String message){
        String[] to = new String[]{"yxia@oswego.edu"};
        String subject =("SearchApp Registration Confirmation");
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        //emailIntent.putExtra(Intent.EXTRA_EMAIL, to);
        emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL,to);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT,subject);
        emailIntent.putExtra(Intent.EXTRA_TEXT, message);
        emailIntent.setType("message/rfc822");
        startActivity(Intent.createChooser(emailIntent,"Email"));
    }




}

