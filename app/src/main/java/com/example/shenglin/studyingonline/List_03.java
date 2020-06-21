package com.example.shenglin.studyingonline;

import android.content.ContentUris;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.GetCallback;

import static android.R.attr.name;
import static android.os.Build.ID;

public class List_03 extends AppCompatActivity {

    private TextView ttt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_03);

        Intent I = getIntent();
        String name = I.getStringExtra("NAME");
        final String ID = I.getStringExtra("ID");
        setTitle(name);

        ttt = (TextView) findViewById(R.id.textView);

        AVQuery<AVObject> cour=new AVQuery<>("Courses");
        cour.getInBackground(ID, new GetCallback<AVObject>() {
            @Override
            public void done(AVObject avObject, AVException e) {
                ttt.setText(avObject.getString("Describe"));
            }
        });

        Button btn1=(Button) findViewById(R.id.list_03_btn);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent I=new Intent(List_03.this,List_04.class);
                I.putExtra("ID",ID);
                startActivity(I);
            }
        });

    }
}
