package com.techreturners.bowling_kata.model;

import java.util.Queue;
import java.util.stream.Stream;

public class SpareFrame implements Frame {

    private final Roll roll1;
    private final Roll roll2;

    public SpareFrame(Roll roll1, Roll roll2) {
        this.roll1 = roll1;
        this.roll2 = roll2;
    }

    @Override
    public boolean canCalculateFrameScore(Queue<Frame> frames) {
        return frames.size() > 1;
    }

    @Override
    public int calculateScore(Queue<Frame> frames) {
        return roll1.getValue() + roll2.getValue() + frames.peek().getRollsAsStream().findFirst().get().getValue();
    }

    @Override
    public Stream<Roll> getRollsAsStream() { // todo: best way?
        return Stream.of(roll1, roll2);
    }
}
