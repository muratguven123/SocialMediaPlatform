package View;

import Controller.AddFriend;
import Controller.RemoveFriend;
import Model.Database;
import Model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Friend extends JPanel {

    public Friend(User mainUser, Database database, User u) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(GUIConstants.white);
        setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 55));

        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(null);

        JLabel author = new JLabel(u.getName());
        author.setFont(new Font("Friend", Font.BOLD, 16));
        header.add(author, BorderLayout.WEST);
        add(header);

        JPanel right = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        right.setBackground(null);

        JButton addFriendButton = new JButton("Follow",10,10);
        addFriendButton.setPreferredSize(new Dimension(81, 37));
        addFriendButton.setVisible(!mainUser.isFriend(u));
        right.add(addFriendButton);

        JLabel removeFriendLabel = new JLabel("Unfollow");
        removeFriendLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        removeFriendLabel.setVisible(mainUser.isFriend(u));
        removeFriendLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        right.add(removeFriendLabel);

        addFriendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (new AddFriend(mainUser, database, u).isAdded()) {
                    mainUser.addFriend(u);
                    addFriendButton.setVisible(false);
                    removeFriendLabel.setVisible(true);
                }
            }
        });

        removeFriendLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (new RemoveFriend(mainUser, database, u).isRemoved()) {
                    mainUser.removeFriend(u);
                    addFriendButton.setVisible(true);
                    removeFriendLabel.setVisible(false);
                }
            }
        });

        add(right);

        Dimension dimension = new Dimension(500, 67);
        setPreferredSize(dimension);
        setMaximumSize(dimension);
        setMinimumSize(dimension);
    }
}

