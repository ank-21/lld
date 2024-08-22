package withDesignPattern;

public class DeleteCollection implements Icommand{
    private CollectionManager collectionManager;
    private String collectionId;
    private String userId;
    private Collection deletedCollection;

    public DeleteCollection(CollectionManager collectionManager, String collectionId, String userId) {
        this.collectionManager = collectionManager;
        this.collectionId = collectionId;
        this.userId = userId;
    }

    @Override
    public void execute() {
        deletedCollection = collectionManager.getCollectionInternal(collectionId);
        if (deletedCollection == null) {
            System.out.println("Collection not present");
        } else {
            if(deletedCollection.getUser().getUserId().equalsIgnoreCase(userId)){
                collectionManager.removeCollectionInternal(collectionId);
            }else{
                System.out.println("Collection not found or user not authorized");
                collectionManager.updateLastCommand(userId);
                deletedCollection = null;
            }
        }
    }

    @Override
    public void undo() {
        if (deletedCollection != null) {
            collectionManager.addCollectionInternal(deletedCollection.getCollectionId(), deletedCollection.getCollectionName(), deletedCollection.getUser());
            System.out.println("Restored collection: " + deletedCollection.getCollectionName());
        }
    }
}
