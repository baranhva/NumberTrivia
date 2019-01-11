package com.example.blackhorse.numbertrivia;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TriviaItem {

    public TriviaItem(String text, Integer number){
        this.text = text;
        this.number = number;
    }

    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("number")
    @Expose
    private Integer number;


    public String getText() { return text; }
    public void setText(String text) {
        this.text = text;
    }
    public Integer getNumber() { return number; }
    public void setNumber(Integer number) {
        this.number = number;
    }

}
