package withDesignPattern;

public class Collection {
    private String collectionId;
    private String collectionName;
    private User user;

    public Collection(String collectionId, String collectionName, User user) {
        this.collectionId = collectionId;
        this.collectionName = collectionName;
        this.user = user;
    }

    public String getCollectionId() {
        return collectionId;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public User getUser() {
        return user;
    }
}
