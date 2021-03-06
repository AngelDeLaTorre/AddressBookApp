package intefaces;

import java.util.Iterator;

public interface SortedList<E extends Comparable<E>> extends Iterable<E>{
	// valid position in the list
	// [0, size() - 1]
	
	public boolean add(E obj);
	public int size();
	public boolean remove(E obj);
	public boolean remove(int index);
	public int removeAll(E obj);
	public E first();
	public E last();
	public E get(int index);
	public E set(int index,E obj);
	public void clear();
	public boolean contains(E e);
	public boolean isEmpty();
	public Iterator<E> iterator(int index);
	public int firstIndex(E e);
	public int lastIndex(E e);

	
}
