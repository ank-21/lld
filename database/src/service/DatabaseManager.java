package service;

import models.Database;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.UUID;

public class DatabaseManager {
    private HashMap<String, Database> databaseHashMap;
    private DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private DatabaseManager(){
        databaseHashMap = new HashMap<>();
    }

    private static volatile DatabaseManager instance = null;

    public static DatabaseManager getInstance(){
        if(instance == null){
            synchronized (DatabaseManager.class){
                if(instance == null){
                    return instance = new DatabaseManager();
                }
            }
        }
        return instance;
    }

    public Database createDatabase(String databaseName){
        if(!databaseHashMap.containsKey(databaseName)){
            String databaseId = UUID.randomUUID().toString();
            Database database = new Database(databaseName, databaseId, LocalDateTime.now());
            databaseHashMap.put(databaseName, database);
            System.out.println("Database " + databaseName + " added successfully!");
            return database;
        }else{
            System.out.println("Database with name " + databaseName + " is already added in the database!");
        }
        return null;
    }

    public void showDB(){
        for (Database database : databaseHashMap.values()){
            System.out.println("Database Name : " + database.getDatabaseName());
            System.out.println("Database Id : " + database.getDatabaseId());
            System.out.println("Database Creation Time : " + database.getCreatedAt().format(dateFormat));
            System.out.println("Total number of tables : " + database.getTables().size());
            System.out.println();
        }
    }

    public void deleteDatabase(String databaseName){
        if(!databaseHashMap.containsKey(databaseName)){
            System.out.println("No database with name" + databaseName + " exists!");
        }else{
            databaseHashMap.remove(databaseName);
            System.out.println("Database " + databaseName + " removed successfully!");
        }
    }
}
