package com.example.xiaoguizi.mytest.fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.xiaoguizi.mytest.R;

import org.xutils.common.Callback;
import org.xutils.common.util.DensityUtil;
import org.xutils.image.ImageOptions;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by xiaoguizi on 16/5/30.
 */
@ContentView(R.layout.fragment_image)
public class ImageFragment extends Fragment {
    @ViewInject(R.id.imageview_show)
    private ImageView imageView;
    @ViewInject(R.id.progress)
    private ProgressBar progressBar;
    @ViewInject(R.id.imageview_gif)
    private ImageView imageViewGif;
    @ViewInject(R.id.progress_gif)
    private ProgressBar progressBarGif;
    @ViewInject(R.id.imageview_webp)
    private ImageView imageViewWebP;
    @ViewInject(R.id.progress_webp)
    private ProgressBar progressBarWebP;
    ImageOptions options = new ImageOptions.Builder().setRadius(DensityUtil.dip2px(5))
            .setLoadingDrawableId(R.mipmap.ic_launcher).setUseMemCache(true).setIgnoreGif(false)
            .setFailureDrawableId(R.mipmap.ic_launcher).build();

    String[] urls = {
            "https://raw.githubusercontent.com/zhonghangIT/MyXutilsTest/master/testimage/flower.jpg",
            "https://raw.githubusercontent.com/zhonghangIT/MyXutilsTest/master/testimage/flower.webp",
            "https://raw.githubusercontent.com/zhonghangIT/MyXutilsTest/master/testimage/coder.gif"
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return x.view().inject(this, inflater, container);
    }

    @Event(R.id.button_show_image)
    private void show(View view) {
//        初始化加载图片的设置option
        x.image().bind(imageView, urls[0], options, new Callback.ProgressCallback<Drawable>() {
            @Override
            public void onWaiting() {

            }

            @Override
            public void onStarted() {

            }

            @Override
            public void onLoading(long total, long current, boolean isDownloading) {
                progressBar.setProgress((int) ((float) current / total * 100));
            }

            @Override
            public void onSuccess(Drawable result) {
                Toast.makeText(getContext(), "成功显示图片", Toast.LENGTH_SHORT).show();
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


    @Event(R.id.button_gif)
    private void bindGif(View view) {
        x.image().bind(imageViewGif, urls[2], options, new Callback.ProgressCallback<Drawable>() {
            @Override
            public void onWaiting() {


            }

            @Override
            public void onStarted() {

            }

            @Override
            public void onLoading(long total, long current, boolean isDownloading) {
                progressBarGif.setProgress((int) ((float) current / total * 100));
            }

            @Override
            public void onSuccess(Drawable result) {

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

    @Event(R.id.button_webp)
    private void bindWebP(View view) {
        x.image().bind(imageViewWebP, urls[1], options, new Callback.ProgressCallback<Drawable>() {
            @Override
            public void onWaiting() {

            }

            @Override
            public void onStarted() {

            }

            @Override
            public void onLoading(long total, long current, boolean isDownloading) {
                progressBarWebP.setProgress((int) ((float) current / total * 100));
            }

            @Override
            public void onSuccess(Drawable result) {

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

}
