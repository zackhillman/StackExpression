public class ListNode<T>{ 
	/**
	 * Instance Variables
	 */
	private T value; //Holds the value of the node ,Type T
	private ListNode<T> next; //the node which comes after this node

	/**
	 * Constructor method. value and next are instantiated to null
	 */
	public ListNode(){
		value=null; next=null;
	} 

	/**
	 * Over loaded constructor. Instantiates value for value and next
	 * @param initValue- the initial value of the node
	 * @param initNext - the node which comes after this one
	 */
	public ListNode(T initValue, ListNode<T> initNext){ 
		value=initValue;
		next=initNext;
	} 

	/**
	 * This method gets the value of the node
	 * @return- the T value of the node
	 */
	public T getValue(){ 
		return value;
	} 
	
	/**
	 * THis method returns the node which comes after this one
	 * @return- the next ListNode
	 */
	public ListNode<T> getNext(){
		return next;
	} 

	/**
	 * This method sets the value of the node
	 * @param newValue- the new T value for the node
	 */
	public void setValue(T newValue){
		value=newValue;
	} 
	/**
	 * This method sets the next ListNode
	 * @param newNext - the next ListNode
	 */
	public void setNext(ListNode<T> newNext){
		next=newNext;
	}
}