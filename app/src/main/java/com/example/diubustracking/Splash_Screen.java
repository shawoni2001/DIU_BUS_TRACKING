package com.example.diubustracking;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Splash_Screen extends AppCompatActivity
{
    private static int SPLASH_SCREEN_DURATION = 2000;
    ImageView mImageView;

    TextView logo,slogan;
    DatabaseHelper mDatabaseHelper;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splash_screen);
        mDatabaseHelper = new DatabaseHelper(this);

        Cursor res = mDatabaseHelper.getData("1");
        if(res.moveToFirst())
            name = res.getString(1);
        mImageView = findViewById(R.id.imageview);
        logo = findViewById(R.id.text);

        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                if(name == null)
                {
                        Intent intent = new Intent(Splash_Screen.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                }
                else
                {
                    Intent intent = new Intent(Splash_Screen.this, MainActivity.class);
                    startActivity(intent);
                    finish();

                 }
            }
        },SPLASH_SCREEN_DURATION);
    }
}