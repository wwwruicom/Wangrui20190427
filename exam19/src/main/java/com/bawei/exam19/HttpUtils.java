package com.bawei.exam19;

import android.os.AsyncTask;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class HttpUtils {

    static HttpUtils httpUtils=new HttpUtils();
    private CallBacks json;

    public static HttpUtils getInstance(){
        return httpUtils;
    }
    public void getAsync(String urls){
        new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... strings) {
                try {
                    URL url=new URL(strings[0]);
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setRequestMethod("GET");
                    if (urlConnection.getResponseCode() == 200) {
                        InputStream inputStream = urlConnection.getInputStream();
                        int len=-1;
                        byte[] bytes=new byte[1024];
                        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
                        while ((len=inputStream.read(bytes))!=-1){
                            byteArrayOutputStream.write(bytes,0,len);
                        }
                        byte[] bytes1 = byteArrayOutputStream.toByteArray();
                        //String ss=new String(bytes1,"UTF-8");
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
                json.getJsons(s);
            }
        }.execute(urls);
    }

    public static interface CallBacks{
        void getJsons(String json);
    }
    public void getCall(CallBacks callBacks){
        this.json=callBacks;
    }
}
