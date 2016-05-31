package com.example.xiaoguizi.mytest;

import android.content.Context;
import android.os.Environment;

import org.xutils.common.util.FileUtil;
import org.xutils.http.RequestParams;
import org.xutils.http.annotation.HttpRequest;
import org.xutils.http.app.DefaultParamsBuilder;
import org.xutils.x;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaoguizi on 16/5/27.
 */
@HttpRequest(host = "http://10.0.14.103:8080",
path="/MyWebTest/UploadFile",
builder = DefaultParamsBuilder.class)
public class MyUploadImageParams extends RequestParams{
    private String name="jim";
//    private byte[] uploadFile;
//    private File file;
    private List<File> fileList;
    public MyUploadImageParams(){
        //上传文件
        setMultipart(true);//只能在此之后上传文件
        File file=x.app().getFileStreamPath(Environment.getExternalStorageDirectory()+"/aa.jpg");
        fileList=new ArrayList<>();
        fileList.add(file);
    }
}
