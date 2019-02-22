package com.example.week2day4homework;

import com.example.week2day4homework.UserDataBaseContract.*;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;

import static com.example.week2day4homework.UserDataBaseContract.*;

public class UserDataBase extends SQLiteOpenHelper {



    public UserDataBase(@Nullable Context context) {
        super(context, TABLE_NAME, null, DATA_BASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createQuery());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }

    public long insertUserIntoDataBase(@NonNull User user){
        SQLiteDatabase writeableDataBase =  this.getWritableDatabase();
        //Data holder used for database key value pairs
        ContentValues contentValues = new ContentValues();

        //insert  key value pairs into the content value container
        contentValues.put(COLUMN_NAME, user.getName());
        contentValues.put(COLUMN_ADDRESS,user.getAddress());
        contentValues.put(COLUMN_CITY,user.getCity());
        contentValues.put(COLUMN_STATE, user.getState());
        contentValues.put(COLUMN_ZIP,user.getZip());
        contentValues.put(COLUMN_PHONE,user.getPhoneNumber());
        contentValues.put(COLUMN_EMAIL,user.getEmail());

        //insert the car into the table using contentValues
        return writeableDataBase.insert(TABLE_NAME,null,contentValues);
    }

    public ArrayList<User> getAllCarsFromDataBase(){
        ArrayList<User> returnUserList = new ArrayList<>();
        SQLiteDatabase readableDataBase = this.getReadableDatabase();
        //GetResults from query and hold in cursor (iterable object for databaase operations
        Cursor cursor = readableDataBase.rawQuery(UserDataBaseContract.getAllUsersQuery(),null);
        //chack to see if we have any results
        if (cursor.moveToFirst()){
            //while we have results, get the values and place in list
            do {
                //get Values
                int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
                String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
                String address = cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS));
                String city = cursor.getString(cursor.getColumnIndex(COLUMN_CITY));
                String state = cursor.getString(cursor.getColumnIndex(COLUMN_STATE));
                String zip = cursor.getString(cursor.getColumnIndex(COLUMN_ZIP));
                String phone = cursor.getString(cursor.getColumnIndex(COLUMN_PHONE));
                String email = cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL));

                //add to list
                returnUserList.add(new User(id,name,address,city,state,zip,phone,email));
            } while (cursor.moveToNext());


        }
        cursor.close();
        //return the result in a list
        return returnUserList;
    }


}
