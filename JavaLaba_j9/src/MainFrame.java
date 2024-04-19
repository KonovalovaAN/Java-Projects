import javax.swing.*;

public class MainFrame extends JFrame {
    public MainFrame(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setSize(800,600);
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("First task", new PanelWithList());
        tabbedPane.addTab("Second task", new PanelWithGrid());
        tabbedPane.addTab("Third task", new PanelWithRadioButtons());
        add(tabbedPane);
        setLocationRelativeTo(null);
    }
}
