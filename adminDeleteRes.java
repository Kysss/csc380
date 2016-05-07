package com.yingying.searchapp;

import android.app.AlertDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ExpandableListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.HashMap;

public class adminDeleteRes extends AppCompatActivity implements SearchView.OnQueryTextListener, SearchView.OnCloseListener {

    Restaurant[] rankedRestaurant = null;
    static String carryUsername;
    static String carryUserEmail;
    Context CTX = this;
    private SearchManager searchManager;
    private SearchView searchView;
    private adminDeleteListAdapter listAdapter;
    private ExpandableListView myList;

    private ArrayList<ParentRow> parentList = new ArrayList<ParentRow>();
    private ArrayList<ParentRow> showTheseParentList = new ArrayList<ParentRow>();

    private ArrayList<UserParentRow> parentList2 = new ArrayList<UserParentRow>();
    private ArrayList<UserParentRow> showTheseParentList2 = new ArrayList<UserParentRow>();

    private MenuItem searchItem;
    public HashMap<String, Restaurant> resHM = new HashMap<String, Restaurant>();
   // public HashMap<String, Profile> userHM = new HashMap<String, Profile>();

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_main);


        Intent intent = getIntent();
        carryUsername = intent.getStringExtra("accountUsername");
        carryUserEmail = intent.getStringExtra("accountEmail");

      //  this.getLocation();



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //   Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //            .setAction("Action", null).show();
                Intent i = new Intent(adminDeleteRes.this,myHomepage.class);
                i.putExtra("accountUsername",carryUsername);
                i.putExtra("accountEmail", carryUserEmail);
                adminDeleteRes.this.startActivity(i);
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



    }

    //from low to high
    private void loadData() {

        RestaurantDatabaseOperations RDOP = new RestaurantDatabaseOperations(CTX);
        Cursor CR = RDOP.getInformation(RDOP);
        CR.moveToFirst();

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
        listAdapter = new adminDeleteListAdapter(adminDeleteRes.this, parentList);


        myList.setAdapter(listAdapter);

        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                AlertDialog.Builder builder = new AlertDialog.Builder(CTX);
                builder.setMessage("Are you sure you want to delete " + parentList.get(position).getChildList().get(position).getText());
                builder.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                RestaurantDatabaseOperations rdo = new RestaurantDatabaseOperations(CTX);
                                rdo.deleteRestaurant(rdo,parentList.get(position).getChildList().get(position).getText());
                                finish();
                                Intent i = new Intent(adminDeleteRes.this,adminDeleteRes.class);
                                i.putExtra("accountUsername",carryUsername);
                                i.putExtra("accountEmail", carryUserEmail);
                                i.putExtra("RestaurantName", parentList.get(position).getChildList().get(position).getText());
                                adminDeleteRes.this.startActivity(i);
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
    public void displayList() {
        loadData();

        myList = (ExpandableListView) findViewById(R.id.expandableListView_search);
        listAdapter = new adminDeleteListAdapter(adminDeleteRes.this, parentList);


        myList.setAdapter(listAdapter);

        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                AlertDialog.Builder builder = new AlertDialog.Builder(CTX);
                builder.setMessage("Are you sure you want to delete " + parentList.get(position).getChildList().get(position).getText());
                builder.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                RestaurantDatabaseOperations rdo = new RestaurantDatabaseOperations(CTX);
                                rdo.deleteRestaurant(rdo, parentList.get(position).getChildList().get(position).getText());

                                RatingDatabaseOperations ratingDB = new RatingDatabaseOperations(CTX);
                                Cursor c = ratingDB.getInformation(ratingDB);
                                c.moveToFirst();
                                while(!c.isAfterLast()){
                                    String name = parentList.get(position).getChildList().get(position).getText();
                                    if(c.getString(0)!=null && c.getString(0).equalsIgnoreCase(name)) {
                                        ratingPost rp = new ratingPost(c.getString(0),c.getString(1),c.getString(2),c.getString(3),c.getString(4),c.getString(5));
                                        ratingDB.deleteReview(ratingDB, name,c.getString(2),c.getString(1));
                                    }
                                    c.moveToNext();
                                }
                                finish();
                                Intent i = new Intent(adminDeleteRes.this, adminDeleteRes.class);
                                i.putExtra("accountUsername", carryUsername);
                                i.putExtra("accountEmail", carryUserEmail);
                                i.putExtra("RestaurantName", parentList.get(position).getChildList().get(position).getText());
                                adminDeleteRes.this.startActivity(i);
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

}
