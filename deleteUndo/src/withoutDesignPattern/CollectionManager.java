package withoutDesignPattern;

import java.util.HashMap;
import java.util.Stack;

public class CollectionManager {
    private HashMap<String, Collection> collections;
    private HashMap<String, Stack<Collection>> userDeletedCollections;

    public CollectionManager() {
        this.collections = new HashMap<>();
        this.userDeletedCollections = new HashMap<>();
    }

    public void addCollection(Collection collection){
        collections.put(collection.getCollectionId(), collection);
        System.out.println("Collection " + collection.getCollectionName() + " added for " + collection.getUser().getName());
    }

    public void deleteCollection(String collectionId, String userId){
        Collection collection = collections.get(collectionId);

        if(collection != null && collection.getUser().getUserId().equalsIgnoreCase(userId)){
            collections.remove(collection.getCollectionId());
            userDeletedCollections
                    .computeIfAbsent(userId, k -> new Stack<>())
                    .push(collection);
            System.out.println("\nCollection deleted: " + collection.getCollectionName() + " by " + collection.getUser().getName());
        } else {
            System.out.println("\nCollection not found or user not authorized");
        }
    }

    public void undoCollection(String userId){
        Stack<Collection> deletedCollection = userDeletedCollections.get(userId);
        if(deletedCollection != null && !deletedCollection.isEmpty()){
            Collection restoredCollection = deletedCollection.pop();
            collections.put(restoredCollection.getCollectionId(), restoredCollection);
            System.out.println("\nRestored collection: " + restoredCollection.getCollectionName());
        }else {
            System.out.println("\nNo collections to restore for this user");
        }
    }

    public void listCollections() {
        for (Collection collection : collections.values()) {
            System.out.println(collection.getCollectionId() + ": " + collection.getCollectionName() + " (Owner: " + collection.getUser().getName() + ")");
        }
    }
}
