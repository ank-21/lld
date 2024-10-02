package models;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class Database {
    private String databaseName;
    private String databaseId;
    private HashMap<String, Table> tables;
    private LocalDateTime createdAt;

    public Database(String databaseName, String databaseId, LocalDateTime createdAt) {
        this.databaseName = databaseName;
        this.databaseId = databaseId;
        this.createdAt = createdAt;
        this.tables = new HashMap<>();
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public String getDatabaseId() {
        return databaseId;
    }

    public HashMap<String, Table> getTables() {
        return tables;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Table createTable(String tableName, List<Column> columns, Column primaryKey){
        if(tables.containsKey(tableName)){
            System.out.println("Table name already exists!");
            return null;
        }
        String tableId = UUID.randomUUID().toString();
        Table table = new Table(tableName, tableId, columns, LocalDateTime.now(), primaryKey);
        tables.put(tableName, table);
        System.out.println("Table " + tableName + " created successfully!");
        return table;
    }

    public void showTables(){
        System.out.println();
        for(Table table : tables.values()){
            System.out.println("Table Name : " + table.getTableName());
            System.out.println("Table Number : " + table.getTableNo());
            System.out.println("Primary Key : " + table.getPrimaryKey().getColumnName());
            for(Column column : table.getColumnHashMap().values()){
                System.out.print("Column Name " + column.getColumnName());
                System.out.println("\tColumn Type " + column.getColumnType());
            }
            System.out.println("----------------------------------\n");
        }
    }

    public void dropTable(String tableName){
        if(!(tables.containsKey(tableName))){
            System.out.println("No such table found");
            return;
        }
        tables.remove(tableName);
        System.out.println("Table " + tableName + " deleted successfully!");
    }
}
