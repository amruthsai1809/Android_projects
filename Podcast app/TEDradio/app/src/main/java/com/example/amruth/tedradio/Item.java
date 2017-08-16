package com.example.amruth.tedradio;

import java.util.Date;

/**
 * Created by amruth on 11/03/17.
 */

public class Item {
    String title,description,imageurl,mp3url;
    Date date;
    Integer duration;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getMp3url() {
        return mp3url;
    }

    public void setMp3url(String mp3url) {
        this.mp3url = mp3url;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Item{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", imageurl='" + imageurl + '\'' +
                ", mp3url='" + mp3url + '\'' +
                ", date=" + date +
                ", duration=" + duration +
                '}';
    }
}
