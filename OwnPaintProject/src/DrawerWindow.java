import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


public class DrawerWindow extends JFrame {

    Drawer drawPanel;
    JPanel colorsPanel;
    ButtonGroup colorChooser;

    public DrawerWindow() {
        super("Drawer");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initComponents();
        setMinimumSize(new Dimension(500, 300));
    }

    public void showWindow() {
        this.setVisible(true);
    }

    private void initComponents() {

        drawPanel = new Drawer();
        initColorsPanel();
        initDrawerPanel();
    }

    private void initDrawerPanel() {
        JScrollPane scroller = new JScrollPane(drawPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        drawPanel.setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());

        scroller.setVisible(true);
        this.add(scroller);
    }

    private void initColorsPanel() {
        colorsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        colorChooser = new ButtonGroup();
        this.add(colorsPanel, BorderLayout.SOUTH);

        initButton("Red", Color.RED);
        initButton("Green", Color.GREEN);
        initButton("Blue", Color.BLUE);
        initDialogueButton();
        initLoadButton();
        drawPanel.setColor(getColorFromActionCommand(colorChooser.getSelection().getActionCommand()));
    }

    private void initButton(String name, Color color) {
        JRadioButton button = new JRadioButton(name);
        button.setBackground(color);
        button.setActionCommand(name);
        button.setSelected(true);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawPanel.setColor(getColorFromActionCommand(colorChooser.getSelection().getActionCommand()));
            }
        });

        colorChooser.add(button);
        colorsPanel.add(button);
    }

    private void initDialogueButton(){
        JButton button = new JButton("Save to File");

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                if (fileChooser.showSaveDialog(drawPanel.getParent()) == JFileChooser.APPROVE_OPTION){
                    try {
                        drawPanel.saveToFile(fileChooser.getSelectedFile());
                        button.setBackground(null);
                    } catch (IOException ex) {
                        button.setBackground(Color.RED);
                    }
                }
            }
        });

        colorsPanel.add(button);
    }

    private void initLoadButton(){
        JButton button = new JButton("Load from File");

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                if (fileChooser.showOpenDialog(drawPanel.getParent()) == JFileChooser.APPROVE_OPTION){
                    try {
                        drawPanel.loadImage(fileChooser.getSelectedFile());
                        button.setBackground(null);
                    } catch (IOException ex) {
                        button.setBackground(Color.RED);
                    }
                }
            }
        });

        colorsPanel.add(button);
    }

    private Color getColorFromActionCommand(String actionCommand) {
        return switch (actionCommand) {
            case "Green" -> Color.GREEN;
            case "Blue" -> Color.BLUE;
            default -> Color.RED;
        };
    }
    public static void main(String[] args) {
            DrawerWindow drawerWindow = new DrawerWindow();
            drawerWindow.showWindow();
    }
}