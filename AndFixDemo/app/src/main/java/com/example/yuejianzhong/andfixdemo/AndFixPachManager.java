package com.example.yuejianzhong.andfixdemo;

import android.content.Context;

import com.alipay.euler.andfix.AndFixManager;
import com.alipay.euler.andfix.patch.PatchManager;

import java.nio.file.PathMatcher;

public class AndFixPachManager {
    private static AndFixPachManager mInstance = null;

    private static PatchManager mPathMatcher = null;

    public static AndFixPachManager getInstance() {
        if (mInstance == null) {
            synchronized (AndFixManager.class) {
                if (mInstance == null) {
                    mInstance = new AndFixPachManager();
                }

            }
        }
        return mInstance;
    }

    public void initPatch(Context context) {
        mPathMatcher = new PatchManager(context);
        mPathMatcher.init("1.0.0");
        mPathMatcher.loadPatch();
    }

    public void addPatch(String path) {
        try {
            if (mPathMatcher != null) {
                mPathMatcher.addPatch(path);
            }
        } catch (Exception e) {

        }

    }
}
