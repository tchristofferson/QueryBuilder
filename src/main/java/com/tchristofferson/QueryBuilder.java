package com.tchristofferson;

public class QueryBuilder {

    protected final StringBuilder sqlBuilder;

    protected QueryBuilder(StringBuilder sqlBuilder) {
        this.sqlBuilder = sqlBuilder;
    }

    @Override
    public String toString() {
        return sqlBuilder.toString() + ";";
    }
}
