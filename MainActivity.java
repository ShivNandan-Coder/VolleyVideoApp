package com.shivnandan.duplicatvideoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static  String JSON_URL="http://shivnandan123.000webhostapp.com//api.php?All_videos";
    List<Video>moveList;//all_videos
    RecyclerView recyclerView;//videoList
    VideoAdapter adaptery;//adapter
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        moveList = new ArrayList<>();
        recyclerView = findViewById(R.id.videoList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adaptery = new VideoAdapter(this,moveList);
        recyclerView.setAdapter(adaptery);
        GetData  getData=new GetData();
        getData.execute();
    }

    public  class  GetData extends AsyncTask<String,String,String> {
        @Override
        protected String doInBackground(String... strings) {
            String current="";
            try {
                URL url;
                HttpURLConnection urlConnection=null;
                try {
                    url = new URL(JSON_URL);
                    urlConnection= (HttpURLConnection) url.openConnection();
                    InputStream is=urlConnection.getInputStream();
                    InputStreamReader isr=new InputStreamReader(is);
                    int data = isr.read();
                    while (data!=-1)
                    {
                        current += (char)data;
                        data = isr.read();
                    }
                    return  current;
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    if(urlConnection!=null){
                        urlConnection.disconnect();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return current;
        }
        @Override
        protected void onPostExecute(String s) {
            try {
                JSONObject jsonObject = new JSONObject(s);
                JSONArray jsonArray = jsonObject.getJSONArray("ALL_IN_ONE_VIDEO");
                for(int i=0;i<jsonArray.length();i++){
                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                    Video model = new Video();
                    //model.setId(jsonObject1.getString("id"));
                    model.setVideo_title(jsonObject1.getString("video_title"));
                    model.setVideo_thumbnail_s(jsonObject1.getString("video_thumbnail_s"));
                    model.setVideo_url(jsonObject1.getString("video_url"));
                    //String videoUrl=jsonArray.getJSONObject(i).getString("video_url");
                    // String video = jsonArray.getString(Integer.parseInt("video_url"));
                    moveList.add(model);
                    adaptery.notifyDataSetChanged();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            putDataIntoRecyclerView(moveList);
        }
    }
    private void putDataIntoRecyclerView(List<Video>moveList){
        VideoAdapter adaptery = new VideoAdapter(this,moveList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adaptery);
    }
}