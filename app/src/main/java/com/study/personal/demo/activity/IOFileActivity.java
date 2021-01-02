package com.study.personal.demo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.study.personal.demo.R;

import java.io.File;
import java.io.FileOutputStream;

public class IOFileActivity extends Activity {
    private static String TAG = IOFileActivity.class.getSimpleName();
    private Button mBtnNewFile;
    private TextView mTvFilePath;

    private String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_io_file);
        mBtnNewFile = findViewById(R.id.btn_new_file);
        mTvFilePath = findViewById(R.id.tv_file_path);
        mBtnNewFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long currentTime = System.currentTimeMillis();
                //"/demoAPP/firstFolder"：此部分为新建的文件夹名称
                path = Environment.getExternalStorageDirectory() + "/demoAPP/firstFolder" ;
                createFolderAndFile(currentTime);
            }
        });
    }

    public void createFolderAndFile(long time) {
        File file = new File(path);
        if (!file.exists()) {
            try {
                file.mkdirs();
            } catch (Exception e) {
                Log.e(TAG, "folder err msg :" + e.getMessage());
            }
        }
        String txt = "我是currentTime_" + time + ".txt文件中的内容";
        // "/currentTime_" + time + ".txt"" : 此部分为新建的文件的名称
        File dir = new File(path + "/currentTime_" + time + ".txt");
        if (!dir.exists()) {
            try {
                //在指定的文件夹中创建文件
                dir.createNewFile();
                //创建文件后向文件中写入内容
                FileOutputStream outputStream =new FileOutputStream(dir);
                outputStream.write(txt.getBytes());
                outputStream.flush();
                outputStream.close();
            } catch (Exception e) {
                Log.e(TAG, "file err msg :" + e.getMessage());
            }
        }
        runOnUiThread(()->{
            mTvFilePath.setText("文件生成路径：" + path + "/currentTime_" + time + ".txt");
        });

    }

}
