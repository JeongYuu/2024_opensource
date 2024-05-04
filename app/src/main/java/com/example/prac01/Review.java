package com.example.prac01;

public class Review {
    int score;
    String content;

    public Review(int score, String content) {
        this.score = score;
        this.content = content;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
