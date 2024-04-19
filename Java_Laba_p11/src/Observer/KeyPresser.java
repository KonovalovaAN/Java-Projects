package Observer;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.util.EventObject;

public class KeyPresser implements Observer {
    private final JLabel keyName;
    private KeyPresser(JLabel label) {
        keyName = label;
    }
    public static KeyPresser createKeyPresser(JLabel label) {
        return new KeyPresser(label);
    }
    @Override
    public void update(EventObject object) {
        KeyEvent event = (KeyEvent) object;
        keyName.setText(KeyEvent.getKeyText(event.getKeyCode()));
    }
}
