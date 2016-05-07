package com.yingying.searchapp;


import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class Setting extends Fragment {

    EditText oldPassword;
    EditText newPassword;
    EditText retypePassword;
    Button sButton;
    String oldPasswordString;
    String newPasswordString;
    String retypePasswordString;
    String carryUsername;
    String carryUserEmail;
     String carryUserType;
    Context context = getActivity();
    View view;
    public Setting() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

      //  carryUsername = getActivity().getIntent().getStringExtra("accountUsername");
        carryUsername = myHomepage.carryUsername;
     //   carryUserEmail = getActivity().getIntent().getStringExtra("accountEmail");
        carryUserEmail = myHomepage.carryUserEmail;
        carryUserType = myHomepage.carryAccountType;
       // carryUserType = getActivity().getIntent().getStringExtra("accountType");



    view = inflater.inflate(R.layout.admin_fragment_setting, container,false);
    oldPassword = (EditText) view.findViewById(R.id.editText);
    newPassword = (EditText) view.findViewById(R.id.editText2);
    retypePassword = (EditText) view.findViewById(R.id.editText3);
    sButton = (Button) view.findViewById(R.id.button);
    sButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            oldPasswordString = oldPassword.getText().toString();
            newPasswordString = newPassword.getText().toString();
            retypePasswordString = retypePassword.getText().toString();
            String p = getPassByName(carryUsername);

            if (p != null && p.equals(oldPasswordString) && newPasswordString.equalsIgnoreCase(retypePasswordString)) {
                DatabseOperations DO = new DatabseOperations(getActivity().getBaseContext());
                DO.updateInformation(DO, carryUsername, oldPasswordString, newPasswordString);

                Toast.makeText(getActivity(), "Changing password successful. Please login again.", Toast.LENGTH_LONG).show();
                Intent intent2 = new Intent(getActivity(), sign_In.class);

                startActivity(intent2);
            } else {
                Toast.makeText(getActivity(), "Please try again.", Toast.LENGTH_LONG).show();
                newPassword.setText("");
                oldPassword.setText("");
                retypePassword.setText("");
            }

        }
    });

     /*   Button deleteUser = (Button)view.findViewById(R.id.button2);
        deleteUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(carryUserType!=null && carryUserType.equalsIgnoreCase("Admin")){
                    Intent intent2 = new Intent(getActivity(),AdminDeleteAccount.class);
                    intent2.putExtra("accountUsername", carryUsername);
                    intent2.putExtra("accountEmail", carryUserEmail);
                    // intent2.putExtra("RestaurantName", carryResName);
                    startActivity(intent2);

                }else {
                    Toast.makeText(getActivity(), "Admin only.", Toast.LENGTH_LONG).show();
                }
            }
        });
        */

        Button addRes = (Button)view.findViewById(R.id.button3);
        addRes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(carryUserType==null || !carryUserType.equalsIgnoreCase("Admin")){
                    Toast.makeText(getActivity(), "Admin only.", Toast.LENGTH_LONG).show();
                }else if (carryUserType.equalsIgnoreCase("Admin")){
                    Intent intent2 = new Intent(getActivity(),addRestaurant.class);
                    intent2.putExtra("accountUsername", carryUsername);
                    intent2.putExtra("accountEmail", carryUserEmail);
                    startActivity(intent2);
                }
            }
        });

        Button deleteRes = (Button)view.findViewById(R.id.button4);
        deleteRes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(carryUserType==null || !carryUserType.equalsIgnoreCase("Admin")){
                    Toast.makeText(getActivity(), "Admin only.", Toast.LENGTH_LONG).show();
                }else{
                    Intent intent2 = new Intent(getActivity(),adminDeleteRes.class);
                    intent2.putExtra("accountUsername", carryUsername);
                    intent2.putExtra("accountEmail", carryUserEmail);
                    startActivity(intent2);
                }
            }
        });


        Button deleteReview = (Button)view.findViewById(R.id.button5);
        deleteReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(carryUserType==null || !carryUserType.equalsIgnoreCase("Admin")){
                    Toast.makeText(getActivity(), "Admin only.", Toast.LENGTH_LONG).show();
                }else{
                    Intent intent2 = new Intent(getActivity(),adminDeleteReview.class);
                    intent2.putExtra("accountUsername", carryUsername);
                    intent2.putExtra("accountEmail", carryUserEmail);
                    startActivity(intent2);
                }
            }
        });


        return view;

    }
    public String getPassByName(String username) {
        DatabseOperations DB = new DatabseOperations(this.getActivity().getBaseContext());
        String pass = null;
        Cursor cr = DB.getInformation(DB);
        cr.moveToFirst();
        while (!cr.isAfterLast()) {
            if (cr.getString(0) != null && cr.getString(0).equalsIgnoreCase(carryUsername)) {
                pass = cr.getString(1);
                break;

            }
            cr.moveToNext();
        }
        return pass;
    }





}