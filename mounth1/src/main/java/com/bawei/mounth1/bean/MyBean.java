package com.bawei.mounth1.bean;

import java.util.ArrayList;
import java.util.List;

public class MyBean {

    List<Datas> data=new ArrayList<>();

    public MyBean(List<Datas> data) {
        this.data = data;
    }

    public List<Datas> getData() {
        return data;
    }

    public void setData(List<Datas> data) {
        this.data = data;
    }

    public static class Datas{
        String text;
        String header;

        public Datas(String text, String header) {
            this.text = text;
            this.header = header;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getHeader() {
            return header;
        }

        public void setHeader(String header) {
            this.header = header;
        }
    }

}
