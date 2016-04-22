package com.yingying.searchapp;

/**
 * Created by Yingying Xia on 2016/4/21.
 */
public class ProfileChildRow {
    private int icon;
    private String text;




    public int getIcon() {
        return icon;
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

    public ProfileChildRow(int icon, String text){
        this.icon = icon;
        this.text = text;
    }
}
