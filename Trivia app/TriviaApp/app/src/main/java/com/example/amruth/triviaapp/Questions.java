package com.example.amruth.triviaapp;

import org.json.JSONArray;

import java.io.Serializable;

/**
 * Created by amruth on 08/02/17.
 */

public class Questions implements Serializable {
    String text,imageUrl,id;
    String[] choices;
    int  answer;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = Integer.parseInt(answer) ;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        if (imageUrl == null || imageUrl.isEmpty() || imageUrl.equals(""))
            this.imageUrl = "http://vignette2.wikia.nocookie.net/bokurawaminnakawaisou/images/f/f6/NoImage.jpg";
        else
            this.imageUrl = imageUrl;
    }

    public String[] getChoices() {
        return choices;
    }

    public void setChoices(String[] choices) {
        this.choices = choices;
    }
}
