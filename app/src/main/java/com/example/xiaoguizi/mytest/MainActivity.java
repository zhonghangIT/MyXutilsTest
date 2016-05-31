package com.example.xiaoguizi.mytest;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.Settings;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.xiaoguizi.mytest.adapter.MyFragmentAdapter;
import com.example.xiaoguizi.mytest.fragment.DbFragment;
import com.example.xiaoguizi.mytest.fragment.HttpFragment;
import com.example.xiaoguizi.mytest.fragment.ImageFragment;

import org.xutils.DbManager;
import org.xutils.common.Callback;
import org.xutils.common.task.PriorityExecutor;
import org.xutils.ex.HttpException;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
@ContentView(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    @ViewInject(R.id.tablayout)
    private TabLayout tablayout;
    @ViewInject(R.id.viewpager)
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        FragmentManager manager = getSupportFragmentManager();
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new HttpFragment());
        fragments.add(new ImageFragment());
        fragments.add(new DbFragment());
        MyFragmentAdapter adapter = new MyFragmentAdapter(manager, fragments);
        viewPager.setAdapter(adapter);
//        tablayout.addTab(tablayout.newTab().setText("网络连接"));
//        tablayout.addTab(tablayout.newTab().setText("图片加载"));
//        tablayout.addTab(tablayout.newTab().setText("数据库"));
        tablayout.setupWithViewPager(viewPager);
        tablayout.getTabAt(0).setText("网络连接");
        tablayout.getTabAt(1).setText("图片加载");
        tablayout.getTabAt(2).setText("数据库");
    }


}
