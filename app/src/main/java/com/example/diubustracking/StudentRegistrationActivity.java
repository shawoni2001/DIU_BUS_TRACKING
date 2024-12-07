package com.example.diubustracking;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class StudentRegistrationActivity extends AppCompatActivity
{
    EditText mPassword, mStudentName, mStudentID, mEmail;
    Button mRegisterButton;
    String password,name,id,email;
    DatabaseHelper mDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_registration);

        mStudentName = findViewById(R.id.studentName);
        mStudentID = findViewById(R.id.studentID);
        mEmail = findViewById(R.id.studentEmail);
        mPassword = findViewById(R.id.password);

        mRegisterButton = findViewById(R.id.register);
        mDatabaseHelper = new DatabaseHelper(this);

        mRegisterButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(StudentRegistrationActivity.this, StudentLoginActivity.class);

                name = mStudentName.getText().toString();
                id = mStudentID.getText().toString();
                password = mPassword.getText().toString();
                email = mEmail.getText().toString();

                if(TextUtils.isEmpty(name))
                    mStudentName.setError("Name is Mandatory");
                if(TextUtils.isEmpty(id))
                    mStudentID.setError("Phone No. is Mandatory");
                if(TextUtils.isEmpty(password))
                    mPassword.setError("Licence No. is Mandatory");
                if(!(TextUtils.isEmpty(name) && TextUtils.isEmpty(id) && TextUtils.isEmpty(password)))
                {
                    mDatabaseHelper .insertData(name, id, password,email);
                    //Toast.makeText()
                    CharSequence charSequence = "Registration Successful";

                    Toast.makeText(getApplicationContext(),charSequence,Toast.LENGTH_LONG).show();
                    startActivity(intent);
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