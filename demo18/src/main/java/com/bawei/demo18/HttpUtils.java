package com.bawei.demo18;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class HttpUtils {
    static HttpUtils httpUtils=new HttpUtils();
    private Backs bit;

    public static HttpUtils getInstance(){
        return httpUtils;
    }
    public void getBitmap(String urls){
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
                bit.getBit(bitmap);
            }
        }.execute(urls);
    }
    public interface Backs{
        void getBit(Bitmap bitmap);
    }
    public void getBacks(Backs backs){
        this.bit=backs;
    }
}
