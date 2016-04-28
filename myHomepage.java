package com.yingying.searchapp;


import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class myHomepage extends AppCompatActivity {



    Toolbar toolbar;

    android.support.v4.app.FragmentTransaction fragmentTransaction;

    Context context = this;
    static String carryUsername;
    static String carryUserEmail;
    static String carryAccountType;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle actionBarDrawerToggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_homepage);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout= (DrawerLayout) findViewById(R.id.drawer_layout);
        actionBarDrawerToggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.draweropen,R.string.drawerclose);

        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            carryUsername = extras.getString("accountUsername");
            carryUserEmail = extras.getString("accountEmail");
        }


        fragmentTransaction= getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.main_container, new AboutFragment());
        fragmentTransaction.commit();
        getSupportActionBar().setTitle("About");
        navigationView = (NavigationView)findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.about_id:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_container, new AboutFragment());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("About");
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        break;


                    case R.id.search_id:
                        item.setChecked(true);
                        Intent intent3 = new Intent(myHomepage.this, MainSearch.class);
                        intent3.putExtra("accountUsername", carryUsername);
                        intent3.putExtra("accountEmail", carryUserEmail);
                        startActivity(intent3);
                        item.setChecked(true);
                    //    drawerLayout.closeDrawers();
                        break;
                    case R.id.profile_id:
                        item.setChecked(true);
                        Intent intent = new Intent(myHomepage.this, ProfilePage.class);
                        intent.putExtra("accountUsername", carryUsername);
                        intent.putExtra("accountEmail", carryUserEmail);
                        intent.putExtra("clickedUsername", carryUsername);

                        startActivity(intent);
                        item.setChecked(true);
                   //     drawerLayout.closeDrawers();
                        break;

                    case R.id.setting_id:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        Bundle bundle = new Bundle();
                        bundle.putString("accountUsername", carryUsername);
                        bundle.putString("accountEmail", carryUserEmail);
                        carryAccountType = getProfileTypeByName(carryUsername);
                        bundle.putString("accountType", carryAccountType);
                        fragmentTransaction.replace(R.id.main_container, new Setting());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("Setting");
                        item.setChecked(true);
                      // drawerLayout.closeDrawers();
                       break;

                    case R.id.faq_id:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_container, new FAQ());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("FAQ/Help");
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        break;


                    case R.id.logout_id:

                        item.setChecked(true);
                        Intent intent2 = new Intent(myHomepage.this, MainActivity.class);
                        startActivity(intent2);
                        item.setChecked(true);
                        finish();

                        break;

                }
                return true;
            }

        });
        View v = navigationView.getHeaderView(0);
        TextView namedisplay = (TextView) v.findViewById(R.id.headerusername);
        namedisplay.setText(carryUsername);

    }

    public String getProfileTypeByName(String username) {
        DatabseOperations DB = new DatabseOperations(context);
        String type = null;
        Cursor cr = DB.getInformation(DB);
        cr.moveToFirst();
        while (!cr.isAfterLast()) {
            if (cr.getString(0) != null && cr.getString(0).equalsIgnoreCase(username)) {
                type = cr.getString(7);
                break;

            }
            cr.moveToNext();
        }
        return type;
    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }
}
