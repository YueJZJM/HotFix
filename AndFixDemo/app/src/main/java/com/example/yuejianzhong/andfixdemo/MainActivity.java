package com.example.yuejianzhong.andfixdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String FILE_END = ".apatch";
    private String mPatchDir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.create).setOnClickListener(this);
        findViewById(R.id.solution).setOnClickListener(this);
        mPatchDir = getExternalCacheDir().getAbsolutePath() + "/apatch/";
        File file = new File(mPatchDir);
        if (file == null || file.exists()) {
            file.mkdirs();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.create:
                Log.d("yuejz", "bug1111");
                break;
            case R.id.solution:
                Log.d("yuejz", "no bug11111");
                AndFixPachManager.getInstance().addPatch(getPatchName());
                Log.d("yuejz", getPatchName());
                break;
        }
    }

    private String getPatchName() {
        return mPatchDir.concat("imooc").concat(FILE_END);
    }
}
