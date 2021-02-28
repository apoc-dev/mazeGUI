import java.awt.*;
import javax.swing.*;



public class RectPanel extends JPanel{
    
    Color rectColor;

    public RectPanel(Color color){
        this.rectColor = color;
    }


    @Override
    public void paintComponent(Graphics g){
        // clear the previous painting
        super.paintComponent(g);
        // cast Graphics to Graphics2D
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(rectColor); // sets Graphics2D color
        // draw the rectangle
        g2.drawRect(0,0,10,10); // drawRect(x-position, y-position, width, height)
        g2.setColor(rectColor);
        g2.fillRect(0,0,120, 60); // fill new rectangle with color blue
    } 

}
