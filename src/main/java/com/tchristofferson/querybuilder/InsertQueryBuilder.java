package com.tchristofferson.querybuilder;

public class InsertQueryBuilder extends QueryBuilder {

    private boolean columnsSet;
    private boolean valuesSet;

    public InsertQueryBuilder(String table) {
        super(new StringBuilder("INSERT INTO ").append(table));
        this.columnsSet = false;
        this.valuesSet = false;
    }

    public InsertQueryBuilder withColumns(String ... columns) {
        if (columnsSet)
            throw new IllegalStateException("columns already specified");

        if (valuesSet)
            throw new IllegalStateException("values already specified");

        sqlBuilder.append(" (").append(String.join(", ", columns)).append(')');
        columnsSet = true;
        return this;
    }

    public InsertQueryBuilder withValues(Object ... values) {
        if (valuesSet)
            sqlBuilder.append(",");
        else
            sqlBuilder.append(" VALUES");

        sqlBuilder.append(" (");

        for (int i = 0; i < values.length; i++) {
            Object value = values[i];

            if (value instanceof CharSequence || value instanceof Character)
                sqlBuilder.append("'").append(value).append("'");
            else
                sqlBuilder.append(value);

            if (i != values.length - 1)
                sqlBuilder.append(", ");
        }

        sqlBuilder.append(')');
        valuesSet = true;
        return this;
    }
}
