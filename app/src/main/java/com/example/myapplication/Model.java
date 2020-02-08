package com.example.myapplication;

import java.util.*;

import android.graphics.Bitmap;
import android.view.ViewGroup;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;
import android.view.Menu;
import android.view.WindowManager;
import android.util.Log;

public class Model {
    private String[] newurls;
    private int image_waiting;
    private ImageAdapter image_adapter;
    private Bitmap[] bmaps;
    private int[] imageratings;
    private String webname = "https://www.student.cs.uwaterloo.ca/~cs349/w19/assignments/images/";
    private List<Integer> image_presenting = new ArrayList<>();


    public static final String TAG = "YOUR-TAG-NAME";
    private String[] images = {"bunny.jpg", "chinchilla.jpg", "doggo.jpg", "fox.jpg", "hamster.jpg",
            "husky.jpg", "kitten.png", "loris.jpg", "puppy.jpg", "sleepy.png"};


    private int current_rating = 0;

    int get_my_image_index(int image){
        return image_presenting.get(image);
    }

    Bitmap get_my_image(int image) {
        int temp = image_presenting.get(image);
        Bitmap mybitmap = null;
        try {
            mybitmap = bmaps[temp];
        } catch (Exception e) {
            Log.e("GETIMAGE", "exception", e);
        }

        return mybitmap;
    }

    int get_my_rating(int rate) {
        int temp = image_presenting.get(rate);
        int rating = 0;
        try {
            rating = imageratings[temp];
        } catch (Exception e) {
            Log.e("GETRATING", "exception", e);
        }
        return rating;
    }


    //ctor
    Model() {
        image_waiting = 10;
        newurls = new String[10];
        bmaps = new Bitmap[10];
        imageratings = new int[10];
        for (int i = 0; i < 10; ++i) {
            newurls[i] = webname + images[i];
            imageratings[i] = 0;
        }
    }

    void display_images() {
        for (int i = 0; i < 10; ++i) {
            new DownloadImageTask(i, this).execute(newurls[i]);
        }
    }

    void notify_load_one_image(int i, Bitmap bm) {
        bmaps[i] = bm;
        image_waiting--;
        if (image_waiting <= 0) {
            notify_load_complete();
        }
    }

    void reload_all_images() {
        erase_images();
        display_images();
    }

    Bitmap access_bitmap(int index) {
        Bitmap temp = get_my_image(index);
        return temp;
    }


    void setImageAdapter(ImageAdapter imgadapter) {
        image_adapter = imgadapter;
        imgadapter.setitem_num(image_presenting.size());

    }


    void set_image_rating(int index, int rating){
        int image = image_presenting.get(index);
        imageratings[image] = rating;
        if(current_rating!=0){
            set_filter(current_rating);
        }
    }


    void notify_load_complete() {
        for (int i = 0; i < 10; i++) {
            image_presenting.add(i);
        }
        image_adapter.setitem_num(10);
        image_adapter.notifyDataSetChanged();
    }

    void erase_images(){
        image_presenting.clear();
        current_rating = 0;
        imageratings = new int[10];
        for (int i = 0;i < 10;++i) {
            bmaps[i] = null;
        }
        image_adapter.setitem_num(0);
        image_adapter.notifyDataSetChanged();
    }




    void set_filter(int filter) {
        image_presenting.clear();
        current_rating = filter;

        for (int i = 0; i < 10;i++) {
            Log.e("rating is",Integer.toString(imageratings[i]));
            if (imageratings[i] >= filter) {
                image_presenting.add(i);
            }
            Log.e("i is",Integer.toString(i));
        }
        Log.e("size is",Integer.toString(image_presenting.size()));
        image_adapter.setitem_num(image_presenting.size());
        image_adapter.notifyDataSetChanged();
    }

}


