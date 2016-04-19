package com.yingying.searchapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Yingying Xia on 2016/4/14.
 */
public class RatingDatabaseOperations extends SQLiteOpenHelper {


    public static final int database_version = 1;


    public String CREATE_QUERY="CREATE TABLE " + RatingTableData.RatingTableInfo.TABLE_NAME + " (" +
            RatingTableData.RatingTableInfo.RES_NAME+ " TEXT," + RatingTableData.RatingTableInfo.REVIEW_DATE+ " TEXT,"+RatingTableData.RatingTableInfo.USER_NAME + " TEXT,"
            + RatingTableData.RatingTableInfo.USER_EMAIL
            + " TEXT," +RatingTableData.RatingTableInfo.RATING +" TEXT," +RatingTableData.RatingTableInfo.USER_REVIEW + " TEXT);";

    public RatingDatabaseOperations(Context context) {
        super(context,RatingTableData.RatingTableInfo.DATABASE_NAME,null,database_version);
        Log.d("RDatabase operations", "Database Created.");

    }

    @Override
    public void onCreate(SQLiteDatabase sdb) {
        sdb.execSQL(CREATE_QUERY);
        Log.d("RDatabase operations","Table Created.");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void putInformation(RatingDatabaseOperations rdop, String rName, String date, String uName, String uEmail, String uRating, String uReview){
        SQLiteDatabase SQ = rdop.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(RatingTableData.RatingTableInfo.RES_NAME, rName);
        cv.put(RatingTableData.RatingTableInfo.REVIEW_DATE,date);
        cv.put(RatingTableData.RatingTableInfo.USER_NAME, uName);
        cv.put(RatingTableData.RatingTableInfo.USER_EMAIL,uEmail);
        cv.put(RatingTableData.RatingTableInfo.RATING,uRating);
        cv.put(RatingTableData.RatingTableInfo.USER_REVIEW, uReview);


        long k = SQ.insert(RatingTableData.RatingTableInfo.TABLE_NAME,null,cv);
        Log.d("RateDatabase operations","One row inserted.");

    }

    public Cursor getInformation(RatingDatabaseOperations rdop){
        SQLiteDatabase SQ = rdop.getReadableDatabase();
        String[] coloumns = {RatingTableData.RatingTableInfo.RES_NAME,RatingTableData.RatingTableInfo.REVIEW_DATE,RatingTableData.RatingTableInfo.USER_NAME,
                RatingTableData.RatingTableInfo.USER_EMAIL,
                RatingTableData.RatingTableInfo.RATING,RatingTableData.RatingTableInfo.USER_REVIEW};
        Cursor SR = SQ.query(RatingTableData.RatingTableInfo.TABLE_NAME,coloumns,null,null,null,null,null);
        return SR;
    }







}
