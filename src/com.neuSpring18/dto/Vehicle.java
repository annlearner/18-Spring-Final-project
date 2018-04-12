package com.neuSpring18.dto;

import java.net.MalformedURLException;
import java.net.URL;

public class Vehicle {

    private String id;
    private String webId;
    private Category category;
    private int year;
    private String make;
    private String model;
    private String trim;
    private String bodyType;
    private float price;
    private URL photoUrl;

    public Vehicle(String s) {
        String[] ss = s.split("~");
        if (ss.length == 10) {
            this.id = ss[0];
            this.webId = ss[1];
            this.category = Category.getCategory(ss[2]);
            this.year = Integer.parseInt(ss[3]);
            this.make = ss[4];
            this.model = ss[5];
            this.trim = ss[6];
            this.bodyType = ss[7];
            this.price = Float.parseFloat(ss[8]);
            try {
                this.photoUrl = new URL(ss[9]);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
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

    public String getWebId() {
        return webId;
    }

    public void setWebId(String webId) {
        this.webId = webId;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getTrim() {
        return trim;
    }

    public void setTrim(String trim) {
        this.trim = trim;
    }

    public String getBodyType() {
        return bodyType;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public URL getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(URL photoUrl) {
        this.photoUrl = photoUrl;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(id).append("~");
        sb.append(webId).append("~");
        sb.append(category).append("~");
        sb.append(year).append("~");
        sb.append(make).append("~");
        sb.append(model).append("~");
        sb.append(trim).append("~");
        sb.append(bodyType).append("~");
        sb.append(price).append("~");
        sb.append(photoUrl);
        return sb.toString();
    }
}
