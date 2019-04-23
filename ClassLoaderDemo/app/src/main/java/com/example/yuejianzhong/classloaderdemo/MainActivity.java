package com.example.yuejianzhong.classloaderdemo;

import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String apkPath = getExternalCacheDir().getAbsolutePath() + "/bundle.apk";
        Log.d("yuejz", apkPath);
        loadApk(apkPath);
    }

    private void loadApk(String apkPath) {
        File optDir = getDir("opt", MODE_PRIVATE);
        DexClassLoader classLoader = new DexClassLoader(apkPath, optDir.getAbsolutePath(), null,
                getClassLoader());

        try {
            Class cls = classLoader.loadClass("com.example.bundle.BundleUtile");
            if (cls != null) {
                Object instance = cls.newInstance();
                Method method = cls.getMethod("printLog");
                method.invoke(instance);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
