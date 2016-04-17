package com.yingying.searchapp;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Yingying Xia on 2016/4/13.
 */
public class DatabseOperations extends SQLiteOpenHelper {
    public static final int database_version = 1;

    public String CREATE_QUERY="CREATE TABLE " + TableData.TableInfo.TABLE_NAME + " (" +
            TableData.TableInfo.USER_NAME+ " TEXT," + TableData.TableInfo.USER_PASS + " TEXT," + TableData.TableInfo.USER_LAST_NAME+ " TEXT,"
            +TableData.TableInfo.USER_FIRST_NAME +" TEXT," + TableData.TableInfo.USER_EMAIL + " TEXT," + TableData.TableInfo.USER_SECURITY_QUESTION+ " TEXT,"
            +TableData.TableInfo.USER_SECURITY_ANSWER+ " TEXT," +TableData.TableInfo.USER_TYPE+ " TEXT);" ;

    public DatabseOperations(Context context) {
        super(context, TableData.TableInfo.DATABASE_NAME, null, database_version);
        Log.d("Database operations", "Database Created.");

    }

    @Override
    public void onCreate(SQLiteDatabase sdb) {
        sdb.execSQL(CREATE_QUERY);
        Log.d("Database operations","Table Created.");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void putInformation(DatabseOperations dop, String name, String pass, String ln, String fn, String email, String sq, String sa,String type){
        SQLiteDatabase SQ = dop.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(TableData.TableInfo.USER_NAME, name);
        cv.put(TableData.TableInfo.USER_PASS, pass);
        cv.put(TableData.TableInfo.USER_LAST_NAME,ln);
        cv.put(TableData.TableInfo.USER_FIRST_NAME,fn);
        cv.put(TableData.TableInfo.USER_EMAIL, email);
        cv.put(TableData.TableInfo.USER_SECURITY_QUESTION, sq);
        cv.put(TableData.TableInfo.USER_SECURITY_ANSWER, sa);
        cv.put(TableData.TableInfo.USER_TYPE, type);

        long k = SQ.insert(TableData.TableInfo.TABLE_NAME,null,cv);
        Log.d("Database operations","One row inserted.");
    }

    public Cursor getInformation(DatabseOperations dop){
        SQLiteDatabase SQ = dop.getReadableDatabase();
        String[] coloumns = {TableData.TableInfo.USER_NAME, TableData.TableInfo.USER_PASS,TableData.TableInfo.USER_LAST_NAME,TableData.TableInfo.USER_FIRST_NAME,
                TableData.TableInfo.USER_EMAIL,TableData.TableInfo.USER_SECURITY_QUESTION,TableData.TableInfo.USER_SECURITY_ANSWER, TableData.TableInfo.USER_TYPE};
        Cursor SR = SQ.query(TableData.TableInfo.TABLE_NAME,coloumns,null,null,null,null,null);
        return SR;
    }


}
