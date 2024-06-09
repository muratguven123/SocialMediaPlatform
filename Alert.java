package View;
import javax.swing.*;
import java.awt.*;
import javax.swing.JPanel;

public class Alert {

    public Alert(String content,javax.swing.JFrame parent) {
        JFrame frame = new JFrame();
        frame.setSize(430,170);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);

        JPanel panel = new JPanel(new BorderLayout(5,5));
        panel.setBorder(BorderFactory.createEmptyBorder(15,15,15,15));
        panel.setBackground(GUIConstants.white);

        Jlabel title = new Jlabel("Alert",24,GUIConstants.blue,Font.BOLD);
        title.setHorizontalAlignment(Jlabel.CENTER);
        panel.add(title,BorderLayout.CENTER);

        Jlabel msg = new Jlabel(content,18,GUIConstants.blue,Font.BOLD);
        msg.setHorizontalAlignment(Jlabel.CENTER);
        panel.add(msg,BorderLayout.SOUTH);

        frame.getContentPane().add(panel);
        frame.setLocationRelativeTo(parent);
        frame.setVisible(true);

    }

}
