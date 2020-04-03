package eg.edu.alexu.csd.datastructure.stack;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*stackImplementation obj=new stackImplementation();
		//System.out.println(obj.isEmpty());
		obj.push(5);
		obj.push(4);
		obj.push(3);
		obj.push(2);
		obj.pop();
		obj.pop();
		obj.peek();
		obj.push(5);
		obj.push(4);
		obj.push(3);
		obj.peek();
		obj.push(2);
		obj.peek();
		obj.pop();
		//System.out.println(obj.peek());
		//System.out.println(obj.size());
		 * 
		 */
		ExpressionImplementation obj2= new ExpressionImplementation();
		String test="  +5+-3";
		System.out.println(obj2.EditExpression(test));
		//String test="12+2*4/5-7";
		//String test="3 8 2 /+5 -";
		String result=obj2.infixToPostfix(test);
		System.out.println(result);
		System.out.println(obj2.evaluate(result));
		//System.out.println(test);

	}

}
