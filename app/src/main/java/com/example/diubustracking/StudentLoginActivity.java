package com.example.diubustracking;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class StudentLoginActivity extends AppCompatActivity
{
    EditText mPassword, mStudentID;
    Button mLoginButton;
    String password,id;
    DatabaseHelper mDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_login);
        mStudentID = findViewById(R.id.studentID);
        mPassword = findViewById(R.id.password);

        mLoginButton = findViewById(R.id.login);
        mDatabaseHelper = new DatabaseHelper(this);

        mLoginButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(StudentLoginActivity.this,StudentLocationActivity.class);

                id = mStudentID.getText().toString();
                password = mPassword.getText().toString();
                if(TextUtils.isEmpty(id))
                    mStudentID.setError("Student ID is Mandatory");
                if(TextUtils.isEmpty(password))
                    mPassword.setError("Password is Mandatory");
                if(!(TextUtils.isEmpty(id) && TextUtils.isEmpty(password)))
                {
                    Cursor cursor = mDatabaseHelper .getPasswordData(id);
                    String data = "";
                    if (cursor.moveToFirst()){
                        do{
                            data = cursor.getString(3);
                        }while(cursor.moveToNext());
                    }
                    cursor.close();
                    if(data.equals(password)){
                        Toast.makeText(getApplicationContext(),"Login Successful",Toast.LENGTH_LONG).show();
                        startActivity(intent);
                    }
                }

            }
        }
        );
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

    }
}