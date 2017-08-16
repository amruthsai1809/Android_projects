package com.example.amruth.toppaidapps;

import java.io.Serializable;

/**
 * Created by amruth on 25/02/17.
 */

public class Apps implements Serializable{
    Double price;
    String appName, thumbURL;

    public Double getPrice() {
        return price;
    }

    public void setPrice(String price) {
        String spli[] = price.split("\\$");
        this.price = Double.parseDouble(spli[1]);
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getThumbURL() {
        return thumbURL;
    }

    public void setThumbURL(String thumbURL) {
        this.thumbURL = thumbURL;
    }

    @Override
    public String toString() {
        return "Apps{" +
                "price=" + price +
                ", appName='" + appName + '\'' +
                ", thumbURL='" + thumbURL + '\'' +
                '}';
    }
}
