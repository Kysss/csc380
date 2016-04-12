package com.kimbrian.searchrestaurantapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.app.Activity;
import android.widget.Button;
import android.widget.EditText;

public class UEconfirm extends Fragment {

    private static EditText username, email;

    UEListener activityCommander;

    public interface UEListener{
        public void CheckUE(String username, String email);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            activityCommander = (UEListener) activity;
        } catch (ClassCastException e){
            throw new ClassCastException(activity.toString());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ueconfirm, container, false);

        username = (EditText) view.findViewById(R.id.usernamePrompt);
        email = (EditText) view.findViewById(R.id.email);
        final Button UEsubmit = (Button) view.findViewById(R.id.UEsubmit);

        UEsubmit.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
                        UEsubmitClicked (v);
                    }
                }

        );

        return view;
    }

    //Button is clicked --> Called
    public void UEsubmitClicked (View view){
        activityCommander.CheckUE(username.getText().toString(), email.getText().toString());
    }
    public String getUsername (){
        return username.getText().toString();
    }

}
