package com.yingying.searchapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class sign_Up extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign__up);
    }

    public void signUpNowClick(View v){
        if(v.getId()==R.id.signUpNowButton){
            EditText fName = (EditText) findViewById(R.id.fn);
            EditText lName = (EditText) findViewById(R.id.ln);
            EditText eMail = (EditText) findViewById(R.id.e);
            EditText username = (EditText) findViewById(R.id.un);
            EditText password = (EditText) findViewById(R.id.p);
            EditText retypePassword = (EditText) findViewById(R.id.rp);
            EditText securityQ = (EditText) findViewById(R.id.sq);
            EditText securityA = (EditText) findViewById(R.id.sa);

            String userfirstName = fName.getText().toString();
            String userLastName = lName.getText().toString();
            String userEmail = lName.getText().toString();
            String userName = username.getText().toString();
            String userPassword = password.getText().toString();
            String confirmPassword = retypePassword.getText().toString();
            String securityQuestion = securityQ.getText().toString();
            String securityAnswer = securityA.getText().toString();

            if(username.equals("Reeves")&&password.equals("helloworld")) {
                Intent i = new Intent(sign_Up.this, sign_In.class);
                startActivity(i);
            }else{
                Intent i = new Intent(sign_Up.this, sign_Up.class);
                startActivity(i);
            }
        }
    }
}
