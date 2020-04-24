package com.tchristofferson.querybuilder;

import com.tchristofferson.querybuilder.options.BooleanOptions;
import com.tchristofferson.querybuilder.options.WhereOptions;

/**
 * Objects will be turned into Strings using toString() by default
 */
//FIXME: Repeating code
public class WhereQueryBuilder extends QueryBuilder {

    protected WhereQueryBuilder(StringBuilder sqlBuilder, String column) {
        super(sqlBuilder);
        sqlBuilder.append(" WHERE ").append(column);
    }

    protected WhereQueryBuilder(StringBuilder sqlBuilder, String table, String column) {
        super(sqlBuilder);
        sqlBuilder.append(" WHERE ").append(table).append('.').append(column);
    }

    protected WhereQueryBuilder(StringBuilder sqlBuilder, String column, BooleanOptions option) {
        super(sqlBuilder);
        sqlBuilder.append(' ').append(option).append(' ').append(column);
    }

    public OperatorQueryBuilder is(String table, String column) {
        return new OperatorQueryBuilder(sqlBuilder, WhereOptions.EQUAL, table + "." + column);
    }

    public OperatorQueryBuilder is(CharSequence value) {
        return new OperatorQueryBuilder(sqlBuilder, WhereOptions.EQUAL, "'" + value + "'");
    }

    public OperatorQueryBuilder is(char character) {
        return is(new String(new char[]{character}));
    }

    public OperatorQueryBuilder is(int value) {
        return is(String.valueOf(value));
    }

    public OperatorQueryBuilder is(double value) {
        return is(String.valueOf(value));
    }

    public OperatorQueryBuilder is(boolean value) {
        return is(String.valueOf(value).toUpperCase());
    }

    public OperatorQueryBuilder isNot(CharSequence value) {
        return new OperatorQueryBuilder(sqlBuilder, WhereOptions.NOT_EQUAL, "'" + value + "'");
    }

    public OperatorQueryBuilder isNot(char character) {
        return isNot(new String(new char[]{character}));
    }

    public OperatorQueryBuilder isNot(int value) {
        return isNot(String.valueOf(value));
    }

    public OperatorQueryBuilder isNot(double value) {
        return isNot(String.valueOf(value));
    }

    public OperatorQueryBuilder isNot(boolean value) {
        return isNot(String.valueOf(value).toUpperCase());
    }

    public OperatorQueryBuilder greaterThan(CharSequence value) {
        return new OperatorQueryBuilder(sqlBuilder, WhereOptions.GREATER, "'" + value + "'");
    }

    public OperatorQueryBuilder greaterThan(char character) {
        return greaterThan(new String(new char[]{character}));
    }

    public OperatorQueryBuilder greaterThan(int value) {
        return greaterThan(String.valueOf(value));
    }

    public OperatorQueryBuilder greaterThan(double value) {
        return greaterThan(String.valueOf(value));
    }

    public OperatorQueryBuilder greaterThan(boolean value) {
        return greaterThan(String.valueOf(value).toUpperCase());
    }

    public OperatorQueryBuilder lessThan(CharSequence value) {
        return new OperatorQueryBuilder(sqlBuilder, WhereOptions.LESS, "'" + value + "'");
    }

    public OperatorQueryBuilder lessThan(char character) {
        return lessThan(new String(new char[]{character}));
    }

    public OperatorQueryBuilder lessThan(int value) {
        return lessThan(String.valueOf(value));
    }

    public OperatorQueryBuilder lessThan(double value) {
        return lessThan(String.valueOf(value));
    }

    public OperatorQueryBuilder lessThan(boolean value) {
        return lessThan(String.valueOf(value).toUpperCase());
    }

    public OperatorQueryBuilder greaterOrEqual(CharSequence value) {
        return new OperatorQueryBuilder(sqlBuilder, WhereOptions.GREATER_EQUAL, "'" + value + "'");
    }

    public OperatorQueryBuilder greaterOrEqual(char character) {
        return greaterOrEqual(new String(new char[]{character}));
    }

    public OperatorQueryBuilder greaterOrEqual(int value) {
        return greaterOrEqual(String.valueOf(value));
    }

    public OperatorQueryBuilder greaterOrEqual(double value) {
        return greaterOrEqual(String.valueOf(value));
    }

    public OperatorQueryBuilder greaterOrEqual(boolean value) {
        return greaterOrEqual(String.valueOf(value).toUpperCase());
    }

    public OperatorQueryBuilder lessOrEqual(CharSequence value) {
        return new OperatorQueryBuilder(sqlBuilder, WhereOptions.LESS_EQUAL, "'" + value + "'");
    }

