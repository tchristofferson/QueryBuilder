package com.tchristofferson.options;

public enum WhereOptions {

    EQUAL("=="),
    NOT_EQUAL("<>"),
    GREATER(">"),
    LESS("<"),
    GREATER_EQUAL(">="),
    LESS_EQUAL("<="),
    BETWEEN("BETWEEN"),
    NOT_BETWEEN("NOT BETWEEN"),
    LIKE("LIKE"),
    NOT_LIKE("NOT LIKE"),
    IN("IN"),
    NOT_IN("NOT IN");

    private final String operator;

    WhereOptions(String operator) {
        this.operator = operator;
    }


    @Override
    public String toString() {
        return operator;
    }
}
