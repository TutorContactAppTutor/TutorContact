package com.example.drmsoul.appestudiante;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

/**
 * Created by nathali on 05/07/16.
 */
public class ImageAdapter extends BaseAdapter {
    private Context context;

    public Integer[] images = {
            R.drawable.logotutorcontact
    };

    public ImageAdapter(Context c){
        context=c;
    }

    public int getCount(){
        return images.length;
    }

    public Object getItem(int position){
        return images[position];
    }

    public long getItemId(int position){
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        ImageView imageView = new ImageView(context);
        imageView.setImageResource(images[position]);
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        imageView.setLayoutParams(new GridView.LayoutParams(150,150));
        return imageView;
    }
}
