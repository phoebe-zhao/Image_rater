package com.example.myapplication;

import android.content.res.Configuration;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import java.util.*;
import android.util.Log;


public class MainActivity extends AppCompatActivity {
    private Model model;
    private boolean star_change = false;
    private ActionBar actionBar;
    private int selected_star = 0;

    RecyclerView recyclerview;
    RecyclerView.LayoutManager lm;
    ImageAdapter imageadapter;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        model = new Model();
        imageadapter = new ImageAdapter(this, model);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.top_toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();

        //display activity title/subtitle.
        actionBar.setDisplayShowTitleEnabled(true);

        recyclerview = findViewById(R.id.image_viewer);
        RecyclerView.LayoutManager view_lm;

        //set view direction
        if(getResources().getConfiguration().orientation== Configuration.ORIENTATION_PORTRAIT){
            actionBar.setDisplayShowTitleEnabled(false);
            view_lm = new LinearLayoutManager(this);
        } else {
            actionBar.setDisplayShowHomeEnabled(true);
            //2 cols
            view_lm = new GridLayoutManager(this,2);
        }
        lm = view_lm;

        //start loading images
        model.display_images();

        recyclerview.setLayoutManager(lm);
        recyclerview.setAdapter(imageadapter);

        //star_change


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        invalidateOptionsMenu();
        return true;
    }

    //done
    @Override
    public void onConfigurationChanged(Configuration newconfig){
        super.onConfigurationChanged(newconfig);
        if(newconfig.orientation==Configuration.ORIENTATION_LANDSCAPE){

            recyclerview.setLayoutManager(new GridLayoutManager(this,2));
        } else {
            recyclerview.setLayoutManager(new LinearLayoutManager(this));
        }

    }


    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if(star_change){
            int temp  = selected_star;
            Log.e("err",Integer.toString(temp));
            //selected_star = 0;
            star_change = false;
            /*menu.findItem(R.id.rating_filter_5).setIcon(R.drawable.star);
            menu.findItem(R.id.rating_filter_4).setIcon(R.drawable.star);
            menu.findItem(R.id.rating_filter_3).setIcon(R.drawable.star);
            menu.findItem(R.id.rating_filter_2).setIcon(R.drawable.star);
            menu.findItem(R.id.rating_filter_1).setIcon(R.drawable.star);*/


            Log.e("2nd err",Integer.toString(temp));
            switch (temp){
                case 5:
                    menu.findItem(R.id.star5).setIcon(R.drawable.star_filled);
                case 4:
                    menu.findItem(R.id.star4).setIcon(R.drawable.star_filled);
                case 3:
                    menu.findItem(R.id.star3).setIcon(R.drawable.star_filled);
                case 2:
                    menu.findItem(R.id.star2).setIcon(R.drawable.star_filled);
                case 1:
                    menu.findItem(R.id.star1).setIcon(R.drawable.star_filled);
            }

        }
        return super.onPrepareOptionsMenu(menu);
    }


    @Override
    public boolean  onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.download:
                model.reload_all_images();
                return true;

            case R.id.clearimage:
                model.erase_images();
                return true;

            case R.id.star1:
                // item.setIcon(R.drawable.star_filled);
                selected_star = 1;
                star_change = true;
                model.set_filter(1);
                return true;

            case R.id.star2:
                selected_star = 2;
                star_change = true;
                model.set_filter(2);
                return true;

            case R.id.star3:
                selected_star = 3;
                star_change = true;
                model.set_filter(3);
                return true;

            case R.id.star4:
                selected_star = 4;
                star_change = true;
                model.set_filter(4);
                return true;

            case R.id.star5:
                selected_star = 5;
                star_change = true;
                model.set_filter(5);
                return true;


            default:
                return super.onOptionsItemSelected(item);
        }




    }


}
