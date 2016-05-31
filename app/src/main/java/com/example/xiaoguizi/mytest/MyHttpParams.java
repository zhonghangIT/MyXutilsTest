package com.example.xiaoguizi.mytest;

import android.os.Environment;

import org.xutils.common.util.FileUtil;
import org.xutils.http.RequestParams;
import org.xutils.http.annotation.HttpRequest;
import org.xutils.http.annotation.HttpResponse;
import org.xutils.http.app.DefaultParamsBuilder;
import org.xutils.http.app.ResponseParser;

import java.io.File;

/**
 * Created by xiaoguizi on 16/5/27.
 */
@HttpRequest(host="http://10.0.14.103:8080",
        path="/MyWebTest/MyFirstServlet",
        builder = DefaultParamsBuilder.class)
public class MyHttpParams extends RequestParams {
    private String name="jim";
    private String sex="male";
    private String[] family={"lucy","lili"};
    public String clazz;
    //http://10.0.14.103:8080/MyWebTest/MyFirstServlet?name=jim&sex=male&family=lucy&family="lili"
    public MyHttpParams(){
    }
}
