package com.techreturners.bowling_kata.model;

public enum Roll {
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    MISS(0),
    SPARE(10),
    STRIKE(10);

    private final int value;

    private Roll(int value) {
        this.value = value;
    }

    public int getValue(){
        return value;
    }
}
