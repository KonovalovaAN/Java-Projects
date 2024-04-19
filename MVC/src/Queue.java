import java.util.ArrayList;
import javax.swing.DefaultListModel;

public class Queue<T> implements Iterable<T> {
    private final ArrayList<T> data;

    public static <T> Queue<T> create() {
        return new Queue<>();
    }

    private Queue() {
        data = new ArrayList<>();
    }

    public int size() {
        return data.size();
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    public void clear() {
        data.clear();
    }

    public boolean equals(Queue<T> otherQueue) {
        return data.equals(otherQueue.data);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Queue: ");

        Iterator<T> iterator = createIterator();

        while (iterator.hasNext()) {
            result.append(iterator.currentItem());
            iterator.next();

            if (iterator.hasNext()) {
                result.append(", ");
            }
        }
        return result.toString();
    }
    public DefaultListModel<T> getListModel() {
        DefaultListModel<T> model = new DefaultListModel<>();

        Iterator<T> iterator = createIterator();

        while (iterator.hasNext()) {
            model.addElement(iterator.currentItem());
            iterator.next();
        }
        return model;
    }

    public T front() {
        if (data.isEmpty()) {
            throw new IndexOutOfBoundsException("Your queue doesn't have the first element");
        }
        Iterator<T> iterator = createIterator();
        return iterator.currentItem();
    }

    public T back() {
        if (data.isEmpty()) {
            throw new IndexOutOfBoundsException("Your queue doesn't have the last element");
        }
        Iterator<T> iterator = createIterator();
        while (iterator.hasNext()) {
            iterator.next();
        }
        iterator.previous();
        return iterator.currentItem();
    }

    public void push(T element) {
        data.add(element);
    }

    public void pop() {
        if (data.isEmpty()) {
            throw new IndexOutOfBoundsException("The queue is empty");
        }
        data.remove(0);
    }

    public void pushAll(ArrayList<T> elements) {
        data.addAll(elements);
    }

    public T getElementAt(int index) {
        if (index < 0 || index >= data.size()) {
            throw new IndexOutOfBoundsException();
        }
        return data.get(index);
    }

    @Override
    public Iterator<T> createIterator() {
        return new QueueIterator<>(this);
    }
}
