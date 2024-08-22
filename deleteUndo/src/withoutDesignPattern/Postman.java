package withoutDesignPattern;

public class Postman {
    public static void main(String[] args) {
        CollectionManager manager = new CollectionManager();

        User user1 = new User("Ankit", "21");
        User user2 = new User("Aayusha", "11");

        manager.addCollection(new Collection("111", "Testing Amazon Read API", "Amazon READ API", user1));
        manager.addCollection(new Collection("112", "Testing Amazon POST API", "Amazon POST API", user1));
        manager.addCollection(new Collection("113", "Testing Payment POST API", "Paytm POST API", user2));
        manager.addCollection(new Collection("114", "Testing Myntra READ API", "Myntra READ API", user1));

        System.out.println("\nInitial collections:");
        manager.listCollections();

        // Delete a collection for user1
        manager.deleteCollection("111", user1.getUserId());

        System.out.println("\nAfter deleting collection 111:");
        manager.listCollections();

        // Try to delete user2's collection as user1 (should fail)
        manager.deleteCollection("112", user2.getUserId());
        manager.deleteCollection("112", user1.getUserId());
        manager.deleteCollection("113", user2.getUserId());

        // Undo delete for user1
        manager.undoCollection(user1.getUserId());

        System.out.println("\nAfter undoing the delete for user1:");
        manager.listCollections();

        // Try to undo for user2 (should have no effect)
        manager.undoCollection(user2.getUserId());

        System.out.println("\nAfter attempting undo for user2:");
        manager.listCollections();
    }
}
