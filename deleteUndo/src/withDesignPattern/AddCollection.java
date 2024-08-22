package withDesignPattern;

public class AddCollection implements Icommand{
    private CollectionManager collectionManager;
    private String collectionId;
    private String collectionName;
    private User user;

    public AddCollection(CollectionManager collectionManager, String collectionId, String collectionName, User user) {
        this.collectionManager = collectionManager;
        this.collectionId = collectionId;
        this.collectionName = collectionName;
        this.user = user;
    }

    @Override
    public void execute() {
        collectionManager.addCollectionInternal(collectionId, collectionName, user);
    }

    @Override
    public void undo() {
        System.out.println("No collection was deleted");
    }
}
