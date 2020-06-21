package com.example.shenglin.studyingonline;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.FindCallback;

import java.util.Arrays;
import java.util.List;

public class note extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        Intent I = getIntent();
        String title=I.getStringExtra("title");
        setTitle(title);

        final TextView content=(TextView)findViewById(R.id.note);

        AVQuery note = new AVQuery("Note");
        note.whereEqualTo("Publisher", AVUser.getCurrentUser());
        note.whereEqualTo("Title", title);
        note.findInBackground(new FindCallback<AVObject>() {
            @Override
            public void done(List<AVObject> list, AVException e) {
                if (list!=null && e==null) {
                    for (AVObject avo:list) {
                        content.setText(avo.get("content").toString());
                    }
                }
            }
        });
    }
}
