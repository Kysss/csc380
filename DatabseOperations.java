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

        long k = SQ.insert(TableData.TableInfo.TABLE_NAME, null, cv);
        Log.d("Database operations","One row inserted.");
    }

    public Cursor getInformation(DatabseOperations dop){
        SQLiteDatabase SQ = dop.getReadableDatabase();
        String[] coloumns = {TableData.TableInfo.USER_NAME, TableData.TableInfo.USER_PASS,TableData.TableInfo.USER_LAST_NAME,TableData.TableInfo.USER_FIRST_NAME,
                TableData.TableInfo.USER_EMAIL,TableData.TableInfo.USER_SECURITY_QUESTION,TableData.TableInfo.USER_SECURITY_ANSWER, TableData.TableInfo.USER_TYPE};
        Cursor SR = SQ.query(TableData.TableInfo.TABLE_NAME, coloumns, null, null, null, null, null);
        return SR;
    }

    public Cursor getUserPass(DatabseOperations DOP, String username){
        SQLiteDatabase SQ = DOP.getReadableDatabase();
        String selection = TableData.TableInfo.USER_NAME+ " LIKE ?";
        String colomns[]={TableData.TableInfo.USER_PASS};
        String args[]={username};
        Cursor CR= SQ.query(TableData.TableInfo.TABLE_NAME, colomns, selection, args, null, null, null);
        return CR;
    }
    public void deleteUser(DatabseOperations DOP, String username, String userEmail){
        String selection = TableData.TableInfo.USER_NAME + " LIKE ? AND " + TableData.TableInfo.USER_EMAIL + " LIKE ?";
        String args[]= {username,userEmail};
        SQLiteDatabase SQ = DOP.getWritableDatabase();
        SQ.delete(TableData.TableInfo.TABLE_NAME, selection, args);

    }

    public void updateInformation(DatabseOperations DOP, String username, String userpass, String newpass){

        SQLiteDatabase SQ = DOP.getWritableDatabase();
        String selection = TableData.TableInfo.USER_NAME + " LIKE ? AND " + TableData.TableInfo.USER_PASS + " LIKE ?";
        String args[] = {username,userpass};
        ContentValues values = new ContentValues();
        values.put(TableData.TableInfo.USER_PASS, newpass);
        SQ.update(TableData.TableInfo.TABLE_NAME, values, selection, args);

    }

    public boolean existUsername(DatabseOperations DOP, String username){
        boolean exist = false;
        Cursor CR = DOP.getInformation(DOP);
        CR.moveToFirst();
        while (!CR.isAfterLast()) {
            if (CR.getString(0).toLowerCase().equalsIgnoreCase(username)) {
                exist = true;
                break;
            } else {

                CR.moveToNext();
            }
        }

        return exist;
    }

    public boolean existEmailname(DatabseOperations DOP, String userEmail){
        boolean exist = false;
        Cursor CR = DOP.getInformation(DOP);
        CR.moveToFirst();
        while(!CR.isAfterLast()){
            if(CR.getString(4).toLowerCase().equalsIgnoreCase(userEmail)){
                exist = true;
                break;
            }else{
                CR.moveToNext();
            }
        }
        return exist;
    }


    public void deleteAccount(DatabseOperations rdop, String username, String email)
    {
        String selection = TableData.TableInfo.USER_NAME + " LIKE ? AND " +
                TableData.TableInfo.USER_EMAIL+" LIKE ?";
        String args[] = {username,email};
        SQLiteDatabase SQ = rdop.getWritableDatabase();
        SQ.delete(TableData.TableInfo.TABLE_NAME,selection,args);
    }


}
