package clases;

import intefaces.SortedList;

import java.io.Serializable;
import java.util.Iterator;
import java.util.NoSuchElementException;



public class SortedArrayList<E extends Comparable<E>> implements SortedList<E>, Serializable {

	// private fields
	private Object elements[];
	private int currentSize;
	private static final int DEFAULT_CAPACITY=100;
	// constructor
	public SortedArrayList()  
	{
		this.elements =    new Object[DEFAULT_CAPACITY];
		this.currentSize=0;
	}
	
	private class ListIterator<E extends Comparable<E>> implements Iterator<E>{

		
		private int currentPosition;
		
		public  ListIterator(){
			this.currentPosition=0;
		}
		public  ListIterator(int index){
			this.currentPosition=index;
		}
		
		@Override
		public boolean hasNext() {
			return this.currentPosition< size(); 
		}

		@Override
		public E next() {
			if(this.hasNext())
			{
				return (E) elements[this.currentPosition++];
			}
			else{
				throw new NoSuchElementException();
			}
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
			
		}
		
	}
	
	@Override
	public Iterator<E> iterator() {
		return new ListIterator<E>();
	}

	@Override
	public boolean add(E obj) {
		if(obj == null)
		{
			throw new IllegalArgumentException("Object cannot be null");
		}
		if(this.currentSize== this.elements.length)
		{
			reAllocate();
		}
		if (this.currentSize < this.elements.length )      // the array is not full
		{ 
			insert(this.currentSize,obj);
			this.currentSize++; 
			return true;
		} 
		else if (((E)this.elements[this.currentSize-1]).compareTo(obj) < 0) { 
			insert(this.elements.length-1,obj);
				return true;
					}
		return false;
	}

	//private method for 
	private void reAllocate() {
		Object newElements[] =  new Object[this.elements.length*2];
		//copy all values
		for(int i =0; i<this.currentSize;i++){
			newElements[i]= this.elements[i]; 
		}
		this.elements= newElements;
		
	}



	// private method used on the add method
	private void insert(int index, E obj) {
		int pos = index-1; 
		while (pos >= 0 && (((E)this.elements[pos]).compareTo(obj) > 0)) {
			this.elements[pos+1] = this.elements[pos]; 
			pos--; 
		}
		this.elements[pos+1] = obj; 
		
	}



	@Override
	public int size() {
		return this.currentSize;
	}

	@Override
	public boolean remove(E obj) {
		if(obj == null){
			throw new IllegalArgumentException("Argument cannot be null.");
		}
		// find first obj in the array
		int target = -1;
		for(int i=0; i<this.currentSize; i++)
		{
			 if(this.elements[i].equals(obj)){
				 // found it
				 target =i;
				 break;
			 }
			 
		}
		if(target == -1)
		{
			return false;
		}
		else{
			for(int i= target; i< this.currentSize; i++)
			{
				this.elements[i]= this.elements[i+1];
			}
			this.elements[--this.currentSize]=null;
			return true;
		}
	}

	@Override
	public boolean remove(int index) {
		if(index>=0 && index< this.currentSize)
		{
			for(int i= index; i< this.currentSize; i++)
			{
				this.elements[i]= this.elements[i+1];
			}
			this.elements[--this.currentSize]=null;
			return true;
		}
		else{
			throw new ArrayIndexOutOfBoundsException();
		}
	}

	@Override
	public int removeAll(E obj) {
		int counter=0;
		while(this.remove(obj)){
			counter++;
		}
		return counter;
	}

	@Override
	public E first() {
		if(this.isEmpty())
		{
			return null;
		}
		return (E)this.elements[0];
	}

	@Override
	public E last() {
		if(this.isEmpty())
		{
			return null;
		}
		return (E)this.elements[this.currentSize-1];
	}

	@Override
	public E get(int index) {
		if(index>=0 && index<this.size())
		{
			return (E)this.elements[index];
		}
		else{
			throw new ArrayIndexOutOfBoundsException();
		}
	}

	@Override
	public void clear() {
		
		for(int i=0; i< this.size(); i++)
		{
			this.elements[i]=null;
		}
		this.currentSize=0;
		
	}

	@Override
	public boolean contains(E e) {
		return this.firstIndex(e) >=0;
	}

	@Override
	public boolean isEmpty() {
		return this.size()==0;
	}

	@Override
	public Iterator<E> iterator(int index) {
		return new ListIterator<E>(index);
	}

	@Override
	public int firstIndex(E e) {
		for (int i=0; i<this.currentSize;i++){
			if(this.elements[i].equals(e)){
				return i;
			}
		}
		return -1;
	}

	@Override
	public int lastIndex(E e) {
		for (int i=this.currentSize-1; i>=0;i--){
			if(this.elements[i].equals(e)){
				return i;
			}
		}
		return -1;
	}

	@Override
	public E set(int index, E obj) {
		if(obj==null)
		{
			throw new IllegalArgumentException("Object cannot be null.");
		}
		
		if(index>=0 && index<this.size())
		{
			E temp = (E)this.elements[index];
			this.elements[index]= obj;
			
			return temp;
		}
		else{
			throw new ArrayIndexOutOfBoundsException();
		}
	
	}
	
	
	
	
	public void copylist(SortedArrayList<E> list)
	{
		this.clear();
		for(E e: list)
		{
			this.add(e);
		}
	}

}
