package GUI;
import main.Main; // Zugang zum Datenbank

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame implements ActionListener {

    private JButton button;
    private JLabel login;
    private JLabel userName;
    private JLabel password;
    private JTextField userNameText;
    private JPasswordField passwordText;
    private JButton registerButton;

    public Login() {
        super("Login");

        try {
            // UI Style
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        Container c = getContentPane();
        JPanel panel = createPanel();
        c.add(panel, BorderLayout.CENTER);

        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private JPanel createPanel() {
        JPanel p = new JPanel(new GridBagLayout());
        p.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // padding

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0; // Spalten
        gbc.gridy = 0; // Zeilen
        gbc.insets = new Insets(10, 0, 10, 0); // Abstand Vertikal

        login = new JLabel("Login");
        login.setFont(new Font("Arial", Font.BOLD, 30));
        p.add(login, gbc); // Element + Layout-info hinzufügen

        gbc.gridy++; // Nummer der Zeile ändern

        userName = new JLabel("Username");
        userName.setFont(new Font("Arial", Font.BOLD, 15));
        p.add(userName, gbc);

        userNameText = new JTextField();
        gbc.gridy++;
        p.add(userNameText, gbc);

        password = new JLabel("Password");
        password.setFont(new Font("Arial", Font.BOLD, 15));
        gbc.gridy++;
        p.add(password, gbc);

        passwordText = new JPasswordField();
        gbc.gridy++;
        p.add(passwordText, gbc);

        button = new JButton("OK");
        button.setBackground(new Color(0, 120, 215));
        button.setForeground(Color.white);
        button.setFont(new Font("Tahoma", Font.BOLD, 12));
        gbc.gridy++;
        p.add(button, gbc);
        button.addActionListener(this); // Aktivität registieren

        registerButton = new JButton("Don't have an account? Register here.");
        registerButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        gbc.gridy++;
        registerButton.addActionListener(this);
        p.add(registerButton, gbc);

        return p;
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == registerButton) {

            dispose();
            new Register(); // Wechsel zu Register

        } else if (source == button) {

            // erst prüfen wir ob der User existiert und der Password stimmt
            if (Main.dataBase.verifyUser(userNameText.getText(), passwordText.getText())) {

                dispose();
                new ATMInterface(Main.dataBase.getUser(userNameText.getText())); // neues ATM interface mit den Kontodaten

            } else {

                JOptionPane.showMessageDialog(new JFrame(), "wrong credentials", "Dialog", JOptionPane.ERROR_MESSAGE);

            }
        }
    }
}

