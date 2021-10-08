package com.homestask.parse.entity;

import javax.persistence.*;

@Entity
@Table(name = "text")
public class Text {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "word")
    private String word;

    @Column(name = "count")
    private int count;

    public Text() {
    }

    public Text(String word, int count) {
        this.word = word;
        this.count = count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}