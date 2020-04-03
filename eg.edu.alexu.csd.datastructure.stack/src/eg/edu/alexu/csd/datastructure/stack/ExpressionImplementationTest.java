package eg.edu.alexu.csd.datastructure.stack;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ExpressionImplementationTest {

	@Test
	void test() {
		ExpressionImplementation obj =new ExpressionImplementation();
		String postfix =obj.infixToPostfix("2 + 3 * 4 ");
		assertEquals("2 3 4 * +", postfix);
		int evaluate =obj.evaluate(postfix);
		
		//System.out.print(evaluate);
		assertEquals(evaluate,14);
		}
	
	@Test
	void test1() {
		ExpressionImplementation obj =new ExpressionImplementation();
		//String postfix =obj.infixToPostfix("2 + 3 ** 4 ");
		//System.out.print(postfix);
		assertThrows(RuntimeException.class,() ->obj.infixToPostfix("2 + 3 *** 4 "),"Wrong expression");
		assertThrows(RuntimeException.class,() ->obj.evaluate("2 3 + 4 ***"),"Wrong expression");
		
		}
	
	@Test
	void test2() {
		ExpressionImplementation obj =new ExpressionImplementation();
		String postfix =obj.infixToPostfix("(5+3)*4+5-5+7-(-7)");
		assertEquals("5 3 + 4 * 5 + 5 - 7 + 0 7 - -", postfix);
		int evaluate =obj.evaluate(postfix);
		
		//System.out.print(evaluate);
		assertEquals(evaluate,46);
		
		}
	@Test
	void test3() {
		ExpressionImplementation obj =new ExpressionImplementation();
		String postfix =obj.infixToPostfix("(5+3)*4+5/0-5+7-(-7)");
		
		assertEquals("5 3 + 4 * 5 0 / + 5 - 7 + 0 7 - -", postfix);
		
		assertThrows(RuntimeException.class,() ->obj.evaluate(postfix),"Wrong expression");
		
		
		}
	@Test
	void test4() {
		ExpressionImplementation obj =new ExpressionImplementation();
		String postfix =obj.infixToPostfix("(5+3)*4+5-5+7-(-7)--7");
		
		assertEquals("5 3 + 4 * 5 + 5 - 7 + 0 7 - - 0 7 - -", postfix);
		int evaluate=obj.evaluate(postfix);
		assertEquals(evaluate,53);
		}
	
	@Test
	void test5() {
		ExpressionImplementation obj =new ExpressionImplementation();
		String postfix =obj.infixToPostfix("7--7-(-7)++8--9");
		
		assertEquals("7 0 7 - - 0 7 - - 0 8 + + 0 9 - -", postfix);
		int evaluate=obj.evaluate(postfix);
		
		assertEquals(evaluate,38);
		}
	
	@Test
	void test6() {
		ExpressionImplementation obj =new ExpressionImplementation();
		assertThrows(RuntimeException.class,() ->obj.infixToPostfix("$"),"Wrong expression");
		assertThrows(RuntimeException.class,() ->obj.evaluate("A*b*c"),"Wrong expression");
		
		}
	
	@Test
	void test7() {
		ExpressionImplementation obj =new ExpressionImplementation();
		assertThrows(RuntimeException.class,() ->obj.infixToPostfix(""),"Wrong expression");
		assertThrows(RuntimeException.class,() ->obj.evaluate("15.5"),"Wrong expression");
		
		}
	@Test
	void test8() {
		ExpressionImplementation obj =new ExpressionImplementation();
		String postfix =obj.infixToPostfix("(50 + 30)*4+5-5+7-(-741)");
		assertEquals("50 30 + 4 * 5 + 5 - 7 + 0 741 - -", postfix);
		
		int evaluate =obj.evaluate(postfix);
		
		//System.out.print(evaluate);
		assertEquals(evaluate,1068);
		
		}
	
	@Test
	void infixToPostfixTest() {
		ExpressionImplementation obj =new ExpressionImplementation();
		assertEquals("v b n - d + / r a - b / +",obj.infixToPostfix("(v/(b-n+d))+(r-a)/b"));
		assertEquals("70 0 3 7 - 0 9 5 + 5 4 - - - + - /",obj.infixToPostfix("(70/-(3-7+-(9+5-(5-4))))"));
		assertEquals("2 1 - 55 8 + + 9 + 8 8 * -",obj.infixToPostfix("((2-1+(55+8))+9-(8*8))"));
		assertEquals("a b *",obj.infixToPostfix("a*b"));
		////  Iportant tests
		assertEquals("0 5 3 + -",obj.infixToPostfix("-(5+3)"));
		assertEquals("0 5 3 + +",obj.infixToPostfix("+(5+3)"));
		assertEquals("0 5 0 3 - + +",obj.infixToPostfix("    +(  5+-3)"));
					 
		////////some weong expression
		
		assertThrows(RuntimeException.class,()-> {obj.infixToPostfix( "5***5" );});
		assertThrows(RuntimeException.class,()-> {obj.infixToPostfix( "5*/+5" );});
		assertThrows(RuntimeException.class,()-> {obj.infixToPostfix( "" );});
		assertThrows(RuntimeException.class,()-> {obj.infixToPostfix( "()()" );});
		assertThrows(RuntimeException.class,()-> {obj.infixToPostfix( "5+8!" );});
		assertThrows(RuntimeException.class,()-> {obj.infixToPostfix( "(5*" );});
		
	}
	@Test
	void evaluatePostfixTest() {
		ExpressionImplementation obj =new ExpressionImplementation();
		assertThrows(RuntimeException.class,()-> {obj.evaluate("v b n - d + / r a - b / +");});
		assertEquals(4,obj.evaluate("70 0 3 7 - 0 9 5 + 5 4 - - - + - /"));
									
		assertEquals(9,obj.evaluate("2 1 - 55 8 + + 9 + 8 8 * -"));
		
		assertEquals(-8,obj.evaluate("0 5 3 + -"));
		assertEquals(2,obj.evaluate("0 5 0 3 - + +"));
		
		///// wrong expression
		assertThrows(RuntimeException.class,()-> {obj.evaluate("a*b*c");});
		assertThrows(RuntimeException.class,()-> {obj.evaluate("10 0 /");});
		assertThrows(RuntimeException.class,()-> {obj.evaluate("5 0 / 3 +");});
		assertThrows(RuntimeException.class,()-> {obj.evaluate("5 10 + -");});
		assertThrows(RuntimeException.class,()-> {obj.evaluate("5+4++");});
		
		
	}
	
		

}
