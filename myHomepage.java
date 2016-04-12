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
        fragmentTransaction= getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.main_container,new SearchFragment());
        fragmentTransaction.commit();
        getSupportActionBar().setTitle("Search");
        navigationView = (NavigationView)findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.profile_id:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_container, new MyProfile());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("My Profile");
                        item.setChecked(true);

                      //  drawerLayout.closeDrawers();
                     //   drawerLayout.closeDrawer();
                        break;
                    case R.id.setting_id:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_container, new Setting());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("Setting");
                        item.setChecked(true);
                    //    drawerLayout.closeDrawers();
                        break;
                    case R.id.faq_id:
                        Intent intent = new Intent(myHomepage.this, FAQ.class);
                        startActivity(intent);
                        item.setChecked(true);
                    //    drawerLayout.closeDrawers();
                        break;


                    case R.id.logout_id:

                         item.setChecked(true);
                        Intent intent2 = new Intent(myHomepage.this, MainActivity.class);
                        startActivity(intent2);
                        item.setChecked(true);
                   //     drawerLayout.closeDrawers();
                        break;

                }
                return true;
            }

        });

    }
}
