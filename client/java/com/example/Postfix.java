package com.example;
import java.util.Stack;

/**
 * Represents a quiz question about postfix evaluation
 */
public class Postfix extends Question {
    private int operators;

    /**
     * Creates a new postfix question according to the amount of operators
     * @param operators how many operators to include in the question
     */
    public Postfix(int operators) {
        super(operators); //num operators as difficulty
        this.operators = operators;
        generateQ();
    }

    /**
     * Generates the postfix question and documents the solution
     */
    public void generateQ() {
        String output = "";
        Stack<Integer> stack = new Stack<>();
        int usedOperators = 0;

        while (usedOperators < operators) {
            if (stack.size() < 2) {
                int digit = (int) ((Math.random()*9)+1);
                stack.push(digit);
                output += String.valueOf(digit);
            } else {
                int n1 = stack.pop();
                int n2 = stack.pop();

                int operator;
                if (n1 == 0) {
                    operator = (int) (Math.random()*3);
                } else {
                    operator = (int) (Math.random()*4);
                }
                switch (operator) {
                    case 0:
                        output += "+";
                        stack.push(n2+n1);
                        break;
                    case 1:
                        output += "-";
                        stack.push(n2-n1);
                        break;
                    case 2:
                        output += "*";
                        stack.push(n2*n1);
                        break;
                    case 3:
                        output += "/";
                        stack.push(n2/n1);
                        break;
                }
                usedOperators += 1;
            }
        }
        super.setAnswer(String.valueOf((int)stack.pop()));
        super.setText(output);
    }
}
