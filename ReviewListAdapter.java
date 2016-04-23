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
 class ReviewListAdapter extends BaseAdapter{

    private Context context;

    private LayoutInflater inflater;
    ArrayList<ratingPost> reviewPosts;

    private class ViewHolder{
        ImageView image;
        TextView date;
        TextView rUsername;
        RatingBar rating;
        TextView reviewContent;

    }

    public ReviewListAdapter(Context context,ArrayList<ratingPost> contents) {
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
            convertView = inflater.inflate(R.layout.single_review,null);
            postHolder.date = (TextView) convertView.findViewById(R.id.reviewPostDate);
            postHolder.rUsername = (TextView) convertView.findViewById(R.id.reviewUserName);
            postHolder.reviewContent = (TextView) convertView.findViewById(R.id.reviewPostContent);
            postHolder.image = (ImageView) convertView.findViewById(R.id.reviewIcon);
            postHolder.rating = (RatingBar) convertView.findViewById(R.id.reviewRatingBar);
            convertView.setTag(postHolder);
        }else{
            postHolder = (ViewHolder)convertView.getTag();
        }

        postHolder.date.setText(reviewPosts.get(position).postdate);
        postHolder.image.setImageResource(R.drawable.cat);
        postHolder.rUsername.setText(reviewPosts.get(position).postUserName);
        postHolder.rating.setRating(Float.valueOf(reviewPosts.get(position).rating));
        postHolder.reviewContent.setText(reviewPosts.get(position).content);

        postHolder.rUsername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, ProfilePage.class);
                i.putExtra("RestaurantName", RestaurantPage.carryResName);
                i.putExtra("accountUsername",RestaurantPage.carryUsername);
                i.putExtra("accountEmail",RestaurantPage.carryUserEmail);
                i.putExtra("clickedUsername",reviewPosts.get(position).postUserName);
                context.startActivity(i);
            }
        });


        return convertView;
    }
}
