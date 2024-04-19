import java.util.ArrayList;
import java.util.EmptyStackException;

public class Stack<T> {
    ArrayList<T> stack = new ArrayList<>();
    public int size(){
        return stack.size();
    }
    private void push(T value){
        stack.add(value);
    }
    public void pop() throws EmptyStackException {
        if (stack.isEmpty()) {
            throw new EmptyStackException();
        }
        stack.remove(size() - 1);
    }
    public T top() throws EmptyStackException {
        if (stack.isEmpty()) {
            throw new EmptyStackException();
        }
        return stack.get(size() - 1);
    }
    public class Iterator<T> {
        int index = 0;
        public boolean hasNext() {
            if (index < size() - 1) {
                return true;
            } else {
                return false;
            }
        }

    }

}


