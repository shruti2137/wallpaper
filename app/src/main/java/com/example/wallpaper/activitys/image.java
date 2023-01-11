package com.example.wallpaper.activitys;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;


import com.example.wallpaper.R;
import com.example.wallpaper.config;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class image extends AppCompatActivity {

    ImageView image,prev,download,share,next;
    ActionBar actionBar;
    int pos;
    int[] img;
    String[] name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image);

        image=findViewById(R.id.image);
        download=findViewById(R.id.image_download);
        share=findViewById(R.id.image_share);
        prev=findViewById(R.id.image_prev);
        next=findViewById(R.id.image_next);

        pos=getIntent().getIntExtra("position",0);
        img=getIntent().getIntArrayExtra("image");
        name=getIntent().getStringArrayExtra("name");

        actionBar=getSupportActionBar();
        actionBar.setTitle(name[pos]);

        image.setImageResource(img[pos]);

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pos>0)
                {
                    pos--;
                    image.setImageResource(img[pos]);
                    actionBar=getSupportActionBar();
                    actionBar.setTitle(name[pos]);
                }
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pos< config.life_image.length-1)
                {
                    pos++;
                    image.setImageResource(img[pos]);
                    actionBar=getSupportActionBar();
                    actionBar.setTitle(name[pos]);
                }
            }
        });
        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap icon =getBitmapFromView(image);
                System.out.println("bitmap======>"+icon);

                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                icon.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
                int num=new Random().nextInt(2000);
                File f = new File(config.file.getAbsolutePath()  + "/temporary_file"+num+".jpg");
                try {
                    f.createNewFile();
                    FileOutputStream fo = new FileOutputStream(f);
                    fo.write(bytes.toByteArray());
                    Toast.makeText(image.this, "file downloaded", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap icon = getBitmapFromView(image);
                System.out.println("bitmap======>"+icon);
                Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("image/jpeg");
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                icon.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
                int num=new Random().nextInt(2000);
                File f = new File(config.file.getAbsolutePath()  + "/temporary_file"+num+".jpg");
                try {
                    f.createNewFile();
                    FileOutputStream fo = new FileOutputStream(f);
                    fo.write(bytes.toByteArray());
                    Toast.makeText(image.this, "file downloaded", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    share.putExtra(Intent.EXTRA_STREAM, Uri.parse(MediaStore.Images.Media.insertImage(getContentResolver(), f.getAbsolutePath(),"img","Identified image")));
                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                startActivity(Intent.createChooser(share, "Share Image"));
            }
        });

    }
    public static Bitmap getBitmapFromView(View view) {
        Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(),Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(returnedBitmap);
        Drawable bgDrawable =view.getBackground();
        if (bgDrawable!=null)
            bgDrawable.draw(canvas);
        else
            canvas.drawColor(Color.WHITE);
        view.draw(canvas);
        return returnedBitmap;
    }
}