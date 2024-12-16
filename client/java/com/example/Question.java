package com.example;

public class Question implements Comparable<Question> {
    private String text;
    private String answer;
    private int difficulty;

    public Question(int difficulty) {
        this.difficulty = difficulty;
    }

    public Question(String text, String answer, int difficulty) {
        this.text = text;
        this.answer = answer;
        this.difficulty = difficulty;
    }

    public int compareTo(Question other) {
        return this.getDifficulty()-other.getDifficulty();
    }

    public boolean correct(String guess) {
        return guess.toLowerCase().equals(answer.toLowerCase());
    }

    public int getDifficulty() {
        return difficulty;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }
}
