package View;

import Model.Database;
import Model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;

public class ChangePassword {
    User u;
        private String password;
        private int id;
        private Database database;
        public ChangePassword(String password,int İD,Database database){
            this.id = id;
            this.password = password;
            this.database = database;
        }
        public boolean change(){
            boolean changed = false;
            String update = "UPDATE `socialmedia`.`users` SET `ID` = '"+u.getID()+"', `firstName` = '"+u.getFirstName()+"', `LastName` = '"+u.getLastName()+"', `Email` = '"+u.getEmail()+"', `password` = '"+u.getPassword()+"' WHERE `ID` = '"+u.getID()+"'";

            try{
                database.getStatement().execute(update);
                changed = true;
            }catch (SQLException e){
                new Alert(e.getMessage(),null);
                changed = false;
            }
            return changed;
        }

    public ChangePassword(User user,Database database) {
        JFrame frame = new JFrame();

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(null);
        panel.setBorder(BorderFactory.createEmptyBorder(83,99,175,99));
        panel.add(new JLabel("Change Password",40),BorderLayout.NORTH);

        JPanel center = new JPanel(new GridLayout(2,1,10,10));
        center.setBackground(null);
        center.setBorder(BorderFactory.createEmptyBorder(58,216,0,216));

        JTextField oldPassword = new JTextField("Old Password");
        center.add(oldPassword);
        JTextField  newPassword = new JTextField("New Password");
        center.add(newPassword);
        JTextField  confirmPassword = new JTextField("Confirm Password");
        center.add(confirmPassword);

        JButton submit = new JButton("Submit",45,20);
        submit.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(!oldPassword.getText().equals(user.getPassword())){
                    new Alert("old password",frame);
                }
                if(oldPassword.getText().isEmpty()) {
                    new Alert("Please enter your old password",frame);
                }
                if(newPassword.getText().isEmpty()) {
                    new Alert("Please enter your new password",frame);
                }
                if(newPassword.getText().length()<6){
                    new Alert("Password must contains at least 6 chracters",frame);
                }
                if(confirmPassword.getText().isEmpty()){
                    new Alert("Please Enter Confirm Passaword",frame);
                }
                if (!newPassword.getText().equals(confirmPassword.getText())){
                    new Alert("password doesn't match",frame);
                }
            }
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
        });
        center.add(submit);
        panel.add(center,BorderLayout.CENTER);
        frame.getContentPane().add(panel);
        frame.setVisible(true);
        frame.requestFocus();

    }

}
