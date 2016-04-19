package com.yingying.searchapp;

import android.provider.BaseColumns;

import java.util.Set;

/**
 * Created by Yingying Xia on 2016/4/14.
 */
public class RestaurantTableData {
    public RestaurantTableData(){

    }
    public static abstract class RestaurantTableInfo implements BaseColumns{
        public static final String RES_NAME = "res_name";
        public static final String RES_ADDRESS="res_address";
        public static final String RES_CONTACT ="res_contact";
        public static final String RES_OPERATION_HOURS = "res_operation_hours";
        public static final String RES_AVERAGE_RATING = "res_average_rating";
        public static final String RES_TYPES = "res_types";
        public static final String RES_DATABASE_NAME = "restaurant_database";
        public static final String RES_TABLE_NAME="restaurant_info";

    }

}

