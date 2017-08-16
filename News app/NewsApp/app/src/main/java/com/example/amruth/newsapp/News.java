package com.example.amruth.newsapp;

/**
 * Created by amruth on 06/02/17.
 */

public class News {
    String author,title,description,url,urlImage,publishedAt;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        String[] split = publishedAt.split("T");
        this.publishedAt = split[0];
    }

    public News(String author, String title, String url, String description, String urlImage, String publishedAt) {

        this.author = author;
        this.title = title;
        this.url = url;
        this.description = description;
        this.urlImage = urlImage;
        this.publishedAt = publishedAt;
    }

    public  News(){

    }
}
