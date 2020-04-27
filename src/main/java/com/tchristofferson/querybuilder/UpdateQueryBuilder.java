package com.tchristofferson.querybuilder;

import com.tchristofferson.querybuilder.options.WhereOptions;

public class UpdateQueryBuilder extends QueryBuilder {

    private boolean hasSet;

    public UpdateQueryBuilder(String table) {
        super(new StringBuilder("UPDATE " + table));
        this.hasSet = false;
    }

    public UpdateQueryBuilder set(String column, Object value) {
        if (!hasSet)
            sqlBuilder.append(" SET");
        else
            sqlBuilder.append(',');

        sqlBuilder.append(' ').append(column).append(' ').append(WhereOptions.EQUAL).append(' ');

        if (value instanceof CharSequence || value instanceof Character)
            sqlBuilder.append("'").append(value).append("'");
        else
            sqlBuilder.append(value);

        hasSet = true;
        return this;
    }

    public WhereQueryBuilder where(String column) {
        return new WhereQueryBuilder(sqlBuilder, column, false);
    }

    public WhereQueryBuilder where(String table, String column) {
        return new WhereQueryBuilder(sqlBuilder, table, column);
    }
}
