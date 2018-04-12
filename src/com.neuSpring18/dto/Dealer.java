package com.neuSpring18.dto;

public class Dealer {
    private String id;
    private String locale;
    private String url;
    public Dealer(String s) {
        String[] ss = s.split("\t");
        if (ss.length == 3) {
            this.id = ss[0].trim();
            this.locale = ss[1].trim();
            this.url = ss[2].trim();
        } else {
            new IllegalArgumentException().printStackTrace();
        }
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getLocale() {
        return locale;
    }
    public void setLocale(String locale) {
        this.locale = locale;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(id).append("\t");
        sb.append(locale).append("\t");
        sb.append(url);
        return sb.toString();
    }
}
