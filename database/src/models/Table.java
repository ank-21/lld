package models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class Table {
    private String tableName;
    private String tableId;
    private HashMap<String, Column> columnHashMap;
    private List<Row> rows;
    private LocalDateTime createdAt;

    public Table(String tableName, String tableId, List<Column> columns, LocalDateTime createdAt) {
        this.tableName = tableName;
        this.tableId = tableId;
        this.createdAt = createdAt;
        this.columnHashMap = new HashMap<>();
        populateColumnHashMap(columns);
        this.rows = new ArrayList<>();
    }

    private void populateColumnHashMap(List<Column> columns){
        for(Column column : columns){
            columnHashMap.put(column.getColumnName(), column);
        }
    }

    public String getTableName() {
        return tableName;
    }

    public String getTableId() {
        return tableId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public HashMap<String, Column> getColumnHashMap() {
        return columnHashMap;
    }

    public List<Row> getRows() {
        return rows;
    }

    public synchronized void insertRow(HashMap<Column, Object> columnValues){
        // Checking if any column name doesn't exist
        for(Column column : columnValues.keySet()){
            if(!checkIfColumnExists(column.getColumnName())) return;
        }
        String id = UUID.randomUUID().toString();
        HashMap<Column, Object> columnData = new HashMap<>(columnValues);
        Row row = new Row(id, columnData);
        rows.add(row);
    }

    public void showAll(){
        System.out.println("Printing values of the table " + tableName + "\n");
        for(Row row : rows){
            HashMap<String, String> columnValue = row.getColumnHashMap();

            columnValue.forEach((k, v) -> System.out.println(k + "\t" + v));
            System.out.println("----------------------");
        }
    }

    public void updateRow(String rowId, String columnName, String columnValue){
        for(Row row : rows){
            if(row.getRowId().equals(rowId)){
                row.updateColumnValue(columnName, columnValue);
                System.out.println("Row successfully updated!");
                return;
            }
        }
        System.out.println("Row with row id " + rowId + " not found!");
    }

    public void truncateRows(){
        this.rows.clear();
    }

    private Boolean checkIfColumnExists(String columnName) {

        if(!columnMap.containsKey(columnName)) {
            System.out.println("TableName: "+this.name+" does not contains column: "+columnName);
            return false;
        }
        return true;
    }
}
