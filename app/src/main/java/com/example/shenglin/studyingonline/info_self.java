package com.example.shenglin.studyingonline;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVUser;

import static android.R.attr.canRetrieveWindowContent;
import static android.R.attr.type;
import static android.provider.Contacts.SettingsColumns.KEY;
import static com.avos.avoscloud.AVUser.AVUSER_ENDPOINT;
import static com.avos.avoscloud.AVUser.getCurrentUser;
import static com.example.shenglin.studyingonline.R.id.用户名;

public class info_self extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_self);

        Intent I = getIntent();
        String TITLE = I.getStringExtra("TITLE");
        setTitle(TITLE);

        TextView text1 = (TextView) findViewById(R.id.self_text1);
        TextView text2 = (TextView) findViewById(R.id.self_text2);
        TextView text3 = (TextView) findViewById(R.id.self_text3);
        ImageButton img = (ImageButton) findViewById(R.id.self_img);
        Button btn = (Button) findViewById(R.id.guanzhu);

        text1.setText(AVUser.getCurrentUser().getUsername());
        text2.setText(AVUser.getCurrentUser().getString("NickName"));
        text3.setText(AVUser.getCurrentUser().getEmail());

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(info_self.this,"更换头像",Toast.LENGTH_SHORT).show();
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(info_self.this,"guanzhu",Toast.LENGTH_SHORT).show();
            }
        });

    }
}
