package com.tchristofferson.querybuilder;

import com.tchristofferson.querybuilder.options.BooleanOptions;
import com.tchristofferson.querybuilder.options.WhereOptions;

public class OperatorQueryBuilder extends QueryBuilder {

    protected OperatorQueryBuilder(StringBuilder sqlBuilder, WhereOptions option, String value) {
        super(sqlBuilder);
        this.sqlBuilder.append(' ').append(option).append(' ').append(value);
    }

    public WhereQueryBuilder and(String column) {
        return new WhereQueryBuilder(sqlBuilder, column, BooleanOptions.AND);
    }

    public WhereQueryBuilder and(String table, String column) {
        return and(table + "." + column);
    }

    public WhereQueryBuilder or(String column) {
        return new WhereQueryBuilder(sqlBuilder, column, BooleanOptions.OR);
    }

    public WhereQueryBuilder or(String table, String column) {
        return or(table + "." + column);
    }
}
