package com.example.yuejianzhong.tinkerdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.yuejianzhong.tinkerdemo.tinker.TinkerManager;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private static final String FILE_END = ".apk";
    private String mPatchDir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPatchDir = getExternalCacheDir().getAbsolutePath() + "/tpatch/";
        Log.d("yuejz", mPatchDir);
        File file = new File(mPatchDir);
        if (file == null || file.exists()) {
            file.mkdir();
        }
    }

    public void loadPatch(View view) {
        TinkerManager.loadPatch(getPatchName());
    }

    private String getPatchName() {
        return mPatchDir.concat("imooc").concat(FILE_END);
    }
}
