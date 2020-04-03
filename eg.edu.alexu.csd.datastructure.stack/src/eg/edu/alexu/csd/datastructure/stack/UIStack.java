package eg.edu.alexu.csd.datastructure.stack;

import java.util.Scanner;

public class UIStack {
	public static void main(String[] args) {
		stackImplementation ourStack = new stackImplementation();
		Scanner input = new Scanner (System.in);
		while(true) {
			System.out.println("Please choose an action ");
			System.out.println("1-push ");
			System.out.println("2-pop ");
			System.out.println("3-peek");
			System.out.println("4-Get Size ");
			System.out.println("5-Check if empty ");
			System.out.println("6-Exit ");
			char choosen =input.next().charAt(0);
			switch(choosen)
			{
			case '1':
					System.out.print("Enter your Element  : ");
					Object x=input.next();
					ourStack.push(x);
					break;
			case '2': 
				try {
				Object pooped=ourStack.pop();
				System.out.println("your Element is : "+pooped);
				}catch(Exception e) {
					System.out.println("Stack is Empty");
				}
				break;
			case '3':
				try {
				Object peeked=ourStack.peek();
				System.out.println("your Element is : "+peeked);
				}catch(Exception e) {
					System.out.println("Stack is Empty");
				}
				break;
			case '4':
				System.out.println("Stack Size  is : "+ourStack.size());
				break;
			case '5':
				boolean indicator=ourStack.isEmpty();
				if(indicator==true) System.out.println("Stack is Empty");
				else System.out.println("Stack is't Empty");
				break;
			case '6': 
				return;
			default:
				System.out.println("Please Enter correct input");
			}
			
				
		}
	}
}
