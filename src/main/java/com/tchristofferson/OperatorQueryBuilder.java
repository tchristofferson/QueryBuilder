package com.tchristofferson;

import com.tchristofferson.options.BooleanOptions;
import com.tchristofferson.options.WhereOptions;

public class OperatorQueryBuilder extends QueryBuilder {

    protected OperatorQueryBuilder(StringBuilder sqlBuilder, WhereOptions option, String value) {
        super(sqlBuilder);
        this.sqlBuilder.append(' ').append(option).append(' ').append(value);
    }

    public WhereQueryBuilder and(String column) {
        return new WhereQueryBuilder(sqlBuilder, BooleanOptions.AND);
    }

    public WhereQueryBuilder or(String column) {
        return new WhereQueryBuilder(sqlBuilder, BooleanOptions.OR);
    }
}
