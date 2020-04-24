package com.tchristofferson.querybuilder;

public class SelectQueryBuilder extends QueryBuilder {

    public SelectQueryBuilder(boolean distinct, String ... columns) {
        super(new StringBuilder("SELECT " + (distinct ? "DISTINCT " : "") + String.join(", ", columns)));
    }

    public SelectQueryBuilder(boolean distinct) {
        this(distinct, "*");
    }

    public FromQueryBuilder from(String ... tables) {
        return new FromQueryBuilder(sqlBuilder, tables);
    }
}
