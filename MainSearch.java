package com.yingying.searchapp;

import android.Manifest;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.hardware.display.DisplayManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ExpandableListView;
import android.widget.SearchView;
import android.widget.Toast;


import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.HashMap;

public class MainSearch extends AppCompatActivity
        implements SearchView.OnQueryTextListener, SearchView.OnCloseListener,LocationListener {
   // private static final int REQUEST_CODE_LOCATION = 2;
    Restaurant[] rankedRestaurant = null;
    static String carryUsername;
    static String carryUserEmail;
    Context CTX = this;
    private SearchManager searchManager;
    private SearchView searchView;
    private MyExpandableListAdapter listAdapter;
    private ExpandableListView myList;

    private ArrayList<ParentRow> parentList = new ArrayList<ParentRow>();
    private ArrayList<ParentRow> showTheseParentList = new ArrayList<ParentRow>();

    private ArrayList<UserParentRow> parentList2 = new ArrayList<UserParentRow>();
    private ArrayList<UserParentRow> showTheseParentList2 = new ArrayList<UserParentRow>();

    private MenuItem searchItem;
    public HashMap<String, Restaurant> resHM = new HashMap<String, Restaurant>();
    public HashMap<String, Profile> userHM = new HashMap<String, Profile>();
    double longtitude = 0.0;
    double latitude = 0.0;
    GPSTracker gps;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    private LocationManager locationManager;
    private String provider;
    private String mprovider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_main);


        Intent intent = getIntent();
        carryUsername = intent.getStringExtra("accountUsername");
        carryUserEmail = intent.getStringExtra("accountEmail");

        this.getLocation();
        Toast.makeText(MainSearch.this, "Long:"+ longtitude + " latitude:"+ latitude, Toast.LENGTH_LONG).show();


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

             //   Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
            //            .setAction("Action", null).show();
                Intent i = new Intent(MainSearch.this,myHomepage.class);
                i.putExtra("accountUsername",carryUsername);
                i.putExtra("accountEmail", carryUserEmail);
                MainSearch.this.startActivity(i);
            }
        });




        searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        parentList = new ArrayList<ParentRow>();
        showTheseParentList = new ArrayList<ParentRow>();

        defaultdisplayList();
        expandAll();

        final CheckBox checkBox = (CheckBox)findViewById(R.id.checkBox);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (checkBox.isChecked()) {
                    checkBox.setChecked(true);
                    searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
                    parentList = new ArrayList<ParentRow>();
                    showTheseParentList = new ArrayList<ParentRow>();

                    displayList();
                    expandAll();

                }else if(!checkBox.isChecked()){
                    checkBox.setChecked(false);
                    searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
                    parentList = new ArrayList<ParentRow>();
                    showTheseParentList = new ArrayList<ParentRow>();

                    defaultdisplayList();
                    expandAll();
                }
            }
        });


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void defaultloadData() {

        RestaurantDatabaseOperations RDOP = new RestaurantDatabaseOperations(CTX);
        Cursor CR = RDOP.getInformation(RDOP);
        CR.moveToFirst();

        //res name = 0,
        //res address = 1
        //res contact = 2
        //res hours = 3
        //res rating = 4
        //res type =5

        while (!CR.isAfterLast()) {
            Restaurant newRes = new Restaurant(CR.getString(0), CR.getString(1), CR.getString(2), CR.getString(3), CR.getString(4), CR.getString(5));
            resHM.put(CR.getString(0), newRes);

            CR.moveToNext();
        }

        DisplayResRank displayResRank = new DisplayResRank(resHM);
        rankedRestaurant = displayResRank.displayHighToLowRanking(resHM);


        ArrayList<ChildRow> childRows = new ArrayList<ChildRow>();
        ParentRow parentRow = null;



        for(int i=0; i< rankedRestaurant.length; i ++){
            Restaurant r = rankedRestaurant[i];
            childRows.add(new ChildRow(R.drawable.dining_icon,r.getName(),r.getAverage(),r.getType()));

        }

        parentRow = new ParentRow("Restaurants", childRows);

        parentList.add(parentRow);


    /*    DatabseOperations UDOP = new DatabseOperations(CTX);
        Cursor cr = UDOP.getInformation(UDOP);
        cr.moveToFirst();

        //res name = 0,
        //res address = 1
        //res contact = 2
        //res hours = 3
        //res rating = 4
        //res type =5

        while (!cr.isAfterLast()) {
            if (cr.getString(7) != null && cr.getString(7).equalsIgnoreCase("Admin")) {
                AdminProfile newAccount = new AdminProfile(cr.getString(0), cr.getString(1), cr.getString(2),
                        cr.getString(3), cr.getString(4), cr.getString(5), cr.getString(6), "Admin");
                userHM.put(cr.getString(0), newAccount);

            } else {
                UserProfile newAccount = new UserProfile(cr.getString(0), cr.getString(1), cr.getString(2),
                        cr.getString(3), cr.getString(4), cr.getString(5), cr.getString(6), "User");
                userHM.put(cr.getString(0), newAccount);

            }
            cr.moveToNext();
        }

   //     UserParentRow parentRow2 = null;
    //    ArrayList<ProfileChildRow> userchildRows = new ArrayList<ProfileChildRow>();
    //    userchildRows = new ArrayList<ProfileChildRow>();
    //    for (String userName : userHM.keySet()) {
    //        userchildRows.add(new ProfileChildRow(R.mipmap.ic_launcher, userName));
    //    }

   //     parentRow2 = new UserParentRow("User Profiles", userchildRows);
    //    parentList2.add(parentRow2);
*/
    }

    //from low to high
    private void loadData() {

        RestaurantDatabaseOperations RDOP = new RestaurantDatabaseOperations(CTX);
        Cursor CR = RDOP.getInformation(RDOP);
        CR.moveToFirst();

        //res name = 0,
        //res address = 1
        //res contact = 2
        //res hours = 3
        //res rating = 4
        //res type =5

        while (!CR.isAfterLast()) {
            Restaurant newRes = new Restaurant(CR.getString(0), CR.getString(1), CR.getString(2), CR.getString(3), CR.getString(4), CR.getString(5));
            resHM.put(CR.getString(0), newRes);

            CR.moveToNext();
        }

        DisplayResRank displayResRank = new DisplayResRank(resHM);
        rankedRestaurant = displayResRank.displayLowToHighRanking(resHM);


        ArrayList<ChildRow> childRows = new ArrayList<ChildRow>();
        ParentRow parentRow = null;



        for(int i=0; i< rankedRestaurant.length; i ++){
            Restaurant r = rankedRestaurant[i];
            childRows.add(new ChildRow(R.drawable.dining_icon,r.getName(),r.getAverage(),r.getType()));

        }

        parentRow = new ParentRow("Restaurants", childRows);

        parentList.add(parentRow);

    }

    public void expandAll() {
        int count = listAdapter.getGroupCount();
        for (int i = 0; i < count; i++) {
            myList.expandGroup(i);
        }
    }

    public void defaultdisplayList() {
        defaultloadData();

        myList = (ExpandableListView) findViewById(R.id.expandableListView_search);
        listAdapter = new MyExpandableListAdapter(MainSearch.this, parentList);


        myList.setAdapter(listAdapter);

        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent i = new Intent(MainSearch.this,RestaurantPage.class);
                i.putExtra("accountUsername",carryUsername);
                i.putExtra("accountEmail", carryUserEmail);
                i.putExtra("RestaurantName",parentList.get(position).getChildList().get(position).getText());
                MainSearch.this.startActivity(i);
            }
        });


    }
    public void displayList() {
        loadData();

        myList = (ExpandableListView) findViewById(R.id.expandableListView_search);
        listAdapter = new MyExpandableListAdapter(MainSearch.this, parentList);


        myList.setAdapter(listAdapter);

        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent i = new Intent(MainSearch.this,RestaurantPage.class);
                i.putExtra("accountUsername",carryUsername);
                i.putExtra("accountEmail", carryUserEmail);
                i.putExtra("RestaurantName",parentList.get(position).getChildList().get(position).getText());
                MainSearch.this.startActivity(i);
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);
        searchItem = menu.findItem(R.id.action_search);
        searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setSearchableInfo
                (searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false);
        searchView.setOnQueryTextListener(this);
        searchView.setOnCloseListener(this);
        searchView.requestFocus();

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onClose() {
        listAdapter.filterData("");
        expandAll();
        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        listAdapter.filterData(query);
        expandAll();
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        listAdapter.filterData(newText);
        expandAll();
        return false;
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "MainSearch Page", // TODO: Define a title for the content shown.
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
                "MainSearch Page", // TODO: Define a title for the content shown.
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


    public Restaurant[] getNearby(Restaurant [] rList){
        for(Restaurant r: rList){

        }
        return rList;
    }


    @Override
    public void onLocationChanged(Location location) {
        longtitude = location.getLongitude();
        latitude=location.getLatitude();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    public void getLocation(){
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();

        mprovider = locationManager.getBestProvider(criteria, false);

        if (mprovider != null && !mprovider.equals("")) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            Location location = locationManager.getLastKnownLocation(mprovider);
            locationManager.requestLocationUpdates(mprovider, 15000, 1, this);
       //     locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 15000, 1, this);

            if (location != null)
                onLocationChanged(location);
            else
                Toast.makeText(getBaseContext(), "No Location Provider Found Check Your Code", Toast.LENGTH_SHORT).show();
        }
    }
}
