package com.example.shenglin.studyingonline;

import android.content.Intent;
import android.nfc.cardemulation.HostApduService;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;
import com.avos.avoscloud.GetDataCallback;
import com.avos.avoscloud.ObjectValueFilter;
import com.google.protobuf.LazyStringArrayList;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import static android.R.id.list;
import static com.xiaomi.push.service.am.s;


public class List_02 extends AppCompatActivity {

    private ListView listview;
    private String Title_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_02);

        Intent intent = getIntent();
        final String TITLE=intent.getStringExtra("TITLE");
        setTitle(TITLE);

        Title_id=new Data().get_Title_id(TITLE);
        listview = (ListView)findViewById(R.id.list_02_listview);

        AVObject cate=AVObject.createWithoutData("CourseCategories",Title_id);
        AVQuery que=new AVQuery("Courses");
        que.include("Title");
        que.include("Describe");
        que.whereEqualTo("categories",cate);
        que.findInBackground(new FindCallback<AVObject>() {
            @Override
            public void done(List<AVObject> list, AVException e) {
                if (list != null && e == null) {
                    ArrayList<HashMap<String,Object>> p = new ArrayList<>();
                    String pID[]={};
                    for (AVObject avo : list) {
                        HashMap<String, Object> map = new HashMap<>();
                        map.put("v_list_02_title1", avo.get("Title"));
                        map.put("v_list_02_title2", avo.get("Describe"));
                        map.put("v_list_02_iv",R.drawable.hahaha);
                        pID= Arrays.copyOf(pID,pID.length+1);
                        pID[pID.length-1]=avo.getObjectId();
                        p.add(map);
                    }
                    SimpleAdapter simpleadapter = new SimpleAdapter(List_02.this,
                            p,
                            R.layout.list_02_vlist,
                            new String[]{"v_list_02_title1", "v_list_02_title2", "v_list_02_iv"},
                            new int[]{R.id.v_list_02_title1, R.id.v_list_02_title2, R.id.v_list_02_iv});
                    listview.setAdapter(simpleadapter);
                    simpleadapter.notifyDataSetChanged();

                    final String[] finalPID = pID;
                    listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            String result = ((TextView)view.findViewById(R.id.v_list_02_title1)).getText().toString();
                            String ID = finalPID[position];
                            Intent I=new Intent(List_02.this,List_03.class);
                            I.putExtra("NAME",result);
                            I.putExtra("ID",ID);
                            startActivity(I);
                        }
                    });
                }
            }
        });
    }
}
