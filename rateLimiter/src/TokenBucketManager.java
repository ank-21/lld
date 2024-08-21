import java.util.HashMap;

public class TokenBucketManager {
    private HashMap<Integer, TokenBucket> userTokenBucketMapping;
    private static volatile TokenBucketManager instance = null;

    private TokenBucketManager() {
        this.userTokenBucketMapping = new HashMap<>();
    }

    public static TokenBucketManager getInstance(){
        if(instance == null){
            synchronized (TokenBucketManager.class){
                if(instance == null){
                    instance = new TokenBucketManager();
                }
            }
        }
        return instance;
    }

    public void addMapping(User user){
        TokenBucket tokenBucket = new TokenBucket();
        userTokenBucketMapping.put(user.getId(), tokenBucket);
    }

    public void addRequest(User user){
        synchronized (this){
            TokenBucket tokenBucket = userTokenBucketMapping.get(user.getId());
            if (tokenBucket == null) {
                System.out.println("User not found: " + user.getName());
                return;
            }
            if(tokenBucket.allowRequest()){
                System.out.println(user.getName() + " has " + tokenBucket.getCurrentTokens() + " tokens left");
            }else{
                System.out.println("Too many requests by " + user.getName());
            }
        }
        /*
        Scope: Only the block inside synchronized is locked, allowing other methods of the same instance to execute concurrently if they don’t require the lock.
        Granularity: This approach allows for finer control, and you can minimize the synchronized code to only the parts that need thread-safety.
        Flexibility: You can synchronize on different objects if needed, providing more flexibility in complex scenarios.
         */
    }
}
