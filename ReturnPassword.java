package com.yingying.searchapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ReturnPassword extends Fragment {
    private static TextView thisPassword;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.rpassword, container, false);
        thisPassword = (TextView) view.findViewById(R.id.thisPassword);
        return view;

    }

    public void setPassword(String password){
        thisPassword.setText(password);
    }

}
