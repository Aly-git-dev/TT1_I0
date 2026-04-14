package com.upiiz.platform_api.models;

public enum TeacherEvaluationRating {
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5);

    private final int value;

    TeacherEvaluationRating(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static void validate(Integer rating, String fieldName) {
        if (rating == null || rating < 1 || rating > 5) {
            throw new IllegalArgumentException(fieldName + " debe estar entre 1 y 5");
        }
    }
}
