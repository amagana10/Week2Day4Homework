package com.example.week2day4homework;


import android.util.Log;

public class UserDataBaseContract {
    public static final String DATA_BASE_NAME = "USER_DB";
    public static final int DATA_BASE_VERSION = 1
            ;
    public static final String TABLE_NAME = "Users";

    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_ADDRESS = "address";
    public static final String COLUMN_CITY = "city";
    public static final String COLUMN_STATE = "state";
    public static final String COLUMN_ZIP = "zip";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_ID = "id";

    public static String createQuery(){
        StringBuilder queryBuilder = new StringBuilder();
        //Query to create Table
        queryBuilder.append("CREATE TABLE ");
        queryBuilder.append(TABLE_NAME);
        queryBuilder.append(" ( ");
        //Must have unique Primary Key
        queryBuilder.append(COLUMN_ID);
        queryBuilder.append(" ");
        queryBuilder.append(" INT NONNULL IDENTITY PRIMARY KEY, ");
        //Add rest of the columns
        queryBuilder.append(COLUMN_NAME);
        queryBuilder.append(" TEXT, ");
        queryBuilder.append(COLUMN_ADDRESS);
        queryBuilder.append(" TEXT, ");
        queryBuilder.append(COLUMN_CITY);
        queryBuilder.append(" TEXT, ");
        queryBuilder.append(COLUMN_STATE);
        queryBuilder.append(" TEXT, ");
        queryBuilder.append(COLUMN_ZIP);
        queryBuilder.append(" TEXT, ");
        queryBuilder.append(COLUMN_PHONE);
        queryBuilder.append(" TEXT, ");
        queryBuilder.append(COLUMN_EMAIL);
        queryBuilder.append(" TEXT )");

        //Log the query so we can check for correctness
        Log.d("TAG", "createQuery: " +queryBuilder.toString());

        return queryBuilder.toString();
    }

    public static String getAllUsersQuery(){
        return  "SELECT * FROM " + TABLE_NAME;
    }

    public static String getOneCarByID(int id){
        return  String.format("SELECT * FROM %s WHERE %s = \"%d\"",TABLE_NAME,COLUMN_ID,id);
        //return "SELECT * FROM " + TABLE_NAME + " WHERE"
    }

}
