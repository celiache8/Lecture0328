package com.example.celiachen.lecture0328;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    Button capturePhoto;

    // when requesting to use camera, the code 1888
    // when requesting to use gallery, the code is 1889
    public static final int CAMERA_REQUEST = 1888;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // When clicking the take a photo button
        // send an intent to request to use the built in camera application

        imageView = findViewById(R.id.imageView);
        capturePhoto = findViewById(R.id.cameraBtn);

        capturePhoto.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                // send an intent
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                // You need to check in MediaStore for the list of codes

                // start activity
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
        });
    }
    // 2 ways to access camera
    // 1. use existing camera app in the phone to take photos
    //    using intent to use other applications
    //      google map, email
    // 2. write your own in-app camera -> snapchat, instagram
    protected void onActivityResult (int requestCode, int resultCode, Intent data){
        // if the request code is camera request
        if (requestCode == CAMERA_REQUEST){
            Bitmap photo = (Bitmap)data.getExtras().get("data");
            imageView.setImageBitmap(photo);
            // if you want, you can save the photo to storage

        }
        // ge the captured photo and display it in the image view

    }

    public void openProximity(View view){
        startActivity(new Intent(MainActivity.this, ProximityActivity.class));
    }
}
