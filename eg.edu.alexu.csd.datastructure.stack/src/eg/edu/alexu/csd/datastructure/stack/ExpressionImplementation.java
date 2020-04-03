package eg.edu.alexu.csd.datastructure.stack;

import java.util.regex.Pattern;

public class ExpressionImplementation implements IExpressionEvaluator {
	
	// check Ing range
	private  final  int max=Integer.MAX_VALUE;
	private  final  int min=Integer.MIN_VALUE;
	/**
	* determine the priority of the top of the stack
	*@param top element of stack x
	*
	* @author sakr
	*@return priority of the top of the stack
	*/
	public int periotityOfTop(Object x) {
		if(x==(Object)'+'||x==(Object)'-') return 1;
		else if(x==(Object)'*'||x==(Object)'/') return 2;
		else 
		return 0;
		
	}
	/**
	* Evaluate the operation between last two operands in stack
	*@param two float number the last two operands in stack
	*@throws if wrong expression
	* @author sakr
	*@return the result of this operation
	*/
	public float ourOperation(float x,float y,char operator) {
		float result=0;
		if(operator=='+') {
			result=x+y;
		}else if(operator=='-') {
			result=x-y;
		}else if(operator=='*') {
			result=x*y;
		}else if(operator=='/') {
			if(y==0) {
				throw new RuntimeException("Can't divide by zero");
			}
			result=x/y;
		}
		else {
			throw new RuntimeException("Wrong expression");
		}
		
		return result;
		
	}
	
