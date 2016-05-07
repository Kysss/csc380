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
class adminDeleteAccountAdapter extends BaseAdapter{

    private Context context;

    private LayoutInflater inflater;
    ArrayList<Profile> accounts;

    private class ViewHolder{
        ImageView image;

        TextView userName;


    }

    public adminDeleteAccountAdapter(Context context,ArrayList<Profile> contents) {
        //    super();

        this.context = context;
        inflater = LayoutInflater.from(context);
        this.accounts=contents;

        //  super(context,R.layout.single_review,contents);
    }


    @Override
    public int getCount() {
        return accounts.size();
    }

    @Override
    public Profile getItem(int position) {
        return accounts.get(position);
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
            convertView = inflater.inflate(R.layout.user_row,null);
            convertView.setFocusable(false);
           // postHolder.date = (TextView) convertView.findViewById(R.id.reviewHistoryPostDate);
            postHolder.userName = (TextView) convertView.findViewById(R.id.child_text);
         //   postHolder.reviewContent = (TextView) convertView.findViewById(R.id.reviewPostHistoryContent);
            postHolder.image = (ImageView) convertView.findViewById(R.id.child_icon);
         //   postHolder.rating = (RatingBar) convertView.findViewById(R.id.reviewHistoryRatingBar);
            convertView.setTag(postHolder);
        }else{
            postHolder = (ViewHolder)convertView.getTag();
        }

        postHolder.image.setImageResource(R.drawable.profile_icon);
        postHolder.userName.setText(accounts.get(position).getUsername());



        return convertView;
    }
}

