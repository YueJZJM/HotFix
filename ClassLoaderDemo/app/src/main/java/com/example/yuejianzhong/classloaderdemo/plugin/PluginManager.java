package com.example.yuejianzhong.classloaderdemo.plugin;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.widget.Button;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

import dalvik.system.DexClassLoader;

public class PluginManager {
    private static PluginManager mInstance;

    private static Context mContext;

    private static File mOptFile;

    private static HashMap<String, PluginInfo> sInfoHashMap;

    private PluginManager(Context context) {
        mContext = context;
        mOptFile = mContext.getDir("opt", mContext.MODE_PRIVATE);
    }

    public static PluginManager getInstance(Context context) {
        if (mInstance == null) {
            synchronized (PluginManager.class) {
                if (mInstance == null) {
                    mInstance = new PluginManager(context);
                    sInfoHashMap = new HashMap<>();
                }
            }
        }
        return mInstance;
    }

    //为插件 apk 创建对应的 classloader
    private static DexClassLoader createPluginDexClassLoader(String apkPath) {
        DexClassLoader classLoader = new DexClassLoader(apkPath,
                mOptFile.getAbsolutePath(),null,null);
        return classLoader;
    }

    // 为插件 apk 创建 AssetManager
    private static AssetManager createPluginAssetManager(String apkPath) {
        try {
            AssetManager assetManager = AssetManager.class.newInstance();
            Method addAssetPath = assetManager.getClass().getMethod("addAssetPath",
                    String.class);
            addAssetPath.invoke(assetManager, apkPath);
            return assetManager;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    //为对应的插件创建 resources
    private static Resources createPlugResources(String apkPath) {
        AssetManager assetManager = createPluginAssetManager(apkPath);

        Resources superResource = mContext.getResources();

        Resources pluginResource = new Resources(assetManager, superResource.getDisplayMetrics(),
                superResource.getConfiguration());

        return pluginResource;
    }

    public static PluginInfo loadApk(String apkPath) {
        if (sInfoHashMap.get(apkPath) != null) {
            return sInfoHashMap.get(apkPath);
        }
        PluginInfo pluginInfo = new PluginInfo();
        pluginInfo.mDexClassLoader = createPluginDexClassLoader(apkPath);
        pluginInfo.mAssetManager = createPluginAssetManager(apkPath);
        pluginInfo.mResources = createPlugResources(apkPath);
        sInfoHashMap.put(apkPath, pluginInfo);
        return pluginInfo;
    }
}
