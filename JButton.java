package View;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;

public class JButton extends Jlabel{
    private Shape shape;
    private int radius;
    public JButton(String text, int textSize, int radius) {
        super(text,textSize, GUIConstants.blue, radius);
        this.radius = radius;
        setFont(new Font("Segoe UI",Font.BOLD,20));
        setOpaque(false);
        setForeground(GUIConstants.white);
        setHorizontalAlignment(CENTER);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
    protected void paintComponent(Graphics g){
        g.setColor(GUIConstants.blue);
        g.fillRoundRect(0,0,getWidth()-1,getHeight()-1,radius,radius);
        super.paintComponent(g);
    }
    protected void paintBorder(Graphics g){
        g.setColor(GUIConstants.white);
        g.drawRoundRect(0,0,getWidth()-1,getHeight()-1,45,45);
    }
    public boolean contains(int x, int y){
        if(shape==null||!shape.getBounds().equals(getBounds())){
            shape = new RoundRectangle2D.Float(0,0,getWidth()-1,getHeight()-1,45,45);
        }
        return shape.contains(x,y);
    }

    public void addActionListener(ActionListener actionListener) {

    }
}
