package models;

import java.time.LocalDateTime;
import java.util.HashMap;

public class Row {
    private String rowId;
    private HashMap<String, Object> columnHashMap;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Row(String rowId, HashMap<String, Object> columnHashMap) {
        this.rowId = rowId;
        this.columnHashMap = columnHashMap;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public HashMap<String, Object> getColumnHashMap() {
        return columnHashMap;
    }

    public void updateColumnValue(String columnName, Object columnValue, ColumnType columnType){

        columnHashMap.put(columnName, columnValue);
        this.updatedAt = LocalDateTime.now();
    }

    public String getRowId() {
        return rowId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
