package models;

public class Column {
    private String columnName;
    private ColumnType columnType;
    private String columnId;

    public Column(String columnId, String columnName, ColumnType columnType) {
        this.columnId = columnId;
        this.columnName = columnName;
        this.columnType = columnType;
    }

    public String getColumnId() {
        return columnId;
    }

    public String getColumnName() {
        return columnName;
    }

    public ColumnType getColumnType() {
        return columnType;
    }
}
