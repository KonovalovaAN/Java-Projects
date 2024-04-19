import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Drawer extends JPanel implements MouseMotionListener {
    private Point lastPoint;
    private Point previousPoint;
    private Color currentColor;
    private BufferedImage image;
    private ArrayList<Point> points = new ArrayList<>();

    public Drawer() {
        super();
        initDrawer();
        this.setDoubleBuffered(true);
    }

    public void setColor(Color color) {
        currentColor = color;
    }

    private void initDrawer() {
        addMouseMotionListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (image != null) {
            g.drawImage(image, 0, 0, this);
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        this.lastPoint = e.getPoint();
        if (previousPoint == null) {
            previousPoint = lastPoint;
        }
        points.add(lastPoint);
        drawCurrentLine(getGraphics());
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        this.previousPoint = null;
        this.lastPoint = e.getPoint();
    }

    private void drawCurrentLine(Graphics g) {
        if (points.size() > 1) {
            g.setColor(currentColor);
            for (int i = 1; i < points.size(); i++) {
                Point from = points.get(i - 1);
                Point to = points.get(i);
                g.drawLine(from.x, from.y, to.x, to.y);
            }
        }
    }

    public void saveToFile(File file) throws IOException {
        if (image != null) {
            ImageIO.write(image, "png", file);
        }
    }

    public void loadImage(File file) throws IOException {
        image = ImageIO.read(file);
        points.clear();
        repaint();
    }
}
