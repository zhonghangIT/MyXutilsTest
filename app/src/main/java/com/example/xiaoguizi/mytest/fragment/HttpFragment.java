package com.example.xiaoguizi.mytest.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.xiaoguizi.mytest.MainActivity;
import com.example.xiaoguizi.mytest.MyHttpParams;
import com.example.xiaoguizi.mytest.MyUploadImageParams;
import com.example.xiaoguizi.mytest.R;

import org.xutils.common.Callback;
import org.xutils.common.task.PriorityExecutor;
import org.xutils.ex.HttpException;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.x;

import java.io.File;
import java.util.concurrent.Executor;

/**
 * Created by xiaoguizi on 16/5/30.
 */
@ContentView(R.layout.fragment_http)
public class HttpFragment extends Fragment {
    private final static int MAX_DOWNLOAD_THREAD = 2; // 有效的值范围[1, 3], 设置为3时, 可能阻塞图片加载.
    private final Executor executor = new PriorityExecutor(MAX_DOWNLOAD_THREAD, true);

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return x.view().inject(this, inflater, container);
    }

    @Event(R.id.button_check)
    private void checkPermisson(View v) {
        Intent intent2 = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent2.setData(Uri.parse("package:" + x.app().getPackageName()));
        startActivity(intent2);
    }

    @Event(R.id.button_cache)
    private void useCache(View view) {



        //使用get请求  url 创建一个类继承RequestParams 使用注解@HttpRequest host 地址和端口 path 具体的接口 bulider DefaultParamsBuilder
        //  提交参数 参数RequestParams的子类的属性
        // 连接成功 失败  回调参数 CommonCallback
        //

//        使用网络连接缓存
        MyHttpParams params = new MyHttpParams();
        params.clazz="三班";
        //开始请求数据
       Callback.Cancelable cancel= x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Toast.makeText(getContext(), "返回的数据"+result, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });



        x.http().get(params, new Callback.CacheCallback<String>() {
            @Override
            public boolean onCache(String result) {
                Toast.makeText(x.app(), "缓存内容" + result, Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public void onSuccess(String result) {
                Toast.makeText(x.app(), "网络连接成功" + result, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(x.app(), "连接错误", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    @Event(R.id.button_download)
    private void download(View v) {
        // 文件的下载

        //创建请求参数
        RequestParams params = new RequestParams("http://10.0.14.103:8080/MyWebTest/android/aa.jpg");
//        RequestParams params = new RequestParams("http://dl.bintray.com/wyouflf/maven/org/xutils/xutils/3.3.34/xutils-3.3.34.aar");
//        设置是否断点续传
        params.setAutoResume(true);
//        是否重命名
        params.setAutoRename(false);
//        设置保存位置
        params.setSaveFilePath(Environment.getExternalStorageDirectory() + "/aa.jpg");
//        设置下载的线程池
        params.setExecutor(executor);
//        设置快速取消
        params.setCancelFast(true);
//        进行下载,ProgressCallback的泛型必须给File类型
        x.http().post(params, new Callback.ProgressCallback<File>() {
            @Override
            public void onWaiting() {

            }

            @Override
            public void onStarted() {

            }

            @Override
            public void onLoading(long total, long current, boolean isDownloading) {
                if(isDownloading){
//                    得到下载的进度   (int)((float)current/totla*100)
                }
            }

            @Override
            public void onSuccess(File result) {

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });





    }

    class DownloadCallback implements Callback.CommonCallback<File>, Callback.ProgressCallback<File> {

        @Override
        public void onSuccess(File result) {
            Toast.makeText(x.app(), "下载成功", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onError(Throwable ex, boolean isOnCallback) {
            Toast.makeText(x.app(), "下载失败" + ex.getMessage(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancelled(CancelledException cex) {

        }

        @Override
        public void onFinished() {

        }

        @Override
        public void onWaiting() {

        }

        @Override
        public void onStarted() {

        }

        @Override
        public void onLoading(long total, long current, boolean isDownloading) {
            Toast.makeText(x.app(), current + "下载中" + total + isDownloading, Toast.LENGTH_SHORT).show();
        }
    }

    @Event(R.id.button_upload)
    private void uploadFile(View v) {
            //提交普通的数据
            //上传文件 流 byte[]
        //提交带有文件的参数
            //设置回调
            //上传的文件比较大
        MyUploadImageParams params = new MyUploadImageParams();

        x.http().post(params, new Callback.ProgressCallback<String>() {
            @Override
            public void onWaiting() {

            }

            @Override
            public void onStarted() {

            }

            @Override
            public void onLoading(long total, long current, boolean isDownloading) {
                //total上传的文件的总字节数
//                current已经上传的文件的字节数
//                isDownloading
                if(!isDownloading){
                    int progress=(int)((float)current/total*100);
                }
            }

            @Override
            public void onSuccess(String result) {

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });


        //显示上传的进度
        x.http().post(params, new Callback.ProgressCallback<String>() {
            @Override
            public void onWaiting() {

            }

            @Override
            public void onStarted() {

            }

            @Override
            public void onLoading(long total, long current, boolean isDownloading) {
                Toast.makeText(x.app(), current + "正在上传" + total, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess(String result) {
                Toast.makeText(x.app(), "成功回调" + result, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(x.app(), "上传失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    @Event(R.id.button_http)
    private void connectHttp(View v) {
        MyHttpParams params = new MyHttpParams();
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Toast.makeText(x.app(), "成功回调" + result, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(x.app(), "返回错误", Toast.LENGTH_SHORT).show();
                if (ex instanceof HttpException) { // 网络错误
                    HttpException httpEx = (HttpException) ex;
                    int responseCode = httpEx.getCode();
                    String responseMsg = httpEx.getMessage();
                    String errorResult = httpEx.getResult();
                    Toast.makeText(x.app(), "错误信息" + responseCode + responseMsg, Toast.LENGTH_SHORT).show();
                    Log.d("tag", "错误信息:" + responseCode + responseMsg + errorResult);
                } else { // 其他错误
                    // ...
                    Toast.makeText(x.app(), "错误信息" + ex.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.d("tag", "错误信息:" + ex.getMessage());
                }
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

}
