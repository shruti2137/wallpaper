package com.example.wallpaper.activitys;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.wallpaper.R;
import com.example.wallpaper.adapters.categoryditeil_adapter;
import com.example.wallpaper.config;

public class category_diteil extends AppCompatActivity {

    GridView gridView;
    int pos;
    int[] image;
    String[] name;
    ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_diteil);

        gridView=findViewById(R.id.categoryditeil_grid);

        pos=getIntent().getIntExtra("position",0);

        actionBar=getSupportActionBar();
        actionBar.setTitle(config.category_name[pos]);

        if (pos==0)
        {
            name = config.cityscapes_name;
            image = config.cityscapes_image;

        }
        if (pos==1)
        {
            name = config.seascapes_name;
            image = config.seascapes_image;

        }
        if (pos==2)
        {
            name = config.landscape_name;
            image = config.landscape_image;

        }
        if (pos==3)
        {
            name = config.earth_name;
            image = config.earth_image;
        }
        if (pos==4)
        {
            name = config.life_name;
            image = config.life_image;

        }
        if (pos==5)
        {
            name = config.art_name;
            image = config.art_image;
        }

        categoryditeil_adapter adapter = new categoryditeil_adapter(this,name,image);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(category_diteil.this,image.class);
                intent.putExtra("position",i);
                intent.putExtra("image",image);
                intent.putExtra("name",name);
                startActivity(intent);
            }
        });
    }
}