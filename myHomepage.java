package com.yingying.searchapp;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class myHomepage extends AppCompatActivity {


    DrawerLayout drawerLayout;
    Toolbar toolbar;
    ActionBarDrawerToggle actionBarDrawerToggle;
    android.support.v4.app.FragmentTransaction fragmentTransaction;

    static String carryUsername;
    static String carryUserEmail;

    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_homepage);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    //    drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        //actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar, "drawer open","drawer close");
//         drawerLayout.setDrawerListener(actionBarDrawerToggle);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            carryUsername = extras.getString("accountUsername");
            carryUserEmail = extras.getString("accountEmail");

        }
        fragmentTransaction= getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.main_container,new AboutFragment());
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

                        break;


                    case R.id.search_id:
                        item.setChecked(true);
                        Intent intent3 = new Intent(myHomepage.this, MainSearch.class);
                        intent3.putExtra("accountUsername",carryUsername);
                        intent3.putExtra("accountEmail", carryUserEmail);
                        startActivity(intent3);
                        item.setChecked(true);
                        break;
                    case R.id.profile_id:
                        item.setChecked(true);
                        Intent intent = new Intent(myHomepage.this, ProfilePage.class);
                        intent.putExtra("accountUsername",carryUsername);
                        intent.putExtra("accountEmail", carryUserEmail);
                        intent.putExtra("clickedUsername",carryUsername);

                        startActivity(intent);
                        item.setChecked(true);
                        break;

                    case R.id.setting_id:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_container, new Setting());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("Setting");
                        item.setChecked(true);

                        break;
                    case R.id.faq_id:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_container, new FAQ());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("FAQ/Help");
                        item.setChecked(true);

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

    }
}
