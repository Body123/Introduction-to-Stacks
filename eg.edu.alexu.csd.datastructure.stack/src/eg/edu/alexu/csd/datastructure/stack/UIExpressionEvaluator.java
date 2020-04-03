package eg.edu.alexu.csd.datastructure.stack;

import java.util.Scanner;

public class UIExpressionEvaluator {

	public static void main(String[] args) {
		stackImplementation ourStack = new stackImplementation();
		ExpressionImplementation obj=new ExpressionImplementation();
		Scanner input = new Scanner (System.in);
		while(true) {
			System.out.println("Please choose an action ");
			System.out.println("1-calculate PostFix Expression from Infix Expression ");
			System.out.println("2-Evaluate PostFix Expression(Digits)  ");
			System.out.println("3-calculate PostFix Expression from Infix Expression And get it's value (Digits)  ");
			System.out.println("4-Evaluate PostFix Expression(Digits or Symbols) And get it's value  ");
			System.out.println("5-Exit ");
			char choosen =input.next().charAt(0);
			switch(choosen)
			{
			case '1':
					System.out.println("Enter your Expression  : ");
					Scanner input1 = new Scanner (System.in);
					String x="";
					x+=input1.nextLine();
					System.out.println(obj.infixToPostfix(x));
					break;
			case '2': 
				System.out.println("Enter your Expression  : ");
				Scanner input2 = new Scanner (System.in);
				String y="";
				y+=input2.nextLine();
				System.out.println(obj.evaluate(y));
				break;
			case '3':
				System.out.println("Enter your Expression  : ");
				Scanner input3 = new Scanner (System.in);
				String u="";
				u+=input3.nextLine();
				String postfix=obj.infixToPostfix(u);
				System.out.println("postFix Expression is : "+postfix);
				System.out.println("value of your Expression is : "+ obj.evaluate(postfix));
				break;
			case '4': 
				System.out.println("Enter your Expression  : ");
				Scanner input4 = new Scanner (System.in);
				String p="";
				p+=input4.nextLine();
				String postfixresult=obj.infixToPostfix(p);
				System.out.println("postFix Expression is : "+postfixresult);
				System.out.println("value of your Expression is : "+obj.evaluate(havingSymbols(postfixresult)));
				
				break;
			case '5': 
				return;
			default:
				System.out.println("Please Enter correct input");
			}
			
				
		}
		

	}
	/**
	* replace symbols with intgers
	*
	* @param x
	* @throws if wrong expression
	* @author sakr
	* postfix x
	* @return the expression with intgers 
	*/
	public static String havingSymbols(String x) {
		Scanner input = new Scanner (System.in);
		String Symbol;
		char characterreplacement;
		for(int o=0; o<x.length();o++) {
			if(Character.isLetter(x.charAt(o))) {
				System.out.println("don't Enter any space (enter INT only instead of symbole)");
				System.out.println("Enter Symbol "+x.charAt(o));
				
				Symbol=input.nextLine();
				for(int z=0;z<Symbol.length();z++) {
				if(!Character.isDigit(Symbol.charAt(z))) {
					///check wrong
					throw new RuntimeException("Wrong expression Enter only int numbers");
					}
				}
				characterreplacement=Character.toUpperCase(x.charAt(o));
				/////// replace symbol 
				x = x.substring(0, o) 
			              + Symbol
			              + x.substring(o + 1);
				for(int r=o+1; r<x.length();r++) {
					if((r<x.length())&&Character.toUpperCase(x.charAt(r))==characterreplacement) {
						x = x.substring(0, r) 
					              + Symbol
					              + x.substring(r+1);
					}
				}
			}
		}
		
		return x;
	}

}
