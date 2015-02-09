 package clases;

import intefaces.List;

import java.io.Serializable;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class ArrayList<E> implements List<E>, Serializable {

	private int currentSize;
	
	private E elements[];
	
	private class ListIterator<E> implements Iterator<E>{
		
		private int currentPosition;
		
		public  ListIterator(){
			this.currentPosition=0;
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
	
	public ArrayList(int initialCapacity)
	{
		if(initialCapacity<1)
		{
			throw new IllegalArgumentException("Capacity must be at least 1.");
		}
		this.currentSize=0;
		this.elements = (E[]) new Object[initialCapacity];
		
	}
	
	
	
	@Override
	public Iterator<E> iterator() {
		return new ListIterator<E>();
	}

	@Override
	public void add(E obj) {
		if(obj ==null){
			throw new IllegalArgumentException("Argument cannot be null.");
		}
		else{
			if(this.currentSize==this.elements.length){
				reAllocate();
			} 
			this.elements[this.currentSize++] =obj;
			return;
		}

	}

	private void reAllocate() {
		//create new array twice size
		E newElements[] = (E[]) new Object[this.elements.length*2];
		//copy all values
		for(int i =0; i<this.currentSize;i++){
			newElements[i]= this.elements[i]; 
		}
		this.elements= newElements;
		
	}



	@Override
	public void add(int index, E obj) {
		if(obj ==null){
			throw new IllegalArgumentException("Argument cannot be null.");
		}
		if(index==this.size())
		{
			this.add(obj);
		}
		
		else if(index>=0 && index<this.currentSize)
		{
			if(this.currentSize == this.elements.length )
			{
				reAllocate();
			}
			// move every element one space back
			for(int i=this.currentSize; i>index; i--){
				this.elements[i]= this.elements[i-1];
			}
			this.elements[index]=obj;
			this.currentSize++;
			
		}
		else{
			throw new ArrayIndexOutOfBoundsException();
		}

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
	public E get(int index) {
		if(index>=0 && index<this.size())
		{
			return this.elements[index];
		}
		else{
			throw new ArrayIndexOutOfBoundsException();
		}
	}

	@Override
	public E set(int index, E obj) {
		if(obj==null)
		{
			throw new IllegalArgumentException("Object cannot be null.");
		}
		
		if(index>=0 && index<this.size())
		{
			E temp = this.elements[index];
			this.elements[index]= obj;
			
			return temp;
		}
		else{
			throw new ArrayIndexOutOfBoundsException();
		}
	}

	@Override
	public E first() {
		if(this.isEmpty())
		{
			return null;
		}
		else{
			return this.elements[0];
		}
	}

	@Override
	public E last() {
		if(this.isEmpty())
		{
			return null;
		}
		else{
			return this.elements[this.currentSize-1];
		}
	}

	@Override
	public int firstIndex(E obj) {
		
		for (int i=0; i<this.currentSize;i++){
			if(this.elements[i].equals(obj)){
				return i;
			}
		}
		return -1;
	}

	@Override
	public int lastIndex(E obj) {
		for(int i=this.currentSize-1;i>=0;i--)
		{
			if(this.elements[i].equals(obj)){
				return i;
			}
		}
		return -1;
	}

	@Override
	public int size() {
		
		return this.currentSize;
	}

	@Override
	public boolean isEmpty() {
		
		return this.size()==0;
	}

	@Override
	public boolean contains(E obj) {
		return this.firstIndex(obj) >=0;
	}

	@Override
	public void clear() {
		for(int i=0; i< this.size(); i++)
		{
			this.elements[i]=null;
		}
		this.currentSize=0;

	}

}
