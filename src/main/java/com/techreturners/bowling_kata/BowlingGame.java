package com.techreturners.bowling_kata;

import java.util.Arrays;

public class BowlingGame {

    public int play(String rollSequence){
        return Arrays.stream(rollSequence.split(" ")).mapToInt(Integer::parseInt).reduce(0,(acc, roll) -> acc + totalOfRoll(roll));
    }

    private int totalOfRoll(int roll) {
        int firstDigit = (roll / 10) % 10;
        int secondDigit = roll % 10;
        return firstDigit + secondDigit;
    }

}
