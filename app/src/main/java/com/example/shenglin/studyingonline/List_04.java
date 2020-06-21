package com.example.shenglin.studyingonline;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.VideoView;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.FindCallback;
import com.avos.avoscloud.GetCallback;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static android.R.id.list;

public class List_04 extends AppCompatActivity {

    private ListView list4_lv;
    private TextView list4_txt;
    private Button list4_btn1;
    private Button list4_btn2;
    private String ID;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            AVObject co=AVObject.createWithoutData("Courses",ID);
            switch (item.getItemId()) {
                case R.id.zj:
                    list4_lv.setVisibility(View.GONE);
                    list4_txt.setVisibility(View.GONE);
                    list4_btn1.setVisibility(View.VISIBLE);
                    list4_btn2.setVisibility(View.VISIBLE);
                    list4_btn1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent I = new Intent(List_04.this,new_taolun.class);
                            startActivity(I);
                        }
                    });
                    list4_btn2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent I = new Intent(List_04.this,new_wenti.class);
                            startActivity(I);
                        }
                    });
                    return true;
                case R.id.js:
                    list4_lv.setVisibility(View.GONE);
                    list4_txt.setVisibility(View.VISIBLE);
                    list4_btn1.setVisibility(View.GONE);
                    list4_btn2.setVisibility(View.GONE);
                    AVQuery<AVObject> cour=new AVQuery<>("Courses");
                    cour.getInBackground(ID, new GetCallback<AVObject>() {
                        @Override
                        public void done(AVObject avObject, AVException e) {
                            list4_txt.setText(avObject.getString("Describe"));
                        }
                    });
                    return true;
                case R.id.tl:
                    list4_lv.setVisibility(View.VISIBLE);
                    list4_txt.setVisibility(View.GONE);
                    list4_btn1.setVisibility(View.GONE);
                    list4_btn2.setVisibility(View.GONE);
                    AVQuery C=new AVQuery("CourseComment");
                    C.orderByDescending("createdAt");
                    C.whereEqualTo("dependent",co);
                    C.findInBackground(new FindCallback<AVObject>() {
                        @Override
                        public void done(List<AVObject> list, AVException e) {
                            ArrayList<HashMap<String,Object>> p = new ArrayList<>();
                            if((list!=null)&&(e==null)) {
                                for (AVObject avo : list) {
                                    HashMap<String, Object> map = new HashMap<>();
                                    map.put("content", avo.get("content"));
                                    map.put("nicheng", AVUser.getCurrentUser().getString("NickName"));
                                    map.put("img",R.drawable.hahaha);
                                    p.add(map);
                                }
                                SimpleAdapter simpleAdapter = new SimpleAdapter(List_04.this,
                                        p,
                                        R.layout.taolun,
                                        new String[]{"img", "content", "nicheng"},
                                        new int[]{R.id.taolun_img, R.id.taolun_text, R.id.taolun_nicheng});
                                list4_lv.setAdapter(simpleAdapter);
                                simpleAdapter.notifyDataSetChanged();
                            }
                        }
                    });
                    return true;

                case R.id.wd:
                    list4_lv.setVisibility(View.VISIBLE);
                    list4_txt.setVisibility(View.GONE);
                    list4_btn1.setVisibility(View.GONE);
                    list4_btn2.setVisibility(View.GONE);
                    AVQuery Q=new AVQuery("question");
                    Q.orderByDescending("createdAt");
                    Q.whereEqualTo("dependent",co);
                    Q.findInBackground(new FindCallback<AVObject>() {
                        @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
                        @Override
                        public void done(List<AVObject> list, AVException e) {
                            ArrayList<HashMap<String,Object>> p = new ArrayList<>();
                            String[] pID = {};
                            if(list!=null && e==null) {
                                for (AVObject avo : list) {
                                    HashMap<String, Object> map = new HashMap<>();
                                    map.put("content", avo.get("Describe"));
                                    map.put("title",avo.get("Title"));
                                    map.put("nicheng", AVUser.getCurrentUser().getString("NickName"));
                                    map.put("img",R.drawable.hahaha);
                                    p.add(map);
                                    pID= Arrays.copyOf(pID,pID.length+1);
                                    pID[pID.length-1]=avo.getObjectId();
                                }
                                SimpleAdapter simpleAdapter = new SimpleAdapter(List_04.this,
                                        p,
                                        R.layout.wenti,
                                        new String[]{"img", "content", "nicheng", "title"},
                                        new int[]{R.id.wenti_img, R.id.wenti_describe, R.id.wenti_nicheng,R.id.wenti_title});
                                list4_lv.setAdapter(simpleAdapter);
                                simpleAdapter.notifyDataSetChanged();

                                final String[] finalPID = pID;
                                list4_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                        String title = ((TextView)findViewById(R.id.wenti_title)).getText().toString();
                                        String ID = finalPID[position];
                                        Intent I=new Intent(List_04.this,answer.class);
                                        I.putExtra("title",title);
                                        I.putExtra("ID",ID);
                                        startActivity(I);
                                    }
                                });
                            }
                        }
                    });
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_04);

        Intent I = getIntent();
        ID = I.getStringExtra("ID");

        VideoView videoView = (VideoView)findViewById(R.id.videoview1) ;
        videoView.setVideoURI(Uri.parse("http://f3.3g.56.com/15/15/JGfMspPbHtzoqpzseFTPGUsKCEqMXFTW_smooth.3gp"));
        videoView.setMediaController(new MediaController(this));
        videoView.requestFocus();
        videoView.start();
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Log.i("通知","完成");
            }
        });
        videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                Log.i("通知","播放出现错误");
                return false;
            }
        });

        list4_lv=(ListView) findViewById(R.id.list_04_lv);
        list4_txt=(TextView) findViewById(R.id.list_04_txt);
        list4_btn1=(Button) findViewById(R.id.list_04_btn1);
        list4_btn2=(Button) findViewById(R.id.list_04_btn2);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.shipin);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
