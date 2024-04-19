package Observer;

import java.util.EventObject;

public interface Subject {
    void attach(Observer subscriber);

    void detach(Observer subscriber);

    void notify(EventObject object);
}
