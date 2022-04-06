package com.techreturners.bowling_kata;

import com.techreturners.bowling_kata.model.Frame;
import com.techreturners.bowling_kata.model.Roll;

import java.util.Arrays;

public class BowlingGame {

    public int play(String rollSequence) {

        String[] framesAsStrings = rollSequence.split(" ");

        int total = 0;

        Frame nextFrame = mapStringToFrame(framesAsStrings[0]);
        for(int i = 0; i < framesAsStrings.length-1; i++){ //iterate up to penultimate frame
            Frame currentFrame = nextFrame;
            nextFrame = mapStringToFrame(framesAsStrings[i+1]);
            currentFrame.addNextFrame(nextFrame);
            total += currentFrame.getFrameTotal();
        }
        total += nextFrame.getFrameTotal(); //add the final frame to accumulator


        return (total);
    }

    /**
     * Map String representation of a frame e.g. "14" or "9/" to a Frame object
     */
    private Frame mapStringToFrame(String frameAsString){
        Roll firstRoll = mapStringToRoll(frameAsString.substring(0,1));
        Roll secondRoll = mapStringToRoll(frameAsString.substring(1));
        return new Frame(firstRoll, secondRoll);
    }

    /**
     *  Map String representation of a roll e.g. "1" or "/" or "-" to a Roll enum
     */
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
                throw new IllegalArgumentException("Attempting to map invalid character: "+rollAsString);
        }
        return roll;
    }

}
