package com.techreturners.bowling_kata.model;

public class Frame {

    private Roll roll1;
    private Roll roll2;
    private Frame nextFrame;

    public Frame(Roll roll1, Roll roll2) {
        this.roll1 = roll1;
        this.roll2 = roll2;
    }

    private Roll getFirstRoll(){
        return roll1;
    }

    public void addNextFrame(Frame nextFrame){
        this.nextFrame = nextFrame;
    }

    private boolean isASpare(){
        return roll2.equals(Roll.SPARE);
    }

    public int getFrameTotal(){
        int basicTotal = getFrameBasicTotal();
        return this.isASpare()? basicTotal + nextFrame.getFirstRoll().getValue() : basicTotal;
    }

    private int getFrameBasicTotal(){
        if(roll2 == Roll.SPARE)
            return 10;
        else
            return roll1.getValue() + roll2.getValue();
    }
}
