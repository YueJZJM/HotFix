package com.example.yuejianzhong.andfixdemo;

import android.app.Application;

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initAndFix();
    }

    private void initAndFix() {
        AndFixPachManager.getInstance().initPatch(this);
    }
}
