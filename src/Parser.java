import java.util.ArrayList;

public class Parser {
	
	public static String parse(String infix,StackADT<String> stack){
		ArrayList<String> tExpression = tokenize(infix);
		checkInput(tExpression);
		ArrayList<String> postfix = getPostfix(tExpression,stack);
		for(String s: postfix){
			System.out.print(s + " ");
		}
		System.out.println();
		return getResult(postfix,stack);
	}
	
	/**
	 * This method gets the result of the inputed postfix expression.
	 * @param postfix- the postfix array that is then evaluated to get a result
	 * @param stack- the stack used by the GUI
	 * @return the final result of the postfix expression
	 */
	private static String getResult(ArrayList<String> postfix,StackADT<String> stack){
		String result = "";
		
		while(postfix.size()>0){
			String current = postfix.get(0);
			postfix.remove(0);
			if(isNumber(current)){
				stack.push(current);
			}else if(isOperator(current)){
				
				int x = getNumber(stack.pop());
				int y = getNumber(stack.pop());
				
				stack.push(evaluate(y,x,current)+"");
			}
		}
		if(stack.isEmpty())
			throw new IllegalArgumentException("Invalid Number");
		result = stack.pop();
		
		return result;
	}
	/**
	 * This method tokenizes the infix. It separates each character and combines numbers.
	 * It accounts for unary negatives.
	 * @param infix- The user inputed expression
	 * @return An arraylist of the infix expression
	 */
	private static ArrayList<String> tokenize(String infix){
		boolean pOperator = false;
		infix = infix.trim();
		ArrayList<String> tokens = new ArrayList<String>();
		int indexStart = 0;
		int index = 0;
		pOperator = true;
		while(index<infix.length()){
			Character currentChar = infix.charAt(index);
			if(isOperator(currentChar.toString())){ //If Operator
				if(indexStart!=index){
					tokens.add(infix.substring(indexStart, index));
				}
				if(currentChar == '-'&&pOperator){
					indexStart = index;	
					pOperator = false;
				}else{
					tokens.add(infix.substring(index,index+1));	 
					indexStart = index+1;
					pOperator = true;
				}
			}else if(isDelim(currentChar)){//If parenthesis
				if(indexStart!=index){
					tokens.add(infix.substring(indexStart, index));
				}
				tokens.add(infix.substring(index,index+1));	 
				indexStart = index+1;
			}else if(currentChar == ' '){ //If space
				if(indexStart!=index){
					tokens.add(infix.substring(indexStart, index));
				}
				indexStart = index+1;
			}else{
				pOperator = false;
			}
			
			index++;
		}
		if(indexStart!=index){
			tokens.add(infix.substring(indexStart,index));
		}
		return tokens;
	}	
	/**
	 * This method evaluates two numbers and an operator
	 * @param y- the first number
	 * @param x- the second number
	 * @param current- the current operator
	 * @return result of y operator x
	 */
	private static int evaluate(int y, int x, String current) { 
		switch(current){
		
		case "+": return y+x;
		case "-": return y-x;
		case "*": return y*x;
		case "/": if(x==0)
					throw new IllegalArgumentException("Cannot divide by 0");
				  return y/x;
		case "^": return (int) Math.pow(y, x);
		case "%": return y%x;
	
		}
		throw new IllegalArgumentException("Unknown operator");
	}

	/**
	 * This method gets the postfix array from the tokenized infix
	 * @param infix- the tokenized infix array
	 * @param stack- the stack to use (from the GUI)
	 * @return- the postfix array from the infix expression
	 */
	private static ArrayList<String> getPostfix(ArrayList<String> infix,StackADT<String> stack){
		ArrayList<String> postfix = new ArrayList<String>();
		stack.push("(");
		infix.add(")");
		
		while(!stack.isEmpty()){
				String current = infix.get(0);
				infix.remove(0);
				if(isNumber(current)){
					postfix.add(current);
				}else if(current.equals("(")){
					stack.push(current.toString());
				}else if(isOperator(current)){
					
					while(isOperator(stack.peek())&&precedenceCheck(current.charAt(0),stack.peek().charAt(0))){
						postfix.add(stack.pop());
					}
					stack.push(current.toString());
				}else if(current.equals(")")){
					
					while(isOperator(stack.peek())){
						postfix.add(stack.pop());
					}
					stack.pop();
				}	
		}
		return postfix;
	}

	/**
	 * This method checks if the current string is an operator
	 * Supported Operations: addition, subtraction, multiplication,
	 * 						 division, modulus, power
	 * @param current- the current String to check
	 * @return true if operator
	 * 		   false if not operator
	 */
	private static boolean isOperator(String current) {
		String[] operators = new String[]{"+","-","*","/","%","^"};
		if(current.length()>1) return false;
		for(String c: operators){
			if(current.equals(c))
				return true;
		}	
		return false;
	}
	/**
	 * This method checks if the current string is a number
	 * @param current- the string to check
	 * @return true if number
	 * 		   false if not number
	 */
	private static boolean isNumber(String current) {
		for(char c : current.toCharArray()){
			if(Character.isDigit(c))
				return true;
		}
		return false;
	
		
	}
	/**
	 * This method checks if the current character is a delimiter
	 * Delimiters: '(' and ')'
	 * @param currentChar- the current character to check
	 * @return true if delimiter
	 * 		   false if not delimiter
	 */
	private static boolean isDelim(Character currentChar) {
		char[] delims = new char[]{')','('};		
		for(char c: delims){
			if(currentChar == c)
				return true;
		}	
		return false;
	}
	
	/**
	 * This method checks the precedence of two different operators
	 * @param base- the first operator
	 * @param check the operator to check if greater than equal to
	 * @return true if the "check" operator is greater than equal to the base operator
	 * 		   false if the "check" operator is less than the base operator
	 */
	private static boolean precedenceCheck(char base, char check){
		return (pLevel(check)>=pLevel(base));
	}
	
	/**
	 * This method gets the precedence level of an operator
	 * @param op- the operator to check the precedence of
	 * @return- the operator's precedence
	 */
	private static int pLevel(char op) {
	    switch (op) {
	        case '+':
	        case '-':
	            return 0;
	        case '*':
	        case '/':
	        case '%':
	            return 1;
	        case '^':
	            return 2;
	        default:
	            throw new IllegalArgumentException("Operator unknown: " + op);
	    }
	}	
	/**
	 * This method gets the numerical value of a string
	 * @param number- the String number to turn into int
	 * @return- the new int
	 */
	private static int getNumber(String number){
		boolean negative = false;
		if(number.charAt(0)=='-') {
			negative = true;
			number=number.substring(1);
		}
		int result = 0;
		int power;
		for(int i=0;i<number.length();i++){
			power = number.length()-i-1;
			result+=Character.getNumericValue(number.charAt(i))*Math.pow(10, power);
		}
		if(negative)
			result = result*-1;
		return result;
	}
	/**
	 * This method checks the input after it has been tokenized
	 * it checks for numbers after parenthesis
	 * @param check- the tokenized infix expression
	 */
	private static void checkInput(ArrayList<String> check){
		for(int i = 0; i < check.size();i++){
			if(i < check.size()-1){
				if(check.get(i).equals(")")&& check.get(i+1).equals("("))
					check.add(i+1, "*");
					
				else if(check.get(i).equals(")")&& isNumber(check.get(i+1)))
					throw new IllegalArgumentException("Invalid Input");
				else if(check.get(i+1).equals("(")&& isNumber(check.get(i)))
					throw new IllegalArgumentException("Invalid Input");
			
			}
		}
		
	}
}