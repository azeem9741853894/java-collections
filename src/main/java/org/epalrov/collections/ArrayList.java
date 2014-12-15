/*
 * ArrayList.java - example of Array List implementation
 *
 * Copyright (C) 2014 Paolo Rovelli
 *
 * Author: Paolo Rovelli <paolorovelli@yahoo.it>
 */

package org.epalrov.collections;

import java.util.List;
import java.util.Collection;
import java.util.Iterator;
import java.util.ListIterator;

/**
 * Array list implementation of the <tt>List</tt> interface. 
 *
 * Note that this implementation is not synchronized. If multiple threads
 * access an array list concurrently, and at least one of the threads modifies
 * the list structurally, it must be synchronized externally. This is typically
 * accomplished by synchronizing on some object that naturally encapsulates
 * the list.
 */

public class ArrayList<E> implements List<E>
{
	private Object[] array; // use Objetc[] instead of E[]
	private int size;

	public ArrayList() {
		array = new Object[0];
		size = 0;
	}

	/**
	 * Returns the number of elements in this list.
	 */
	public int size() {
		return size;
	}

	/**
	 * Returns <tt>true</tt> if this list contains no elements.
	 */
	public boolean isEmpty() {
		return size == 0 ? true : false;
	}

	/**
	 * Appends the specified element to the end of this list.
	 */
	public boolean add(E e) {
		arrayResize(size + 1);
		array[size] = e;
		size++;
		return true;
	}

