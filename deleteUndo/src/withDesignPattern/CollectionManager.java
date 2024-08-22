package withDesignPattern;

import java.util.HashMap;
import java.util.Stack;

public class CollectionManager {
    private HashMap<String, Collection> collections;
    private HashMap<String, Stack<Icommand>> userCollectionHistory;

    public CollectionManager(){
        collections = new HashMap<>();
        userCollectionHistory = new HashMap<>();
    }

    public void executeCommand(Icommand command, String userId){
        userCollectionHistory
                .computeIfAbsent(userId, k -> new Stack<>())
                .push(command);
        command.execute();
    }

    public Collection getCollectionInternal(String collectionId){
        return collections.get(collectionId);
    }

    public void addCollectionInternal(String collectionId, String collectionName, User user){
        Collection collection = new Collection(collectionId, collectionName, user);
        collections.put(collectionId, collection);
        System.out.println("Collection added: " + collectionName + " (Owner: " + user.getName() + ")");
    }

    void removeCollectionInternal(String collectionId){
        collections.remove(collectionId);
    }

    void updateLastCommand(String userId){
        Stack<Icommand> userCommands = userCollectionHistory.get(userId);
        if(userCommands != null && !userCommands.empty()){
            Icommand lastCommand = userCommands.pop();
        }
    }

    public void undo(String userId){
        Stack<Icommand> userCommands = userCollectionHistory.get(userId);

        if(userCommands != null && !userCommands.empty()){
            Icommand lastCommand = userCommands.pop();
            lastCommand.undo();
        }
    }

    public void listCollections(){
        for(Collection collection : collections.values()){
            System.out.println(collection.getCollectionId() + ": " + collection.getCollectionName() + " (Owner: " + collection.getUser().getName() + ")");
        }
        System.out.println("\n");
    }


}
