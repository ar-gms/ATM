package main;

import GUI.Register;
import banking.DataBase;

public class Main {

    public static DataBase dataBase = new DataBase(); // Beim Start wird dieser (künstliche) Datenbank nur einmal erstellt
    public static void main(String[] args) {

        new Register();

    }
}