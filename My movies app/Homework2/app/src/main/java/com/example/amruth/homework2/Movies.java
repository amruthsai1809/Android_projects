package com.example.amruth.homework2;

import java.io.Serializable;
import java.util.Comparator;

/**
 * Created by amruth on 25/01/17.
 */

public class Movies implements Serializable, Comparable<Movies> {
    String name,description,imdb;
    int rating,year, genre;

    public Movies(String name, String description, int genre, String imdb, int rating, int year) {
        this.name = name;
        this.description = description;
        this.genre = genre;
        this.imdb = imdb;
        this.rating = rating;
        this.year = year;
    }

    @Override
    public String toString() {
        return "Movies{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", genre='" + genre + '\'' +
                ", imdb='" + imdb + '\'' +
                ", rating=" + rating +
                ", year=" + year +
                '}';
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getGenre() {
        return genre;
    }

    public String getImdb() {
        return imdb;
    }

    public int getRating() {
        return rating;
    }

    public int getYear() {
        return year;
    }

    @Override
    public int compareTo(Movies movies) {
        if(this.year< movies.year){
            return -1;
        }else if(this.year>movies.year){
            return 1;
        }
            return 0;



    }
}
