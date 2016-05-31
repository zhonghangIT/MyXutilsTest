package com.example.xiaoguizi.mytest.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.xiaoguizi.mytest.R;
import com.example.xiaoguizi.mytest.dao.Student;

import org.xutils.DbManager;
import org.xutils.common.util.KeyValue;
import org.xutils.db.sqlite.WhereBuilder;
import org.xutils.ex.DbException;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * Created by xiaoguizi on 16/5/30.
 */

@ContentView(R.layout.fragment_db)
public class DbFragment extends Fragment {
    private int id;
    DbManager.DaoConfig config = new DbManager.DaoConfig().setDbName("test.db")
            .setDbVersion(2)
            .setDbOpenListener(new DbManager.DbOpenListener() {
                @Override
                public void onDbOpened(DbManager db) {
                    db.getDatabase().enableWriteAheadLogging();
                }
            }).setDbUpgradeListener(new DbManager.DbUpgradeListener() {
                @Override
                public void onUpgrade(DbManager db, int oldVersion, int newVersion) {

                }
            });


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return x.view().inject(this, inflater, container);
    }

    @Event(R.id.button_insert)
    private void insert(View view) {
        DbManager manager = x.getDb(config);

        Student zhangsan = new Student();
        zhangsan.setName("张三");
        zhangsan.setSex("男");
        try {
            manager.saveBindingId(zhangsan);
        } catch (DbException e) {
            e.printStackTrace();
        }
        id = zhangsan.getId();
        Toast.makeText(getContext(), "数据插入成功 id=" + id, Toast.LENGTH_SHORT).show();
    }

    @Event(R.id.button_update)
    private void update(View view) {
        DbManager manager = x.getDb(config);
        try {
            manager.update(Student.class, WhereBuilder.b("id", "=", id), new KeyValue("name", "李四"));
        } catch (DbException e) {
            e.printStackTrace();
        }
        Toast.makeText(getContext(), "数据修改", Toast.LENGTH_SHORT).show();
    }

    @Event(R.id.button_select)
    private void select(View view) {
        DbManager manager = x.getDb(config);
        try {
            List<Student> students = manager.findAll(Student.class);
            String content = "";
            for (Student student : students) {
                content += student.getId() + " 姓名: " + student.getName() + "    ";
            }
            Toast.makeText(getContext(), content, Toast.LENGTH_SHORT).show();
        } catch (DbException e) {
            e.printStackTrace();
        }
    }
}

