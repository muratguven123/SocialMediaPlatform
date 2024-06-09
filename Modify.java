package View;

import Controller.UpdateUser;
import Model.Database;
import Model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Modify {

    public Modify(User user, Database database) {
        JFrame jFrame = new JFrame("Modify Profile");

        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(false);  // Transparency if needed
        panel.setBorder(BorderFactory.createEmptyBorder(72, 84, 149, 84));

        JLabel titleLabel = new JLabel("Modify Your Profile", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 40));
        titleLabel.setForeground(GUIConstants.blue);
        panel.add(titleLabel, BorderLayout.NORTH);

        JPanel centerPanel = createCenterPanel(user, database, jFrame);
        panel.add(centerPanel, BorderLayout.CENTER);

        JLabel changePasswordLabel = createChangePasswordLabel(user, database, jFrame);
        panel.add(changePasswordLabel, BorderLayout.SOUTH);

        jFrame.getContentPane().add(panel);
        jFrame.setSize(800, 600);  // Adjust size as needed
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
        jFrame.requestFocus();
    }

    private JPanel createCenterPanel(User user, Database database, JFrame jFrame) {
        JPanel centerPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        centerPanel.setOpaque(false);  // Transparency if needed
        centerPanel.setBorder(BorderFactory.createEmptyBorder(50, 231, 17, 231));

        JTextField firstNameField = createTextField("First Name", user.getFirstName());
        centerPanel.add(firstNameField);

        JTextField lastNameField = createTextField("Last Name", user.getLastName());
        centerPanel.add(lastNameField);

        JTextField emailField = createTextField("Email", user.getEmail());
        centerPanel.add(emailField);

        JButton submitButton = createSubmitButton(user, database, jFrame, firstNameField, lastNameField, emailField);
        centerPanel.add(submitButton);

        return centerPanel;
    }

    private JTextField createTextField(String placeholder, String text) {
        JTextField textField = new JTextField(text, 20);
        textField.setText(text);
        textField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        return textField;
    }

    private JButton createSubmitButton(User user, Database database, JFrame jFrame, JTextField firstNameField, JTextField lastNameField, JTextField emailField) {
        JButton submitButton = new JButton("Submit",10,10);
        submitButton.setFont(new Font("Segoe UI", Font.BOLD, 10));
        submitButton.setPreferredSize(new Dimension(45, 20));
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSubmitButtonClick(user, database, jFrame, firstNameField, lastNameField, emailField);
            }
        });
        return submitButton;
    }

    private void handleSubmitButtonClick(User user, Database database, JFrame jFrame, JTextField firstNameField, JTextField lastNameField, JTextField emailField) {
        String firstName = firstNameField.getText().trim();
        String lastName = lastNameField.getText().trim();
        String email = emailField.getText().trim();

        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty()) {
            new Alert("All fields must be filled", jFrame);
            return;
        }

        User updatedUser = user;
        updatedUser.setFirstName(firstName);
        updatedUser.setLastName(lastName);
        updatedUser.setEmail(email);

        UpdateUser updateUser = new UpdateUser(user, database);
        if (!email.equals(user.getEmail()) && updateUser.isEmailUsed(email)) {
            new Alert("This email has been used before", jFrame);
            return;
        }

        if (updateUser.update()) {
            new Home(updatedUser, database);
            new Alert("Profile Updated", jFrame);
            jFrame.dispose();
        } else {
            new Home(user, database);
        }
    }

    private JLabel createChangePasswordLabel(User user, Database database, JFrame jFrame) {
        JLabel changePasswordLabel = new JLabel("Change Password", SwingConstants.CENTER);
        changePasswordLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        changePasswordLabel.setForeground(GUIConstants.black);
        changePasswordLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        changePasswordLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                new ChangePassword(user, database);
                jFrame.dispose();
            }
        });
        return changePasswordLabel;
    }
}
