package com.yingying.searchapp;

/**
 * Created by Yingying Xia on 2016/4/21.
 */

import java.util.ArrayList;



import java.util.ArrayList;

/**
 * Created by Reeves on 4/7/16.
 */
public class UserParentRow {
    public UserParentRow(String name, ArrayList<ProfileChildRow> childList) {
        this.name = name;
        this.childList = childList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<ProfileChildRow> getChildList() {
        return childList;
    }

    public void setChildList(ArrayList<ProfileChildRow> childList) {
        this.childList = childList;
    }

    private String name;
    private ArrayList<ProfileChildRow> childList;
}

