package com.bawei.mounth1;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import com.bawei.mounth1.view.MyFragmentOne;
import com.bawei.mounth1.view.MyFragmentThree;
import com.bawei.mounth1.view.MyFragmentTwo;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewpager;
    private RadioGroup group;
    List<Fragment> fragmentList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewpager = findViewById(R.id.viewpager);
        group = findViewById(R.id.group);

        MyFragmentOne myFragmentOne=new MyFragmentOne();
        MyFragmentTwo myFragmentTwo=new MyFragmentTwo();
        MyFragmentThree fragmentThree=new MyFragmentThree();
        fragmentList.add(myFragmentOne);
        fragmentList.add(myFragmentTwo);
        fragmentList.add(fragmentThree);
        viewpager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return fragmentList.get(i);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }
        });
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                switch (i){
                    case 0:
                        group.check(R.id.btn_1);
                        break;
                    case 1:
                        group.check(R.id.btn_2);
                        break;
                    case 2:
                        group.check(R.id.btn_3);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.btn_1:
                        viewpager.setCurrentItem(0);
                        break;
                    case R.id.btn_2:
                        viewpager.setCurrentItem(1);
                        break;
                    case R.id.btn_3:
                        viewpager.setCurrentItem(2);
                        break;
                }
            }
        });
    }
}
