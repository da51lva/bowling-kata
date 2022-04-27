package matt.thewizard.techreturners.bowlingkata.model;

import java.util.LinkedList;
import java.util.Queue;

public class BowlingBoard {

    private final Queue<Frame> frames = new LinkedList<>();
    private int scoreAccumulator = 0;

    public void addFrame(Frame frame){
        frames.add(frame);
        attemptCalculation();
    }

    private void attemptCalculation() {
        while(!frames.isEmpty() && frames.peek().canCalculateFrameScore(frames)){
            scoreAccumulator+=frames.remove().calculateScore(frames);
        }
    }

    public int getScoreAccumulator() {
        return scoreAccumulator;
    }
}
