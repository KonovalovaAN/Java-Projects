package Observer;

import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;

public class Keyboard implements Subject {
    private final List<Observer> observers;
    private Keyboard() {
        observers = new ArrayList<>();
    }

    public static Keyboard createKeyboard() {
        return new Keyboard();
    }

    @Override
    public void attach(Observer o) {
        observers.add(o);
    }

    @Override
    public void detach(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notify(EventObject object) {
        for (Observer o : observers) {
            o.update(object);
        }
    }
}