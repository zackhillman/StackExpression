import java.util.ArrayList;

import javax.swing.*;
import BreezySwing.*;

public class StackGUI extends GBFrame{

	private JLabel expressionL; //Tells user where to input the number
	private JTextField expressionF; //Where the user inputs the number to convert to binary
	
	private JLabel sizeL;//Tells the user where to input the starting size
	private IntegerField sizeF; //Where the user inputs the starting size
	
	private JButton outputLinkedList; //Converts the number to binary using linkedlist stack
	private JButton outputArray; //Converts the number to binary using array stack
	
	private StackADT<String> stack; //Global variable StackADT, used for conversion
	
	Parser p;
	
	/**
	 * This is the constructor method. The GUI objects are instantiated.
	 * stack is not instantiated here.
	 */
	public StackGUI(){
		
		expressionL = addLabel("Number:",1,1,2,1);
		expressionF = addTextField("",2,1,2,1);
		sizeL = addLabel("Size:",4,1,2,1);
		sizeF = addIntegerField(10,5,1,2,1);
		
		outputLinkedList = addButton("List Output",3,1,1,1);
		outputArray = addButton("Array Output",3,2,1,1);
		
	}
	
	/**
	 * This method is called when a button is clicked
	 */
	public void buttonClicked(JButton buttonObj) {
		try{
			if (buttonObj == outputLinkedList) {
				stack = new StackLinkedList<String>();
				p = new Parser(stack);
			//	sizeF.setText(p.getResult(expressionF.getText()));
				ArrayList<String> tokens = p.tokenize(expressionF.getText());
				System.out.print("[");
				for(String s : tokens){
					System.out.print(s+",");
				}
				System.out.print("]");
				System.out.println();
		//		System.out.println(p.tokenize(expressionF.getText()).toString());
				System.out.println(p.getResult(expressionF.getText()));
		//		messageBox(stack.toString(),300,200);
	
			}
			else if(buttonObj == outputArray){
				stack = new StackArray<String>(10);
				p = new Parser(stack);
			//	messageBox(stack.toString(),300,200);
			}
		}catch(Exception e){
			messageBox(e,300,200);
		}
	}
	
	

	/**
	 * This is the main method it creates the actual GUI
	 * @param args
	 */
	public static void main(String[] args) {
		StackGUI theGUI = new StackGUI();
		theGUI.setSize(300, 200);
		theGUI.setVisible(true);
		theGUI.setTitle("Stack Program");
	}
}
