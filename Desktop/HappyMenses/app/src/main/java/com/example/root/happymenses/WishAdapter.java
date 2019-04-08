package com.example.root.happymenses;

/**
 * Created by root on 7/4/19.
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.root.happymenses.data.Fragrance;
import com.example.root.happymenses.data.FragranceContract;
import com.example.root.happymenses.data.Wish;
import com.example.root.happymenses.data.FragranceContract;

import static com.example.root.happymenses.DetailActivity.FRAGRANCE_DESCRIPTION;
import static com.example.root.happymenses.DetailActivity.FRAGRANCE_ID;
import static com.example.root.happymenses.DetailActivity.FRAGRANCE_IMAGE;
import static com.example.root.happymenses.DetailActivity.FRAGRANCE_NAME;
import static com.example.root.happymenses.DetailActivity.FRAGRANCE_PRICE;
import static com.example.root.happymenses.DetailActivity.FRAGRANCE_RATING;

/**
 * Created by delaroy on 10/1/17.
 */

public class WishAdapter extends RecyclerView.Adapter<WishAdapter.ViewHolder> {

    Cursor dataCursor;
    Context context;
    int id;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name, userrating, description, price;
        public ImageView thumbnail;

        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.title);
            //  userrating = (TextView) itemView.findViewById(R.id.userrating);
            thumbnail = (ImageView) itemView.findViewById(R.id.thumbnail);

            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION){
                        Intent intent = new Intent(context, DetailActivity.class);
                        intent.putExtra(FRAGRANCE_ID, getItem(pos).id);
                        intent.putExtra(FRAGRANCE_NAME, getItem(pos).name);
                        intent.putExtra(FRAGRANCE_DESCRIPTION, getItem(pos).description);
                        intent.putExtra(FRAGRANCE_PRICE, getItem(pos).price);
                        intent.putExtra(FRAGRANCE_IMAGE, getItem(pos).imageUrl);
                        intent.putExtra(FRAGRANCE_RATING, getItem(pos).userRating);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }
                }
            });
        }
    }

    public WishAdapter(Activity mContext, Cursor cursor) {
        dataCursor = cursor;
        context = mContext;
    }


    @Override
    public WishAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View cardview = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragrance_cart, parent, false);
        return new WishAdapter.ViewHolder(cardview);
    }

    @Override
    public void onBindViewHolder(WishAdapter.ViewHolder holder, int position) {
        dataCursor.moveToPosition(position);

        id = dataCursor.getInt(dataCursor.getColumnIndex(FragranceContract.FragranceEntry._WISHID));
        String mName = dataCursor.getString(dataCursor.getColumnIndex(FragranceContract.FragranceEntry.COLUMN_WISH_NAME));
        String mDescription = dataCursor.getString(dataCursor.getColumnIndex(FragranceContract.FragranceEntry.COLUMN_WISH_DESCRIPTION));
        String mImageUrl = dataCursor.getString(dataCursor.getColumnIndex(FragranceContract.FragranceEntry.COLUMN_WISH_IMAGE));
        int mPrice = dataCursor.getInt(dataCursor.getColumnIndex(FragranceContract.FragranceEntry.COLUMN_WISH_PRICE));
        int mUserrating = dataCursor.getInt(dataCursor.getColumnIndex(FragranceContract.FragranceEntry.COLUMN_WISH_USERRATING));


        holder.name.setText(mName);

        String poster = "http://boombox.ng/images/fragrance/" + mImageUrl;

        Glide.with(context)
                .load(poster)
                .placeholder(R.drawable.load)
                .into(holder.thumbnail);


    }

    public Cursor swapCursor(Cursor cursor) {
        if (dataCursor == cursor) {
            return null;
        }
        Cursor oldCursor = dataCursor;
        this.dataCursor = cursor;
        if (cursor != null) {
            this.notifyDataSetChanged();
        }
        return oldCursor;
    }

    @Override
    public int getItemCount() {
        return (dataCursor == null) ? 0 : dataCursor.getCount();
    }

    public Wish getItem(int position) {
        if (position < 0 || position >= getItemCount()) {
            throw new IllegalArgumentException("Item position is out of adapter's range");
        } else if (dataCursor.moveToPosition(position)) {
            return new Wish(dataCursor);
        }
        return null;
    }
}

