package model;

import model.exceptions.DuplicateElementException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Collection use to restrain the ArrayList collection.
 * This collection does not accept duplicate elements or <tt>null</tt> elements.
 * @author Paul Givel and Guillaume Hartenstein
 * @see ArrayList
 */
class UniqueList<E> {
    /**
     * The list used to restrain the collection.
     */
    private final List<E> list;
    
    /**
     * Constructs an empty list.
     */
    public UniqueList() {
        list = new ArrayList<>();
    }
    
    /**
     * Add a new element at the end of the list.
     * @param element The element to add
     * @throws NullPointerException If <tt>element</tt> is <tt>null</tt>
     * @throws DuplicateElementException If this list already contains <tt>element</tt>
     */
    public void add(E element) {
        if(element == null)
            throw new NullPointerException("Element is null");
        
        if(list.contains(element))
            throw new DuplicateElementException();
        
        list.add(element);
    }
    
    /**
     * Remove an element.
     * @param index The index of the element to remove
     * @see ArrayList#remove(int) 
     */
    public void remove(int index) {
        list.remove(index);
    }
    
    /**
     * Get an element.
     * @param index The index of the element to get
     * @return The element
     * @see ArrayList#get(int)
     */
    public E get(int index) {
        return list.get(index);
    }
    
    /**
     * Accessor to the size of this list.
     * @return The size of this list
     * @see ArrayList#size()
     */
    public int size() {
        return list.size();
    }
    
    /**
     * Casts this <tt>UniqueList</tt> to a <tt>List</tt>. 
     * The returned <tt>List</tt> will be unmodifiable (read-only).
     * @return A <tt>List</tt> representing this <tt>UniqueList</tt>
     */
    public List<E> asList() {
        return Collections.unmodifiableList(list);
    }
    
    /**
     * Check wether this list contains an element.
     * @param element The element to check
     * @return <tt>true</tt> if the element is in the list,
     *         <tt>false</tt> otherwise.
     * @see ArrayList#contains(java.lang.Object) 
     */
    public boolean contains(E element) {
        return list.contains(element);
    }
    
    /**
     * Return an iterator over this list
     * @return Iterator over this list.
     * @see ArrayList#iterator()
     */
    public Iterator<E> iterator() {
        return list.iterator();
    }
}
