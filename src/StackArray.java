
public class StackArray<T> implements StackADT<T>{

	private T[] array; //Holds the elements in the form of a stack
	private int top; //Tracks the index of the top of the stack

	
	/**This is the default constructor method. It instantiates
	 * top to -1 and array to a T array w/ size 10
	 */
	public StackArray(){
		array = (T[]) new Object[10];
		top = -1;
	}
	
	/**This is the other constructor method. It instantiates
	 * top to -1 and array to a T array w/ inputed size
	 * @param size- the desired size of the array
	 */
	public StackArray(int size){
		array = (T[]) new Object[size];
		top = -1;
	}
	
	
	/**This method pushes an element onto the stack
	 * If the array is full, a new one is created with double
	 * the size. 
	 * @param- The new element to add to the stack
	 */
	public void push(T element) {
		if(size()==array.length){
			doubleArraySize();
		}
		array[++top] = element;
	}

	/**
	 * This method doubles the size of the array.
	 */
	private void doubleArraySize() {
		T[] newArray = (T[]) new Object[array.length*2];
		for(int i = 0;i<size();i++){
			newArray[i] = array[i];
		}
		array = newArray;
	}
	/**
	 * This method removes and returns the element on the top of the stack.
	 *  @return- the element being removed
	 */
	public T pop() {
		if(isEmpty()){
			throw new IllegalStateException("Stack is empty");
		}
		return array[top--];
	}

	/**
	 * This method returns the element on the top of the stack
	 * @return- the element being returned
	 */
	public T peek() {
		if(isEmpty()){
			throw new IllegalStateException("Stack is empty");
		}
		return array[top];
	}

	/**This method determines if the stack is empty
	 * @return- true if empty
	 * 			false is nonempty
	 */
	public boolean isEmpty() {
		return size()==0;
	}

	/**
	 * This method returns the number of elements in the stack
	 * @return- the number of elements
	 */
	public int size() {
		return top+1;
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
