package com.yingying.searchapp;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Parcelable;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class sign_In extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
  //  private hashmap hm;
  //  private HashMap<String, Profile> userData;
    Profile account;
  Context CTX = this;
    EditText u,p;
    String username,password;
    static String carryUsername;
    static String carryUserEmail;
    boolean loginStatus;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign__in);
        loginStatus = false;
        final TextView register = (TextView) findViewById(R.id.registerLinkText);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent registerIntent = new Intent(sign_In.this, sign_Up.class);
                sign_In.this.startActivity(registerIntent);
            }
        });

        final TextView findPassword = (TextView) findViewById(R.id.forgotPasswordText);

        findPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent findPasswordIntent = new Intent(sign_In.this, ForgetPassword.class);
                sign_In.this.startActivity(findPasswordIntent);
            }
        });

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

    }



    public void OKButtonClick(View v){
        if(v.getId()==R.id.okButton){
           u = (EditText) findViewById(R.id.userName);
            p = (EditText) findViewById(R.id.userPassword);

            username = u.getText().toString();
            password = p.getText().toString();

            DatabseOperations DOP = new DatabseOperations(CTX);
            Cursor CR = DOP.getInformation(DOP);
            CR.moveToFirst();
            loginStatus = false;
            String name = "";

            while(!CR.isAfterLast()){
                if(username.equals(CR.getString(0))&&password.equals(CR.getString(1))){
                    carryUsername = CR.getString(0);
                    carryUserEmail=  CR.getString(4);

                 loginStatus = true;
                 break;
                }else{
                    CR.moveToNext();
                }

            }
            if(loginStatus == true){
                Toast.makeText(getBaseContext(),"Login Success",Toast.LENGTH_LONG).show();
                //Toast.makeText(getBaseContext(),"Please wait...",Toast.LENGTH_LONG).show();
                finish();
                Intent i = new Intent(sign_In.this, MainSearch.class);
                i.putExtra("accountUsername", carryUsername);
                i.putExtra("accountEmail",carryUserEmail);
                startActivity(i);
            }else if(loginStatus == false){
                Toast.makeText(getBaseContext(),"Login Authentication failed. Please try again.",Toast.LENGTH_LONG).show();
                u.setText("");
                p.setText("");
            }




        }
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "sign_In Page", // TODO: Define a title for the content shown.
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
                "sign_In Page", // TODO: Define a title for the content shown.
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
