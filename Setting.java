package com.yingying.searchapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class Setting extends Fragment {

    EditText oldPassword;
    EditText newPassword;
    EditText retypePassword;
    Button sButton;

    public Setting() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_setting,container,false);
        oldPassword = (EditText) view.findViewById(R.id.editText);
        newPassword=(EditText)view.findViewById(R.id.editText2);
        retypePassword=(EditText)view.findViewById(R.id.editText3);
        sButton = (Button)view.findViewById(R.id.button);
   //    sButton.setOnClickListener((View.OnClickListener) this);
        return view;

    }

}