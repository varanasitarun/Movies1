package com.example.user.moviesappstage1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MovieDescription extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_description);
        ImageView iv=findViewById(R.id.imageView);

        TextView textViewCount=findViewById(R.id.rating);
        TextView overview=findViewById(R.id.id_synopsis);
        TextView textViewReleaseDate=findViewById(R.id.release_date);

        Intent intent=getIntent();
        textViewCount.setText(intent.getStringExtra(MainActivity.VOTE_COUNT));
        overview.setText(intent.getStringExtra(MainActivity.SYNOPSIS));
        textViewReleaseDate.setText(intent.getStringExtra(MainActivity.RELEASE_DATE));
        String i1=intent.getStringExtra(MainActivity.BACKDROP_PATH);
        String i2=BuildUrl.buildImage(i1);
        Picasso.with(this).load(i2).into(iv);
        setTitle(intent.getStringExtra(MainActivity.MAIN_TITLE));


    }
}
