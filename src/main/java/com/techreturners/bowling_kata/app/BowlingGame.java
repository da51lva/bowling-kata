package com.techreturners.bowling_kata.app;

import com.techreturners.bowling_kata.model.*;

import java.util.LinkedList;
import java.util.Queue;

public class BowlingGame {

    private static final int STRIKE_FRAME_LENGTH = 1;
    private static final int REGULAR_FRAME_LENGTH = 2;

    public int play(String rollSequence) {

        BowlingBoard board = new BowlingBoard();
        String[] framesAsStrings = rollSequence.split(" ");

        for(String frameAsString : framesAsStrings){
            Frame frame = mapStringToFrame(frameAsString);
            board.addFrame(frame);
        }


        return board.getScoreAccumulator();
    }

    /**
     * Map String representation of a frame e.g. "14" or "9/" to a Frame object
     */
    private Frame mapStringToFrame(String frameAsString){
        Frame frame;
        if(frameAsString.equals("X")){
            frame = new StrikeFrame();
        } else if(frameAsString.substring(1).equals("/")){ //Spare frame
            Roll firstRoll = mapStringToRoll(frameAsString.substring(0,1));
            String secondRollAsString = String.valueOf(10-firstRoll.getValue());
            Roll secondRoll = mapStringToRoll(secondRollAsString);
            frame = new SpareFrame(firstRoll,secondRoll);
        } else {
            Roll firstRoll = mapStringToRoll(frameAsString.substring(0,1));
            Roll secondRoll = mapStringToRoll(frameAsString.substring(1));
            frame = new StandardFrame(firstRoll, secondRoll);
        }

        return frame;
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
            default:
                throw new IllegalArgumentException("Attempting to map invalid character: "+rollAsString);
        }
        return roll;
    }

}
