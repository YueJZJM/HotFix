package com.example.yuejianzhong.classloaderdemo.plugin;

import android.content.res.AssetManager;
import android.content.res.Resources;

import dalvik.system.DexClassLoader;

public class PluginInfo {
    public DexClassLoader mDexClassLoader;
    public AssetManager mAssetManager;
    public Resources mResources;
}
