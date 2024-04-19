import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;

public class DrawingPanel extends JPanel {
    Color color;
    Graphics graphics;
    private int currX, currY, oldX, oldY;
    BufferedImage image;

    DrawingPanel(){
        image = new BufferedImage(1000,1000,BufferedImage.TYPE_INT_RGB);
        graphics = image.createGraphics();

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                oldX = e.getX();
                oldY = e.getY();
                graphics.setColor(color);
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                currX = e.getX();
                currY = e.getY();
                graphics.drawLine(oldX,oldY,currX,currY);
                graphics.setColor(color);
                repaint();
                oldX = currX;
                oldY = currY;
            }
        });
    }
    @Override
    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        graphics.drawImage(image,0,0,this);
    }

    public void setColor(Color color){
        this.color = color;
    }
}