package com.techreturners.bowling_kata;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BowlingGameTest {

    @Test
    public void testAllRollsOnes() {
        BowlingGame game = new BowlingGame();
        assertEquals(20, game.play("11 11 11 11 11 11 11 11 11 11"));
    }

    @ParameterizedTest
    @MethodSource("generateRollSequenceAndExpectedScoreWithDigits1To8NoMissesSparesOrStrikes")
    public void testRollSequencesWithDigits1To8WithNoMissesSparesOrStrikes(int expectedScore, String rollSequence) {
        BowlingGame game = new BowlingGame();
        assertEquals(expectedScore, game.play(rollSequence));
    }

    /**
     * generates random roll sequences of 10 frames with digits 1 to 8, excluding misses spares or strikes and
     * calculates the expected score
     */
    private static Stream<Arguments> generateRollSequenceAndExpectedScoreWithDigits1To8NoMissesSparesOrStrikes() {
        List<Arguments> arguments = new ArrayList<Arguments>();

        for (int i = 0; i < 10; i++) {

            List<Integer> rollSequenceList = new Random().ints(11, 81) //infinite stream of ints
                    .filter(e -> e % 10 != 0) // Ones digit is 0 as this is a miss
                    .filter(e -> digitTotalLessThan10(e)) //total of digits < 10 as this is spare or strike
                    .boxed()
                    .limit(10) //10 frames in bowling match with no spare or strikes
                    .collect(Collectors.toList());

            String rollSequence = rollSequenceList.stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(" ")); //convert List to String

            int totalScore = rollSequenceList.stream()
                    .reduce(0, (acc, e) -> acc + totalOfDigits(e)); //add all digits to get total score


            arguments.add(Arguments.of(totalScore, rollSequence));
        }

        return arguments.stream();

    }

    /**
     * adds the digits of a 2-digit number together
     */
    private static int totalOfDigits(Integer i) {
        int firstDigit = (i / 10) % 10;
        int secondDigit = i % 10;
        return firstDigit + secondDigit;
    }

    /**
     * checks the combined total a 2-digit number is < 10
     */
    private static boolean digitTotalLessThan10(int i) {
        int firstDigit = (i / 10) % 10;
        int secondDigit = i % 10;
        return firstDigit + secondDigit < 10;
    }

}