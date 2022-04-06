package com.techreturners.bowling_kata;

import com.techreturners.bowling_kata.model.Frame;
import com.techreturners.bowling_kata.model.Roll;

import java.util.Arrays;

public class BowlingGame {

    public int play(String rollSequence) {

        String[] framesAsString = rollSequence.split(" ");

        int total = 0;

        Frame nextFrame = mapStringToFrame(framesAsString[0]);
        for(int i = 0; i < framesAsString.length-1; i++){
            Frame currentFrame = nextFrame;
            nextFrame = mapStringToFrame(framesAsString[i+1]);
            currentFrame.addNextFrame(nextFrame);
            total += currentFrame.getFrameTotal();
        }
        total += nextFrame.getFrameTotal(); //add the final frame


        return (total);
    }

    private Frame mapStringToFrame(String frameAsString){
        Roll firstRoll = mapStringToRoll(frameAsString.substring(0,1));
        Roll secondRoll = mapStringToRoll(frameAsString.substring(1));
        return new Frame(firstRoll, secondRoll);
    }

    private Roll mapStringToRoll(String rollAsString){
        Roll roll;
        switch(rollAsString) {
            case ("1"):
                roll = Roll.ONE;
                break;
            case ("2"):
                roll = Roll.TWO;
                break;
            case ("3"):
                roll = Roll.THREE;
                break;
            case ("4"):
                roll = Roll.FOUR;
                break;
            case ("5"):
                roll = Roll.FIVE;
                break;
            case ("6"):
                roll = Roll.SIX;
                break;
            case ("7"):
                roll = Roll.SEVEN;
                break;
            case ("8"):
                roll = Roll.EIGHT;
                break;
            case ("9"):
                roll = Roll.NINE;
                break;
            case ("-"):
                roll = Roll.MISS;
                break;
            case ("/"):
                roll = Roll.SPARE;
                break;
            default:
                throw new IllegalArgumentException();
        }
        return roll;
    }

}
