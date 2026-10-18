package src.stack;

import java.util.ArrayList;
import java.util.EmptyStackException;

public class ArrayListStack<T> implements Stack<T> {
    private ArrayList<T> list;

    public ArrayListStack() {
        this.list = new ArrayList<>();
    }

    @Override
    public boolean empty() {
        return list.isEmpty();
    }

    @Override
    public T peek() {
        if (empty()) {
            throw new EmptyStackException();
        }
        return list.get(list.size() - 1);
    }

    @Override
    public T pop() {
        if (empty()) {
            throw new EmptyStackException();
        }
        return list.remove(list.size() - 1);
    }

    @Override
    public T push(T item) {
        list.add(item);
        return item;
    }
}