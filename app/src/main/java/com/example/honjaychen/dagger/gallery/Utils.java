package com.example.honjaychen.dagger.gallery;

import android.content.Context;
import android.content.res.AssetManager;

import android.util.Log;

import com.example.honjaychen.dagger.gallery.gallery.Image;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by honjayChen on 2017/4/29.
 */

public class Utils {

    private static final String TAG = "Utils";

    public static List<Image> loadImages(Context context){
        try{
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            JSONArray array = new JSONArray(loadJSONFromAsset(context, "images.json"));
            List<Image> imageList = new ArrayList<>();
            for(int i=0;i<array.length();i++){
                Image image = gson.fromJson(array.getString(i), Image.class);
                imageList.add(image);
            }
            return imageList;
        }catch (Exception e){
            Log.d(TAG,"seedGames parseException " + e);
            e.printStackTrace();
            return null;
        }
    }

    private static String loadJSONFromAsset(Context context, String jsonFileName) {
        String json = null;
        InputStream is=null;
        try {
            AssetManager manager = context.getAssets();
            Log.d(TAG,"path "+jsonFileName);
            is = manager.open(jsonFileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}