package com.yingying.searchapp;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;

import static android.view.View.*;


public class MyExpandableListAdapter extends BaseExpandableListAdapter {
    private Context context;
    private ArrayList<ParentRow> parentRowList;
    private ArrayList<ParentRow> originalList;


    public MyExpandableListAdapter(Context context, ArrayList<ParentRow> originalList) {
        this.context = context;
        this.parentRowList = new ArrayList<>();
        this.parentRowList.addAll(originalList);
        this.originalList = new ArrayList<>();
        this.originalList.addAll(originalList);
    }


    @Override
    public int getGroupCount() {
        return parentRowList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return parentRowList.get(groupPosition).getChildList().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return parentRowList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return parentRowList.get(groupPosition).getChildList().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ParentRow parentRow = (ParentRow) getGroup(groupPosition);

        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.parent_row, null);
        }

        TextView heading = (TextView) convertView.findViewById(R.id.parent_text);

        heading.setText(parentRow.getName().trim());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {


        ChildRow childRow = (ChildRow) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater)
                    context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.child_row, null);
        }
        ImageView childIcon = (ImageView) convertView.findViewById(R.id.child_icon);
      //  childIcon.setImageResource(R.mipmap.ic_launcher);
        childIcon.setImageResource(R.drawable.dining_icon);


        final TextView childText = (TextView) convertView.findViewById(R.id.child_text);
        childText.setText(childRow.getText().trim());

        final RatingBar childRating = (RatingBar)convertView.findViewById(R.id.child_rating);
        childRating.setRating(Float.parseFloat(childRow.getRating()));

        final TextView childType = (TextView)convertView.findViewById(R.id.child_type);
        childType.setText(childRow.getType().trim());

        final View finalConvertView = convertView;
       childText.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent i = new Intent(context, RestaurantPage.class);
               i.putExtra("RestaurantName", childText.getText());
               i.putExtra("accountUsername", MainSearch.carryUsername);
               i.putExtra("accountEmail", MainSearch.carryUserEmail);

               context.startActivity(i);

               Toast.makeText(context, childText.getText(), Toast.LENGTH_LONG).show();
           }
       });



        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


    public void filterData(String query) {
        query = query.toLowerCase();
        parentRowList.clear();
        if (query.isEmpty()) {
            parentRowList.addAll(originalList);
        }

        else{
            for(ParentRow parentRow:originalList){
                ArrayList<ChildRow> childlist = parentRow.getChildList();
                ArrayList<ChildRow> newList = new ArrayList<ChildRow>();

                for(ChildRow childRow:childlist){
                    if(childRow.getText().toLowerCase().contains(query)||childRow.getType().toLowerCase().contains(query)){
                        newList.add(childRow);

                    }
                }

                if(newList.size()>0){
                    ParentRow nParentRow = new ParentRow(parentRow.getName(), newList);
                    parentRowList.add((nParentRow));
                }
            }
            notifyDataSetChanged();
        }
    }


    public Restaurant getRestaurantByName(String res) {
        Restaurant r = null;
        RestaurantDatabaseOperations ResDOP = new RestaurantDatabaseOperations(context);
        Cursor cr = ResDOP.getInformation(ResDOP);
        cr.moveToFirst();
        while (!cr.isAfterLast()) {
            if (cr.getString(0) != null && cr.getString(0).equalsIgnoreCase(res)) {
                r = new Restaurant(cr.getString(0),cr.getString(1),cr.getString(2),cr.getString(3),cr.getString(4),cr.getString(5));

            }else{
                r = null;
            }
            cr.moveToNext();
        }
        return r;
    }


}