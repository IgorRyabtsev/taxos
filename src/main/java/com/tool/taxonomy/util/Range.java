package com.tool.taxonomy.util;

public class Range <T> {
    private T leftValue;
    private T rightValue;

    public Range() {}

    public Range(T leftValue, T rightValue) {
        this.leftValue = leftValue;
        this.rightValue = rightValue;
    }

    public T getLeftValue() {
        return leftValue;
    }

    public void setLeftValue(final T leftValue) {
        this.leftValue = leftValue;
    }

    public T getRightValue() {
        return rightValue;
    }

    public void setRightValue(final T rightValue) {
        this.rightValue = rightValue;
    }
}
