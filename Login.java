package View;



import Controller.ReadUser;
import Model.Database;
import Model.User;
import View.Alert;
import View.GUIConstants;
import View.Welcome;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

public class Login {

    private JFrame frame;
    private JTextField emailField;
    private JPasswordField passwordField;

    public Login(Database database) {
        initializeFrame();
        JPanel panel = createMainPanel();
        frame.getContentPane().add(panel);
        frame.setVisible(true);
        frame.requestFocus();
    }

    private void initializeFrame() {
        frame = new JFrame("Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 600);
    }

    private JPanel createMainPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(115, 0, 185, 0));

        JLabel title = createTitleLabel();
        panel.add(title, BorderLayout.NORTH);

        JPanel centerPanel = createCenterPanel();
        panel.add(centerPanel, BorderLayout.CENTER);

        JLabel createAccLabel = createCreateAccountLabel();
        panel.add(createAccLabel, BorderLayout.SOUTH);

        return panel;
    }

    private JLabel createTitleLabel() {
        JLabel title = new JLabel("Login", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 40));
        title.setForeground(GUIConstants.blue);
        return title;
    }

    private JPanel createCenterPanel() {
        JPanel centerPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(34, 50, 17, 50));

        emailField = new JTextField("Email");
        centerPanel.add(emailField);

        passwordField = new JPasswordField("Password");
        centerPanel.add(passwordField);

        JButton loginButton = createLoginButton();
        centerPanel.add(loginButton);

        return centerPanel;
    }

    private JButton createLoginButton() {
        JButton loginButton = new JButton("Login",10,10);
        loginButton.setFont(new Font("Segoe UI", Font.BOLD, 20));
        loginButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleLoginButtonClick();
            }
        });
        return loginButton;
    }

    private JLabel createCreateAccountLabel() {
        JLabel createAccLabel = new JLabel("Don't have an account? Sign up", SwingConstants.CENTER);
        createAccLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        createAccLabel.setForeground(GUIConstants.black);
        createAccLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        createAccLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new Welcome();
                frame.dispose();
            }
        });
        return createAccLabel;
    }

    private void handleLoginButtonClick() {
        String email = emailField.getText().trim();
        String password = new String(passwordField.getPassword());

        if (email.isEmpty()) {
            new Alert("Email cannot be empty", frame);
            return;
        }
        if (password.isEmpty()) {
            new Alert("Password cannot be empty", frame);
            return;
        }

        try {
            Database database = new Database();
            ReadUser readUser = new ReadUser(email, password, database);

            if (readUser.loggedIn()) {
                User user = readUser.getUser();
                new Alert("Logged in successfully! User ID: " + user.getID(), frame);
                new Home(user, database);
                frame.dispose();
            } else {
                new Alert("Login failed", frame);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            new Alert("An error occurred while logging in", frame);
        }
    }
}