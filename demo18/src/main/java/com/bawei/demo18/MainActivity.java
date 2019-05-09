package com.bawei.demo18;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {

    String path="http://172.17.8.100/small/commodity/v1/bannerShow";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.banner);

    }
    public void getAsyncTaskBit(){
        HttpUtils instance = HttpUtils.getInstance();
        instance.getBitmap(path);
        instance.getBacks(new HttpUtils.Backs() {
            @Override
            public void getBit(Bitmap bitmap) {
                Gson gson=new Gson();
                //gson.fromJson(bitmap,MyBean.class);

            }
        });
    }
}
