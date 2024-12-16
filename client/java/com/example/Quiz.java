package com.example;

import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.text.Text;

import java.util.PriorityQueue;

public class Quiz {
    public static Quiz quizInstance;
    private PriorityQueue<Question> questions = new PriorityQueue<>();
    private Question currQuestion;
    private ClientPlayerEntity player;
    public Quiz(ClientPlayerEntity player) {
        this.player = player;
        questions.add(new Arithmetic(5));
        questions.add(new Arithmetic(3));
        questions.add(new Arithmetic(8));
    }

    public void printQuestion() {
        player.sendMessage(Text.literal(getCurrQuestion().getText()), false);
    }

    public Question nextQuestion() {
        Question q = questions.remove();
        currQuestion = q;
        return q;
    }

    public Question getCurrQuestion() {
        return currQuestion;
    }

    public void endQuiz(String guess) {
        player.sendMessage(Text.literal(getCurrQuestion().getAnswer()), false);
        quizInstance = null;
    }

    public void correct() {
        player.sendMessage(Text.literal(getCurrQuestion().getAnswer() + " is the correct answer!"), false);
    }
}
