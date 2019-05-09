package com.bawei.exam17;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;

public class MainActivity extends AppCompatActivity {

    private GridView gridview;
    String path="http://gank.io/api/data/%E7%A6%8F%E5%88%A9/10/1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridview = findViewById(R.id.gridview);
    }
}
