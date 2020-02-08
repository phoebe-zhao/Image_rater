package com.example.myapplication;
import android.os.AsyncTask;

import java.io.InputStream;
import java.lang.*;

import android.view.Display;
import  android.widget.ImageView;
import java.net.URL;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.annotation.SuppressLint;
import  android.util.Log;
import java.util.*;

public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
    private Model mymodel;
    int index;

    public DownloadImageTask(int i,Model m) {
        index = i;
        mymodel= m;
    }

    protected Bitmap doInBackground(String... urls) {
        Bitmap bm = null;
        String urldisplay = urls[0];

        try {
            InputStream in = new java.net.URL(urldisplay).openStream();
            bm = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return bm;
    }

    @SuppressLint("NewApi")
    protected void onPostExecute(Bitmap result) {
        super.onPostExecute(result);
        mymodel.notify_load_one_image(index,result);

    }
}
