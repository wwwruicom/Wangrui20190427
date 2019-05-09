package com.bawei.mounth1.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ScrollView;

import com.bawei.mounth1.R;
import com.bawei.mounth1.adapter.MyAdapter;
import com.bawei.mounth1.base.BaseFragment;
import com.bawei.mounth1.bean.MyBean;
import com.bawei.mounth1.utils.HttpUtils;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.youth.banner.Banner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MyFragmentOne extends BaseFragment {
    String path="https://www.apiopen.top/satinGodApi?type=1&";
    String ban_path="https://www.apiopen.top/satinApi?type=1&page=1";
    private PullToRefreshScrollView myscrollview;
    private TabLayout tablayout;
    private Banner banner;
    private ListView mylistview;
    int pager=1;

    List<MyBean.Datas> list=new ArrayList<>();
    private MyAdapter myAdapter;

    @Override
    public int initLayout() {
        return R.layout.frag1;
    }

    @Override
    public void initData() {
        tablayout.addTab(tablayout.newTab().setText("新闻"));
        tablayout.addTab(tablayout.newTab().setText("娱乐"));
        tablayout.addTab(tablayout.newTab().setText("搞笑"));
        tablayout.addTab(tablayout.newTab().setText("视频"));
        initJson();
        setScorllView();
    }

    @Override
    public void initID(View view) {
        myscrollview = view.findViewById(R.id.myscrollview);
        tablayout = view.findViewById(R.id.tablayout);
        banner = view.findViewById(R.id.banner);
        mylistview = view.findViewById(R.id.mylistview);
    }
    //监听
    @Override
    public void initListener() {

    }

    public void setScorllView(){
        myscrollview.setMode(PullToRefreshBase.Mode.BOTH);
        myscrollview.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ScrollView> pullToRefreshBase) {
                pager=1;
                initJson();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ScrollView> pullToRefreshBase) {
                pager++;
                initJson();
            }
        });
        myAdapter = new MyAdapter(list,getActivity());
        mylistview.setAdapter(myAdapter);
    }
    public void initJson(){
        HttpUtils instance = HttpUtils.getInstance();
        String params=path+"page="+pager;
        instance.getAsyncTaskData(params);
        instance.getCallBack(new HttpUtils.CallBacks() {
            @Override
            public void getAJson(String json) {
                Gson gson=new Gson();
                MyBean myBean = gson.fromJson(json, MyBean.class);
                List<MyBean.Datas> datasList = myBean.getData();
                if (pager==1){
                        list.clear();
                    }
                    list.addAll(datasList);
                    myAdapter.notifyDataSetChanged();
                    myscrollview.onRefreshComplete();
            }
        });
    }
    public void initBitmap(){
        HttpUtils instance = HttpUtils.getInstance();
        String params=ban_path+"page="+pager;
        instance.getAsyncTaskBitmap(params);
    }

}
