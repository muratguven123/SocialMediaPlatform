package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class JTextArea extends javax.swing.JTextArea{
private String hint;
    public JTextArea(String hint,int textSize,int padding){
        super();
        this.hint = hint;
        setFont(new Font("Segoe UI",Font.BOLD,textSize));
        setBackground(null);
        setText(hint);
        setForeground(GUIConstants.textAreaHint);
        setForeground(GUIConstants.textAreaHint);
        setBorder(BorderFactory.createEmptyBorder(padding,padding,padding,padding));

        addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if(getText().equals(hint)){
                    setText("");
                    setForeground(GUIConstants.black);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(getText().equals("")){
                    setText(hint);
                    setForeground(GUIConstants.textAreaHint);
                }
            }
        });
    }
        public JTextArea(String text,int textSize,Color color,int style){
            super();
            setFont(new Font("Segoe UI",style,textSize));
            setText(text);
            setForeground(color);
            setEditable(false);
            setPreferredSize(new Dimension(1000, (int) getPreferredSize().getHeight()));
    }
    public boolean isEmpty(){
        return getText().equals(hint)|| getText().equals("");
    }

}
