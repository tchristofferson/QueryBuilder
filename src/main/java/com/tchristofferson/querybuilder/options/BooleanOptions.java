package com.tchristofferson.querybuilder.options;

public enum BooleanOptions {

    AND("AND"),
    OR("OR");

    private final String operator;

    BooleanOptions(String operator) {
        this.operator = operator;
    }

    @Override
    public String toString() {
        return operator;
    }
}
