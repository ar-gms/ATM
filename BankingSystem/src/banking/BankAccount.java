package banking;

public class BankAccount extends User{

    protected double balance = 0;
    protected int bankNumber;

    public BankAccount(int id, String name, String password, double balance, int bankNumber) {
        super(id, name, password);
        this.balance = balance;
        this.bankNumber = bankNumber;
    }

    // Einzahlung und Auszahlung können den Kontostand ändern (Setter-Funktion)
    public void einzahlen(double geld){
         this.balance += geld;
    }

    public void auszahlen(double geld){
         this.balance -= geld;
    }

    // Getters
    public double getBalance() {
        return balance;
    }

    public int getBankNumber() {
        return bankNumber;
    }

    public String toString(){
        return "(" + id + ", " + name + " )";
    }

}
