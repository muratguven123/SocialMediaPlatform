package View;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;

public class JScrollPane extends  javax.swing.JScrollPane {
    public JScrollPane(Component component) {
        super(component);
        setBackground(null);
        getViewport().setBackground(null);
        setBorder(null);

        JScrollBar scrollBar = new JScrollBar();
        scrollBar.setBackground(null);
        scrollBar.setBorder(null);
        scrollBar.setUI(new BasicScrollBarUI() {
            // to change scroll bar color
            @Override
            protected void configureScrollBarColors(){
                this.thumbColor = GUIConstants.textAreaHint;
            }
            // to hide the arrows of scroll bar

            protected JButton createDecraseButton(int orientation){
                return createZeroButton();
            }

            protected JButton createİncreaseButton(int orientation){
                return createZeroButton();
            }
            private JButton createZeroButton(){
                JButton btn = new JButton("button",10,10);
                btn.setPreferredSize(new Dimension());
                btn.setMaximumSize(new Dimension());
                btn.setMinimumSize(new Dimension());
                return btn;
            }
        });
            setVerticalScrollBar(scrollBar);
            setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);
            getVerticalScrollBar().setPreferredSize(new Dimension(5,0));
            getHorizontalScrollBar().setPreferredSize(new Dimension(5,0));

    }
}
