package com.yingying.searchapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Yingying Xia on 2016/4/17.
 */
class adminDeleteAdapter extends BaseAdapter{

    private Context context;

    private LayoutInflater inflater;
    ArrayList<ratingPost> reviewPosts;

    private class ViewHolder{
        ImageView image;
        TextView date;
        TextView resName;
        RatingBar rating;
        TextView reviewContent;

    }

    public adminDeleteAdapter(Context context,ArrayList<ratingPost> contents) {
        //    super();

        this.context = context;
        inflater = LayoutInflater.from(context);
        this.reviewPosts=contents;

        //  super(context,R.layout.single_review,contents);
    }


    @Override
    public int getCount() {
        return reviewPosts.size();
    }

    @Override
    public ratingPost getItem(int position) {
        return reviewPosts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {


        ViewHolder postHolder = null;
        if(convertView==null){
            postHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.profile_single_review,null);
           // convertView.setClickable(true);
          //  convertView.setFocusable(false);
            postHolder.date = (TextView) convertView.findViewById(R.id.reviewHistoryPostDate);
            postHolder.resName = (TextView) convertView.findViewById(R.id.reviewResName);
            postHolder.reviewContent = (TextView) convertView.findViewById(R.id.reviewPostHistoryContent);
            postHolder.image = (ImageView) convertView.findViewById(R.id.reviewHistoryIcon);
            postHolder.rating = (RatingBar) convertView.findViewById(R.id.reviewHistoryRatingBar);
            convertView.setTag(postHolder);
        }else{
            postHolder = (ViewHolder)convertView.getTag();
        }

        postHolder.date.setText(reviewPosts.get(position).postdate);
        postHolder.image.setImageResource(R.drawable.cat);
        postHolder.resName.setText(reviewPosts.get(position).resName);
        postHolder.rating.setRating(Float.valueOf(reviewPosts.get(position).rating));
        postHolder.reviewContent.setText(reviewPosts.get(position).content);



        return convertView;
    }
}

