package com.yingying.searchapp;

import android.provider.BaseColumns;

/**
 * Created by Yingying Xia on 2016/4/13.
 */
public class TableData {
    public TableData(){

    }
    public static abstract class TableInfo implements BaseColumns{
        public static final String USER_NAME = "user_name";
        public static final String USER_PASS="user_pass";
        public static final String USER_LAST_NAME ="user_last_name";
        public static final String USER_FIRST_NAME = "user_first_name";
        public static final String USER_EMAIL = "user_email";
        public static final String USER_SECURITY_QUESTION= "user_security_question";
        public static final String USER_SECURITY_ANSWER = "user_security_answer";
        public static final String USER_TYPE = "user_type";
        public static final String DATABASE_NAME = "user_database";
        public static final String TABLE_NAME="register_info";

    }

}
