import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.*;

public class DrawingFrame extends JFrame {
    private static Color color = Color.black;
    private final JButton buttonColor = new JButton("Color");
    private final DrawingPanel drawingPanel;

    public static void main(String[] args) {
        new DrawingFrame("Paint");
    }

    public DrawingFrame(String string) {
        super(string);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        drawingPanel = new DrawingPanel();
        drawingPanel.setPreferredSize(new Dimension(800, 600));

        buttonColor.addActionListener(e -> {
            Color tempColor = JColorChooser.showDialog(buttonColor, "Choose a color", color);
            if (tempColor != null) {
                color = tempColor;
            }
            drawingPanel.setColor(color);
        });
        JButton buttonOpen = new JButton("Open");
        buttonOpen.addActionListener((e -> {
            JFileChooser fileChooser = new JFileChooser();
            if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                try {
                    drawingPanel.image = ImageIO.read(fileChooser.getSelectedFile());
                    setPreferredSize(new Dimension(drawingPanel.image.getWidth(), drawingPanel.image.getHeight()));
                    drawingPanel.graphics = drawingPanel.image.createGraphics();
                    drawingPanel.setColor(drawingPanel.color);
                    repaint();

                } catch (IOException | IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }));
        JButton buttonSave = new JButton("Save");
        buttonSave.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                try {
                    ImageIO.write(drawingPanel.image, "png", fileChooser.getSelectedFile());
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        Box vertical = new Box(1);
        Box horizontal = new Box(2);
        vertical.add(drawingPanel);
        horizontal.add(buttonColor);
        horizontal.add(buttonOpen);
        horizontal.add(buttonSave);
        vertical.add(new JScrollPane(drawingPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
        vertical.add(horizontal);
        add(vertical);

        pack();
        setVisible(true);

    }
}