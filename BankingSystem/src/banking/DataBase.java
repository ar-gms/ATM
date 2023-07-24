package banking;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static banking.PasswordAuthentication.authenticate;
import static banking.PasswordAuthentication.hashPassword;


public class DataBase {

    private Map<String, BankAccount> data;
    private Random random;

    public DataBase(){
        data = new HashMap<>();
        random = new Random();
    }

    // User im Datenbank eintragen. Nur username und password wird eingegeben der Rest wird generiert
    public void registerUser(String username, String password) {
        int id = generateId();
        int bankNumber = generateBankNumber();
        String passwordHash = hashPassword(password); // vom PasswordAuthenticate importiert
        double balance = 0.0;

        BankAccount bankAccount = new BankAccount(id, username, passwordHash, balance, bankNumber);
        data.put(username, bankAccount);
    }

    private int generateId() {
        // ID generieren (6 Stellen)
        int id = 100000 + random.nextInt(900000);
        return id;
    }


    private int generateBankNumber() {
        // Kontonummer generieren (10 Stelen)
        int bankNumber = 1000000000 + random.nextInt(900000000);
        return bankNumber;
    }

    public BankAccount getUser(String username) {
        return data.get(username);
    }

    // prüft ob der user existiert und das Password stimmt
    public boolean verifyUser(String username, String password){

        BankAccount account = data.get(username);
        if (account != null) {
            return authenticate(password, account.passwordHash); // von Password Autheticate importiert
        }
        return false;
    }

}
