package com.example.wallpaper.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.wallpaper.R;
import com.example.wallpaper.activitys.category;
import com.example.wallpaper.config;

public class category_adapter extends BaseAdapter {

    category category;


    public category_adapter(category category) {
        this.category=category;
    }

    @Override
    public int getCount() {
        return config.category_name.length;
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
        view= LayoutInflater.from(category).inflate(R.layout.category_item,viewGroup,false);

        TextView textView=view.findViewById(R.id.category_text);

        textView.setText(config.category_name[i]);
        return view;
    }
}
