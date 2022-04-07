package com.techreturners.bowling_kata.app;

import com.techreturners.bowling_kata.model.*;

import java.util.Arrays;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BowlingGame {

    private static final Pattern spare_pattern = Pattern.compile("^(X?([1-9]))/([1-9X])?$");

    public int play(String rollSequence) {

        BowlingBoard board = new BowlingBoard();

        Arrays.stream(rollSequence.split(" "))
                .map(this::mapStringToFrame)
                .forEach(board::addFrame);

        return board.getScoreAccumulator();
    }

    /**
     * Map String representation of a frame e.g. "14" or "9/" to a Frame object
     */
    private Frame mapStringToFrame(String frameAsString) { //todo: refactor disgusting code
        Frame frame;
        if (frameAsString.equals("X")) {
            frame = new StrikeFrame();
        } else if (frameAsString.substring(1).equals("/")) { // Spare frame
            Roll firstRoll = mapStringToRoll(frameAsString.substring(0, 1));
            String secondRollAsString = String.valueOf(10 - firstRoll.getValue());
            Roll secondRoll = mapStringToRoll(secondRollAsString);
            frame = new SpareFrame(firstRoll, secondRoll);
        } else if (frameAsString.length() == 2) { // Standard frame
            Roll firstRoll = mapStringToRoll(frameAsString.substring(0, 1));
            Roll secondRoll = mapStringToRoll(frameAsString.substring(1));
            frame = new StandardFrame(firstRoll, secondRoll);
        } else if (frameAsString.length() == 3) { // 10th Frame with bonus rolls
            frameAsString = replaceSpareToken(frameAsString);
            Roll firstRoll = mapStringToRoll(frameAsString.substring(0, 1));
            Roll secondRoll = mapStringToRoll(frameAsString.substring(1, 2));
            Roll thirdRoll = mapStringToRoll(frameAsString.substring(2));
            return new BonusRollFrame(firstRoll, secondRoll, thirdRoll);
        } else {
            throw new IllegalArgumentException("Attempting to map invalid string to frame: " + frameAsString);
        }
        return frame;
    }

    private String replaceSpareToken(String frame) {
        Matcher matcher = spare_pattern.matcher(frame);
        if (matcher.find()) {
            int precedingNumber = Integer.parseInt(matcher.group(2));
            return matcher.replaceFirst(matcher.group(1) + (10 - precedingNumber) + Optional.ofNullable(matcher.group(3)).orElse(
                    ""));
        }
        return frame;
    }

    /**
     * Map String representation of a roll e.g. "1" or "/" or "-" to a Roll enum
     */
    private Roll mapStringToRoll(String rollAsString) {
        Roll roll;
        switch (rollAsString) {
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
            case ("X"):
                roll = Roll.STRIKE;
                break;
            default:
                throw new IllegalArgumentException("Attempting to map invalid character: " + rollAsString);
        }
        return roll;
    }

}
