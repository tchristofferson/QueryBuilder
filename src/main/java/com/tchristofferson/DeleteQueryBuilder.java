package com.tchristofferson;

public class DeleteQueryBuilder extends QueryBuilder {

    public DeleteQueryBuilder() {
        super(new StringBuilder("DELETE"));
    }

    public FromQueryBuilder from(String table) {
        return new FromQueryBuilder(sqlBuilder, table);
    }
}
