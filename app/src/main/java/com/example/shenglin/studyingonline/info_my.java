package com.example.shenglin.studyingonline;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.FindCallback;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static android.R.attr.id;
import static android.R.id.list;
import static android.icu.util.HebrewCalendar.AV;

public class info_my extends AppCompatActivity {

    private ListView my_listview1;//biji
    private ListView my_listview2;//tiwen
    private ListView my_listview3;//kecheng
    private ListView my_listview4;//guanzhu
    private SimpleAdapter simple;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_my);

        Intent I = getIntent();
        String TITLE = I.getStringExtra("TITLE");
        setTitle(TITLE);

        my_listview1=(ListView) findViewById(R.id.my_lv_biji);
        my_listview2=(ListView) findViewById(R.id.my_lv_tiwen);
        my_listview3=(ListView) findViewById(R.id.my_lv_kecheng);
        my_listview4=(ListView) findViewById(R.id.my_lv_guanzhu);

        if (TITLE.equals("我的笔记")) {
            my_listview1.setVisibility(View.VISIBLE);
            my_listview2.setVisibility(View.GONE);
            my_listview3.setVisibility(View.GONE);
            my_listview4.setVisibility(View.GONE);
            AVQuery note = new AVQuery("Note");
            note.whereEqualTo("Publisher", AVUser.getCurrentUser());
            note.findInBackground(new FindCallback<AVObject>() {
                @Override
                public void done(List<AVObject> list, AVException e) {
                    if (list!=null && e==null) {
                        String[] p = {};
                        for (AVObject avo : list) {
                            p = Arrays.copyOf(p, p.length + 1);
                            p[p.length - 1] = avo.get("Title").toString();
                        }
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(info_my.this,
                                android.R.layout.simple_list_item_1,
                                p);
                        my_listview1.setAdapter(arrayAdapter);
                        final String[] finalP = p;
                        my_listview1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                String title = finalP[position];
                                Intent I=new Intent(info_my.this,note.class);
                                I.putExtra("title",title);
                                startActivity(I);
                            }
                        });
                    }
                }
            });
        }
        else if (TITLE.equals("我的提问")) {
            my_listview1.setVisibility(View.GONE);
            my_listview2.setVisibility(View.VISIBLE);
            my_listview3.setVisibility(View.GONE);
            my_listview4.setVisibility(View.GONE);
            AVQuery note = new AVQuery("question");
            note.whereEqualTo("Publisher", AVUser.getCurrentUser());
            note.findInBackground(new FindCallback<AVObject>() {
                @Override
                public void done(List<AVObject> list, AVException e) {
                    if (list!=null && e==null) {
                        ArrayList<HashMap<String,Object>> p = new ArrayList<>();
                        String[] pID={};
                        for (AVObject avo : list) {
                            HashMap<String, Object> map = new HashMap<>();
                            map.put("title", avo.get("Title"));
                            map.put("describe", avo.get("Describe"));
                            map.put("nicheng",AVUser.getCurrentUser().getString("NickName"));
                            map.put("img",R.drawable.hahaha);
                            p.add(map);
                            pID= Arrays.copyOf(pID,pID.length+1);
                            pID[pID.length-1]=avo.getObjectId();
                        }
                        SimpleAdapter simpleadapter = new SimpleAdapter(info_my.this,
                                p,
                                R.layout.wenti,
                                new String[]{"title", "describe", "img","nicheng"},
                                new int[]{R.id.wenti_title, R.id.wenti_describe, R.id.wenti_img,R.id.wenti_nicheng});
                        my_listview2.setAdapter(simpleadapter);
                        simpleadapter.notifyDataSetChanged();

                        final String[] finalPID = pID;
                        my_listview2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                String title = ((TextView)findViewById(R.id.wenti_title)).getText().toString();
                                Intent I=new Intent(info_my.this,answer.class);
                                String ID = finalPID[position];
                                I.putExtra("title",title);
                                I.putExtra("ID",ID);
                                startActivity(I);
                            }
                        });
                    }
                }
            });
        }
        else if (TITLE.equals("我的课程")) {
            my_listview1.setVisibility(View.GONE);
            my_listview2.setVisibility(View.GONE);
            my_listview3.setVisibility(View.VISIBLE);
            my_listview4.setVisibility(View.GONE);

            AVQuery<AVObject> cs=new AVQuery<>("RCS");
            cs.whereEqualTo("Student",AVUser.getCurrentUser());
            cs.findInBackground(new FindCallback<AVObject>() {
                @Override
                public void done(List<AVObject> list, AVException e) {
                    if (list!=null && e==null) {
                        ArrayList<HashMap<String, Object>> p = new ArrayList<>();
                        String[] pID={};
                        for (AVObject avo : list) {
                            AVObject co = avo.getAVObject("Course");
                            HashMap<String, Object> map = new HashMap<>();
                            map.put("title", co.getString("Title"));
                            map.put("decribe", co.getString("Describe"));
                            map.put("img", R.drawable.hahaha);
                            pID= Arrays.copyOf(pID,pID.length+1);
                            pID[pID.length-1]=avo.getObjectId();
                            p.add(map);
                        }
                        SimpleAdapter simpleadapter = new SimpleAdapter(info_my.this,
                                p,
                                R.layout.list_02_vlist,
                                new String[]{"title", "decribe", "img"},
                                new int[]{R.id.v_list_02_title1, R.id.v_list_02_title2, R.id.v_list_02_iv});
                        my_listview3.setAdapter(simpleadapter);
                        simpleadapter.notifyDataSetChanged();

                        final String[] finalPID = pID;
                        my_listview3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                String result = ((TextView)view.findViewById(R.id.v_list_02_title1)).getText().toString();
                                String ID = finalPID[position];
                                Intent I=new Intent(info_my.this,List_03.class);
                                I.putExtra("NAME",result);
                                I.putExtra("ID",ID);
                                startActivity(I);
                            }
                        });
                    }
                }
            });
        }

//
//
//
//             || TITLE.equals("我的提问")
//            AVQuery myque=new AVQuery("question");
//            myque.whereEqualTo("Publisher",AVUser.getCurrentUser());
//            myque.findInBackground(new FindCallback<AVObject>() {
//                @Override
//                public void done(List<AVObject> list, AVException e) {
//
//                }
//
//
//            });
//
//
//
//
//
//
//            simple = new SimpleAdapter(info_my.this,
//                    new Data().get_mydata(TITLE),
//                    R.layout.my_layout_01,
//                    new String[]{"Title", "Describe"},
//                    new int[]{R.id.my_txt1,R.id.my_txt2});
//        }
//        else {
//
//            AVQuery mycou=new AVQuery("Courses");
//            myque.whereEqualTo("Publisher",AVUser.getCurrentUser());
//            myque.findInBackground(new FindCallback<AVObject>() {
//                @Override
//                public void done(List<AVObject> list, AVException e) {
//
//                }
//
//
//            });
//
//            simple = new SimpleAdapter(info_my.this,
//                    new Data().get_mydata(TITLE),
//                    R.layout.my_layout_02,
//                    new String[] {"img","NAME"},
//                    new int[] {R.id.my_img,R.id.my_txt3});
//        }
//        my_listview.setAdapter(simple);
    }
}
