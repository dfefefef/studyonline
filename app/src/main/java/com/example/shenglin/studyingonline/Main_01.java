package com.example.shenglin.studyingonline;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.constraint.solver.SolverVariable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;
import com.avos.avoscloud.GetCallback;
import com.avos.avoscloud.GetDataCallback;
import com.avos.avoscloud.ProgressCallback;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;


public class Main_01 extends AppCompatActivity {

    private ListView lv;
    private MyAdapter myAdapter;
    private ViewPager mViewPaper;
    private ViewPageAdapter adapter;
    private TextView main_title;
    private List<ImageView> images;
    private List<View> dots;
    private int currentItem;
    private int oldPosition = 0;
    private int[] imageIds = new int[]{
            R.drawable.l_1,
            R.drawable.l_2,
            R.drawable.l_3,
            R.drawable.l_4,
            R.drawable.l_5
    };
    private String[]  titles = new String[]{
            "学好Java，走到哪里都不怕",
            "学好Java，高薪就业不是梦",
            "C++，从入门到精通",
            "想做ios开发？先和我一起学好C语言吧",
            "C语言入门"
    };
    private ScheduledExecutorService scheduledExecutorService;
    private FrameLayout LunBo;
    private String obid;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    setTitle("Home");
                    LunBo.setVisibility(View.VISIBLE);
                    myAdapter=new MyAdapter(Main_01.this,1);
                    lv.setAdapter(myAdapter);
                    myAdapter.notifyDataSetChanged();
                    return true;
                case R.id.navigation_list:
                    setTitle("List");
                    LunBo.setVisibility(View.GONE);
                    myAdapter=new MyAdapter(Main_01.this,2);
                    lv.setAdapter(myAdapter);
                    myAdapter.notifyDataSetChanged();
                    return true;
                case R.id.navigation_down:
                    setTitle("Download");
                    LunBo.setVisibility(View.GONE);
                    myAdapter=new MyAdapter(Main_01.this,1);
                    lv.setAdapter(myAdapter);
                    myAdapter.notifyDataSetChanged();
//                    Button xz=(Button)findViewById(R.id.button);///////////////设置下载按钮//////////////////////
//                    xz.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            String tag="1234abcd";         /////////////////在tag里面保存特定的识别信息/////////////////////////////
//                            AVQuery<AVObject> info=new AVQuery<>("VIDEOS");
//                            info.whereEqualTo("Tag",tag);
//                            info.getFirstInBackground(new GetCallback<AVObject>() {
//                                @Override
//                                public void done(AVObject avObject, AVException e) {
//                                    obid=avObject.getString("OBID");
//                                }
//                            });
//                            AVQuery<AVObject> query = new AVQuery<>("_File");
//
//                            query.getInBackground(obid, new GetCallback<AVObject>() {
//                                @Override
//                                public void done(AVObject avObject, AVException e) {
//                                    if (e == null) {
//                                        final String name=avObject.getString("name");
//                                        AVFile file = new AVFile(name, avObject.getString("url"), new HashMap<String, Object>());
//                                        file.getDataInBackground(new GetDataCallback() {
//                                            @Override
//                                            public void done(byte[] bytes, AVException e) {
//                                                File downloadedFile = new File(Environment.getExternalStorageDirectory() + "/网络学习平台/下载/"+name);
//                                                FileOutputStream fout = null;
//                                                try {
//                                                    fout = new FileOutputStream(downloadedFile);
//                                                    fout.write(bytes);
//                                                    fout.close();
//                                                } catch (FileNotFoundException e1) {
//                                                    e1.printStackTrace();
//                                                } catch (IOException e1) {
//                                                    Log.d("saved", "文件读取异常.");
//                                                }
//
//                                            }
//                                        }, new ProgressCallback() {
//                                            @Override
//                                            public void done(Integer integer) {
//                                                Log.d("saved", "文件下载进度" + integer);
//                                            }
//                                        });
//                                    }
//                                }
//                            });
//                        }
//                    });
                    return true;
                case R.id.navigation_info:
                    setTitle("Information");
                    LunBo.setVisibility(View.GONE);
                    myAdapter=new MyAdapter(Main_01.this,4);
                    lv.setAdapter(myAdapter);
                    myAdapter.notifyDataSetChanged();
                    return true;
            }
            return false;
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_01);

        lv=(ListView)findViewById(R.id.main_lv);
        LunBo=(FrameLayout)findViewById(R.id.ViewLunBo);

        images = new ArrayList<>();
        for(int i = 0; i < imageIds.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setBackgroundResource(imageIds[i]);
            images.add(imageView);
        }
        dots = new ArrayList<>();
        dots.add(findViewById(R.id.dot_0));
        dots.add(findViewById(R.id.dot_1));
        dots.add(findViewById(R.id.dot_2));
        dots.add(findViewById(R.id.dot_3));
        dots.add(findViewById(R.id.dot_4));

        main_title = (TextView) findViewById(R.id.main_title);
        main_title.setText(titles[0]);

        mViewPaper=(ViewPager)findViewById(R.id.main_vp);
        adapter=new ViewPageAdapter();
        mViewPaper.setAdapter(adapter);

        mViewPaper.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                main_title.setText(titles[position]);
                dots.get(position).setBackgroundResource(R.drawable.dot_focused);
                dots.get(oldPosition).setBackgroundResource(R.drawable.dot_normal);
                oldPosition = position;
                currentItem = position;
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {}

            @Override
            public void onPageScrollStateChanged(int arg0) {}

        });

        myAdapter=new MyAdapter(Main_01.this,1);
        lv.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    public class ViewPageAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return images.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void destroyItem(ViewGroup view, int position, Object object) {
            view.removeView(images.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup view, int position) {
            view.addView(images.get(position));
            return images.get(position);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleWithFixedDelay(
                new ViewPageTask(),
                3,
                3,
                TimeUnit.SECONDS);
    }

    private class ViewPageTask implements Runnable{

        @Override
        public void run() {
            currentItem = (currentItem + 1) % imageIds.length;
            mHandler.sendEmptyMessage(0);
        }
    }

    private Handler mHandler = new Handler(){
        public void handleMessage(android.os.Message msg) {
            mViewPaper.setCurrentItem(currentItem);
        }
    };

    @Override
    protected void onStop() {
        super.onStop();
    }

    public class MyAdapter extends BaseAdapter {

        private Context mcontext;
        private int KEY;
        private ArrayList<HashMap<String, Object>> list01_Data;
        private ArrayList<HashMap<String, Object>> list02_Data;
        private ArrayList<HashMap<String, Object>> list03_Data;
        private ArrayList<HashMap<String, Object>> home03_Data;
        private ArrayList<HashMap<String, Object>> info02_Data;

        private int Type_Zero=0;//注意这个不同布局的类型起始值必须从0开始
        private int Type_One=1;
        private int Type_Two=2;
        private int Type_Three=3;
        private int Type_Four=4;
        private int Type_Five=5;
        private int Type_Six=6;
        private int Type_Seven=7;

        private int Type_Two_indx=0;
        private int Type_Three_indx=0;
        private int Type_Four_indx=0;
        private int Type_Five_indx=0;

        public MyAdapter(Context context,int key_int){
            mcontext=context;
            KEY=key_int;
            list01_Data=new Data().get_list_01_Data();
            list02_Data=new Data().get_list_02_Data();
            list03_Data=new Data().get_list_03_Data();
            home03_Data=new Data().get_home_03_Data();
            info02_Data=new Data().get_info_02_Data();
        }

        @Override
        public int getCount() {
            switch (KEY) {
                case 1:
                    return 9;
                case 2:
                    return 4;
                case 3:
                    return 4;
                case 4:
                    return 9;
            }
            return 0;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public int getItemViewType(int position) {
            switch (KEY) {
                case 1:
                    if (position==0 || position==3 || position==6)
                        return Type_Zero;
                    else
                        return Type_One;
                case 2:
                    if (position==0 || position==3)
                        return Type_Two;
                    else if (position==1)
                        return Type_Three;
                    else if (position==2)
                        return Type_Four;
                case 3:
                    return Type_Zero;
                case 4:
                    if (position==0)
                        return Type_Seven;
                    else if (position==1)
                        return Type_Six;
                    else
                        return Type_Five;
            }
            return 0;
        }

        @Override
        public int getViewTypeCount() {
            return 8;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            ViewHolder_00 ViewHolder_00;
            ViewHolder_01 ViewHolder_01;
            ViewHolder_02 ViewHolder_02;
            ViewHolder_03 ViewHolder_03;
            ViewHolder_04 ViewHolder_04;
            ViewHolder_05 ViewHolder_05;
            ViewHolder_06 ViewHolder_06;
            ViewHolder_07 ViewHolder_07;
            final String[] Strings0 = new String[] {"HTML/CSS","Javascript","HTML5","前端工具","WebApp"};
            final String[] Strings1 = new String[] {"Android","ios","Unity_3D","Cocos2d_x"};
            final String[] Strings2 = new String[] {"大数据","云计算"};
            final String[] Strings3 = new String[] {"Java","Python","PHP","C++","C#"};
            final String[] StringMy = new String[] {"我的课程","我的提问","我的笔记","我的关注","个人信息","关于我们"};
            int Type=getItemViewType(position);
            if (Type==Type_Zero) {
                if (convertView==null) {
                    convertView=getLayoutInflater().inflate(R.layout.list_home_02,parent,false);
                    ViewHolder_00=new ViewHolder_00();
                    ViewHolder_00.list_home_02_text= (TextView) convertView.findViewById(R.id.list_home_02_text);
                    convertView.setTag(ViewHolder_00);
                }
                else
                    ViewHolder_00= (ViewHolder_00) convertView.getTag();
                if (position==0)
                    ViewHolder_00.list_home_02_text.setText("新课速递");
                else if (position==3)
                    ViewHolder_00.list_home_02_text.setText("课程推荐");
                else if (position==6)
                    ViewHolder_00.list_home_02_text.setText("猜你喜欢");
            }
            else {
                if (Type == Type_One) {
                    if (convertView == null) {
                        convertView = getLayoutInflater().inflate(R.layout.list_home_03, parent, false);
                        ViewHolder_01 = new ViewHolder_01();
                        ViewHolder_01.list_home_03_text1 = (TextView) convertView.findViewById(R.id.list_home_03_text1);
                        ViewHolder_01.list_home_03_text2 = (TextView) convertView.findViewById(R.id.list_home_03_text2);
                        ViewHolder_01.list_home_03_btn1 = (Button) convertView.findViewById(R.id.list_home_03_btn1);
                        ViewHolder_01.list_home_03_btn2 = (Button) convertView.findViewById(R.id.list_home_03_btn2);
                        convertView.setTag(ViewHolder_01);
                    } else
                        ViewHolder_01 = (ViewHolder_01) convertView.getTag();
                    if (position < 3)
                        Type_Two_indx = position - 1;
                    else if (position < 6)
                        Type_Two_indx = position - 2;
                    else
                        Type_Two_indx = position - 3;
                    final String s1 = (String) home03_Data.get(Type_Two_indx).get("list_home_03_text1");
                    ViewHolder_01.list_home_03_text1.setText(s1);
                    ViewHolder_01.list_home_03_btn1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent I = new Intent(Main_01.this, List_03.class);
                            I.putExtra("name", s1);
                            startActivity(I);
                        }
                    });
                    final String s2 = (String) home03_Data.get(Type_Two_indx).get("list_home_03_text2");
                    ViewHolder_01.list_home_03_text2.setText(s2);
                    ViewHolder_01.list_home_03_btn2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent I = new Intent(Main_01.this, List_03.class);
                            I.putExtra("name", s2);
                            startActivity(I);
                        }
                    });
                } else if (Type == Type_Two) {
                    if (convertView == null) {
                        convertView = getLayoutInflater().inflate(R.layout.list_list_01, parent, false);
                        ViewHolder_02 = new ViewHolder_02();
                        ViewHolder_02.list_list_01_Title =
                                (TextView) convertView.findViewById(R.id.list_list_01_title);
                        ViewHolder_02.list_list_01_btn_1 =
                                (ImageButton) convertView.findViewById(R.id.list_list_01_btn_1);
                        ViewHolder_02.list_list_01_btn_2 =
                                (ImageButton) convertView.findViewById(R.id.list_list_01_btn_2);
                        ViewHolder_02.list_list_01_btn_3 =
                                (ImageButton) convertView.findViewById(R.id.list_list_01_btn_3);
                        ViewHolder_02.list_list_01_btn_4 =
                                (ImageButton) convertView.findViewById(R.id.list_list_01_btn_4);
                        ViewHolder_02.list_list_01_btn_5 =
                                (ImageButton) convertView.findViewById(R.id.list_list_01_btn_5);
                        convertView.setTag(ViewHolder_02);
                    } else
                        ViewHolder_02 = (ViewHolder_02) convertView.getTag();
                    if (position == 0)
                        Type_Two_indx = 0;
                    else if (position == 3)
                        Type_Two_indx = 1;
                    ViewHolder_02.list_list_01_Title.setText((String)
                            list01_Data.get(Type_Two_indx).get("list_list_01_Title"));
                    ViewHolder_02.list_list_01_btn_1.setBackgroundResource((Integer)
                            list01_Data.get(Type_Two_indx).get("list_list_01_g_1"));
                    ViewHolder_02.list_list_01_btn_2.setBackgroundResource((Integer)
                            list01_Data.get(Type_Two_indx).get("list_list_01_g_2"));
                    ViewHolder_02.list_list_01_btn_3.setBackgroundResource((Integer)
                            list01_Data.get(Type_Two_indx).get("list_list_01_g_3"));
                    ViewHolder_02.list_list_01_btn_4.setBackgroundResource((Integer)
                            list01_Data.get(Type_Two_indx).get("list_list_01_g_4"));
                    ViewHolder_02.list_list_01_btn_5.setBackgroundResource((Integer)
                            list01_Data.get(Type_Two_indx).get("list_list_01_g_5"));
                    ImageButton[] Buttons = new ImageButton[]{
                            ViewHolder_02.list_list_01_btn_1,
                            ViewHolder_02.list_list_01_btn_2,
                            ViewHolder_02.list_list_01_btn_3,
                            ViewHolder_02.list_list_01_btn_4,
                            ViewHolder_02.list_list_01_btn_5,
                    };
                    for (int i = 0; i < 5; i++) {
                        final int finalI = i;
                        Buttons[i].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent I = new Intent(Main_01.this, List_02.class);
                                if (position == 0)
                                    I.putExtra("TITLE", Strings0[finalI]);
                                else if (position == 3)
                                    I.putExtra("TITLE", Strings3[finalI]);
                                startActivity(I);
                            }
                        });
                    }
                } else if (Type == Type_Three) {
                    if (convertView == null) {
                        convertView = getLayoutInflater().inflate(R.layout.list_list_02, parent, false);
                        ViewHolder_03 = new ViewHolder_03();
                        ViewHolder_03.list_list_02_Title =
                                (TextView) convertView.findViewById(R.id.list_list_02_title);
                        ViewHolder_03.list_list_02_btn_1 =
                                (ImageButton) convertView.findViewById(R.id.list_list_02_btn_1);
                        ViewHolder_03.list_list_02_btn_2 =
                                (ImageButton) convertView.findViewById(R.id.list_list_02_btn_2);
                        ViewHolder_03.list_list_02_btn_3 =
                                (ImageButton) convertView.findViewById(R.id.list_list_02_btn_3);
                        ViewHolder_03.list_list_02_btn_4 =
                                (ImageButton) convertView.findViewById(R.id.list_list_02_btn_4);
                        convertView.setTag(ViewHolder_03);
                    } else
                        ViewHolder_03 = (ViewHolder_03) convertView.getTag();
                    if (position == 1)
                        Type_Three_indx = 0;
                    ViewHolder_03.list_list_02_Title.setText((String)
                            list02_Data.get(Type_Three_indx).get("list_list_02_Title"));
                    ViewHolder_03.list_list_02_btn_1.setBackgroundResource((Integer)
                            list02_Data.get(Type_Three_indx).get("list_list_02_g_1"));
                    ViewHolder_03.list_list_02_btn_2.setBackgroundResource((Integer)
                            list02_Data.get(Type_Three_indx).get("list_list_02_g_2"));
                    ViewHolder_03.list_list_02_btn_3.setBackgroundResource((Integer)
                            list02_Data.get(Type_Three_indx).get("list_list_02_g_3"));
                    ViewHolder_03.list_list_02_btn_4.setBackgroundResource((Integer)
                            list02_Data.get(Type_Three_indx).get("list_list_02_g_4"));
                    ImageButton[] Buttons = new ImageButton[]{
                            ViewHolder_03.list_list_02_btn_1,
                            ViewHolder_03.list_list_02_btn_2,
                            ViewHolder_03.list_list_02_btn_3,
                            ViewHolder_03.list_list_02_btn_4,
                    };
                    for (int i = 0; i < 4; i++) {
                        final int finalI = i;
                        Buttons[i].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent I = new Intent(Main_01.this, List_02.class);
                                I.putExtra("TITLE", Strings1[finalI]);
                                startActivity(I);
                            }
                        });
                    }
                } else if (Type == Type_Four) {
                    if (convertView == null) {
                        convertView = getLayoutInflater().inflate(R.layout.list_list_03, parent, false);
                        ViewHolder_04 = new ViewHolder_04();
                        ViewHolder_04.list_list_03_Title =
                                (TextView) convertView.findViewById(R.id.list_list_03_title);
                        ViewHolder_04.list_list_03_btn_1 =
                                (ImageButton) convertView.findViewById(R.id.list_list_03_btn_1);
                        ViewHolder_04.list_list_03_btn_2 =
                                (ImageButton) convertView.findViewById(R.id.list_list_03_btn_2);
                        convertView.setTag(ViewHolder_04);
                    } else
                        ViewHolder_04 = (ViewHolder_04) convertView.getTag();
                    if (position == 2)
                        Type_Four_indx = 0;
                    ViewHolder_04.list_list_03_Title.setText((String)
                            list03_Data.get(Type_Four_indx).get("list_list_03_Title"));
                    ViewHolder_04.list_list_03_btn_1.setBackgroundResource((Integer)
                            list03_Data.get(Type_Four_indx).get("list_list_03_g_1"));
                    ViewHolder_04.list_list_03_btn_2.setBackgroundResource((Integer)
                            list03_Data.get(Type_Four_indx).get("list_list_03_g_2"));
                    ImageButton[] Buttons = new ImageButton[]{
                            ViewHolder_04.list_list_03_btn_1,
                            ViewHolder_04.list_list_03_btn_2,
                    };
                    for (int i = 0; i < 2; i++) {
                        final int finalI = i;
                        Buttons[i].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent I = new Intent(Main_01.this, List_02.class);
                                I.putExtra("TITLE", Strings2[finalI]);
                                startActivity(I);
                            }
                        });
                    }
                } else if (Type == Type_Five) {
                    if (convertView == null) {
                        convertView = getLayoutInflater().inflate(R.layout.list_info_02, parent, false);
                        ViewHolder_05 = new ViewHolder_05();
                        ViewHolder_05.list_info_02_text = (Button) convertView.findViewById(R.id.list_info_02_text);
                        convertView.setTag(ViewHolder_05);
                    } else
                        ViewHolder_05 = (ViewHolder_05) convertView.getTag();
                    Type_Five_indx = position - 2;
                    ViewHolder_05.list_info_02_text.setText((String)
                            info02_Data.get(Type_Five_indx).get("list_info_02_text"));

                    final int LL_Five = Type_Five_indx;
                    ViewHolder_05.list_info_02_text.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent I = new Intent();
                            if (LL_Five <= 3) {
                                I = new Intent(Main_01.this, info_my.class);
                            } else if (LL_Five == 4) {
                                I = new Intent(Main_01.this, new_note.class);
                            }
                            else if (LL_Five == 5) {
                                I = new Intent(Main_01.this, info_self.class);
                            } else if (LL_Five == 6) {
                                I = new Intent(Main_01.this, info_AboutUs.class);
                            }
                            I.putExtra("TITLE", StringMy[LL_Five]);
                            startActivity(I);
                        }
                    });
                } else if (Type == Type_Six) {
                    if (convertView == null) {
                        convertView = getLayoutInflater().inflate(R.layout.list_info_03, parent, false);
                        ViewHolder_06 = new ViewHolder_06();
                        ViewHolder_06.list_info_03_btn1 = (Button) convertView.findViewById(R.id.list_info_03_btn1);
                        ViewHolder_06.list_info_03_btn2 = (Button) convertView.findViewById(R.id.list_info_03_btn2);
                        ViewHolder_06.list_info_03_btn3 = (Button) convertView.findViewById(R.id.list_info_03_btn3);
                        ViewHolder_06.list_info_03_btn4 = (Button) convertView.findViewById(R.id.list_info_03_btn4);
                        convertView.setTag(ViewHolder_06);
                    } else
                        ViewHolder_06 = (ViewHolder_06) convertView.getTag();

                    Button[] ButtonMy = new Button[]{
                            ViewHolder_06.list_info_03_btn1,
                            ViewHolder_06.list_info_03_btn2,
                            ViewHolder_06.list_info_03_btn3,
                            ViewHolder_06.list_info_03_btn4,
                    };
                    for (int i = 0; i <= 3; i++) {
                        ButtonMy[i].setText(StringMy[i]);
                        final int finalI = i;
                        ButtonMy[i].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent I = new Intent(Main_01.this, info_my.class);
                                I.putExtra("TITLE", StringMy[finalI]);
                                startActivity(I);
                            }
                        });
                    }
                } else if (Type == Type_Seven) {
                    if (convertView == null) {
                        convertView = getLayoutInflater().inflate(R.layout.list_info_01, parent, false);
                        ViewHolder_07 = new ViewHolder_07();
                        ViewHolder_07.list_info_01_img = (ImageView) convertView.findViewById(R.id.list_info_01_img);
                        ViewHolder_07.list_info_01_text1 = (TextView) convertView.findViewById(R.id.list_info_01_text1);
                        ViewHolder_07.list_info_01_text2 = (TextView) convertView.findViewById(R.id.list_info_01_text2);
                        convertView.setTag(ViewHolder_07);
                    } else
                        ViewHolder_07 = (ViewHolder_07) convertView.getTag();
                    ViewHolder_07.list_info_01_img.setImageResource(R.drawable.hahaha);
                    ViewHolder_07.list_info_01_text1.setText("ID");
                    ViewHolder_07.list_info_01_text2.setText("个人介绍");
                }
            }
            return convertView;
        }

        private class ViewHolder_00 {
            private TextView list_home_02_text;
        }

        private class ViewHolder_01 {
            private TextView list_home_03_text1;
            private TextView list_home_03_text2;
            private Button list_home_03_btn1;
            private Button list_home_03_btn2;
        }

        private class ViewHolder_02 {
            public TextView list_list_01_Title;
            public ImageButton list_list_01_btn_1;
            public ImageButton list_list_01_btn_2;
            public ImageButton list_list_01_btn_3;
            public ImageButton list_list_01_btn_4;
            public ImageButton list_list_01_btn_5;
        }

        private class ViewHolder_03 {
            public TextView list_list_02_Title;
            public ImageButton list_list_02_btn_1;
            public ImageButton list_list_02_btn_2;
            public ImageButton list_list_02_btn_3;
            public ImageButton list_list_02_btn_4;
        }

        private class ViewHolder_04 {
            public TextView list_list_03_Title;
            public ImageButton list_list_03_btn_1;
            public ImageButton list_list_03_btn_2;
        }

        private class ViewHolder_05 {
            public Button list_info_02_text;
        }

        private class ViewHolder_06 {
            public Button list_info_03_btn1;
            public Button list_info_03_btn2;
            public Button list_info_03_btn3;
            public Button list_info_03_btn4;
        }

        private class ViewHolder_07 {
            public ImageView list_info_01_img;
            public TextView list_info_01_text1;
            public TextView list_info_01_text2;
        }
    }
}