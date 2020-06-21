package com.example.shenglin.studyingonline;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.R.id.list;

public class Data {

    private static ArrayList<HashMap<String, Object>> listItem;

    public ArrayList<HashMap<String, Object>> get_home_03_Data() {
        listItem = new ArrayList<>();
        HashMap<String, Object> map = new HashMap<>();
        map.put("list_home_03_text1", "前发");
        map.put("list_home_03_text2", "/CSS");
        listItem.add(map);

        map = new HashMap<>();
        map.put("list_home_03_text1", "前端发");
        map.put("list_home_03_text2", "HTCSS");
        listItem.add(map);

        map = new HashMap<>();
        map.put("list_home_03_text1", "前");
        map.put("list_home_03_text2", "HTS");
        listItem.add(map);

        map = new HashMap<>();
        map.put("list_home_03_text1", "发");
        map.put("list_home_03_text2", "HTML/jkljilS");
        listItem.add(map);

        map = new HashMap<>();
        map.put("list_home_03_text1", "前");
        map.put("list_home_03_text2", "HTS");
        listItem.add(map);

        map = new HashMap<>();
        map.put("list_home_03_text1", "发");
        map.put("list_home_03_text2", "HTML/jkljilS");
        listItem.add(map);

        return listItem;
    }

    public ArrayList<HashMap<String, Object>> get_list_01_Data() {
        listItem = new ArrayList<>();
        HashMap<String, Object> map = new HashMap<>();
        map.put("list_list_01_Title", "前端开发");
        map.put("list_list_01_g_1", R.drawable.new_2);
        map.put("list_list_01_g_2", R.drawable.new_9);
        map.put("list_list_01_g_3", R.drawable.new_6);
        map.put("list_list_01_g_4", R.drawable.new_19);
        map.put("list_list_01_g_5", R.drawable.new_17);
        listItem.add(map);

        map = new HashMap<>();
        map.put("list_list_01_Title", "后端开发");
        map.put("list_list_01_g_1", R.drawable.new_8);
        map.put("list_list_01_g_2", R.drawable.new_14);
        map.put("list_list_01_g_3", R.drawable.new_13);
        map.put("list_list_01_g_4", R.drawable.new_5);
        map.put("list_list_01_g_5", R.drawable.new_4);
        listItem.add(map);

        return listItem;
    }

    public ArrayList<HashMap<String, Object>> get_list_02_Data() {
        listItem = new ArrayList<>();
        HashMap<String, Object> map = new HashMap<>();
        map.put("list_list_02_Title", "移动开发");
        map.put("list_list_02_g_1", R.drawable.new_3);
        map.put("list_list_02_g_2", R.drawable.new_7);
        map.put("list_list_02_g_3", R.drawable.new_16);
        map.put("list_list_02_g_4", R.drawable.new_1);
        listItem.add(map);
        return listItem;
    }

    public ArrayList<HashMap<String, Object>> get_list_03_Data() {
        listItem = new ArrayList<>();
        HashMap<String, Object> map = new HashMap<>();
        map.put("list_list_03_Title", "大数据&云计算");
        map.put("list_list_03_g_1", R.drawable.new_18);
        map.put("list_list_03_g_2", R.drawable.new_20);
        listItem.add(map);
        return listItem;
    }

    public ArrayList<HashMap<String, Object>> get_info_02_Data() {
        listItem = new ArrayList<>();
        String[]  info_02_text = new String[]{
                "我的课程",
                "我的提问",
                "我的笔记",
                "我的关注",
                "新建笔记",
                "个人信息",
                "关于我们"
        };
        for (int i=0;i<=6;i++) {
            HashMap<String, Object> map=new HashMap<>();
            map.put("list_info_02_text",info_02_text[i]);
            listItem.add(map);
        }
        return listItem;
    }

    public String get_Title_id(String sTitle) {
        if (sTitle.equals("HTML/CSS"))
            return "5964ad77570c350058e8a3cd";
        else if (sTitle.equals("Javascript"))
            return "5964ad8061ff4b00578431a5";
        else if (sTitle.equals("HTML5"))
            return "5964ad8b128fe1006900decc";
        else if (sTitle.equals("前端工具"))
            return "5965aba58d6d81005857810e";
        else if (sTitle.equals("WebApp"))
            return "5965ab9bac502e006cd9708f";

        else if (sTitle.equals("Android"))
            return "5964ac09128fe1006900c9f8";
        else if (sTitle.equals("ios"))
            return "5964adaaac502e0064854885";
        else if (sTitle.equals("Unity_3D"))
            return "5965ab66128fe100690b7bea";
        else if (sTitle.equals("Cocos2d_x"))
            return "5965ab8e0ce463005883baaa";

        else if (sTitle.equals("大数据"))
            return "5964ad9bac502e0064854815";
        else if (sTitle.equals("云计算"))
            return "5965ab381b69e600667ee340";

        else if (sTitle.equals("Java"))
            return "5965ab4bac502e00648ffe74";
        else if (sTitle.equals("Python"))
            return "5964ac9eac502e006ccef5d8";
        else if (sTitle.equals("PHP"))
            return "5964aca4ac502e006ccef61b";
        else if (sTitle.equals("C++"))
            return "5964ac94a22b9d006a6182cd";
        else //if (sTitle.equals("C#"))
            return "5965ab528d6d810058577a69";
    }

    public ArrayList<HashMap<String, Object>> get_mydata(String Title) {
        listItem = new ArrayList<>();

        if (Title.equals("我的笔记")) {}
        else if (Title.equals("我的提问")) {}
        else if (Title.equals("我的课程")) {}
        else {}//if (Title.equals("我的关注"))

        return listItem;
    }
}
