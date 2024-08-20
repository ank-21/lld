package Inventory;

public class UserNotificationInventoryObserver implements InventoryObserver{
    String number;

    public UserNotificationInventoryObserver(String number) {
        this.number = number;
    }

    @Override
    public void update(String item, int quantityLeft) {
        sendSMS(item, quantityLeft);
    }

    private void sendSMS(String item, int quantityLeft){
        System.out.println("A SMS has been sent to user for the item " + item + " regarding only " + quantityLeft + " quantities are left" + "\n");
    }
}
