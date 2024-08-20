package Inventory;

public enum ItemName {
    SUGAR_SACHET("Sugar sachet"),
    COFFEE_POWDER_SACHET("Coffee Powder Sachet"),
    MILK("Milk");

    private final String description;

    ItemName(String description) {
        this.description = description;
    }

//    public String getDescription() {
//        return description;
//    }

    // Static method to get an enum from a description
//    public static ItemName fromDescription(String description) {
//        for (ItemName item : ItemName.values()) {
//            if (item.getDescription().equalsIgnoreCase(description)) {
//                return item;
//            }
//        }
//        throw new IllegalArgumentException("No enum constant with description " + description);
//    }
}
