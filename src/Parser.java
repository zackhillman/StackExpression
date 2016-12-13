import java.util.ArrayList;

public class Parser {

	private StackADT<String> stack;
	
	
	
	public Parser(StackADT<String> s){
		stack = s;
	}
	
	public ArrayList<String> tokenize(String infix){
		infix = infix.trim();
		ArrayList<String> tokens = new ArrayList<String>();
		int indexStart = 0;
		int index = 0;
		while(index<infix.length()){
			Character currentChar = infix.charAt(index);
			if(isOperator(currentChar)||isDelim(currentChar)){
				if(index!=indexStart){
					tokens.add(infix.substring(indexStart, index));
				}
				if(currentChar!=' '){
					if(currentChar == '-'&&isOperator(tokens.get(tokens.size()-1).charAt(0))){
						
					}else{
						tokens.add(infix.substring(index,index+1));	
					}
				}
				indexStart = ++index;
			}else{
				index++;
			}
		}
		if(indexStart!=index){
			tokens.add(infix.substring(indexStart,index));
		}
		return tokens;
	}	
	
	public String getResult(String infix){
		String result = "";
		String postfix = getPostfix(tokenize(infix));
		System.out.println(postfix);
		while(postfix.length()>0){
			Character currentChar = postfix.charAt(0);
			postfix = postfix.substring(1);
			if(Character.isDigit(currentChar)){
				String number = currentChar.toString();
				while(Character.isDigit(postfix.charAt(0))){
					currentChar = postfix.charAt(0);
					postfix = postfix.substring(1);
					number+=currentChar;
				}
				stack.push(number);
			
			}else if(isOperator(currentChar)){
				
				int x = Integer.parseInt(stack.pop());
				int y = Integer.parseInt(stack.pop());
				
				stack.push(evaluate(y,x,currentChar)+"");
			}
		}
		result = stack.pop();
		
		return result;
	}
	
	private int evaluate(int y, int x, Character currentChar) {
		switch(currentChar){
		
		case '+': return y+x;
		case '-': return y-x;
		case '*': return y*x;
		case '/': return y/x;
		case '^': return (int) Math.pow(y, x);
		case '%': return y%x;
	
		}
		throw new IllegalArgumentException("Unknown operator");
	
	}

	private String getPostfix(ArrayList<String> infix){
	
		String postfix = "";
		stack.push("(");
		infix.add(")");
		while(!stack.isEmpty()){
				String current = infix.get(0);
				infix.remove(0);
				if(Character.isDigit(current.charAt(0))){
					postfix+= current + " ";
				}else if(current.equals("(")){
					stack.push(current.toString());
				}else if(isOperator(current.charAt(0))){
					
					while(isOperator(stack.peek().charAt(0))&&precedenceCheck(current.charAt(0),stack.peek().charAt(0))){
						postfix += stack.pop()+ " ";
					}
					stack.push(current.toString());
				}else if(current.equals(")")){
					
					while(isOperator(stack.peek().charAt(0))){
						postfix += stack.pop() + " ";
					}
					stack.pop();
				}
			
		}
		return postfix;
	}

	private boolean isOperator(Character currentChar) {
		char[] operators = new char[]{'+','-','*','/','%','^'};
		for(char c: operators){
			if(currentChar == c)
				return true;
		}	
		return false;
	}
	
	private boolean isDelim(Character currentChar) {
		char[] delims = new char[]{' ',')','('};		
		for(char c: delims){
			if(currentChar == c)
				return true;
		}	
		return false;
	}
	
	private boolean precedenceCheck(char base, char check){
		return (pLevel(check)>=pLevel(base));
	}
	
	private int  pLevel(char op) {
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
	
	
	
}
