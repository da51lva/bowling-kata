package com.techreturners.bowlingkata.app;

import com.techreturners.bowlingkata.model.*;

import java.util.Arrays;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BowlingGame {

    private static final Pattern SPARE_FRAME_PATTERN = Pattern.compile("^([1-9])/$");
    private static final Pattern TENTH_FRAME_SPARE_PATTERN = Pattern.compile("^(X?([1-9]))/([1-9X])?$");

    public int play(String rollSequence) {

        BowlingBoard board = new BowlingBoard();

        Arrays.stream(rollSequence.split(" ")) // process each frame individually
                .map(this::mapStringToFrame) // map string representation of frame to Frame object
                .forEach(board::addFrame); // add frame to Bowling Board

        return board.getScoreAccumulator();
    }

    /**
     * Map String representation of a frame e.g. "14" or "9/" to a Frame object
     */
    private Frame mapStringToFrame(String frameAsString) {

        Frame frame;
        Roll firstRoll = mapStringToRoll(frameAsString.substring(0, 1)); //every frame type will have a first roll
        Matcher matcher = SPARE_FRAME_PATTERN.matcher(frameAsString);


        if (frameAsString.equals("X")) {

            frame = new StrikeFrame();

        } else if (matcher.find()) { // Spare frame

            Roll secondRoll = mapStringToRoll(replaceSpareTokenInSpareFrame(matcher).substring(1));
            frame = new SpareFrame(firstRoll, secondRoll);

        } else if (frameAsString.length() == 2) { // Standard frame

            Roll secondRoll = mapStringToRoll(frameAsString.substring(1));
            frame = new StandardFrame(firstRoll, secondRoll);

        } else if (frameAsString.length() == 3) { // 10th Frame with bonus rolls

            matcher = TENTH_FRAME_SPARE_PATTERN.matcher(frameAsString);
            if (matcher.find()) frameAsString = replaceSpareTokenIn10thFrame(matcher); //replace spare token if present

            Roll secondRoll = mapStringToRoll(frameAsString.substring(1, 2));
            Roll thirdRoll = mapStringToRoll(frameAsString.substring(2));
            frame = new BonusRollFrame(firstRoll, secondRoll, thirdRoll);

        } else {
            throw new IllegalArgumentException("Attempting to map invalid string to frame: " + frameAsString);
        }
        return frame;
    }

    /**
     * Replaces Spare token with roll value in 10th Frame. Takes a matcher object which has performed matcher,find()
     */
    private String replaceSpareTokenIn10thFrame(Matcher matcher) {
        int precedingNumber = Integer.parseInt(matcher.group(2));
        return matcher.replaceFirst(matcher.group(1) + (10 - precedingNumber) + Optional.ofNullable(matcher.group(3)).orElse(
                ""));
    }

    /**
     * Replaces Spare token with roll value in a normal Frame. Takes a matcher object which has performed matcher,find()
     */
    private String replaceSpareTokenInSpareFrame(Matcher matcher) {
        int precedingNumber = Integer.parseInt(matcher.group(1));
        return matcher.replaceFirst(matcher.group(1) + (10 - precedingNumber));
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
