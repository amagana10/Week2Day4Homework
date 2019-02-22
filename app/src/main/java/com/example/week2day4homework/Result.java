package com.example.week2day4homework;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Result extends AppCompatActivity {
    public static final int RESULT_CODE = 101;
    EditText etName;
    EditText etAddress;
    EditText etCity;
    EditText etState;
    EditText etZip;
    EditText etPhoneNumber;
    EditText etEmail;
    Intent thisIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        bindEditTextViews();

        thisIntent = getIntent();

    }

    public  void bindEditTextViews(){
        etName = findViewById(R.id.etName);
        etAddress = findViewById(R.id.etAddress);
        etCity = findViewById(R.id.etCity);
        etState = findViewById(R.id.etState);
        etZip = findViewById(R.id.etZip);
        etPhoneNumber = findViewById(R.id.etPhoneNumber);
        etEmail = findViewById(R.id.etEmailAddress);
    }

    public User generateUserFromInput(){
        User user = new User();

        user.setName(etName.getText() !=null ?etName.getText().toString():"");
        user.setAddress(etAddress.getText() !=null ?etAddress.getText().toString():"");
        user.setCity(etCity.getText() !=null ?etCity.getText().toString():"");
        user.setState(etState.getText() !=null ?etState.getText().toString():"");
        user.setZip(etZip.getText() !=null ?etZip.getText().toString():"");
        user.setPhoneNumber(etPhoneNumber.getText() !=null ?etPhoneNumber.getText().toString():"");
        user.setEmail(etEmail.getText() !=null ?etEmail.getText().toString():"");
        return user;
    }

    public void onClick(View view) {
        Bundle bundle = new Bundle();

        User user = generateUserFromInput();
        bundle.putParcelable("user",user);
        thisIntent.putExtras(bundle);
        setResult(RESULT_CODE,thisIntent);
        finish();
    }
}
