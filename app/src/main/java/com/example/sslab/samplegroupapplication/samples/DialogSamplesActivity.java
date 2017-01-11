package com.example.sslab.samplegroupapplication.samples;

import android.support.v4.util.Pair;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;

import com.example.sslab.samplegroupapplication.R;

import java.util.ArrayList;

import dmax.dialog.SpotsDialog;

public class DialogSamplesActivity extends AppCompatActivity implements View.OnClickListener{

    GridLayout mainLayout;
    ArrayList<Pair<Button,Integer>> buttonList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_samples);

        buttonList.clear();
        mainLayout = ( GridLayout ) findViewById( R.id.activity_dialog_samples);
        for(int i =0;i<mainLayout.getChildCount();i++){
            if(mainLayout.getChildAt(i) instanceof Button){

                mainLayout.getChildAt(i).setId(i);
                final Button button = ( Button ) mainLayout.getChildAt(i);
                final Integer buttonId = i;

                button.setOnClickListener(this);

                Pair<Button,Integer> pair = new Pair<>( button, buttonId );

                buttonList.add(pair);
            }
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id){
            case 0:
                new SpotsDialog(this, R.style.Custom).show();
                break;
            case 1:
                // something Customizing
                break;
        }
    }

}
