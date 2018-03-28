package com.example.joshuaschlisserman.miniapp2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Context mContext;
    Button mStartButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //open app to activity_main
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;
        mStartButton = (Button) findViewById(R.id.start_cooking_button);

        //start new activity upon clicking start button
        mStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nextActivity = new Intent(mContext, SearchActivity.class);
                startActivity(nextActivity);

            }


        });

    }

}
