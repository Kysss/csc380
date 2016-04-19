package com.yingying.searchapp;

/**
 * Created by Reeves on 4/7/16.
 */
public class ChildRow {
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

    public ChildRow(int icon, String text){
        this.icon = icon;
        this.text = text;

    }
}
