package com.example.shenglin.studyingonline;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.FindCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.os.Build.ID;

public class answer extends AppCompatActivity {

    private ListView answer_lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);

        answer_lv=(ListView)findViewById(R.id.answer_lv);

        Intent I = getIntent();
        String title=I.getStringExtra("title");
        String ID=I.getStringExtra("ID");
        setTitle(title);

        AVObject que=AVObject.createWithoutData("question",ID);
        final AVQuery ans=new AVQuery("Answer");
        ans.whereEqualTo("dependent",que);
        ans.findInBackground(new FindCallback<AVObject>() {
            @Override
            public void done(List<AVObject> list, AVException e) {
                ArrayList<HashMap<String,Object>> p = new ArrayList<>();
                if (list!=null && e==null) {
                    for (AVObject avo : list) {
                        HashMap<String, Object> map = new HashMap<>();
                        map.put("content", avo.getString("content").toString());
                        map.put("nicheng", avo.getAVUser("Publisher").getString("NickName").toString());
                        map.put("img",R.drawable.hahaha);
                        p.add(map);
                    }
                    SimpleAdapter simpleAdapter = new SimpleAdapter(answer.this,
                            p,
                            R.layout.taolun,
                            new String[]{"img", "content", "nicheng"},
                            new int[]{R.id.taolun_img, R.id.taolun_text, R.id.taolun_nicheng});
                    answer_lv.setAdapter(simpleAdapter);
                    simpleAdapter.notifyDataSetChanged();
                }
            }
        });
    }
}
