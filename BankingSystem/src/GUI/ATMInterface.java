package GUI;

import banking.BankAccount;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ATMInterface extends JFrame implements ActionListener {

    private BankAccount bankAccount;
    private DefaultListModel<String> transactionListModel; // eine Liste für Transaktionen
    private JLabel historyLabel; // zeigt alle Transaktionen an
    private JButton einzahlenButton;
    private JButton auszahlenButton;
    private JButton logoutButton;
    private JLabel einzahlenLabel;
    private JLabel auszahlenLabel;
    private JTextField einzahlenText;
    private JTextField auszahlenText;
    private JLabel userInfo; // Konto

    public ATMInterface(BankAccount bankAccount) {
        this.bankAccount = bankAccount;

        try {
            // UI Style
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        Container c = getContentPane();
        c.setLayout(new GridLayout(1,2)); // 2 Spalten für 2 Panels

        JPanel infoPanel = createTranactionInfoPanel();
        JPanel inputPanel = createUserInputPanel();

        // die beiden erstellten Panels hinzufügen
        c.add(infoPanel);
        c.add(inputPanel);

        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    // Panel für User Eingaben
    private JPanel createUserInputPanel() {
        JPanel p = new JPanel(new GridBagLayout());
        p.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // padding

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0; // Spalten
        gbc.gridy = 0; // Zeilen
        gbc.insets = new Insets(10, 0, 10, 0); // Abstand Vertikal

        einzahlenLabel = new JLabel("Einzahlen");
        einzahlenLabel.setFont(new Font("Arial", Font.BOLD, 30));
        gbc.gridy++; // Nummer der Zeile ändern
        p.add(einzahlenLabel, gbc); // Element + Layout-info hinzufügen


        einzahlenText = new JTextField();
        gbc.gridy++;
        p.add(einzahlenText, gbc);

        einzahlenButton = new JButton("Ok");
        einzahlenButton.setBackground(new Color(0, 120, 215));
        einzahlenButton.setForeground(Color.white);
        einzahlenButton.setFont(new Font("Tahoma", Font.BOLD, 12));
        gbc.gridy++;
        p.add(einzahlenButton, gbc);
        einzahlenButton.addActionListener(this); // Aktivität registieren

        auszahlenLabel = new JLabel("Auszahlen");
        auszahlenLabel.setFont(new Font("Arial", Font.BOLD, 30));
        gbc.gridy++;
        p.add(auszahlenLabel, gbc);

        auszahlenText = new JTextField();
        gbc.gridy++;
        p.add(auszahlenText, gbc);

        auszahlenButton = new JButton("Ok");
        auszahlenButton.setBackground(new Color(0, 120, 215));
        auszahlenButton.setForeground(Color.white);
        auszahlenButton.setFont(new Font("Tahoma", Font.BOLD, 12));
        gbc.gridy++;
        p.add(auszahlenButton, gbc);
        auszahlenButton.addActionListener(this);

        // Abstand herstellen
        gbc.gridy++;
        p.add(Box.createVerticalStrut(50), gbc);

        logoutButton = new JButton("Logout");
        logoutButton.setBackground(new Color(120, 25, 0));
        logoutButton.setForeground(Color.white);
        logoutButton.setFont(new Font("Tahoma", Font.BOLD, 12));
        gbc.gridy++;
        p.add(logoutButton, gbc);
        logoutButton.addActionListener(this);
        return p;
    }

    // Panel für Information
    private JPanel createTranactionInfoPanel() {
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));

        userInfo = new JLabel();
        System.out.println(bankAccount.getBalance());
        updateBalanceLabel();
        p.add(userInfo);

        historyLabel = new JLabel("Kontonummer" + bankAccount.getBankNumber());
        p.add(historyLabel);

        historyLabel = new JLabel("Transaction History:");
        p.add(historyLabel);

        transactionListModel = new DefaultListModel<>();
        JList<String> transactionHistory = new JList<>(transactionListModel);
        p.add(new JScrollPane(transactionHistory));

        return p;
    }


    //
    public void actionPerformed(ActionEvent e) {

        // Zeitrahmen für die einzelnen Transaktionen
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String timestamp = LocalDateTime.now().format(formatter);

        if(e.getSource() == einzahlenButton){

            // wenn keine zahlen eingeben werden wird das Progam eine Ausnahme aus.
            double einzahlenEingabe = Double.parseDouble(einzahlenText.getText()); // Eingabe zu Double verwandeln
            bankAccount.einzahlen(einzahlenEingabe);
            updateBalanceLabel();
            // Transaktion im historyLabel + Datum ausgeben
            transactionListModel.addElement("eingezahlt $" + einzahlenEingabe + "          " + timestamp);

        }
        else if(e.getSource() == auszahlenButton){
            double auszahlenEingabe = Double.parseDouble(auszahlenText.getText());
            bankAccount.auszahlen(auszahlenEingabe);
            updateBalanceLabel();
            transactionListModel.addElement("ausgezahlt $" + auszahlenEingabe + "          " + timestamp);

        } else if(e.getSource() == logoutButton){
            dispose();
            new Login();
        }
    }

    // ändert den Kontostand auf dem panel. Wird bei jeder Transaktion aufgerufen
    private void updateBalanceLabel() {
        userInfo.setText("Kontostand: $" + bankAccount.getBalance());
    }
}

