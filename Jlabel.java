package View;

import javax.swing.*;
import java.awt.*;
@SuppressWarnings("serial")
public class Jlabel extends javax.swing.JLabel {
    public Jlabel(String text, int textSize, Color blue, int style) {
        setFont(new Font("Segoe UI", style, textSize));
        setText(text);
        setForeground(GUIConstants.textFieldHint);
    }

    public Jlabel(ImageIcon imageIcon) {
    }
}
