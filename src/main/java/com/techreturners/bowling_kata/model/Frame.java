package com.techreturners.bowling_kata.model;

public class Frame {

    private Roll roll1;
    private Roll roll2;
    private Frame nextFrame;

    public Frame(Roll roll1, Roll roll2) {
        this.roll1 = roll1;
        this.roll2 = roll2;
        nextFrame = null;
    }

    public Frame(Roll roll1) {
        this.roll1 = roll1;
        roll2 = null;
        nextFrame = null;
    }

    private Roll getFirstRoll() {
        return roll1;
    }

    public void addNextFrame(Frame nextFrame) {
        this.nextFrame = nextFrame;
    }

    private boolean isASpare() {
        return roll2 == Roll.SPARE;
    }

    private boolean isAStrike() {
        return roll1 == Roll.STRIKE;
    }

    public int getFrameTotal() {
        int frameTotal = getFrameBasicTotal();
        if(this.isAStrike())
            frameTotal+=nextFrame.getFrameBasicTotal();
        else if(this.isASpare())
            frameTotal+=nextFrame.getFirstRoll().getValue();
        return frameTotal;
    }

    private int getFrameBasicTotal() {
        if (this.isAStrike())
            return roll1.getValue();
        if (this.isASpare())
            return roll2.getValue();
        else
            return roll1.getValue() + roll2.getValue();
    }
}
