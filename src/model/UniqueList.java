package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.UnaryOperator;
import model.exceptions.DuplicateElementException;

/**
 * Collection use to restrain the ArrayList collection.
 * This collection does not accept duplicate elements or <tt>null</tt> elements.
 * @author Paul Givel and Guillaume Hartenstein
 * @param <E> Generic type of the list
 */
public class UniqueList<E> extends ArrayList<E> {

    public UniqueList() {
        super();
    }
    
    public UniqueList(Collection<? extends E> c) {
        super();
        checkDuplicateInCollection(c);
        super.addAll(c);
    }

    @Override
    public void replaceAll(UnaryOperator<E> operator) {
        throw new DuplicateElementException("This operation might create duplicates in the list.");
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        checkDuplicateInCollection(c);
        return super.addAll(index, c);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        checkDuplicateInCollection(c);
        return super.addAll(c);
    }

    @Override
    public void add(int index, E element) {
        checkDuplicate(element);
        super.add(index, element);
    }

    @Override
    public boolean add(E e) {
        checkDuplicate(e);
        return super.add(e);
    }

    @Override
    public E set(int index, E element) {
        checkDuplicate(element);
        return super.set(index, element);
    }
    
    private void checkDuplicateInCollection(Collection<? extends E> c) {
        for(E e : c)
            if(contains(e))
                throw new DuplicateElementException();
    }
    
    private void checkDuplicate(E e) {
        if(contains(e))
            throw new DuplicateElementException();
    }
}
