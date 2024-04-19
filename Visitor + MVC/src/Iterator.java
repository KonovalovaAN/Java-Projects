interface Iterator<T> {
    void first();
    void next();
    void previous();
    boolean hasNext();

    T currentItem() throws IndexOutOfBoundsException;
}