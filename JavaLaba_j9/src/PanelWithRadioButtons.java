import javax.swing.*;

public class PanelWithRadioButtons extends JPanel {
    PanelWithRadioButtons(){
        Box box = new Box(BoxLayout.X_AXIS);
        ButtonGroup buttonGroup = new ButtonGroup();
        ImageIcon[] icons = {
                new ImageIcon("src/cat.jpg"),
                new ImageIcon("src/dog.jpg"),
                new ImageIcon("src/esh.jpg"),
                new ImageIcon("src/kapibara.jpg")
                };
        for (int i = 0; i < 4; i++) {
            JRadioButton radioButton = new JRadioButton(icons[0]);
            radioButton.setPressedIcon(icons[1]);
            radioButton.setRolloverIcon(icons[2]);
            radioButton.setSelectedIcon(icons[3]);
            buttonGroup.add(radioButton);
            box.add(radioButton);
        }
        add(box);
    }
}
