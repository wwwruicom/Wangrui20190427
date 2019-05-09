package com.bawei.exam16;

public class MyBean {

    String url;
    String type;
    String who;

    public MyBean(String url, String type, String who) {
        this.url = url;
        this.type = type;
        this.who = who;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }
}
