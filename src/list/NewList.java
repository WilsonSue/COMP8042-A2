package src.list;

import java.util.NoSuchElementException;
import java.util.Iterator;

public class NewList<T> implements Remover<T>, Iterable<T> {
//   This section uses ArrayList sample code
    private T[] array;
    private int length;

    @SuppressWarnings("unchecked")
    public NewList(int initialSize) {
        this.array = (T[]) new Object[initialSize];
        this.length = 0;
    }

    public NewList(T[] array){
        this.array = array;
        this.length = array.length;
    }

    private int size() {
        return this.array.length;
    }

    public int length() {
        return this.length;
    }

    private boolean should_resize() {
        return this.length == this.size();
    }


    private void resize(){
        @SuppressWarnings("unchecked") // Ignore because type T
        T[] new_array = (T[]) new Object[size() * 2];
        for(int i = 0; i < this.length; i++){
            new_array[i] = this.array[i];
        }
        this.array = new_array;
    }

    private void resizeIfNecessary(){
        if(this.should_resize()){
            this.resize();
        }
    }

    private void checkIndexValid(int index) throws Exception{
        if(index < 0 || index >= this.length()){
            throw new IndexOutOfBoundsException(String.format("Index %d out of bounds", index));
        }
    }

    private void shuffleForward(int startingIndex) throws Exception{
        for (int i = this.length; i > startingIndex; i--) {
            array[i] = this.array[i - 1];
        }
    }

    private void shuffleBackward(int startingIndex) throws Exception{
        for(int i = startingIndex; i < this.length() - 1; i++){
            this.array[i] = this.array[i+1];
        }
        this.array[this.length()] = null;
    }

    public boolean is_empty(){
        return this.length == 0;
    }

    public void add(int position, T element, boolean validateIndex) throws Exception{
        if(validateIndex) {
            this.checkIndexValid(position);
        }

        this.resizeIfNecessary();

        if (position < this.length) {
            this.shuffleForward(position);
        }

        this.array[position] = element;
        ++this.length;
    }

    public void add(int position, T element) throws Exception{
        this.add(position, element, true);
    }

    public void add(T element) throws Exception {
        this.add(length(), element, false);
    }

    public T remove(int index) throws Exception{
        this.checkIndexValid(index);
        T removedElement =  (T)this.array[index];
        this.shuffleBackward(index);
        --this.length;
        return removedElement;
    }

    protected T get(int index, boolean validateIndex) throws Exception{
        if (validateIndex){
            this.checkIndexValid(index);
        }

        return (T)this.array[index];
    }

    public T get(int index) throws Exception{
        return (T)this.get(index, false);
    }

    public void set(int index, T element) throws Exception{
        this.checkIndexValid(index);
        this.array[index] = element;
    }

    public void print(){
        try{
            for(int i = 0; i < length; i++){
                System.out.print(this.get(i, false).toString() + ",");
            }
            System.out.println();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayListIterator();
    }

    private class ArrayListIterator implements Iterator<T> {
        private int currentIndex;

        public ArrayListIterator() {
            this.currentIndex = 0;
        }

        @Override
        public boolean hasNext() {
            return currentIndex < length;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return array[currentIndex++];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Remove operation is not supported");
        }
    }

    // Methods section
    @Override
    public void removeEvens(NewList<T> list) {
        int writeIndex = 0;
        for (int readIndex = 0; readIndex < list.length; readIndex++) {
            T element = list.array[readIndex];

            boolean isEven = (element instanceof Integer) && (((Integer) element) % 2 == 0); // Mod 2 any even number = 0

            if (!isEven) {
                list.array[writeIndex++] = element; // copies to current writeIndex spot at the front of array
            }
        }

        for (int i = writeIndex; i < list.length; i++) { // start where writeIndex stopped at last loop
            list.array[i] = null; // turn evens into null for JMM to delete
        }
        list.length = writeIndex; // update boundary
    }

    @Override
    public void removeOdds(NewList<T> list) { // same logic as evens
        int writeIndex = 0;
        for (int readIndex = 0; readIndex < list.length; readIndex++) {
            T element = list.array[readIndex];

            boolean isOdd = (element instanceof Integer) && (((Integer) element) % 2 != 0); // uneven number mod 2 cannot = 0

            if (!isOdd) {
                list.array[writeIndex++] = element;
            }
        }

        for (int i = writeIndex; i < list.length; i++) {
            list.array[i] = null;
        }
        list.length = writeIndex;
    }

    @Override
    public void removeAllInstances(NewList<T> list, T elementToRemove) {
        int writeIndex = 0;
        for (int readIndex = 0; readIndex < list.length; readIndex++) {
            T element = list.array[readIndex];

            // Prevents NullPointerException when comparing and makes it more reusable
            if (element == null ? elementToRemove != null : !element.equals(elementToRemove)) { // Checks if current element is null then check target removal for null otherwise compare
                list.array[writeIndex++] = element;
            }
        }

        for (int i = writeIndex; i < list.length; i++) {
            list.array[i] = null;
        }
        list.length = writeIndex;
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Remove Odds Test: ");
        NewList<Integer> list1 = new NewList<>(10);
        for (int i = 1; i <= 8; i++) list1.add(i);
        System.out.print("Original: ");
        list1.print();
        list1.removeOdds(list1);
        System.out.print("Result: ");
        list1.print();

        System.out.println("\n Remove All Instances Test: ");
        NewList<Integer> list2 = new NewList<>(10);
        int[] vals = {1, 5, 6, 4, 6, 2, 8, 6};
        for (int v : vals) list2.add(v);
        System.out.print("Original: ");
        list2.print();
        list2.removeAllInstances(list2, 6);
        System.out.print("Result: ");
        list2.print();
    }
}