	/**
	* Takes a symbolic/numeric infix expression as input and converts it to
	* postfix notation. There is no assumption on spaces between terms or the
	* length of the term (e.g., two digits symbolic or numeric term)
	*
	* @param expression
	* @throws if wrong expression
	* @author sakr
	* infix expression
	* @return postfix expression
	*/
	@Override
	public String infixToPostfix(String expression) {
		//check ingers
		if(expression.contains(".")) throw new RuntimeException("Wrong expression this only for Integer NUmbers");
		//check empty string
		if(expression=="")  throw new RuntimeException("Wrong expression");
		for(int q=0;q<expression.length();q++) {
			if(Character.isLetter(expression.charAt(q))) {
				while((q+1)<expression.length()&&expression.charAt(q+1)==' ') {
					q++;
				}
				if((q+1)<expression.length()&&Character.isLetter(expression.charAt(q+1))) {
					throw new RuntimeException("Wrong expression");
				}
			}
		}
		
		//check wrong cases
	for(int q=0;q<expression.length();q++) {
			if(expression.charAt(q)==')') {
				while((q+1)<expression.length()&&expression.charAt(q+1)==' ') {
					q++;
				}
				if((q+1)<expression.length()&&expression.charAt(q+1)=='(') {
					throw new RuntimeException("Wrong expression");
				}
			}
		}
	//check wrong cases
	for(int q=0;q<expression.length();q++) {
		if(expression.charAt(q)=='+'||expression.charAt(q)=='-'||expression.charAt(q)=='*'||expression.charAt(q)=='/') {
			while((q+1)<expression.length()&&expression.charAt(q+1)==' ') {
				q++;
			}
			if((q+1)<expression.length()&&expression.charAt(q+1)=='+'||expression.charAt(q+1)=='-'||expression.charAt(q+1)=='*'||expression.charAt(q+1)=='/') {
				q++;
				while((q+1)<expression.length()&&expression.charAt(q+1)==' ') {
					q++;
				}
				if((q+1)<expression.length()&&expression.charAt(q+1)=='+'||expression.charAt(q+1)=='-'||expression.charAt(q+1)=='*'||expression.charAt(q+1)=='/') {
				throw new RuntimeException("Wrong expression");
			}
		}
		}
	}
		stackImplementation Stack=new stackImplementation();
		// check if expression need to edit
		for(int o=0; o<expression.length();o++) {
			if(expression.charAt(o)=='+'||expression.charAt(o)=='-'||expression.charAt(o)=='*'||expression.charAt(o)=='/') {
				while((o+1)<expression.length()&&expression.charAt(o)==' ') {
					o++;
				}
			if(expression.charAt(o)=='+'||expression.charAt(o)=='-'||expression.charAt(o)=='(') {
				expression=EditExpression(expression);
				}
			}
		}
		
		String result="";
		//check wrong cases
		if(expression.charAt(0)==')') {
			throw new RuntimeException("Wrong expression");
		}
		for(int i=0; i<expression.length();i++) {
			if(expression.charAt(i)==' ') {
				continue;
			}
			//check ing or symbols 
			else if(Character.isDigit(expression.charAt(i))||Character.isLetter(expression.charAt(i))) {
				while(Character.isDigit(expression.charAt(i))||Character.isLetter(expression.charAt(i))) {
					result+=expression.charAt(i++);
					if(i==expression.length()) break;
				}
				i=i-1;
				result+=' ';
				continue;
				
			}else if(expression.charAt(i)=='(') {
				Stack.push(expression.charAt(i));
			}else if(expression.charAt(i)==')') {
				if(Stack.isEmpty())throw new RuntimeException("Wrong expression");
				while(Stack.peek()!= (Object)'(') {
				result+=Stack.pop();
				result+=' ';
				}if(Stack.isEmpty())throw new RuntimeException("Wrong expression");
				Stack.pop();
				
			}else {
				while(!Stack.isEmpty()&&periotityOfTop(expression.charAt(i))<=periotityOfTop(Stack.peek())){
					result+=Stack.pop();
					result+=' ';
				}
				if(!(expression.charAt(i)=='+')&&!(expression.charAt(i)=='-')&&!(expression.charAt(i)=='/')&&!(expression.charAt(i)=='*')) {
					throw new RuntimeException("Wrong expression");
				}
				Stack.push(expression.charAt(i));
				
			}
			
		}
		while(!Stack.isEmpty()) {
			result+=Stack.pop();
			if(Stack.isEmpty()) break;
			result+=' ';
		}
		//check if contain space at last of expresiion
		if(result.contains("(")||result.contains(")")) throw new RuntimeException("Wrong expression");
		String checkResult="";
		if(result.charAt(result.length()-1)==' ') {
			for(int t=0;t<(result.length()-1);t++) {
				checkResult+=result.charAt(t);
			}
			return checkResult ;
		}
		
		return result;
	}
	/**
	* Evaluate a postfix numeric expression, with a single space separator
	*
	* @param expression
	* @throws if wrong expression
	* @author sakr
	* postfix expression
	* @return the expression evaluated value
	*/
	@Override
	public int evaluate(String expression) {
		// TODO Auto-generated method stub
		if(expression.contains(".")) throw new RuntimeException("Wrong expression this only for Integer NUmbers");
		if(expression=="")  throw new RuntimeException("Wrong expression");
		stackImplementation StackForEvaluate=new stackImplementation();
		float addedToStack,firstOperand,secondOperand,result=0;
		String sub="";
		for(int i=0; i<expression.length();i++) {
			if(expression.charAt(i)==' ') {
				if((i+1)<expression.length()&&expression.charAt(1+i)==' ') {
					System.out.println("Having More than One Space betweet terms ");
					throw new RuntimeException("Wrong expression");	
				}else
				continue;
			}
			if(Character.isDigit(expression.charAt(i))) {
				while((i+1)<expression.length()&&expression.charAt(i+1)!=' ') {
					sub+=expression.charAt(i++);
				}
				sub+=expression.charAt(i++);
				
				addedToStack=Integer.parseInt(sub);
				StackForEvaluate.push(addedToStack);
				sub="";
				
				
			}
			else {
				if(StackForEvaluate.isEmpty()) throw new RuntimeException("Wrong expression");
				secondOperand=(float) StackForEvaluate.pop();
				if(StackForEvaluate.isEmpty()) throw new RuntimeException("Wrong expression");
				firstOperand=(float) StackForEvaluate.pop();
				result=ourOperation(firstOperand,secondOperand,expression.charAt(i));
				StackForEvaluate.push(result);
			}
		}
		if(!StackForEvaluate.isEmpty())
		result=(float) StackForEvaluate.pop();
		else
			throw new RuntimeException("Wrong expression");
		if(result<max&&result>min) {
			return (int)result;
		}else {
			throw new RuntimeException("Range bigger than INT capacity");
		}
		
	}
	/**
	* Edit the Expression if it contain negative numbers negative parenthesis using dummy zero
	*
	* @param x
	* @throws if wrong expression
	* @author sakr
	* @return y in form using dummy zero to evaluate postfix expreesion
	*/
	public  String EditExpression(String x) {
		String y="";
		
		stackImplementation tester =new stackImplementation();
		for(int i=0;i<x.length();i++) {
			//check having one sign in the first after spaces
			if((y=="")&&x.charAt(i)==' '){
				
				while((i+1<x.length())&&x.charAt(i+1)==' ') {
					i++;

				}
				if((i+1<x.length())&&(x.charAt(i+1)=='-'||x.charAt(i+1)=='+')) {
					i++;
					if(x.charAt(i)=='-')y+="(0-";
					else y+="(0+";
					if((((i+1)<x.length())&&x.charAt(i+1)=='(')) {
						i++;
						y+=x.charAt(i);
						while(((i+1)<x.length())&&!(x.charAt(i+1)==')')) {
							i++;
							y+=x.charAt(i);
						}
						y+=")";
						continue;
					}else {
					while(((i+1)<x.length())&&(Character.isDigit(x.charAt(1+i))||Character.isLetter(x.charAt(1+i)))) {
						i++;
						y+=x.charAt(i);
					}
					y+=" )";
					continue;
					}
				}
			}
			//check having one sign in the first
			if((y=="")&&(x.charAt(0)=='-'||x.charAt(0)=='+')){
				
				if(x.charAt(0)=='-')y+="(0-";
				else y+="(0+";
				if((((i+1)<x.length())&&x.charAt(i+1)=='(')) {
					i++;
					y+=x.charAt(i);
					while(((i+1)<x.length())&&!(x.charAt(i+1)==')')) {
						i++;
						y+=x.charAt(i);
					}
					y+=")";
					continue;
				}else {
				while(((i+1)<x.length())&&(Character.isDigit(x.charAt(1+i))||Character.isLetter(x.charAt(1+i)))) {
					i++;
					y+=x.charAt(i);
				}
				y+=')';
				continue;
				}
			}
			      //check having one sign after the operand
			if(x.charAt(i)=='+'||x.charAt(i)=='-'||x.charAt(i)=='/'||x.charAt(i)=='*') {
				y+=x.charAt(i);
				
				while(((i+1)<x.length())&&!Character.isDigit(x.charAt(1+i))&&!Character.isLetter(x.charAt(1+i))) {
					i++;
					if(x.charAt(i)=='+') {
						while(((i+1)<x.length())&&x.charAt(i+1)==' ') {
							i++;
							y+=' ';
						}
						//check wrong cases
						if(((i+1)<x.length())&&x.charAt(i+1)=='+') throw new RuntimeException("Wrong expression");
						y+="(0+";
						if((((i+1)<x.length())&&x.charAt(i+1)=='(')) { 
							i++;
							y+=x.charAt(i);
							while(((i+1)<x.length())&&!(x.charAt(i+1)==')')) {
								i++;
								y+=x.charAt(i);
							}
							y+=")";
								
						}else {										
						while(((i+1)<x.length())&&!(x.charAt(1+i)=='+')&&!(x.charAt(1+i)=='-')&&!(x.charAt(1+i)=='/')&&!(x.charAt(1+i)=='*')) {
							i++;
							
							y+=x.charAt(i);
						}
						y+=")";
						break;
						}
					}else if(x.charAt(i)=='-') {
						y+="(0-";
						if((((i+1)<x.length())&&x.charAt(i+1)=='(')) {
							i++;
							y+=x.charAt(i);
							while(((i+1)<x.length())&&!(x.charAt(i+1)==')')) {
								i++;
								y+=x.charAt(i);
							}
							y+=")";
								
						}else {
						while(((i+1)<x.length())&&x.charAt(i+1)==' ') {
							i++;
							y+=' ';
						}
						if(((i+1)<x.length())&&x.charAt(i+1)=='-') throw new RuntimeException("Wrong expression");
						while(((i+1)<x.length())&&!(x.charAt(1+i)=='+')&&!(x.charAt(1+i)=='-')&&!(x.charAt(1+i)=='/')&&!(x.charAt(1+i)=='*')) {
							i++;
							y+=x.charAt(i);
						}
						y+=")";
						break;
						}
					}else if(x.charAt(i)=='*'||x.charAt(i)=='/') throw new RuntimeException("Wrong expression");
					else {
						y+=x.charAt(i);
					}
					
				}
				
			}
			else {
				y+=x.charAt(i);
			}
		}
		return y;
		
	}
	
	

}
