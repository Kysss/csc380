package com.yingying.searchapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Button;

public class SQAconfirm extends Fragment {

    private static TextView securityQuestion;
    private static EditText securityAnswer;
    private String username;

    SQAListener activityCommander;

    public interface SQAListener{
        public void CheckSA(String securityAnswer, String username);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            activityCommander = (SQAListener) activity;
        } catch (ClassCastException e){
            throw new ClassCastException(activity.toString());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sqaconfirm, container, false);

        securityQuestion = (TextView) view.findViewById(R.id.securityQuestion);
        securityAnswer = (EditText) view.findViewById(R.id.securityAnswer);
        final Button SAsubmit = (Button) view.findViewById(R.id.SAsubmit);

        SAsubmit.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
                        SAsubmitClicked (v);
                    }
                }

        );

        return view;
    }

    public void setSecurityQuestion(String Question, String Username){
        securityQuestion.setText(Question);
        this.username = Username;
    }

    //Button is clicked --> Called
    public void SAsubmitClicked (View view){
        activityCommander.CheckSA(securityAnswer.getText().toString(), username);
    }

}

