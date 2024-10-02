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

        Column c1 = new Column("c101","PlayerName", ColumnType.STRING);
        Column c2 = new Column("c102","Price", ColumnType.INT);
        Column c3 = new Column("c103", "Runs", ColumnType.INT);
        Column c4 = new Column("c104","Wickets", ColumnType.INT);

        // For KKR Batsmen table
        List<Column> batsmenColumns = new ArrayList<>();
        batsmenColumns.add(c1);
        batsmenColumns.add(c2);
        batsmenColumns.add(c3);
        Table table1 = db.createTable("KKR Batsmen", batsmenColumns);

        // For KKR AllRounders table
        List<Column> allRoundersColumns = new ArrayList<>();
        allRoundersColumns.add(c1);
        allRoundersColumns.add(c2);
        allRoundersColumns.add(c3);
        allRoundersColumns.add(c4);
        Table table2 = db.createTable("KKR AllRounders", allRoundersColumns);

        // For KKR Bowlers table
        List<Column> bowlersColumns = new ArrayList<>();
        bowlersColumns.add(c1);
        bowlersColumns.add(c2);
        bowlersColumns.add(c4);  // Exclude Runs column for bowlers
        Table table3 = db.createTable("KKR Bowlers", bowlersColumns);

        db.showTables();

        HashMap<String, String> columnValue1 = new HashMap<>();
        columnValue1.put(c1.getColumnName(), "S.Iyer");
        columnValue1.put(c2.getColumnName(), "14");
        String rowId1 = table1.insertRow(columnValue1);

        HashMap<String, String> columnValue2 = new HashMap<>();
        columnValue2.put(c1.getColumnName(), "P.Salt");
        columnValue2.put(c2.getColumnName(), "6");

        String rowId2 = table1.insertRow(columnValue2);

        table1.showAll();

        table1.updateRow(rowId1, c3.getColumnName(), "434");
        table1.updateRow(rowId2, c2.getColumnName(), "10");
        table1.showAll();
        db.dropTable(table2.getTableName());
        db.showTables();
    }
}
