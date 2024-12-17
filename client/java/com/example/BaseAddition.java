package com.example;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a quiz question about adding numbers of the same base
 */
public class BaseAddition extends Question {
    private static final Map<Character, Integer> ctoi = Map.ofEntries(
            Map.entry('0', 0), Map.entry('1', 1), Map.entry('2', 2), Map.entry('3', 3),
            Map.entry('4', 4), Map.entry('5', 5), Map.entry('6', 6), Map.entry('7', 7),
            Map.entry('8', 8), Map.entry('9', 9), Map.entry('A', 10), Map.entry('B', 11),
            Map.entry('C', 12), Map.entry('D', 13), Map.entry('E', 14), Map.entry('F', 15)
    );

    private static final Map<Integer, Character> itoc = Map.ofEntries(
            Map.entry(0, '0'), Map.entry(1, '1'), Map.entry(2, '2'), Map.entry(3, '3'),
            Map.entry(4, '4'), Map.entry(5, '5'), Map.entry(6, '6'), Map.entry(7, '7'),
            Map.entry(8, '8'), Map.entry(9, '9'), Map.entry(10, 'A'), Map.entry(11, 'B'),
            Map.entry(12, 'C'), Map.entry(13, 'D'), Map.entry(14, 'E'), Map.entry(15, 'F')
    );
    private int digits;
    private String num1;
    private String num2;
    private int base;

    /**
     * Creates a new base addition question with two numbers of specified digit length and base
     * @param digits the amount of digits in each number
     * @param base the base representation of each number
     */
    public BaseAddition(int digits, int base) {
        super(digits);
        this.digits = digits;
        this.base = base;
        makeQ();
        solveQ();
    }

    /**
     * Creates the question and documents the two randomly generated numbers
     */
    public void makeQ() {
        StringBuilder num1 = new StringBuilder();
        StringBuilder num2 = new StringBuilder();
        for (int i=0; i<digits; i++) {
            num1.append(itoc.get((int) (Math.random()*base)));
            num2.append(itoc.get((int) (Math.random()*base)));
        }
        this.num1 = num1.toString();
        this.num2 = num2.toString();
        super.setText(this.num1 + " + " + this.num2 + " (base " + base + ") =");
    }

    /**
     * Solves the question and documents the answer based on the generated numbers
     */
    public void solveQ() {
        StringBuilder result = new StringBuilder();
        int carry = 0;
        for (int i=digits-1; i>=0; i--) {
            int val1 = ctoi.get(num1.charAt(i));
            int val2 = ctoi.get(num2.charAt(i));
            int sum = val1 + val2 + carry;
            carry = sum/base;
            sum %= base;
            result.append(itoc.get(sum));
        }

        if (carry>0) {
            result.append(itoc.get(carry));
        }

        super.setAnswer(result.reverse().toString());
    }
}
