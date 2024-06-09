package View;

import Controller.CreatUser;
import Model.Database;
import Model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

public class Welcome {
    public Welcome() {
        JFrame frame = createMainFrame();
        JPanel panel = createMainPanel(frame);
        frame.getContentPane().add(panel);
        frame.setVisible(true);
        frame.requestFocus();
    }

    private JFrame createMainFrame() {
        JFrame frame = new JFrame("Welcome");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 600);
        return frame;
    }

    private JPanel createMainPanel(JFrame frame) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(53, 84, 76, 84));

        JLabel titleLabel = new JLabel("Welcome", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 40));
        panel.add(titleLabel, BorderLayout.NORTH);

        JPanel centerPanel = createCenterPanel(frame);
        panel.add(centerPanel, BorderLayout.CENTER);

        JLabel loginLabel = createLoginLabel(frame);
        panel.add(loginLabel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createCenterPanel(JFrame frame) {
        JPanel centerPanel = new JPanel(new GridLayout(6, 1, 10, 10));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(22, 50, 17, 50));

        JTextField firstNameField = new JTextField("First Name");
        centerPanel.add(firstNameField);

        JTextField lastNameField = new JTextField("Last Name");
        centerPanel.add(lastNameField);

        JTextField emailField = new JTextField("Email");
        centerPanel.add(emailField);

        JPasswordField passwordField = new JPasswordField("Password");
        centerPanel.add(passwordField);

        JPasswordField confirmPasswordField = new JPasswordField("Confirm Password");
        centerPanel.add(confirmPasswordField);

        JButton createAccountButton = createCreateAccountButton(frame, firstNameField, lastNameField, emailField, passwordField, confirmPasswordField);
        centerPanel.add(createAccountButton);

        return centerPanel;
    }

    private JButton createCreateAccountButton(JFrame frame, JTextField firstNameField, JTextField lastNameField, JTextField emailField, JPasswordField passwordField, JPasswordField confirmPasswordField) {
        JButton createAccountButton = new JButton("Create Account",10,10);
        createAccountButton.setFont(new Font("Segoe UI", Font.BOLD, 20));
        createAccountButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleCreateAccount(frame, firstNameField, lastNameField, emailField, passwordField, confirmPasswordField);
            }
        });
        return createAccountButton;
    }

    private JLabel createLoginLabel(JFrame frame) {
        JLabel loginLabel = new JLabel("Already have an account? Login", SwingConstants.CENTER);
        loginLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        loginLabel.setForeground(GUIConstants.black);
        loginLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    new Login(new Database()); // Assuming you have a Database instance to pass
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                frame.dispose();
            }
        });
        return loginLabel;
    }

    private void handleCreateAccount(JFrame frame, JTextField firstNameField, JTextField lastNameField, JTextField emailField, JPasswordField passwordField, JPasswordField confirmPasswordField) {
        if (firstNameField.getText().isEmpty()) {
            new Alert("First Name cannot be empty", frame);
            return;
        }
        if (lastNameField.getText().isEmpty()) {
            new Alert("Last Name cannot be empty", frame);
            return;
        }
        if (emailField.getText().isEmpty()) {
            new Alert("Email cannot be empty", frame);
            return;
        }
        if (passwordField.getPassword().length == 0) {
            new Alert("Password cannot be empty", frame);
            return;
        }
        if (confirmPasswordField.getPassword().length == 0) {
            new Alert("Please confirm password", frame);
            return;
        }
        if (passwordField.getPassword().length < 6) {
            new Alert("Password must be at least 6 characters", frame);
            return;
        }
        if (!String.valueOf(passwordField.getPassword()).equals(String.valueOf(confirmPasswordField.getPassword()))) {
            new Alert("Passwords do not match", frame);
            return;
        }

        try {
            Database database = new Database();
            User user = new User();
            user.setFirstName(firstNameField.getText());
            user.setLastName(lastNameField.getText());
            user.setEmail(emailField.getText());
            user.setPassword(String.valueOf(passwordField.getPassword()));

            CreatUser creatUser = new CreatUser(user, database);

            if (!creatUser.isEmailUsed()) {
                creatUser.create();
                user = creatUser.getUser();
                new Home(user, database);
                frame.dispose();
            } else {
                new Alert("Email is already in use", frame);
            }
        } catch (SQLException ex) {
            new Alert("An error occurred while accessing the database", frame);
            ex.printStackTrace();
        }
    }
}


