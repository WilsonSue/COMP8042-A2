public class NewList<T> implements Remover<T> {
    private Object[] data;
    private int size;

    public NewList(int capacity) {
        data = new Object[capacity];
        size = 0;
    }

    public int size(){

        return 0;
    }

    public void add(T element){

    }

    public T get(int index){

        return null;
    }

    @Override
    public void removeEvens(NewList<T> list) {

    }

    @Override
    public void removeOdds(NewList<T> list) {

    }

    @Override
    public void removeAllInstances(NewList<T> list, T elementToRemove) {

    }
}
