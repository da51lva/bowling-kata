package com.techreturners.bowling_kata;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BowlingGameTest {

    @Test
    public void testAllRollsOnes(){
        BowlingGame game = new BowlingGame();
        assertEquals(20, game.play("11 11 11 11 11 11 11 11 11 11"));
    }

}