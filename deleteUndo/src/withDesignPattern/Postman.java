package withDesignPattern;

public class Postman {
    public static void main(String[] args) {
        CollectionManager collectionManager = new CollectionManager();

        User user1 = new User("Ankit", "21");
        User user2 = new User("Aayusha", "11");

        // can be done by first setting the command and then calling execute
        // In that case command reference will be used in CollectionManager
        // But it will increase code line as not like the DP example where there is a fixed no of commands
        collectionManager.executeCommand(new AddCollection(collectionManager, "1", "API Tests", user1), user1.getUserId());
        collectionManager.executeCommand(new AddCollection(collectionManager, "2", "Web Scraping", user1), user1.getUserId());
        collectionManager.executeCommand(new AddCollection(collectionManager, "3", "Database Queries", user2), user2.getUserId());

        System.out.println("\nInitial collections:");
        collectionManager.listCollections();

        // Delete a collection for user1
        collectionManager.executeCommand(new DeleteCollection(collectionManager, "2", user1.getUserId()), user1.getUserId());

        System.out.println("\nAfter deleting collection 2:");
        collectionManager.listCollections();

        // Try to delete user2's collection as user1 (should fail)
        collectionManager.executeCommand(new DeleteCollection(collectionManager, "3", user1.getUserId()), user1.getUserId());
        collectionManager.listCollections();

        // Undo delete for user1
        collectionManager.undo(user1.getUserId());

        System.out.println("\nAfter undoing the delete for user1:");
        collectionManager.listCollections();

        // Try to undo for user2 (should have no effect) as undo is for delete
        collectionManager.undo(user2.getUserId());

        System.out.println("\nAfter attempting undo for user2:");
        collectionManager.listCollections();
    }
}
