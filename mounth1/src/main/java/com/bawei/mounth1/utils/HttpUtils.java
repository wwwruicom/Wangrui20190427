package com.bawei.mounth1.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *封装HttpURLConnection工具类
 * 异步处理
 */
public class HttpUtils {
    //单例模式
    static HttpUtils httpUtils=new HttpUtils();
    private CallBacks json;
    private MyBackBitmap bit;

    public static HttpUtils getInstance(){
        return httpUtils;
    }
    //异步处理
    public void getAsyncTaskData(String urls){
        new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... strings) {
                try {
                    URL url=new URL(strings[0]);
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setRequestMethod("GET");
                    if (urlConnection.getResponseCode()==200){
                        InputStream inputStream = urlConnection.getInputStream();
                        int len=-1;
                        byte[] bytes=new byte[1024];
                        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
                        while ((len=inputStream.read(bytes))!=-1){
                            byteArrayOutputStream.write(bytes,0,len);
                        }
                        String s = byteArrayOutputStream.toString();
                        return s;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                json.getAJson(s);
            }
        }.execute(urls);
    }
    //接口
    public interface CallBacks{
        void getAJson(String json);
    }
    //接口回调
    public void getCallBack(CallBacks callBacks){
        this.json=callBacks;
    }
    public void getAsyncTaskBitmap(String urls){
        new AsyncTask<String, Void, Bitmap>() {
            @Override
            protected Bitmap doInBackground(String... strings) {
                try {
                    URL url=new URL(strings[0]);
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setRequestMethod("GET");
                    if (urlConnection.getResponseCode()==200){
                        InputStream inputStream = urlConnection.getInputStream();
                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

                        return bitmap;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Bitmap bitmap) {
                super.onPostExecute(bitmap);
                bit.getImg(bitmap);
            }
        }.execute(urls);
    }
    public interface MyBackBitmap{
        void getImg(Bitmap bitmap);
    }
    public void getBackBitmap(MyBackBitmap myBackBitmap){
        this.bit=myBackBitmap;
    }
}
