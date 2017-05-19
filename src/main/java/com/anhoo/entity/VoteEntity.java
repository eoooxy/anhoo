package com.anhoo.entity;

/**
 * Created by IntelliJ IDEA.
 * Author XueYuan
 * Data  2017/05/19
 * Time  13:19
 */

public class VoteEntity {

    private int id;
    private String name;
    private int scores;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScores() {
        return scores;
    }

    public void setScores(int scores) {
        this.scores = scores;
    }
}
