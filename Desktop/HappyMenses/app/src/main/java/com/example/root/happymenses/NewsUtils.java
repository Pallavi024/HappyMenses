package com.example.root.happymenses;

/**
 * Created by root on 13/4/19.
 */

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class NewsUtils {

    public static final String LOG_TAG = NewsUtils.class.getSimpleName();

    /** Sample JSON response for a USGS query */
    public static ArrayList<News> fetchNewsDetails(String requestUrl){

        Log.v("NewsActivity","Fetch News details");
        URL url = createUrl(requestUrl);

        //Perform http request and recieve json response back

        String jsonResponse = null;
        try {
            jsonResponse=makeHttpRequest(url);
        }
        catch (IOException e){
            Log.e(LOG_TAG, "Error closing input stream", e);
        }
        // Extract relevant fields from the JSON response and create a list of {@link Earthquake}s
        ArrayList<News> result = extractFeatureFromJson(jsonResponse);
        return  result;
    }

    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Error with creating URL ", e);
        }
        return url;
    }

    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        // If the URL is null, then return early.
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // If the request was successful (response code 200),
            // then read the input stream and parse the response.
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the earthquake JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }
    /**
     * Create a private constructor because no one should ever create a {@link NewsUtils} object.
     * This class is only meant to hold static variables and methods, which can be accessed
     * directly from the class name QueryUtils (and an object instance of QueryUtils is not needed).
     */
    private NewsUtils() {
    }

    /**
     * Return a list of {@link News} objects that has been built up from
     * parsing a JSON response.
     */
    public static ArrayList<News> extractFeatureFromJson(String newsJSON) {

        // If the JSON string is empty or null, then return early.
        if (TextUtils.isEmpty(newsJSON)) {
            return null;
        }
        // Create an empty ArrayList that we can start adding news to
        ArrayList<News> data = new ArrayList<>();

        // Try to parse the SAMPLE_JSON_RESPONSE. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {

            JSONObject baseJsonResponse = new JSONObject(newsJSON);
            JSONObject response = baseJsonResponse.getJSONObject("response");
            JSONArray results = response.getJSONArray("results");

            for (int i = 0; i < results.length(); i++) {

                JSONObject currentResult = results.getJSONObject(i);

                String Title = currentResult.getString("webTitle");
                String category = currentResult.getString("sectionName");
                String date = currentResult.getString("webPublicationDate");
                String url = currentResult.getString("webUrl");
                String author="";
                try{
                    JSONArray tagsArray = currentResult.getJSONArray("tags");
                    JSONObject firstTag = tagsArray.getJSONObject(0);
                    author = firstTag.getString("webTitle");
                }
                catch(Exception e){
                    // When author name is not available in JSON Response.
                    author = " Author is not available";
                }


                News news = new News(category,date,Title,url,author);

                data.add(news);


            }


            // TODO: Parse the response given by the SAMPLE_JSON_RESPONSE string and
            // build up a list of news objects with the corresponding data.

        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("NewsUtils", "Problem parsing the earthquake JSON results", e);
        }

        // Return the list of news
        return data;

    }
}

