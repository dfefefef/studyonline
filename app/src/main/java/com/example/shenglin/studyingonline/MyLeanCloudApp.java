package com.example.shenglin.studyingonline;


import android.app.Application;

import com.avos.avoscloud.AVOSCloud;

public class MyLeanCloudApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // 初始化参数依次为 this, AppId, AppKey
        AVOSCloud.initialize(this,"FMbCiPEV38qtyKrxrduwmxq7-gzGzoHsz","LCionmLqGipFxmF3jdVtMVEn"

        );
        AVOSCloud.setDebugLogEnabled(true);
    }
}