	/**
	 * Inserts the specified element at the specified position in this list.
	 */
	public void add(int index, E e) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException(
				"Index: "+index+", Size: "+size);
		}
		arrayResize(size + 1);
		arrayInsert(index, 1);
		array[index] = e;
		size++;
	}

	/**
	 * Returns the element at the specified position in this list.
	 */
	public E get(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException(
				"Index: "+index+", Size: "+size);
		}
		return (E)array[index]; // unchecked cast
	}

	/**
	 * Replaces the element at the specified position in this list with the
	 * specified element.
	 */
	public E set(int index, E e) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException(
				"Index: "+index+", Size: "+size);
		}
		E elem = (E)array[index]; // unchecked cast
		array[index] = e;
		return elem;
	}

	/**
	 * Removes the element at the specified position in this list.
	 */
	public E remove(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException(
				"Index: "+index+", Size: "+size);
		}
		E elem = (E)array[index]; // unchecked cast
		arrayRemove(index, 1);
		arrayResize(size - 1);
		size--;
		return elem;
	}

	/**
	 * Removes the first occurrence of the specified element from this list,
	 * if it is present.
	 */
	public boolean remove(Object o) {
                for (int i = 0; i < size; i++) {
                        if (o.equals(array[i]) || (o == null && array[i] == null)) {
				arrayRemove(i, 1);
				arrayResize(size - 1);
				size--;
                                return true;
			}
                }
		return false;
	}

	/**
	 * Removes all of the elements from this list.
	 */
	public void clear() {
		for (int i = 0; i < size; i++)
			array[i] = null;
		array = new Object[0];
		size = 0;
	}

	/**
	 * Returns the index of the first occurrence of the specified element
	 * in this list, or -1 if this list does not contain the element.
	 */
	public int indexOf(Object o) {
		for (int i = 0; i < size; i++) {
			if (o.equals(array[i]) || (o == null && array[i] == null))
				return i;
		}
		return -1;
	}

	/**
	 * Returns the index of the last occurrence of the specified element
	 * in this list, or -1 if this list does not contain the element.
	 */
	public int lastIndexOf(Object o) {
		for (int i = size - 1; i >= 0; i--) {
			if (o.equals(array[i]) || (o == null && array[i] == null))
				return i;
		}
		return -1;
	}

	public boolean contains(Object o) {
		return indexOf(o) != -1 ? true : false;
	}

	/**
	 * Returns an iterator over the elements in this list in proper sequence.
	 */
	public Iterator<E> iterator() {
		return new ListItr(0);
	}

	/**
	 * Returns a list iterator over the elements in this list in proper sequence.
	 */
	public ListIterator<E> listIterator() {
		return new ListItr(0);
	}

	/**
	 * Returns a list iterator over the elements in this list (in proper
	 * sequence), starting at the specified position in the list.
	 */
	public ListIterator<E> listIterator(int index) {
		return new ListItr(index);
	}

	private class ListItr implements ListIterator<E> {

		private int currIndex;
		private int nextIndex;

		ListItr(int index) {
			if (index < 0 || index >= size) {
				throw new IndexOutOfBoundsException(
					"Index: "+index+", Size: "+size);
			}
			currIndex = -1;
			nextIndex = index;
		}

		public boolean hasNext() {
			return nextIndex == size ? false : true;
		}

		public E next() {
			currIndex = nextIndex;
			nextIndex++;
			return (E)array[currIndex]; // unchecked cast
		}

		public int nextIndex() {
			return nextIndex;
		}

		public boolean hasPrevious() {
			return nextIndex == -1 ? false : true;
		}

		public E previous() {
			currIndex = nextIndex;
			nextIndex--;
			return (E)array[currIndex]; // unchecked cast
		}

		public int previousIndex() {
			return nextIndex;
		}

		public void set(E e) {
			array[currIndex] = e;
		}

		public void add(E e) {
			arrayResize(size + 1);
			arrayInsert(nextIndex, 1);
			array[nextIndex] = e;
			nextIndex++;
			size++;
		}

		public void remove() {
			arrayRemove(nextIndex, 1);
			arrayResize(size - 1);
			nextIndex--;
			size--;
		}
	}

	/**
	 * Returns an array containing all of the elements in this list in proper
	 * sequence (from first to last element).
	 */
	public Object[] toArray() {
		Object[] o = new Object[size];
		for (int i = 0; i < size; i++)
			o[i] = array[i];

		return o;
	} 

	/**
	 * Returns an array containing all of the elements in this list in
	 * proper sequence (from first to last element); the runtime type of
	 * the returned array is that of the specified array.
	 */
	public <T> T[] toArray(T[] a) {
		/* realloc */
		if (a.length < size)
			a = (T[])java.lang.reflect.Array.newInstance(
				a.getClass().getComponentType(), size);

		for (int i = 0; i < size; i++)
			a[i] = (T)array[i];

		return a;
	}

	/**
	 * Returns <tt>true</tt> if this list contains all of the elements of the
	 * specified collection.
	 */
	public boolean containsAll(Collection<? extends Object> c) {
		Iterator<? extends Object> it = c.iterator();
		while (it.hasNext())
			if (!contains(it.next()))
				return false;

		return true;
	}

	/**
	 * Appends all of the elements in the specified collection to the end of
	 * this list.
	 */
	public boolean addAll(Collection<? extends E> c) {
		Iterator<? extends E> it = c.iterator();
		while (it.hasNext()) {
			arrayResize(size + 1);
			array[size] = (E)it.next();
			size++;
		}
		return true;
	}

	/**
	 * Inserts all of the elements in the specified collection into this
	 * list at the specified position.
	 */
	public boolean addAll(int index, Collection<? extends E> c) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException(
				"Index: "+index+", Size: "+size);
		}
		
		Iterator<? extends E> it = c.iterator();
		while (it.hasNext()) {
			arrayResize(size + 1);
			arrayInsert(index, 1);
			array[index] = (E)it.next();
			size++;
		}
		return true;
	}	

	/**
	 * Removes from this list all of its elements that are contained in the
	 * specified collection.
	 */
	public boolean removeAll(Collection<? extends Object> c) {
		for (int i = 0; i < size; i++) {
			if (c.contains(array[i])) {
				arrayRemove(i, 1);
				arrayResize(size - 1);
				size--;
			}
		}
		return true;
	}

	/**
	 * Retains only the elements in this list that are contained in the
	 * specified collection.
	 */
	public boolean retainAll(Collection<?> c) {
		for (int i = 0; i < size; i++) {
			if (!c.contains(array[i])) {
				arrayRemove(i, 1);
				arrayResize(size - 1);
				size--;
			}
		}
		return true;
	}

	/**
	 * Not implemented ...
	 */
	public List<E> subList(int fromIndex, int toIndex) {
		return null;
	}

	/**
	 * Compares the specified object with this list for equality; returns
	 * <tt>true</tt> if and only if the specified object is also a list,
	 * both lists have the same size, and all corresponding pairs of
	 * elements in the two lists are equal.
	 */
	public boolean equals(Object o) {
		// same object reference
		if (o == this)
			return true;

		// check instance type (List)
		if (!(o instanceof List))
			return false;

		// check all list elements
		Iterator<E> eIterator = iterator();
		Iterator<?> oIterator = ((List<?>) o).iterator();
		while (eIterator.hasNext() && oIterator.hasNext()) {
			E elem = eIterator.next();
			Object obj = oIterator.next();
			if (!(obj.equals(elem) || (obj == null && elem == null)))
				return false;
		}

		// check list size
		return !(eIterator.hasNext() || oIterator.hasNext());
	}

	/**
	 * Returns the hash code value for this list.
	 */
	public int hashCode() {
		int hashCode = 1;
		Iterator<E> eIterator = iterator();
		while (eIterator.hasNext()) {
			E elem = eIterator.next();
			hashCode = 31*hashCode + (elem == null ? 0 : elem.hashCode());
		}

		return hashCode;
	}

	private void arrayResize(int len) {
		Object[] a = array;
		if (a.length != len) {
			array = new Object[len];
			for (int i = 0; i < len && i < a.length; i++)
				array[i] = a[i];
		}
	}

	private void arrayInsert(int index, int len) {
		for (int i = array.length - 1; i - len > index; i--)
			array[i] = array[i - len];
	}

	private void arrayRemove(int index, int len) {
		for (int i = index ; i + len < array.length; i++)
			array[i] = array[i + len];
	}

}
