package com.example.usuario.userprofile;

import android.renderscript.ScriptGroup;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;

import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class UserProfileActivity extends AppCompatActivity {

    private UserProfile userprofile;
    private Gson gson;
    private TextView nameview;
    private TextView handleview;
    private TextView followersview;
    private TextView followingview;
    private TextView aboutview;
    private ImageView photoface;
    private ImageView background;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        gson = new Gson();

        try{
            InputStream input_stream = getAssets().open("JohnDoe.json");
            InputStreamReader streamReader = new InputStreamReader(input_stream);
            userprofile = gson.fromJson(streamReader,UserProfile.class);
        }catch (IOException e){
            Toast.makeText(this,"Error",Toast.LENGTH_SHORT).show();
        }


        nameview = findViewById(R.id.nameview);
        handleview = findViewById(R.id.handleview);
        followersview = findViewById(R.id.followersview);
        followingview = findViewById(R.id.followingview);
        aboutview = findViewById(R.id.aboutview);
        photoface = findViewById(R.id.imageView5);
        background = findViewById(R.id.imageView4);

        Glide.with(this).load("file:///android_asset/UserProfile-background.jpg").into(background);
        Glide.with(this).load("file:///android_asset/foto.png").apply(RequestOptions.circleCropTransform()).into(photoface);



      UpdateUserProfile();


    }

    private void UpdateUserProfile()
    {
        nameview.setText(userprofile.getName());
        handleview.setText(userprofile.getHandle());
        followersview.setText(userprofile.getFollowers());
        followingview.setText(userprofile.getFollowing());
        aboutview.setText(userprofile.getAbout());
    }
}
