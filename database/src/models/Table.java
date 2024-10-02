package models;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class Table {
    private String tableName;
    private String tableId;
    private static AtomicInteger count = new AtomicInteger(0);
    private int tableNo;
    private HashMap<String, Column> columnMap;  // Mapping of column Name with column object
    private HashMap<String, Row> rowHashMap;    // All the rows in the table mapped as RowId -> Row
    private Column primaryKey;
    private LocalDateTime createdAt;

    public Table(String tableName, String tableId, List<Column> columns, LocalDateTime createdAt, Column primaryKey) {
        this.tableName = tableName;
        this.tableId = tableId;
        this.tableNo = count.incrementAndGet();
        this.createdAt = createdAt;
        this.columnMap = new HashMap<>();
        populateColumnHashMap(columns);
        this.rowHashMap = new HashMap<>();
        this.primaryKey = primaryKey;
    }

    private void populateColumnHashMap(List<Column> columns){
        for(Column column : columns){
            columnMap.put(column.getColumnName(), column);
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
        return columnMap;
    }

    public HashMap<String, Row> getRowHashMap() {
        return rowHashMap;
    }

    public int getTableNo() {
        return tableNo;
    }

    public Column getPrimaryKey() {
        return primaryKey;
    }

    public synchronized String insertRow(HashMap<String, Object> columnValues){
        // Checking primary key
        if(!columnValues.containsKey(primaryKey.getColumnName())){
            System.out.println("Please add primary key " + primaryKey.getColumnName() + " as well." );
            return "null";
        }

        String id = UUID.randomUUID().toString();
        HashMap<String, Object> columnData = new HashMap<>(columnValues);
        Row row = new Row(id, columnData);
        rowHashMap.put(id, row);
        return id;
    }

    public void showAll(){
        System.out.println("Printing values of the table " + tableName + "\n");
        for(Row row : rowHashMap.values()){
            row.getColumnHashMap().forEach((columnName, columnValue) -> {
                System.out.println(columnName + " : " + columnValue);
            });
        }
        System.out.println();
    }

    public void updateRow(String columnName, Object columnValue, String rowId){
        if(!rowHashMap.containsKey(rowId))
            System.out.println("Row with row id " + rowId + " not found!");
        else{
            HashMap<String, Object> columnMap = rowHashMap.get(rowId).getColumnHashMap();
            columnMap.put(columnName, columnValue);
        }
    }

    public void showRecordWithCondition(String columnName, Object data){
        for(Row row : rowHashMap.values()){
            if(row.getColumnHashMap().containsKey(columnName) && row.getColumnHashMap().get(columnName).equals(data)){
                System.out.println("Row which matches to the condition where column Name is " + columnName +
                        " and value is " + data + "is "
                );
                row.getColumnHashMap().forEach((columnKey, columnValue) -> {
                    System.out.println(columnKey + " : " + columnValue);
                });
            }
        }
    }

    public void truncateRows(){
        this.rowHashMap.clear();
    }
}
