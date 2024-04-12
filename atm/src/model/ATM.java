package model;

import state.ATMState;

public class ATM {
    private ATMState currentATMState;
    private int balance;
    private int noOf2kNotes;
    private int noOf500Notes;
    private int noOf100Notes;

    private ATM(){}

    private static ATM INSTANCE;

    public static ATM getInstance() {
        if(INSTANCE == null){
            INSTANCE =  new ATM();
        }
        return INSTANCE;
    }

    public ATMState getCurrentATMState() {
        return currentATMState;
    }

    public void setCurrentATMState(ATMState currentATMState) {
        this.currentATMState = currentATMState;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getNoOf2kNotes() {
        return noOf2kNotes;
    }

    public void setNoOf2kNotes(int noOf2kNotes) {
        this.noOf2kNotes = noOf2kNotes;
    }

    public int getNoOf500Notes() {
        return noOf500Notes;
    }

    public void setNoOf500Notes(int noOf500Notes) {
        this.noOf500Notes = noOf500Notes;
    }

    public int getNoOf100Notes() {
        return noOf100Notes;
    }

    public void setNoOf100Notes(int noOf100Notes) {
        this.noOf100Notes = noOf100Notes;
    }

    protected void displayCurrentATMStatus(){
        System.out.println("Hey User, Welcome!");
        System.out.println("No. of 2000 Rs notes - " + getNoOf2kNotes());
        System.out.println("No. of 500 Rs notes - " + getNoOf500Notes());
        System.out.println("No. of 100 Rs notes - " + getNoOf100Notes());
    }
}
