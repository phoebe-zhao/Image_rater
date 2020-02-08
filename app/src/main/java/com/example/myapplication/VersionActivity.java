package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.graphics.Bitmap;
import java.net.URL;
import java.net.HttpURLConnection;
import  java.io.*;
import android.graphics.BitmapFactory;
import com.squareup.picasso.Picasso;

public class VersionActivity extends AppCompatActivity{

    private ImageView imageView;
    private String[] images = {"bunny.jpg", "chinchilla.jpg", "doggo.jpg", "fox.jpg", "hamster.jpg",
            "husky.jpg", "kitten.png", "loris.jpg", "puppy.jpg", "sleepy.png"};
    private String webname = "https://www.student.cs.uwaterloo.ca/~cs349/w19/assignments/images/";
    private String[] new_urls = new String[10];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_version);

        // Get the intent that started this activity
        Intent intent = getIntent();
        int index = intent.getIntExtra("index_id", 0);
        Log.e("index is ",Integer.toString(index));

        for(int i = 0;i<10;++i){
            new_urls[i] = webname+images[i];
        }
        SetImage(index);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    void  SetImage(int index){
        imageView = (ImageView) findViewById(R.id.large_image);
        Bitmap my_bm = null;
        Picasso.get().load(new_urls[index]).into(imageView);
    }



}