    public OperatorQueryBuilder lessOrEqual(char character) {
        return lessOrEqual(new String(new char[]{character}));
    }

    public OperatorQueryBuilder lessOrEqual(int value) {
        return lessOrEqual(String.valueOf(value));
    }

    public OperatorQueryBuilder lessOrEqual(double value) {
        return lessOrEqual(String.valueOf(value));
    }

    public OperatorQueryBuilder lessOrEqual(boolean value) {
        return lessOrEqual(String.valueOf(value).toUpperCase());
    }

    public OperatorQueryBuilder in(CharSequence ... values) {
        return new OperatorQueryBuilder(sqlBuilder, WhereOptions.IN, getInString(values));
    }

    public OperatorQueryBuilder in(Character ... values) {
        return new OperatorQueryBuilder(sqlBuilder, WhereOptions.IN, getInString(values));
    }

    public OperatorQueryBuilder in(Integer ... values) {
        return new OperatorQueryBuilder(sqlBuilder, WhereOptions.IN, getInString(values));
    }

    public OperatorQueryBuilder in(Double ... values) {
        return new OperatorQueryBuilder(sqlBuilder, WhereOptions.IN, getInString(values));
    }

    public OperatorQueryBuilder notIn(CharSequence ... values) {
        return new OperatorQueryBuilder(sqlBuilder, WhereOptions.NOT_IN, getInString(values));
    }

    public OperatorQueryBuilder notIn(Character ... values) {
        return new OperatorQueryBuilder(sqlBuilder, WhereOptions.NOT_IN, getInString(values));
    }

    public OperatorQueryBuilder notIn(Integer ... values) {
        return new OperatorQueryBuilder(sqlBuilder, WhereOptions.NOT_IN, getInString(values));
    }

    public OperatorQueryBuilder notIn(Double ... values) {
        return new OperatorQueryBuilder(sqlBuilder, WhereOptions.NOT_IN, getInString(values));
    }

    //TODO: Between & Like
    public OperatorQueryBuilder between(CharSequence lowerValue, CharSequence higherValue) {
        return new OperatorQueryBuilder(sqlBuilder, WhereOptions.BETWEEN, "'" + lowerValue + "' AND '" + higherValue + "'");
    }

    public OperatorQueryBuilder between(char lowerValue, char higherValue) {
        return between(String.valueOf(lowerValue), String.valueOf(higherValue));
    }

    public OperatorQueryBuilder between(int lowerValue, int higherValue) {
        return new OperatorQueryBuilder(sqlBuilder, WhereOptions.BETWEEN, lowerValue + " AND " + higherValue);
    }

    public OperatorQueryBuilder between(double lowerValue, double higherValue) {
        return new OperatorQueryBuilder(sqlBuilder, WhereOptions.BETWEEN, lowerValue + " AND " + higherValue);
    }

    public OperatorQueryBuilder notBetween(CharSequence lowerValue, CharSequence higherValue) {
        return new OperatorQueryBuilder(sqlBuilder, WhereOptions.NOT_BETWEEN, "'" + lowerValue + "' AND '" + higherValue + "'");
    }

    public OperatorQueryBuilder notBetween(char lowerValue, char higherValue) {
        return new OperatorQueryBuilder(sqlBuilder, WhereOptions.NOT_BETWEEN, "'" + lowerValue + "' AND '" + higherValue + "'");
    }

    public OperatorQueryBuilder notBetween(int lowerValue, int higherValue) {
        return new OperatorQueryBuilder(sqlBuilder, WhereOptions.NOT_BETWEEN, lowerValue + " AND " + higherValue);
    }

    public OperatorQueryBuilder notBetween(double lowerValue, double higherValue) {
        return new OperatorQueryBuilder(sqlBuilder, WhereOptions.NOT_BETWEEN, lowerValue + " AND " + higherValue);
    }

    public OperatorQueryBuilder like(String value) {
        return new OperatorQueryBuilder(sqlBuilder, WhereOptions.LIKE, value);
    }

    public OperatorQueryBuilder notLike(String value) {
        return new OperatorQueryBuilder(sqlBuilder, WhereOptions.NOT_LIKE, value);
    }

    private String getInString(Object[] objects) {
        StringBuilder builder = new StringBuilder("(");

        for (int i = 0; i < objects.length; i++) {
            Object object = objects[i];

            if (object instanceof CharSequence || object instanceof Character) {
                builder.append("'").append(object).append("'");
            } else {
                builder.append(object);
            }

            if (i != objects.length - 1) {
                builder.append(", ");
            }
        }

        return builder.append(")").toString();
    }
}
