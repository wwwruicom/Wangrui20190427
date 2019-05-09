package com.bawei.exam17;

import android.os.AsyncTask;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtils {

    static HttpUtils httpUtils=new HttpUtils();
    private CallBacks json;

    public static HttpUtils getInstance(){
        return httpUtils;
    }

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
                json.getJson(s);
            }
        }.execute(urls);
    }
    public interface CallBacks{
        void getJson(String json);
    }
    public void getCalls(CallBacks callBacks){
        this.json=callBacks;
    }

}
