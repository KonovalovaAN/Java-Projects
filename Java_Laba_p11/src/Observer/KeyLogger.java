package Observer;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.util.EventObject;

public class KeyLogger implements Observer {
    DefaultListModel<String> listModel;
    private KeyLogger(DefaultListModel<String> listModel) {
        this.listModel = listModel;
    }
    public static KeyLogger createKeyLogger(DefaultListModel<String> listModel) {
        return new KeyLogger(listModel);
    }
    @Override
    public void update(EventObject object) {
        KeyEvent event = (KeyEvent) object;
        listModel.addElement(KeyEvent.getKeyText(event.getKeyCode()));
    }
}
