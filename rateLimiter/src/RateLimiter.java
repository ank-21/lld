import static java.lang.Thread.sleep;

public class RateLimiter {
    public static void main(String[] args) {
        TokenBucketManager manager = TokenBucketManager.getInstance();
        User user1 = addUser("Ankit", 21, manager);
        User user2 = addUser("Aayusha", 11, manager);
        try {
            manager.addRequest(user1);
            manager.addRequest(user1);
            manager.addRequest(user2);
            manager.addRequest(user2);
            Thread.sleep(7000);
            manager.addRequest(user2);
            manager.addRequest(user2);
            manager.addRequest(user2);
        }catch (InterruptedException e){
            System.err.println("Thread was interrupted: " + e.getMessage());
        }
    }
    private static User addUser(String name, int id, TokenBucketManager manager){
        User user = new User(name, id);
        manager.addMapping(user);
        return user;
    }
}
