package com.yingying.searchapp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class addRestaurant extends AppCompatActivity {

Context ctx = this;
    EditText resname,resaddress,rescontact,reshours,resrating,resttype;
    String resName,resAddress,resContact,resHours,resRating,resType;
    Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_restaurant);
        resname = (EditText)findViewById(R.id.editText6);
        resaddress = (EditText)findViewById(R.id.editText7);
        rescontact = (EditText)findViewById(R.id.editText8);
        reshours = (EditText)findViewById(R.id.editText9);
        resrating = (EditText)findViewById(R.id.editText10);
        resttype = (EditText)findViewById(R.id.editText11);

       addButton = (Button)findViewById(R.id.button7);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                resName = resname.getText().toString();
                resAddress = resaddress.getText().toString();
                resContact = rescontact.getText().toString();
                resHours = reshours.getText().toString();
                resRating = resrating.getText().toString();
                resType = resttype.getText().toString();
                RestaurantDatabaseOperations rdop = new RestaurantDatabaseOperations(ctx);
                if(resName!=null && resAddress!=null && resContact!=null &&resHours!=null &&resRating!=null&&resType!=null) {
                    rdop.putResInformation(rdop, resName, resAddress, resContact, resHours, resRating, resType);
                      Toast.makeText(ctx,"adding restaurant info successful",Toast.LENGTH_SHORT).show();
                    resname.setText("");
                    resaddress.setText("");
                    rescontact.setText("");
                    reshours.setText("");
                    resrating.setText("");
                    resttype.setText("");


                }else{
                    Toast.makeText(ctx,"something is wrong.", Toast.LENGTH_SHORT).show();
                }
            }

        });

    }
}
