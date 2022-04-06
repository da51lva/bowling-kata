package com.techreturners.bowling_kata.model;

import java.util.Queue;
import java.util.stream.Stream;

public class StrikeFrame implements Frame {


    @Override
    public boolean canCalculateFrameScore(Queue<Frame> frames) {
        return frames.stream()
                .flatMap(frame -> frame.getRollsAsStream()) //convert to Stream of rolls
                .limit(3) //Strike frame needs two next rolls to score
                .count() == 3;
    }

    @Override
    public int calculateScore(Queue<Frame> frames) {
        return Roll.STRIKE.getValue() + frames.stream() //add next two rolls
                .flatMap(frame -> frame.getRollsAsStream()) //convert to Stream of rolls
                .limit(2) //Strike frame needs two next rolls to score
                .reduce(0, (acc, roll) -> acc + roll.getValue(), Integer::sum);
    }

    @Override
    public Stream<Roll> getRollsAsStream() {
        return Stream.of(Roll.STRIKE);
    }
}
