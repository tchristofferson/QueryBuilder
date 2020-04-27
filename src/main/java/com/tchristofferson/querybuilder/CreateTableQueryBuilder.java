package com.tchristofferson.querybuilder;

public class CreateTableQueryBuilder extends QueryBuilder {

    private boolean hasColumns;
    private boolean hasPrimaryKey;

    public CreateTableQueryBuilder(boolean createIfNotExists, String table) {
        super(new StringBuilder("CREATE TABLE ").append(createIfNotExists ? "IF NOT EXISTS " : "").append(table).append('('));
        this.hasColumns = false;
        this.hasPrimaryKey = false;
    }

    public CreateTableQueryBuilder withColumn(String column, String dataType, String ... constraints) {
        if (hasColumns)
            sqlBuilder.append(", ");

        sqlBuilder.append(column).append(' ').append(dataType);

        if (constraints.length > 0)
            sqlBuilder.append(' ').append(String.join(", ", constraints));

        this.hasColumns = true;
        return this;
    }

    public CreateTableQueryBuilder primaryKey(String ... columns) {
        checkForColumns();

        if (hasPrimaryKey)
            throw new IllegalStateException("query already has primary key specified");

        sqlBuilder.append(", PRIMARY KEY (").append(String.join(", ", columns)).append(')');
        return this;
    }

    public CreateTableQueryBuilder foreignKey(String column, String tableReference, String columnReference) {
        checkForColumns();
        sqlBuilder.append(", FOREIGN KEY (").append(column).append(") REFERENCES ").append(tableReference).append('(')
                .append(columnReference).append(')');

        return this;
    }

    @Override
    public String toString() {
        return sqlBuilder.toString() + ");";
    }

    private void checkForColumns() {
        if (!hasColumns)
            throw new IllegalStateException("no columns were found");
    }
}
