package com.example.amruth.myfavouritemovies;

import java.io.Serializable;

/**
 * Created by amruth on 13/03/17.
 */

public class Movie implements Serializable {
    String name,description,imdb;
    int rating,year, genre;

    public Movie(String name, String description, int genre, String imdb, int rating, int year) {
        this.name = name;
        this.description = description;
        this.genre = genre;
        this.imdb = imdb;
        this.rating = rating;
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImdb() {
        return imdb;
    }

    public void setImdb(String imdb) {
        this.imdb = imdb;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getGenre() {
        return genre;
    }

    public void setGenre(int genre) {
        this.genre = genre;
    }
}
