package com.neuSpring18.dto;

public class Dealer {
    private String id;
    private String locale;
    private String url;

    public static Dealer generateByString(String s) {
        Dealer d = new Dealer();
        String[] ss = s.split("\t");
        if (ss.length >= 3) {
            d.id = ss[0].trim();
            d.locale = ss[1].trim();
            d.url = ss[2].trim();
        } else {
            new IllegalArgumentException().printStackTrace();
        }
        return d;
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
