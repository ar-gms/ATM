package GUI;
import main.Main; // Zugang zum Datenbank

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Register extends JFrame implements ActionListener {

    private JButton button;
    private JLabel login;
    private JLabel userName;
    private JLabel password;
    private JLabel passwordConfirm;
    private JTextField userNameText;
    private JPasswordField passwordText;
    private JPasswordField passwordTextConfirm;
    private JButton loginButton;

    public Register(){
        super("Register");

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

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0; // Spalten
        gbc.gridy = 0; // Zeilen
        gbc.insets = new Insets(10, 0, 10, 0); // Abstand Vertikal

        login = new JLabel("Register");
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

        passwordConfirm = new JLabel("Confirm password");
        passwordConfirm.setFont(new Font("Arial", Font.BOLD, 15));
        gbc.gridy++;
        p.add(passwordConfirm, gbc);

        passwordTextConfirm = new JPasswordField();
        gbc.gridy++;
        p.add(passwordTextConfirm, gbc);

        button = new JButton("OK");
        button.setBackground(new Color(0, 120, 215));
        button.setForeground(Color.white);
        button.setFont(new Font("Tahoma", Font.BOLD, 12));
        gbc.gridy++;
        p.add(button, gbc);
        button.addActionListener(this);

        loginButton = new JButton("Already have an account? Login here.");
        loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        gbc.gridy++;
        loginButton.addActionListener(this); // leitet zu Login weiter
        p.add(loginButton, gbc);

        return p;
    }

    public void actionPerformed(ActionEvent e){
        Object source = e.getSource();

        if (source == button){

            // Prüfen, ob beide Passwords gleich sind
            if(passwordText.getText().equals(passwordTextConfirm.getText())){

                Main.dataBase.registerUser(userNameText.getText(), passwordText.getText()); // Eingaben erfassen und im Datenbank eingeben

                dispose(); // Fenster schließen
                new Login();

            } else {

                JOptionPane.showMessageDialog(new JFrame(), "password does not Match", "Dialog",
                        JOptionPane.ERROR_MESSAGE);
            }

        } else if (source == loginButton) {

            dispose(); // Fenster schließen
            new Login();
        }
    }
}

