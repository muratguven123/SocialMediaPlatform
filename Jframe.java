package View;

public class Jframe extends javax.swing.JFrame {
    @SuppressWarnings("serial")
    public Jframe() {
        super("Social Media Platform");
        getContentPane().setBackground(GUIConstants.white);
        setSize(900,625);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}
