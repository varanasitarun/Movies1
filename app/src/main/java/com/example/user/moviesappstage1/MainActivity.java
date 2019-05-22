package com.example.user.moviesappstage1;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.PersistableBundle;
import android.support.v4.internal.view.SupportMenuItem;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
public class MainActivity extends AppCompatActivity {
    private TextView error_msg;
    private ProgressBar progressBar;

    private RecyclerView recyclerView;
    private RecyclerAdapter recyclerAdapter;
    public static final String MAIN_TITLE="original_title";
    public static final String BACKDROP_PATH="backdrop_path";
    public static final String VOTE_COUNT="vote_average";
    public static final String RELEASE_DATE="release_date";
    public static final String SYNOPSIS="overview";
    public String jsondata;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recyclerView);
        error_msg=findViewById(R.id.error_msg_tv);
        progressBar=findViewById(R.id.progress_bar);

        recyclerAdapter=new RecyclerAdapter(this);
        GridLayoutManager g=new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(g);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(recyclerAdapter);
        if(savedInstanceState!=null){
            jsondata=savedInstanceState.getString("KEY");
            try {
                String[] strings=JsonInformation.getData(jsondata);
                recyclerAdapter.fitImages(strings);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        else if (!networkinfo())
        {
            recyclerView.setVisibility(View.INVISIBLE);
        }
        else
        {
            call("popular");
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if(jsondata!=null){
            outState.putString("KEY",jsondata);
        }
    }

    public Boolean networkinfo()
    {
        ConnectivityManager connectivityManager=(ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo()!=null;
    }
    private void call(String item)
    {
        URL url=BuildUrl.buildMovieUrl(item);
        new MovieTask().execute(url);
    }
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_items,menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id=item.getItemId();
        switch (id)
        {
            case R.id.popular_id:
                recyclerAdapter.fitImages(null);
                call("popular");
                return true;
            case R.id.top_rated:
                recyclerAdapter.fitImages(null);
                call("top_rated");
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public class MovieTask extends AsyncTask<URL,Void,String>
    {
        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);

        }
        @Override
        protected String doInBackground(URL... urls) {
            URL url=urls[0];
            try {
                HttpsURLConnection httpsURLConnection=(HttpsURLConnection) url.openConnection();
                InputStream inputStream=httpsURLConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new BufferedReader(new InputStreamReader(inputStream)));
                StringBuilder stringBuilder=new StringBuilder();
                String line="";
                while ((line=bufferedReader.readLine())!=null)
                {
                    stringBuilder.append(line);
                }
                return stringBuilder.toString();

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(String s)
        {
            super.onPostExecute(s);
            progressBar.setVisibility(View.INVISIBLE);
            if(s==null)
            {
                recyclerView.setVisibility(View.INVISIBLE);
                error_msg.setVisibility(View.VISIBLE);
            }
            else {
                error_msg.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.VISIBLE);
                try {
                   String[] strings=JsonInformation.getData(s);
                   jsondata=s;
                   recyclerAdapter.fitImages(strings);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}


 
