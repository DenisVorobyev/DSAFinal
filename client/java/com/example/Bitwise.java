package com.example;

import java.util.Stack;

/**
 * a bitwise question
 */
public class Bitwise extends Question {
    private int operators;

    /**
     *
     * @param difficulty the amount of operations and the maximum number which can occur
     */
    public Bitwise(int difficulty){
        super(difficulty);
        operators = difficulty;
        generateQ();
    }

    /**
     * Generate a question and its answer based on the difficulty
     */
    public void generateQ() {
        String output = "";
        Stack<Integer> stack = new Stack<>();
        int usedOperators = 0;
        int range = (int)Math.pow(2,super.getDifficulty());
        while (usedOperators < operators) {
            if(stack.isEmpty()){
                stack.push((int)(Math.random()*range));
            }
            stack.push((int)(Math.random()*range));
            int operator = (int)(Math.random()*5);
            int n2 = stack.pop();
            int n1 = stack.pop();
            output += n1;
            switch(operator){
                case 0: // &
                    output += " & ";
                    stack.push(n1 & n2);
                    break;
                case 1:
                    output += " | ";
                    stack.push(n1 | n2);
                    break;
                case 2:
                    output += " >> ";
                    stack.push(n1 >> n2);
                    break;
                case 3:
                    output += " << ";
                    stack.push(n1 << n2);
                    break;
                case 4:
                    output += " ^ ";
                    stack.push(n1 ^ n2);
                    break;
            }
            output += n2;
            usedOperators++;
        }
        super.setAnswer(String.valueOf((int)stack.pop()));
        super.setText(output);
    }
}