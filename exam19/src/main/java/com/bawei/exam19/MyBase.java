package com.bawei.exam19;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MyBase extends BaseAdapter {
    List<MyBean.Results.Pzshs.CommodityLists> list=new ArrayList<>();
    Context context;

    public MyBase(List<MyBean.Results.Pzshs.CommodityLists> list, Context context) {
        this.list = list;
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
            convertView=View.inflate(context,R.layout.items,null);
            viewHolder.imageView=convertView.findViewById(R.id.iv);
            viewHolder.textView=convertView.findViewById(R.id.tv);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        Glide.with(context).load(list.get(position).getMasterPic()).into(viewHolder.imageView);
        viewHolder.textView.setText(list.get(position).getCommodityName());
        return convertView;
    }

    private class ViewHolder {
        ImageView imageView;
        TextView textView;
    }
}
