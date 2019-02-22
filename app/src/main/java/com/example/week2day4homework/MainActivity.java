package com.example.week2day4homework;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static final int REQUEST_CODE = 100;
    TextView tvName;
    TextView tvAddress;
    TextView tvCity;
    TextView tvState;
    TextView tvZip;
    TextView tvPhoneNumber;
    TextView tvEmailAddress;
    TextView tvSharePrefUserName;
    SharedPreferences sharedPreferences;
    UserDataBase userDataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindTextViews();

        sharedPreferences = getSharedPreferences("shared_pref",MODE_PRIVATE);

        userDataBase = new UserDataBase(this);
    }

    public void bindTextViews(){
        tvName = findViewById(R.id.tvName);
        tvAddress = findViewById(R.id.tvAddress);
        tvCity = findViewById(R.id.tvCity);
        tvState = findViewById(R.id.tvState);
        tvZip = findViewById(R.id.tvZip);
        tvPhoneNumber = findViewById(R.id.tvPhoneNumber);
        tvEmailAddress = findViewById(R.id.tvEmailAddress);
        tvSharePrefUserName = findViewById(R.id.tvSharePrefUserName);

    }
    public void populateTheViews(User user){
        tvName.setText(user.getName());
        tvAddress.setText(user.getAddress());
        tvCity.setText(user.getCity());
        tvState.setText(user.getState());
        tvZip.setText(user.getZip());
        tvPhoneNumber.setText(user.getPhoneNumber());
        tvEmailAddress.setText(user.getEmail());
        tvSharePrefUserName.setText(sharedPreferences.getString("user_name","NO USER"));
    }

    public void saveNameToPref(User user){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("user_name",user.getName());
        editor.commit();
    }

    public  void saveUserToDataBaseAndSeeLog(User user){
        userDataBase.insertUserIntoDataBase(user);
        ArrayList<User> users = userDataBase.getAllCarsFromDataBase();
        for (User currentUser :users) {
            Log.d("TAG", currentUser.toString());
        }
    }

    public void onClick(View view) {
        Intent explicitIntent = new Intent(this,Result.class);

        startActivityForResult(explicitIntent, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data != null){
            Bundle bundle = data.getExtras();
            if (bundle != null){
                User user = bundle.getParcelable("user");
                if (user !=null){
                    saveNameToPref(user);
                    saveUserToDataBaseAndSeeLog(user);
                    populateTheViews(user);
                }
            }
        }

    }
}
