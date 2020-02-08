package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.content.Intent;
import java.io.ByteArrayOutputStream;
import android.graphics.Bitmap;
import android.util.Base64;


public class ImageAdapter extends RecyclerView.Adapter {

    private Model model;
    private Bitmap current_img;
    private int item_num = 0;
    private int img_index;
    private Context my_context;


    ImageAdapter(Context c, Model m){
        model = m;
        current_img = null;
        my_context = c;
        model.setImageAdapter(this);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(my_context).inflate(R.layout.image_card, parent, false);
        return new ImageViewHolder(itemView);
    }


    public String BitMapToString(Bitmap bitmap){
        ByteArrayOutputStream baos=new  ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100, baos);
        byte [] b=baos.toByteArray();
        String temp=Base64.encodeToString(b, Base64.DEFAULT);
        return temp;
    }

    void setitem_num(int item){
        item_num = item;
    }

    @Override
    public int getItemCount() {
        return item_num;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewholder, int index) {
        ImageViewHolder image_holder = (ImageViewHolder) viewholder;
        image_holder.imageView.setImageBitmap(model.get_my_image(index));
        image_holder.ratingbar.setRating(model.get_my_rating(index));
    }



    public static Context mycontext;

    public class ImageViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        RatingBar ratingbar;
        View myview;

        ImageViewHolder(View itemview){
            super(itemview);
            myview = itemView;
            imageView = itemview.findViewById(R.id.img);

            imageView.setOnClickListener(new ImageView.OnClickListener(){
                @Override
                public  void onClick(View v){
                    mycontext = v.getContext();
                    img_index = model.get_my_image_index(getAdapterPosition());
                    Intent intent = new Intent(my_context,VersionActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                    intent.putExtra("index_id", img_index);
                    Log.e("index is ",Integer.toString(img_index));
                    my_context.startActivity(intent);
                }
            });

            ratingbar = itemview.findViewById(R.id.ratingBar);
            ratingbar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                @Override
                public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                    if(fromUser){
                        model.set_image_rating(getLayoutPosition(), (int)rating);
                    }
                }
            });



        }
    }






}

