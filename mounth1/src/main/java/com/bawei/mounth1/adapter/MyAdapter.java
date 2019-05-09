package com.bawei.mounth1.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.mounth1.R;
import com.bawei.mounth1.bean.MyBean;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends BaseAdapter {

    List<MyBean.Datas> list =new ArrayList<>();
    Context context;

    public MyAdapter(List<MyBean.Datas> myList, Context context) {
        this.list = myList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


            switch (getItemViewType(position)){
                case 0:
                    ViewHolderOne viewHolderOne=new ViewHolderOne();
                    if (convertView == null) {
                        convertView=View.inflate(context, R.layout.item1,null);
                        viewHolderOne.imageView=convertView.findViewById(R.id.item1_img);
                        viewHolderOne.textView=convertView.findViewById(R.id.item1_tv);
                        convertView.setTag(viewHolderOne);
                    }else {
                        viewHolderOne= (ViewHolderOne) convertView.getTag();
                    }
                    viewHolderOne.textView.setText(list.get(position).getText());
                    Glide.with(context).load(list.get(position).getHeader()).into(viewHolderOne.imageView);
                    break;
                case 1:
                    ViewHolderTwo viewHolderTwo=new ViewHolderTwo();
                    if (convertView == null) {
                        convertView = View.inflate(context, R.layout.item2, null);
                        viewHolderTwo.imageView=convertView.findViewById(R.id.item2_img);
                        viewHolderTwo.textView=convertView.findViewById(R.id.item2_tv);
                        convertView.setTag(viewHolderTwo);
                    }else {
                        viewHolderTwo= (ViewHolderTwo) convertView.getTag();
                    }
                    viewHolderTwo.textView.setText(list.get(position).getText());
                    Glide.with(context).load(list.get(position).getHeader()).into(viewHolderTwo.imageView);
                    break;
            }
        return convertView;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        return position%2;
    }
    private class ViewHolderOne {
        ImageView imageView;
        TextView textView;
    }
    private class ViewHolderTwo {
        ImageView imageView;
        TextView textView;
    }
}
