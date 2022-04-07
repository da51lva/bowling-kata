package com.techreturners.bowlingkata.model;

import java.util.Queue;
import java.util.stream.Stream;

public interface Frame {

    boolean canCalculateFrameScore(Queue<Frame> frames);

    int calculateScore(Queue<Frame> frames);

    Stream<Roll> getRollsAsStream();
}
