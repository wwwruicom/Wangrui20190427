package com.bawei.exam16;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    String grid_path="http://gank.io/api/data/%E7%A6%8F%E5%88%A9/10/1";
    private GridView gridview;
    List<MyBean> list=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridview = findViewById(R.id.gridview);

    }
    public void initData(){
        HttpUtils instance = HttpUtils.getInstance();
        instance.getAsyncTaskData(grid_path);
        instance.getCalls(new HttpUtils.CallBacks() {
            @Override
            public void getJson(String json) {
                try {
                    JSONObject jsonObject=new JSONObject(json);
                    JSONArray results = jsonObject.getJSONArray("results");
                    for (int i = 0; i < results.length(); i++) {
                        String url = results.getJSONObject(i).getString("url");
                        String type = results.getJSONObject(i).getString("type");
                        String who = results.getJSONObject(i).getString("who");
                        list.add(new MyBean(url,type,who));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
