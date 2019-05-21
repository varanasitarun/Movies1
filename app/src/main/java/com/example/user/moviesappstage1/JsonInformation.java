package com.example.user.moviesappstage1;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonInformation {
    private static String Title;
    private static String backdroppath;
    private static String image;
    private static String overview;
    private static String voteAverage;
    private static String releaseDate;
    private static String RESULTS="results";
    private static String POSTER_PATH="poster_path";


    public static JSONArray jsonArray;
    static String[] getData(String json) throws JSONException
    {
        JSONObject jsonObject=new JSONObject(json);
        if(jsonObject.has(RESULTS))
        {
            jsonArray=jsonObject.getJSONArray(RESULTS);
            String[] images=new String[jsonArray.length()];
            for(int i=0;i<jsonArray.length();i++)
            {
                JSONObject jsonObject1=jsonArray.getJSONObject(i);
                images[i]=jsonObject1.getString(POSTER_PATH);
            }
            return images;
        }
        return null;
    }
    public static void sortData(int position)
    {
        JSONObject jsonObject=null;
        try {
            {
                jsonObject=jsonArray.getJSONObject(position);
                Title=jsonObject.getString(MainActivity.MAIN_TITLE);
                backdroppath=jsonObject.getString(MainActivity.BACKDROP_PATH);
                image=jsonObject.getString(POSTER_PATH);
                overview=jsonObject.getString(MainActivity.SYNOPSIS);
                voteAverage=jsonObject.getString(MainActivity.VOTE_COUNT);
                releaseDate=jsonObject.getString(MainActivity.RELEASE_DATE);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }



    public static String getTitle() {
        return Title;
    }

    public static void setTitle(String title) {
        Title = title;
    }

    public static String getBackdroppath() {
        return backdroppath;
    }

    public static void setBackdroppath(String backdroppath) {
        JsonInformation.backdroppath = backdroppath;
    }

    public static String getImage() {
        return image;
    }

    public static void setImage(String image) {
        JsonInformation.image = image;
    }

    public static String getOverview() {
        return overview;
    }

    public static void setOverview(String overview) {
        JsonInformation.overview = overview;
    }

    public static String getVoteAverage() {
        return voteAverage;
    }

    public static void setVoteAverage(String voteAverage) {
        JsonInformation.voteAverage = voteAverage;
    }

    public static String getReleaseDate() {
        return releaseDate;
    }

    public static void setReleaseDate(String releaseDate) {
        JsonInformation.releaseDate = releaseDate;
    }



}
