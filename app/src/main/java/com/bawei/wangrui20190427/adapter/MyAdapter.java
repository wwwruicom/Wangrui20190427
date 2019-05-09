package com.bawei.wangrui20190427.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.wangrui20190427.R;
import com.bawei.wangrui20190427.bean.MyBean;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends BaseAdapter {

    List<MyBean.Results> list =new ArrayList<>();
    Context context;

    public MyAdapter(List<MyBean.Results> myList, Context context) {
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
        ViewHolder viewHolder=new ViewHolder();
        if (convertView == null) {
            convertView=View.inflate(context, R.layout.mygrid,null);
            viewHolder.textView=convertView.findViewById(R.id.tv);
            viewHolder.imageView=convertView.findViewById(R.id.my_img);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        viewHolder.textView.setText(list.get(position).getSummary());
        Glide.with(context).load(list.get(position).getImageUrl()).into(viewHolder.imageView);
        return convertView;
    }

    private class ViewHolder {
        ImageView imageView;
        TextView textView;
    }
}
