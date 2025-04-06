package edu.ics211.h09;

/**
 * A singly-linked list suitable for recursive implementation of methods
 * 
 * @author Edo Biagioni
 * @assignment ICS 211 assignment 9
 * @date March 28, 2025
 */

public class LinkedListRecursive<E> {

  // here, include the LinkedNode definition

  /**
   * A node in a singly-linked list
   * 
   * @author Edo Biagioni
   * @lecture ICS 211 Jan 27 or later
   * @date January 26, 2010
   */

  private static class LinkedNode<T> {
    private T item;
    private LinkedNode<T> next;

    /**
     * constructor to build a node with no successor
     * 
     * @param the value to be stored by this node
     */
    private LinkedNode(T value) {
      item = value;
      next = null;
    }


    /**
     * constructor to build a node with specified (maybe null) successor
     * 
     * @param the value to be stored by this node
     * @param the next field for this node
     */
    private LinkedNode(T value, LinkedNode<T> reference) {
      item = value;
      next = reference;
    }
  }
  // end of the LinkedNode definition

  // this is the start of the linked list. If the list is empty, it is null
  protected LinkedNode<E> head;

  /**
   * initializes an empty linked list
   */
  public LinkedListRecursive() {
    super();
    head = null;
  }


  /**
   * return the size of the list
   * 
   * @return the size of the list
   * @runtime O(n)
   */
  public int size() {
    return sizeRecursiveHelper(head);
  } // End of size


  public int sizeRecursiveHelper(LinkedNode<E> node) {
    if (node == null) {
      return 0;
    } // End of if statement

    return 1 + sizeRecursiveHelper(node.next);
  } // End of sizeRecursiveHelper


  /**
   * adds a value to the end of the list
   * 
   * @param the value to be added
   * @return true (the add always succeeds)
   */
  public boolean add(E value) {
    head = addRecursiveHelper(head, value);
    return true;
  } // end of add


  public LinkedNode<E> addRecursiveHelper(LinkedNode<E> node, E value) {
    if (node == null) {
      return new LinkedNode<>(value);
    } // End of if statement

    node.next = addRecursiveHelper(node.next, value);
    return node;
  } // End of addRecursiveHelper


  /**
   * adds a value to the list, in the given position
   * 
   * @param the position at which to add: 0 to add at the start
   * @param the value to be added
   * @throws IndexOutOfBoundsException if the index is less than 0 or greater than the number of elements in the linked
   * list
   */
  public void add(int index, E value) {
    if (index < 0 || index > size()) {
      throw new IndexOutOfBoundsException("Index: " + index + " out of bounds.");
    } // End of if statement

    head = addAtIndexRecursiveHelper(head, value);
    return;
  } // End of add


  public LinkedNode<E> addAtIndexRecursiveHelper(LinkedNode<E> node, E value) {
    if (node == null) {
      return new LinkedNode(value);
    } // End of if statement

    node.next = addAtIndexRecursiveHelper(node.next, value);
    return node;
  } // End of addAtIndexRecursiveHelper


  /**
   * removes a value from the given position in the list
   * 
   * @param the position at which to remove: 0 to remove the first element
   * @return the removed element
   * @throws IndexOutOfBoundsException if the index is less than 0 or greater than or equal to the number of elements in
   * the linked list
   */
  public E remove(int index) {
    if (index < 0 || index >= size()) {
      throw new IndexOutOfBoundsException("Index: " + index + " out of bounds.");
    } // End of if statement
    
    RemoveValue newList = removeRecursiveHelper(head, index);
    head = newList.node;
    return newList.RemovedValue;
  } // End of remove

  /**
   * A class that will store the edited LinkedList and the removed value
   */
  public class RemoveValue {
    LinkedNode<E> node;
    E RemovedValue;

    RemoveValue(LinkedNode<E> node, E RemovedValue) {
      this.node = node;
      this.RemovedValue = RemovedValue;
    } // End of constructor

  } // End of class RemoveValue

  public RemoveValue removeRecursiveHelper(LinkedNode<E> node, int index) {
    if (index == 0) {
      return new RemoveValue(node.next, node.item);
    } // End of if statement 
    
      RemoveValue result = removeRecursiveHelper(node.next, index - 1);
      node.next = result.node;
      return result;
  } // End of RemoveValue


  /**
   * concatenates the elements of the linked list, separated by " ==> "
   * 
   * @return the string representation of the list
   */
  public String toString() {
    return toStringRecursiveHelper(head);
  } // End of toString


  public String toStringRecursiveHelper(LinkedNode<E> node) {
    if (node == null) {
      return "";
    } else if (node.next == null) {
      return node.item.toString();
    } else {
      return node.item + toStringRecursiveHelper(node.next);
    } // End of if...else statement

  } // End of toStringRecursiveHelper


  /**
   * checks element equality with value.equals(node.item).
   * 
   * @return the number of times this element occurs in this list, or 0.
   */
  public int numMatches(E value) {
    return numMatchesRecursiveHelper(head, value);
  } // End of numMatches


  public int numMatchesRecursiveHelper(LinkedNode<E> node, E value) {
    if (node == null) {
      return 0;
    } // End of if statement
    return (value.equals(node.item) ? 1 : 0) + numMatchesRecursiveHelper(node.next, value);

  } // End of numMatchesRecursiveHelper


  /**
   * @return the last value for which value.compareTo(node.item) > 0, or
   * @return null if value <= each of the items in the list
   */
  public E lastGreater(Comparable<E> value) {
    return lastGreaterRecursiveHelper(head, value);
  } // End of lastGreater


  public E lastGreaterRecursiveHelper(LinkedNode<E> node, Comparable<E> value) {
    if (node == null) {
      return null;
    } // End of if statement

    E later = lastGreaterRecursiveHelper(node.next, value);
    if (later != null) {
      return later;
    } // End of if statement
    return value.compareTo(node.item) < 0 ? node.item : null;

  } // End of lastGreaterRecursiveHelper


  /**
   * @return the first value for which value.compareTo(node.item) > 0, or
   * @return null if value <= each of the items in the list
   */
  public E firstGreater(Comparable<E> value) {
    return firstGreaterRecursiveHelper(head, value);
  } // End of firstGreater


  public E firstGreaterRecursiveHelper(LinkedNode<E> node, Comparable<E> value) {
    if (node == null) {
      return null;
    } // End of if statement

    if (value.compareTo(node.item) < 0) {
      return node.item;
    } // End of if statement

    return firstGreaterRecursiveHelper(node.next, value);
  } // End of firstGreaterRecursiveHelper


  /**
   * @return an array containing only the even-numbered (0, 2, 4, 6, ...) elements of the list.
   */
  public E[] toEvenArray() {
    // TO DO: call size to get number of elements in the list,
    // then create an array with (size + 1) / 2 elements.
    // then use a recursive helper method to fill the array with
    // the even-numbered elements, and return that array.
    // this method counts for twice as much credit (20%) as the others
    int size = size();
    E[] result = (E[]) new Object[(size + 1) / 2]; // even-indexed elements
    toEvenArrayHelper(head, result, 0, 0);
    return result;
  } // End of toEvenArray


  public void toEvenArrayHelper(LinkedNode<E> node, E[] result, int index, int resultIndex) {
    if (node == null) {
      return;
    } // End of if statement

    if (index % 2 == 0) {
      result[resultIndex] = node.item;
      resultIndex++;
    } // End of if statement

    toEvenArrayHelper(node.next, result, index + 1, resultIndex);
  } // End of toEvenArrayHelper

} // End of class LinkedListRecursive
