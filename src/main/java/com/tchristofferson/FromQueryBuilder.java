package com.tchristofferson;

public class FromQueryBuilder extends QueryBuilder {

    protected FromQueryBuilder(StringBuilder sqlBuilder, String[] tables) {
        super(sqlBuilder);
        this.sqlBuilder.append(" FROM ").append(String.join(", ", tables));
    }

    public WhereQueryBuilder where(String column) {
        return new WhereQueryBuilder(sqlBuilder, column);
    }
}
