package com.yingying.searchapp;

        import android.content.ContentValues;
        import android.content.Context;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;
        import android.util.Log;

        import java.util.Set;

/**
 * Created by Yingying Xia on 2016/4/14.
 */
public class RestaurantDatabaseOperations extends SQLiteOpenHelper {
        public static final int database_version = 1;

        public String CREATE_QUERY="CREATE TABLE " + RestaurantTableData.RestaurantTableInfo.RES_TABLE_NAME + " (" +
                RestaurantTableData.RestaurantTableInfo.RES_NAME+ " TEXT," + RestaurantTableData.RestaurantTableInfo.RES_ADDRESS + " TEXT,"
                + RestaurantTableData.RestaurantTableInfo.RES_CONTACT+ " TEXT," + RestaurantTableData.RestaurantTableInfo.RES_OPERATION_HOURS + " TEXT,"
                + RestaurantTableData.RestaurantTableInfo.RES_AVERAGE_RATING +" TEXT," + RestaurantTableData.RestaurantTableInfo.RES_TYPES + " TEXT);" ;

        public RestaurantDatabaseOperations(Context context) {
                super(context, RestaurantTableData.RestaurantTableInfo.RES_DATABASE_NAME, null, database_version);
                Log.d("Res Database operations", "Database Created.");

        }

        @Override
        public void onCreate(SQLiteDatabase sdb) {
                sdb.execSQL(CREATE_QUERY);
                Log.d("Res Database operations","Table Created.");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }

        public void putResInformation(RestaurantDatabaseOperations rdop, String resName, String resAddress, String resContact, String resHours, String resAverageRating,String resTypes){
                SQLiteDatabase SQ = rdop.getWritableDatabase();
                ContentValues cv = new ContentValues();

                cv.put(RestaurantTableData.RestaurantTableInfo.RES_NAME, resName);
                cv.put(RestaurantTableData.RestaurantTableInfo.RES_ADDRESS,resAddress);
                cv.put(RestaurantTableData.RestaurantTableInfo.RES_CONTACT, resContact);
                cv.put(RestaurantTableData.RestaurantTableInfo.RES_OPERATION_HOURS,resHours);
                cv.put(RestaurantTableData.RestaurantTableInfo.RES_AVERAGE_RATING,resAverageRating);
                cv.put(RestaurantTableData.RestaurantTableInfo.RES_TYPES, resTypes);


                long k = SQ.insert(RestaurantTableData.RestaurantTableInfo.RES_TABLE_NAME, null, cv);
                Log.d("Res Database operations","One row inserted.");

        }

        //Database operations?
        public Cursor getInformation(RestaurantDatabaseOperations rdop){
                SQLiteDatabase SQ = rdop.getReadableDatabase();
                String[] coloumns = {RestaurantTableData.RestaurantTableInfo.RES_NAME,RestaurantTableData.RestaurantTableInfo.RES_ADDRESS,
                        RestaurantTableData.RestaurantTableInfo.RES_CONTACT, RestaurantTableData.RestaurantTableInfo.RES_OPERATION_HOURS,RestaurantTableData.RestaurantTableInfo.RES_AVERAGE_RATING,
                        RestaurantTableData.RestaurantTableInfo.RES_TYPES};
                Cursor SR = SQ.query(RestaurantTableData.RestaurantTableInfo.RES_TABLE_NAME, coloumns, null, null, null, null, null);
                return SR;
        }

        public void deleteRestaurant(RestaurantDatabaseOperations rdop,String resName){
                String selection = RestaurantTableData.RestaurantTableInfo.RES_NAME +" LIKE ?";
                String args[] = {resName};
                SQLiteDatabase SQ = rdop.getWritableDatabase();
                SQ.delete(RestaurantTableData.RestaurantTableInfo.RES_TABLE_NAME,selection,args);

        }



        public void updateRestaurantAverageRating(RestaurantDatabaseOperations rdop,
                                                  String resName,
                                                  String resContact,
                                                  String newAverage){
                SQLiteDatabase SQ = rdop.getWritableDatabase();
                String selection = RestaurantTableData.RestaurantTableInfo.RES_NAME + " LIKE ? AND " + RestaurantTableData.RestaurantTableInfo.RES_CONTACT + " LIKE ?";
                String args[] = {resName,resContact};
                ContentValues values = new ContentValues();
                values.put(RestaurantTableData.RestaurantTableInfo.RES_AVERAGE_RATING,newAverage);
                SQ.update(RestaurantTableData.RestaurantTableInfo.RES_TABLE_NAME,values,selection,args);
        }





}

