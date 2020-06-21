package com.example.shenglin.studyingonline;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class info_AboutUs extends AppCompatActivity {

    private TextView us;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info__about_us);

        Intent I = getIntent();
        String TITLE = I.getStringExtra("TITLE");
        setTitle(TITLE);

        us= (TextView) findViewById(R.id.us);
        us.setText("Hello, My First App.");
    }
}
