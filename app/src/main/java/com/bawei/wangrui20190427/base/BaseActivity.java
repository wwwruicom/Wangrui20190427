package com.bawei.wangrui20190427.base;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bawei.wangrui20190427.R;

/**
 * BaseActivity的封装
 */
public abstract class BaseActivity extends AppCompatActivity {
    //布局
    public abstract int initLayout();
    //数据
    public abstract void setData();
    //控件
    public abstract void setView();
    //监听
    public abstract void setListener();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(initLayout());

        setView();
        setData();
        setListener();

    }
}
