package com.yingying.searchapp;

/**
 * Created by Reeves on 4/7/16.
 */
public class ChildRow {
    private int icon;
    private String text;
    private String rating;
    private String type;

public String getType(){
    return type;
}
    public String setType(String type){
        return this.type=type;
    }

    public int getIcon() {
        return icon;
    }
    public String getRating(){
        return this.rating;
    }
    public void setRating(String rating){
        this.rating = rating;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public void setText(String text){
        this.text = text;
    }

    public String getText(){
        return text;
    }

    public ChildRow(int icon, String text,String rate,String type){
        this.icon = icon;
        this.text = text;
        this.rating = rate;
this.type= type;
    }
}
