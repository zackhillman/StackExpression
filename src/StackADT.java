
public interface StackADT <T>{

	public void push(T element);//Pushes element to the stack
	public T pop();//Removes and returns top element from the stack
	public T peek();//Returns the top element from the stack
	public boolean isEmpty();//Checks if stack is empty
	public int size();//Returns the number of elements in the stack
	public String toString();//Returns string of the stack after popping.
	
}
