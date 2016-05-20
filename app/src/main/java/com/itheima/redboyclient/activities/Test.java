package com.itheima.redboyclient.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.itheima.redboyclient.R;
import com.itheima.redboyclient.DB.MyDBOpenHelper;
import com.itheima.redboyclient.dao.DbUtils;

/**
 * Created by 李正春 on 2016/4/4.
 */
public class Test extends Activity {

    private DbUtils db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        MyDBOpenHelper helper = new MyDBOpenHelper(this);
        helper.getReadableDatabase();
        db = new DbUtils(this);
    }

    public void insert(View view) {
        db.insert("赝品", "88", "3", "ML", "粉红色");
        db.insert("赝品", "8玩玩", "3", "ML", "粉红色");
    }

    public void delete(View view) {
        db.delete("88");
    }

    public void update(View view) {
        db.update("88", "22", "MM", "黑色");
    }

    public void queryAll(View view) {
        db.queryAll();
    }

    public void query(View view) {
        db.query("88");
    }
}
