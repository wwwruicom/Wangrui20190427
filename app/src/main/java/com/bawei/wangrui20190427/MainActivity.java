package com.bawei.wangrui20190427;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ScrollView;

import com.bawei.wangrui20190427.adapter.Gridadapter;
import com.bawei.wangrui20190427.adapter.MyAdapter;
import com.bawei.wangrui20190427.base.BaseActivity;
import com.bawei.wangrui20190427.bean.GridBean;
import com.bawei.wangrui20190427.bean.MyBean;
import com.bawei.wangrui20190427.utils.HttpUtils;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    ImageView imageView;
    PullToRefreshScrollView scrollview;
    GridView gridview;
    GridView my_gridview;
    String grid_path="http://gank.io/api/data/%E7%A6%8F%E5%88%A9/10/1";
    String mygrid_path="http://172.17.8.100/movieApi/movie/v1/findHotMovieList?";
    List<GridBean> gridList=new ArrayList<>();
    List<MyBean.Results> myList =new ArrayList<>();
    int pager=1;
    private MyAdapter myAdapter;

    //布局
    @Override
    public int initLayout() {
        return R.layout.activity_main;
    }
    //数据
    @Override
    public void setData() {
        setGrid();
        setPullToRefresh();
    }
    //控件
    @Override
    public void setView() {
        imageView=findViewById(R.id.imageview);
        scrollview=findViewById(R.id.scrollview);
        gridview=findViewById(R.id.gridview);
        my_gridview=findViewById(R.id.my_gridview);
    }
    //监听
    @Override
    public void setListener() {
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,1000);
            }
        });
    }
    //图片回传
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 1000 && resultCode == RESULT_OK){
            Uri uri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                imageView.setImageBitmap(bitmap);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    //九宫格
    public void setGrid(){
        HttpUtils instance = HttpUtils.getInstance();
        instance.getAsyncTaskData(grid_path);
        instance.getCallBack(new HttpUtils.CallBacks() {
            @Override
            public void getJson(String json) {
                try {
                    JSONObject jsonObject=new JSONObject(json);
                    JSONArray jsonArray = jsonObject.getJSONArray("results");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        String url = jsonArray.getJSONObject(i).getString("url");
                        GridBean gridBean=new GridBean(url);
                        gridList.add(gridBean);
                    }
                    //创建适配器
                    Gridadapter gridadapter=new Gridadapter(gridList,MainActivity.this);
                    gridview.setAdapter(gridadapter);
                    //调用获取MyGridView的数据方法
                    setMyGrid();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    //PullToRefreshScrollView实现上拉下拉
    public void setPullToRefresh(){
        scrollview.setMode(PullToRefreshBase.Mode.BOTH);
        //下拉刷新
        ILoadingLayout starting = scrollview.getLoadingLayoutProxy(true, false);
        starting.setPullLabel("下拉刷新");
        starting.setRefreshingLabel("正在刷新");
        starting.setReleaseLabel("松开刷新");
        //上拉加载
        ILoadingLayout ending = scrollview.getLoadingLayoutProxy(false, true);
        ending.setPullLabel("上拉加载");
        ending.setRefreshingLabel("正在加载");
        ending.setReleaseLabel("松开加载");
        scrollview.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ScrollView> pullToRefreshBase) {
                pager=1;
                setMyGrid();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ScrollView> pullToRefreshBase) {
                pager++;
                setMyGrid();
            }
        });
        //创建适配器
        myAdapter = new MyAdapter(myList, MainActivity.this);
        my_gridview.setAdapter(myAdapter);
    }
    //获取获取MyGridView的数据
    public void setMyGrid(){
        HttpUtils instance = HttpUtils.getInstance();

        String params=mygrid_path+"page="+pager+"&count=3";
        instance.getAsyncTaskData(params);
        instance.getCallBack(new HttpUtils.CallBacks() {
            @Override
            public void getJson(String json) {
                Gson gson=new Gson();
                MyBean myBean = gson.fromJson(json, MyBean.class);
                List<MyBean.Results> resultsList = myBean.getResult();
                if (pager==1){
                    myList.clear();
                }
                myList.addAll(resultsList);
                myAdapter.notifyDataSetChanged();
                scrollview.onRefreshComplete();
            }
        });
    }
}
