package com.example.root.happymenses;

/**
 * Created by root on 13/4/19.
 */

import android.widget.ArrayAdapter;

/**
 * Created by root on 6/10/18.
 */

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.graphics.drawable.GradientDrawable;

import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.DecimalFormat;


/**
 * Created by root on 19/8/18.
 */

public class NewsAdapter extends ArrayAdapter <News>{

    public NewsAdapter(Activity context, ArrayList<News> data) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0,data);

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if there is an existing list item view (called convertView) that we can reuse,
        // otherwise, if convertView is null, then inflate a new list item layout.
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.activity_news_list_item, parent, false);
        }

        // Find the earthquake at the given position in the list of earthquakes
        News currentNews = getItem(position);


        String output = currentNews.getSectionName();

        // Find the TextView with view ID magnitude
        TextView magnitudeView = (TextView) listItemView.findViewById(R.id.SectionName);
        // Display the magnitude of the current earthquake in that TextView
        magnitudeView.setText(output);

        //We have done the below because when we parse JSON in Android the format of location
        //was 88 KM N of Yelizovo,Russia but we want to split this big string into two strings for this
        //we use split() function and after that we display these 2 splited parts as 2 textviews

        String PublicationDate = currentNews.getwebPublicationDate();
        // Find the TextView with view ID location
        TextView date = (TextView) listItemView.findViewById(R.id.webPublicationDate);
        // Display the location of the current earthquake in that TextView
        date.setText(PublicationDate);

        String TITLE =currentNews.getwebTitle();
        // Find the TextView with view ID location
        TextView title = (TextView) listItemView.findViewById(R.id.webTitle);
        // Display the location of the current earthquake in that TextView
        title.setText(TITLE);

        String author = currentNews.getauthor();
        TextView Author=(TextView) listItemView.findViewById(R.id.author);
        Author.setText(author);

        // Return the list item view that is now showing the appropriate data
        return listItemView;
    }



}



