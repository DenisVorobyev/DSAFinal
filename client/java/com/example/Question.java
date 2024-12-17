package com.example;

/**
 * A general Question class with a question, answer, and difficulty
 * Sorted by difficulty within the Quiz class (less difficult come first)
 */
public class Question implements Comparable<Question> {
    private String text;
    private String answer;
    private int difficulty;

    /**
     * Creates a new question with specified difficulty
     * @param difficulty the question's difficulty
     */
    public Question(int difficulty) {
        this.difficulty = difficulty;
    }

    /**
     * Creates a new question with specified difficulty, question wording, and answer
     * @param text the wording of the question
     * @param answer the answer of the question
     * @param difficulty the difficulty of the question
     */
    public Question(String text, String answer, int difficulty) {
        this.text = text;
        this.answer = answer;
        this.difficulty = difficulty;
    }

    /**
     * Compares the difficulty with another question for sorting purposes
     * @param other the object to be compared.
     * @return the difference in difficulties
     */
    public int compareTo(Question other) {
        return this.getDifficulty()-other.getDifficulty();
    }

    /**
     * Returns whether the question is answered correctly
     * @param guess the user's guess
     * @return whether the guess is correct
     */
    public boolean correct(String guess) {
        return guess.toLowerCase().equals(answer.toLowerCase());
    }

    /**
     * Get the difficulty
     * @return the difficulty
     */
    public int getDifficulty() {
        return difficulty;
    }

    /**
     * Get the question wording
     * @return the question wording
     */
    public String getText() {
        return text;
    }

    /**
     * Set the question wording
     * @param text the new question wording
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Get the question answer
     * @return the question answer
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * Set the question answer
     * @param answer the new question answer
     */
    public void setAnswer(String answer) {
        this.answer = answer;
    }

    /**
     * Set the question difficulty
     * @param difficulty the new question difficulty
     */
    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }
}
