package src.list;

public interface Remover<T> {
    void removeEvens(NewList<T> list);
    void removeOdds(NewList<T> list);
    void removeAllInstances(NewList<T> list, T elementToRemove);
}