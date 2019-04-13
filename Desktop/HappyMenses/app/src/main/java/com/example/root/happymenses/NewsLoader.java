package com.example.root.happymenses;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.List;

/**
 * Created by root on 6/10/18.
 */

public class NewsLoader extends AsyncTaskLoader <List<News>>{

    private static final String LOG_TAG = NewsLoader.class.getName();
    private String mUrl;

    public NewsLoader(Context context, String RequestUrl) {
        super(context);
        mUrl=RequestUrl;
    }

    @Override
    protected void onStartLoading() {
        Log.v("NewsActivity","Start loading");
        super.onStartLoading();
        forceLoad();
        Log.v("NewsActivity","Force loading");
    }

    @Override
    public List<News> loadInBackground() {
        Log.v("NewsLoader","Load in background method to make request and parse json");
        if(mUrl==null){
            return null;
        }
        List<News> data= NewsUtils.fetchNewsDetails(mUrl);
        return data;
    }
}

