package matt.thewizard.techreturners.bowlingkata.model;

import java.util.Queue;
import java.util.stream.Stream;

public class BonusRollFrame implements Frame{

    private final Roll roll1;
    private final Roll roll2;
    private final Roll roll3;

    public BonusRollFrame(Roll roll1, Roll roll2, Roll roll3) {
        this.roll1 = roll1;
        this.roll2 = roll2;
        this.roll3 = roll3;
    }

    @Override
    public boolean canCalculateFrameScore(Queue<Frame> frames) {
        return true;
    }

    @Override
    public int calculateScore(Queue<Frame> frames) {
        return roll1.getValue() + roll2.getValue() + roll3.getValue();
    }

    @Override
    public Stream<Roll> getRollsAsStream() {
        return Stream.of(roll1,roll2, roll3);
    }

}