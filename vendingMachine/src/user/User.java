package user;

import java.util.Scanner;

public class User {
    private static User INSTANCE;

    private User(){}

    public static User getInstance(){
        if(INSTANCE == null){
            INSTANCE = new User();
        }
        return INSTANCE;
    }

    public int selectOption(){
        System.out.println("Please choose one of the following options");
        System.out.println("1. Select Item");
        System.out.println("2. Insert Coins");
        System.out.println("3. Collect Item");
        System.out.println("4. Collect Change");
        System.out.println("5. Cancel Transaction");

        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        sc.nextLine();

        if( choice == 5 ){
            System.out.print("Are you sure you want to cancel the transaction : Y/N ");
            String userLeaving = sc.nextLine();

            if(userLeaving.equalsIgnoreCase("n")){
                selectOption();
            }
        }else if(choice > 5 || choice <= 0){
            System.out.println("Please select a choice with in the range!");
            selectOption();
        }
        return choice;
    }
}
