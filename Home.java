package View;

import Controller.CreatePost;
import Controller.GenerateTimeLİne;
import Model.Database;
import Model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class Home {
    public Home(User user, Database database) {

        JFrame frame = new JFrame("Home");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());

         new GenerateTimeLİne(user,database);
        // Sidebar setup
        JPanel sideBar = new JPanel();
        sideBar.setBackground(GUIConstants.white);
        Dimension sidebarDim = new Dimension(182, 1000);
        sideBar.setPreferredSize(sidebarDim);
        sideBar.setMaximumSize(sidebarDim);
        sideBar.setMinimumSize(sidebarDim);
        sideBar.setLayout(new BoxLayout(sideBar, BoxLayout.Y_AXIS));
        sideBar.add(Box.createVerticalStrut(10));

        // Profile panel
        JPanel profile = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        profile.setMaximumSize(new Dimension(182, 50));
        profile.setBackground(GUIConstants.white);
        profile.setCursor(new Cursor(Cursor.HAND_CURSOR));
        profile.add(new JLabel(user.getName(), SwingConstants.CENTER));
        profile.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new Modify(user, database);
                frame.dispose();
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
        sideBar.add(profile);
        sideBar.add(Box.createVerticalStrut(3));

        // Sidebar buttons
        sideBar.add(new SideButton("Posts", "myposts",user,database,frame));
        sideBar.add(Box.createVerticalStrut(3));
        sideBar.add(new SideButton("Comments", "mycomments",user,database,frame));
        sideBar.add(Box.createVerticalStrut(3));
        sideBar.add(new SideButton("Likes", "mylikes",user,database,frame));
        sideBar.add(Box.createVerticalStrut(3));
        sideBar.add(new SideButton("Friends", "myfriends",user,database,frame));
        sideBar.add(Box.createVerticalStrut(3));

        frame.getContentPane().add(sideBar, BorderLayout.WEST);

        // Main content panel
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(GUIConstants.white);

        // Header setup
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(GUIConstants.white);
        Dimension dimension = new Dimension(500, 159);
        header.setPreferredSize(dimension);
        header.setMaximumSize(dimension);
        header.setMinimumSize(dimension);
        header.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        // Header north section
        JPanel north = new JPanel(new BorderLayout());
        north.setBackground(GUIConstants.white);
        north.add(new JLabel("Home", SwingConstants.LEFT), BorderLayout.WEST);
        header.add(north, BorderLayout.NORTH);

        // Text area for posting
        JTextArea postIn = new JTextArea("Share Your Thoughts",10,GUIConstants.textAreaHint,Font.BOLD);
        postIn.setFont(new Font("Arial", Font.PLAIN, 18));
        header.add(new JScrollPane(postIn), BorderLayout.CENTER);

        // Header south section with Post button
        JPanel south = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        south.setBackground(GUIConstants.white);
        JButton postBtn = new JButton("Post",10,10);
        postBtn.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(postBtn.getText().isEmpty()){
                    new Alert("Cannot publish empty post",frame);
                    return;
                }
                Model.Post post = new Model.Post(postIn.getText(),user);
                if(new CreatePost(post,database).posted()){
                    new Alert("Post successfully published",frame);
                    postIn.setText("");
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
        postBtn.setPreferredSize(new Dimension(81, 37));
        south.add(postBtn);
        header.add(south, BorderLayout.SOUTH);

        panel.add(header);
        panel.add(Box.createVerticalStrut(7));
        panel.add(new Post());
        ArrayList<Model.Post> posts = new GenerateTimeLİne(user,database).getPost();
        for (int i=0;i<10;i++){
            panel.add(Box.createVerticalStrut(7));
            panel.add(new Post());
        }

        // Main content scroll pane
        JScrollPane mainScrollPane = new JScrollPane(panel);
        mainScrollPane.setBorder(null);
        frame.getContentPane().add(mainScrollPane, BorderLayout.CENTER);

        frame.setSize(800, 600);
        frame.setVisible(true);
        frame.requestFocus();
    }
}
