package com.yingying.searchapp;

import android.provider.BaseColumns;

/**
 * Created by Yingying Xia on 2016/4/14.
 */
public class RatingTableData {
    public RatingTableData(){
    }

    public static abstract class RatingTableInfo implements BaseColumns {
        public static final String RES_NAME = "res_name";
        public static final String REVIEW_DATE = "review_date";
        public static final String USER_NAME="user_name";
        public static final String USER_EMAIL = "user_email";
        public static final String RATING ="user_rating";
        public static final String USER_REVIEW = "user_review";

        public static final String DATABASE_NAME = "rating_database";
        public static final String TABLE_NAME="rating_info";

    }

}
