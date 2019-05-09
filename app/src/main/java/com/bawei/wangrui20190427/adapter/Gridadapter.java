package com.bawei.wangrui20190427.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bawei.wangrui20190427.R;
import com.bawei.wangrui20190427.base.BaseActivity;
import com.bawei.wangrui20190427.bean.GridBean;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;
//九宫格适配器
public class Gridadapter extends BaseAdapter {

    List<GridBean> list=new ArrayList<>();
    Context context;

    public Gridadapter(List<GridBean> gridBeanList, Context context) {
        this.list = gridBeanList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=new ViewHolder();
        if (convertView == null) {
            convertView=View.inflate(context, R.layout.grid,null);
            viewHolder.imageView=convertView.findViewById(R.id.img);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        Glide.with(context).load(list.get(position).getUrl()).into(viewHolder.imageView);
        return convertView;
    }

    private class ViewHolder {
        ImageView imageView;
    }
}
