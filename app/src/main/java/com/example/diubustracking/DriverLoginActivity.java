package com.example.diubustracking;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class DriverLoginActivity extends AppCompatActivity
{
    EditText mLicenceNo, mMemberName, mPhoneNumber, mEmail;
    Button mLoginButton;
    String licence_no, name, phone, email;
    DatabaseHelper mDatabaseHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.driver_login);

        mLicenceNo = findViewById(R.id.licence_no);
        mPhoneNumber = findViewById(R.id.phone);
        mMemberName = findViewById(R.id.name);
        mEmail = findViewById(R.id.email);

        mLoginButton = findViewById(R.id.login);
        mDatabaseHelper = new DatabaseHelper(this);

        mLoginButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(DriverLoginActivity.this, DriverLocationActivity.class);

                name = mMemberName.getText().toString();
                phone = mPhoneNumber.getText().toString();
                licence_no = mLicenceNo.getText().toString();
                email = mEmail.getText().toString();

                if(TextUtils.isEmpty(name))
                    mMemberName.setError("Name is Mandatory");
                if(TextUtils.isEmpty(phone))
                    mPhoneNumber.setError("Phone No. is Mandatory");
                if(TextUtils.isEmpty(licence_no))
                    mLicenceNo.setError("Licence No. is Mandatory");
                if(!(TextUtils.isEmpty(name) && TextUtils.isEmpty(phone) && TextUtils.isEmpty(licence_no)))
                {
                    mDatabaseHelper .insertData(name, phone, licence_no, email);               // Insert data into SqLite Database
                    startActivity(intent);
                }
            }
        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Driver Register");
        toolbar.setTitleTextColor(getResources().getColor(R.color.white,getTheme()));
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onBackPressed();
            }
        });

        getWindow().setStatusBarColor(getResources().getColor(R.color.darkblue2, this.getTheme()));
       // getWindow().setNavigationBarColor(getResources().getColor(R.color.yellow,this.getTheme()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_withoutroute, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();

        if(id == R.id.share)
        {
            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            String shareBody = "Content body";
            sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject");
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
            startActivity(Intent.createChooser(sharingIntent, "Share via"));
        }
        else
        {
            Intent intent = new Intent(DriverLoginActivity.this, AboutActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}