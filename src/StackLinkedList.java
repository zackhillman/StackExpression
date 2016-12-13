
public class StackLinkedList<T>  implements StackADT<T>{

	private ListNode<T> head; //Holds reference of the first node of the linkedlist

	/**
	 * Constructor method, instantiates all instance variables to null
	 */
	public StackLinkedList(){
		head = null;
	}
	
	/**This method pushes an element onto the stack 
	 * @param- The new element to add to the stack
	 */
	public void push(T element) {
		ListNode<T> node = new ListNode<T>(element,head);
		head = node;
	}

	/**
	 * This method removes and returns the element on the top of the stack.
	 *  @return- the element being removed
	 */
	public T pop() {
		if(isEmpty())
			throw new IllegalStateException("Stack is empty");
		
		T element = head.getValue();
		head = head.getNext();
		return element;
	}

	/**
	 * This method returns the element on the top of the stack
	 * @return- the element being returned
	 */
	public T peek() {
		if(isEmpty())
			throw new IllegalStateException("Stack is empty");
		return head.getValue();
	}

	/**This method determines if the stack is empty
	 * @return- true if empty
	 * 			false is nonempty
	 */
	public boolean isEmpty() {
		return head==null;
	}
	
	/**
	 * This method returns the number of elements in the stack
	 * @return- the number of elements
	 */
	public int size() {
		ListNode<T> current = head;
		int size = 0;
		while(current!=null){
			size++;
			current = current.getNext();
		}
		return size;
	}
	/**
	 * This method pops all of the elements off the stack and returns
	 * then as a string.
	 * @return- All of the elements popped off the stack
	 */
	public String toString(){
		String str = "";
		while(!isEmpty())
			str += pop();
		return str;
	}
	
}
