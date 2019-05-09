package com.bawei.exam19;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ScrollView;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    String path="http://172.17.8.100/small/commodity/v1/commodityList";
    List<MyBean.Results.Pzshs.CommodityLists> list=new ArrayList<>();
    int pager=1;
    PullToRefreshScrollView my_scroll;
    GridView my_grid;
    GridView grid;
    MyBase myBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        my_scroll = findViewById(R.id.my_scroll);
        my_grid = findViewById(R.id.my_grid);
        grid = findViewById(R.id.grid);

        getJiu();

        setPull();


    }
    public void setPull(){
        my_scroll.setMode(PullToRefreshBase.Mode.BOTH);
        my_scroll.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ScrollView> pullToRefreshBase) {
                pager=1;
                getHttpJson();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ScrollView> pullToRefreshBase) {
                pager++;
                getHttpJson();
            }
        });
        myBase = new MyBase(list,this);
        my_grid.setAdapter(myBase);
    }
    public void getJiu(){
        HttpUtils instance = HttpUtils.getInstance();
        instance.getAsync(path);
        instance.getCall(new HttpUtils.CallBacks() {
            @Override
            public void getJsons(String json) {
                Gson gson=new Gson();
                MyBean myBean = gson.fromJson(json, MyBean.class);
                List<MyBean.Results.Pzshs.CommodityLists> commodityList = myBean.getResult().getPzsh().getCommodityList();
                MyGridBase myGridBase=new MyGridBase(commodityList,MainActivity.this);
                grid.setAdapter(myGridBase);
                getHttpJson();
            }
        });



    }
    public void getHttpJson(){
        HttpUtils instance = HttpUtils.getInstance();
        instance.getAsync(path);
        instance.getCall(new HttpUtils.CallBacks() {
            @Override
            public void getJsons(String json) {
                Gson gson=new Gson();
                MyBean myBean = gson.fromJson(json, MyBean.class);
                List<MyBean.Results.Pzshs.CommodityLists> commodityList = myBean.getResult().getPzsh().getCommodityList();
                if (pager==1){
                    list.clear();
                }
                list.addAll(commodityList);
                myBase.notifyDataSetChanged();
                my_scroll.onRefreshComplete();
            }
        });
    }
}
