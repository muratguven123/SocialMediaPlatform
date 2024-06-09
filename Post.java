package View;

import javax.swing.*;
import java.awt.*;

public class Post extends JPanel {

    public Post() {
        initializePanel();
        addHeaderPanel();
        addContentPanel();
        addBottomPanel();
        adjustPanelSize();
    }

    private void initializePanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(GUIConstants.white);
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 25));
    }

    private void addHeaderPanel() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(GUIConstants.white);

        JLabel author = new JLabel("User Name");
        author.setFont(new Font("Arial", Font.PLAIN, 20));
        header.add(author, BorderLayout.WEST);

        JLabel date = new JLabel("Thu 0 Jan 2000");
        date.setFont(new Font("Arial", Font.PLAIN, 15));
        header.add(date, BorderLayout.EAST);

        add(header);
        add(Box.createVerticalStrut(7));
    }

    private void addContentPanel() {
        JPanel center = new JPanel(new BorderLayout());
        center.setBackground(GUIConstants.white);

        JTextArea content = createContentTextArea();
        JScrollPane scrollPane = new JScrollPane(content);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(new Dimension(460, 100));

        center.add(scrollPane, BorderLayout.CENTER);
        add(center);
        add(Box.createVerticalStrut(7));
    }

    private JTextArea createContentTextArea() {
        JTextArea content = new JTextArea(
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                        "In feugiat sem sit amet sodales faucibus. Mauris eu ullamcorper est. Donec nec libero erat. " +
                        "Pellentesque pulvinar, risus convallis mattis volutpat, quam arcu convallis diam, cursus varius diam mauris at elit. Vivamus eget magna non justo aliquet eleifend eget et eros. " +
                        "In mollis gravida lacus, vel fermentum quam convallis pretium. Ut dictum eu ante ac auctor. Maecenas et dictum lectus. Nam turpis leo, lobortis id venenatis ut, mollis non augue. Morbi ligula orci, pretium ac ultricies nec, " +
                        "viverra pretium leo. Vivamus dictum sit amet enim vitae rutrum. Nullam vitae lectus id tortor gravida tempus quis a sem.",10,GUIConstants.post,Font.BOLD
        );
        content.setFont(new Font("Arial", Font.PLAIN, 10));
        content.setBackground(GUIConstants.background);
        content.setLineWrap(true);
        content.setWrapStyleWord(true);
        content.setEditable(false);
        return content;
    }

    private void addBottomPanel() {
        JPanel bottom = new JPanel(new BorderLayout());
        bottom.setBackground(GUIConstants.white);

        JPanel likes = new JPanel(new FlowLayout(FlowLayout.LEFT, 13, 13));
        likes.setBackground(GUIConstants.white);

        JLabel likesLabel = new JLabel("0 Likes");
        likesLabel.setFont(new Font("Arial", Font.BOLD, 15));
        likesLabel.setForeground(GUIConstants.textFieldHint);
        likes.add(likesLabel);

        bottom.add(likes, BorderLayout.WEST);

        JLabel comments = new JLabel("0 Comments");
        comments.setFont(new Font("Arial", Font.BOLD, 15));
        comments.setForeground(GUIConstants.textFieldHint);
        bottom.add(comments, BorderLayout.EAST);

        add(bottom);
    }

    private void adjustPanelSize() {
        int height = (int) (115 + 100); // Adjust according to preferred content height
        Dimension dimension = new Dimension(500, height);
        setPreferredSize(dimension);
        setMaximumSize(dimension);
        setMinimumSize(dimension);
    }

    // Main method for testing purposes
    public static void main(String[] args) {
        JFrame frame = new JFrame("Post Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        Post post = new Post();
        frame.add(post, BorderLayout.CENTER);

        frame.setVisible(true);
    }
}
