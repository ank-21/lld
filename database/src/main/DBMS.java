package main;

import models.Column;
import models.ColumnType;
import models.Database;
import models.Table;
import service.DatabaseManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DBMS {
    public static void main(String[] args) {
        DatabaseManager databaseManager = DatabaseManager.getInstance();
        Database db = databaseManager.createDatabase("IPL-DB");
        databaseManager.showDB();

        Column c0 = new Column("C100", "S.NO", ColumnType.INT);
        Column c1 = new Column("c101","PlayerName", ColumnType.STRING);
        Column c2 = new Column("c102","Price", ColumnType.INT);
        Column c3 = new Column("c103", "Runs", ColumnType.INT);
        Column c4 = new Column("c104","Wickets", ColumnType.INT);

        // For KKR Batsmen table
        List<Column> batsmenColumns = new ArrayList<>();
        batsmenColumns.add(c0);
        batsmenColumns.add(c1);
        batsmenColumns.add(c2);
        batsmenColumns.add(c3);
        Table table1 = db.createTable("KKR Batsmen", batsmenColumns, c0);

        // For KKR AllRounders table
        // Need to keep separate ArrayList, otherwise it will get changed as the columns list
        // in the DBMS class is being shared across multiple tables.
        // When you modify this list (e.g., by adding or removing columns),
        // the changes reflect in all tables that reference this list.
        // This happens because Java passes objects by reference.

        List<Column> allRoundersColumns = new ArrayList<>();
        allRoundersColumns.add(c0);
        allRoundersColumns.add(c1);
        allRoundersColumns.add(c2);
        allRoundersColumns.add(c3);
        allRoundersColumns.add(c4);
        Table table2 = db.createTable("KKR AllRounders", allRoundersColumns, c0);

        // For KKR Bowlers table
        List<Column> bowlersColumns = new ArrayList<>();
        bowlersColumns.add(c0);
        bowlersColumns.add(c1);
        bowlersColumns.add(c2);
        bowlersColumns.add(c4);  // Exclude Runs column for bowlers
        Table table3 = db.createTable("KKR Bowlers", bowlersColumns, c0);

        db.showTables();

        HashMap<String, Object> columnValue1 = new HashMap<>();
        columnValue1.put(c0.getColumnName(), 32);
        columnValue1.put(c1.getColumnName(), "S.Iyer");
        columnValue1.put(c2.getColumnName(), 14);
        String rowId1 = table1.insertRow(columnValue1);

        HashMap<String, Object> columnValue2 = new HashMap<>();
        columnValue2.put(c0.getColumnName(), 54);
        columnValue2.put(c1.getColumnName(), "P.Salt");
        columnValue2.put(c2.getColumnName(), 6);

        String rowId2 = table1.insertRow(columnValue2);

        table1.showAll();

        table1.updateRow(c3.getColumnName(), 434, rowId1);
        table1.updateRow(c2.getColumnName(), 10, rowId2);
        table1.showAll();
        db.dropTable(table2.getTableName());
        db.showTables();
        table1.showRecordWithCondition(c1.getColumnName(), "P.Salt");
    }
}
