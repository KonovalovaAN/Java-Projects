import java.util.Queue;

class QueueIterator<T> implements Iterator<T> {
    private final Queue<T> queue;
    private int index;

    QueueIterator(Queue<T> queue) {
        this.queue = queue;
        index = 0;
    }
    @Override
    public void first() {
        index = 0;
    }

    @Override
    public void next() {
        index++;
    }

    @Override
    public boolean hasNext() {
        return index < queue.size();
    }

    @Override
    public void previous() {
        index--;
    }

    @Override
    public T currentItem() throws IndexOutOfBoundsException{
        if (index < 0 || index >= queue.size()){
            throw new IndexOutOfBoundsException();
        }
        return queue.getElementAt(index);
    }
}