package withoutDesignPattern;

public class Collection {
    private String collectionId;
    private String description;
    private String collectionName;
    private User user;

    public Collection(String collectionId, String description, String collectionName, User user) {
        this.collectionId = collectionId;
        this.description = description;
        this.collectionName = collectionName;
        this.user = user;
    }

    public String getCollectionId() {
        return collectionId;
    }

    public String getDescription() {
        return description;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public User getUser() {
        return user;
    }
}
