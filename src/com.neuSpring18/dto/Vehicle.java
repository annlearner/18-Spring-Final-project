package com.neuSpring18.dto;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Vehicle {

    private String id;
    private String webId;
    private Category category;
    private int year;
    private String make;
    private String model;
    private String trim;
    private CarType bodyType;
    private double price;
    private URL photoUrl;
    private List<String> morePhotos;

    public Vehicle() {
        morePhotos = new ArrayList<>();
    }

    public static Vehicle generateVehicle(String s) {
        Vehicle result = new Vehicle();
        String[] ss = s.split("~");
        if (ss.length == 10) {
            result.id = ss[0];
            result.webId = ss[1];
            result.category = Category.getCategory(ss[2]);
            result.year = Integer.parseInt(ss[3]);
            result.make = ss[4];
            result.model = ss[5];
            result.trim = ss[6];
            result.bodyType = CarType.getType(ss[7]);
            result.price = Double.parseDouble(ss[8]);
            try {
                result.photoUrl = new URL(ss[9]);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        } else {
            new IllegalArgumentException().printStackTrace();
        }
        return result;
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

    public CarType getBodyType() {
        return bodyType;
    }

    public void setBodyType(CarType bodyType) {
        this.bodyType = bodyType;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
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

    public String toCompleteString() {
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
        sb.append(photoUrl).append("\n");
        sb.append(morePhotos);
        return sb.toString();
    }

    public String toSearchString() {
        StringBuilder sb = new StringBuilder();
        sb.append(id).append("~");
//        sb.append(webId).append("~");
        sb.append(category).append("~");
        sb.append(year).append("~");
        sb.append(make).append("~");
        sb.append(model).append("~");
        sb.append(trim).append("~");
        sb.append(bodyType);
//        sb.append(price);
        return sb.toString();
    }

    public List<String> getMorePhotos() {
        return morePhotos;
    }

    public void setMorePhotos(List<String> morePhotos) {
        this.morePhotos = morePhotos;
    }
}
