package com.yingying.searchapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class AdminDeleteAccount extends AppCompatActivity {

    static String carryUsername;
    static String carryUserEmail;
    static String carryResName;
    static String carryClickedUsername;
    ArrayList<Profile> userAccountList;
    Context CTX = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete_account_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            carryUsername = extras.getString("accountUsername");
            carryUserEmail = extras.getString("accountEmail");
            carryResName = extras.getString("RestaurantName");
            carryClickedUsername = extras.getString("clickedUsername");
        }

        ListView accountList = (ListView) findViewById(R.id.accountList);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AdminDeleteAccount.this, myHomepage.class);
                i.putExtra("accountUsername", carryUsername);
                i.putExtra("accountEmail", carryUserEmail);
                AdminDeleteAccount.this.startActivity(i);
            }
        });
        userAccountList = new ArrayList<Profile>();
        loadAccounts();
        adminDeleteAccountAdapter adminDeleteAccountAdapter = new adminDeleteAccountAdapter(this, userAccountList);
        accountList.setAdapter(adminDeleteAccountAdapter);
        accountList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(CTX);
                builder.setMessage("Are you sure you want to delete " + userAccountList.get(position).getUsername() +"? This will delete all user review as well.");
                builder.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                DatabseOperations rdo = new DatabseOperations(CTX);
                                rdo.deleteUser(rdo, userAccountList.get(position).getUsername(),userAccountList.get(position).getEmail());

                                Intent i = new Intent(AdminDeleteAccount.this,AdminDeleteAccount.class);
                                i.putExtra("accountUsername",carryUsername);
                                i.putExtra("accountEmail", carryUserEmail);
                            //    i.putExtra("RestaurantName", userAccountList.get(position).getChildList().get(position).getText());
                                AdminDeleteAccount.this.startActivity(i);
                            }
                        });

                builder.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder.create();
                alert11.show();
            }
        });
    }


    public void loadAccounts() {
        DatabseOperations RDB = new DatabseOperations(CTX);
        Cursor cr = RDB.getInformation(RDB);
        cr.moveToFirst();


            while (!cr.isAfterLast()) {
                if (cr.getString(7) != null && cr.getString(7).equalsIgnoreCase("Admin")) {
                    AdminProfile newAccount = new AdminProfile(cr.getString(0), cr.getString(1), cr.getString(2),
                            cr.getString(3), cr.getString(4), cr.getString(5), cr.getString(6), "Admin");
                    userAccountList.add(newAccount);

                } else {
                    UserProfile newAccount = new UserProfile(cr.getString(0), cr.getString(1), cr.getString(2),
                            cr.getString(3), cr.getString(4), cr.getString(5), cr.getString(6), "User");
                    userAccountList.add(newAccount);

                }
                cr.moveToNext();
            }

    }

}