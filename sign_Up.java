package com.yingying.searchapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class sign_Up extends AppCompatActivity {
EditText fName, lName,eMail, username,password,retypePassword,securityQ, securityA;
    String userfirstName, userLastName, userEmail, userName, userPassword,confirmPassword, securityQuestion, securityAnswer;
    String userType = null;

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
        SUN = (Button)findViewById(R.id.signUpNowButton);
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

                if(!(userPassword.equals(confirmPassword))){
                    Toast.makeText(getBaseContext(),"Passwords are not matching.", Toast.LENGTH_LONG).show();
                    password.setText("");
                    retypePassword.setText("");
                }else{
                    DatabseOperations DB = new DatabseOperations(ctx);
                    DB.putInformation(DB,userName,userPassword,userLastName,userfirstName,userEmail,securityQuestion,securityAnswer,userType);
                    Toast.makeText(getBaseContext(),"Registration success! You can login now.",Toast.LENGTH_LONG).show();
                    finish();
                    Intent i = new Intent(sign_Up.this, sign_In.class);
                    startActivity(i);
                }




            }
        });
    }

   // public void signUpNowClick(View v){
    //    if(v.getId()==R.id.signUpNowButton){

       //     String userfirstName = fName.getText().toString();
      //      String userLastName = lName.getText().toString();
      //      String userEmail = lName.getText().toString();
     //       String userName = username.getText().toString();
      //      String userPassword = password.getText().toString();
      //      String confirmPassword = retypePassword.getText().toString();
      //      String securityQuestion = securityQ.getText().toString();
      //      String securityAnswer = securityA.getText().toString();

       //     if(username.equals("Reeves")&&password.equals("helloworld")) {
      //          Intent i = new Intent(sign_Up.this, sign_In.class);
      //          startActivity(i);
       //     }else{
       //         Intent i = new Intent(sign_Up.this, sign_Up.class);
     //           startActivity(i);
   //         }
   //     }
 //   }
}
