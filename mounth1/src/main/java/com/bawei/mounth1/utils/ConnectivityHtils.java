package com.bawei.mounth1.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;

/**
 * 封装判断网络工具类
 */
public class ConnectivityHtils {
    //判断网络
    public boolean isConnection(Context context){
        ConnectivityManager manager= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        if (networkInfo!=null){
            return true;
        }else {
            return false;
        }
    }
    //设置网络连接
    public void setConnection(final Context context){
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        builder.setTitle("提示");
        builder.setMessage("请连接网络");
        builder.setPositiveButton("设置", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent=new Intent(Settings.ACTION_WIRELESS_SETTINGS);
                context.startActivity(intent);
            }
        }).show();
    }

}
