package com.tchristofferson;

public class FromQueryBuilder extends QueryBuilder {

    protected FromQueryBuilder(StringBuilder sqlBuilder, String[] tables) {
        this(sqlBuilder, String.join(", ", tables));
    }

    protected FromQueryBuilder(StringBuilder sqlBuilder, String table) {
        super(sqlBuilder);
        this.sqlBuilder.append(" FROM ").append(table);
    }

    public WhereQueryBuilder where(String column) {
        return new WhereQueryBuilder(sqlBuilder, column);
    }

    public WhereQueryBuilder where(String table, String column) {
        return new WhereQueryBuilder(sqlBuilder, table, column);
    }
}
