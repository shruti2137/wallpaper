package com.example.wallpaper.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.wallpaper.R;
import com.example.wallpaper.activitys.category_diteil;

public class categoryditeil_adapter extends BaseAdapter {

    category_diteil category_diteil;
    String[] name;
    int[] image;
    public categoryditeil_adapter(category_diteil category_diteil ,String[] name , int[] image) {
        this.category_diteil=category_diteil;
        this.name=name;
        this.image=image;
    }



    @Override
    public int getCount() {
        return image.length;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view= LayoutInflater.from(category_diteil).inflate(R.layout.categoryditeil_item,viewGroup,false);

        ImageView imageView=view.findViewById(R.id.categoryditeil_image);
        imageView.setImageResource(image[i]);
        return view;
    }
}
