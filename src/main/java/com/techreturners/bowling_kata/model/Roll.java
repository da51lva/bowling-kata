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
    SPARE(null);

    private final Integer value;

    private Roll(Integer value) {
        this.value = value;
    }

    public Integer getValue(){
        return value;
    }
}
