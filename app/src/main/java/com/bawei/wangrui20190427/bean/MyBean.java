package com.bawei.wangrui20190427.bean;

import java.util.List;

public class MyBean {
    List<Results> result;

    public MyBean(List<Results> result) {
        this.result = result;
    }

    public List<Results> getResult() {
        return result;
    }

    public void setResult(List<Results> result) {
        this.result = result;
    }

    public static class Results{
        String imageUrl;
        String summary;

        public Results(String imageUrl, String summary) {
            this.imageUrl = imageUrl;
            this.summary = summary;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }
    }

}
