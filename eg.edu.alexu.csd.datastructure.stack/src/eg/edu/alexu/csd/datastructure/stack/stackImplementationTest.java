package eg.edu.alexu.csd.datastructure.stack;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class stackImplementationTest {

	@Test
	void test() {
		stackImplementation obj=new stackImplementation();
		assertEquals(obj.isEmpty(),true);
		assertEquals(obj.size(),0);
		obj.push("Abdo");
		obj.push("Sakr");
		obj.push(100);
		assertEquals(obj.peek(),100);
		obj.push(150);
		assertEquals(obj.size(),4);
		assertEquals(obj.isEmpty(),false);
		assertEquals(obj.pop(),150);
		assertEquals(obj.size(),3);
		obj.push(900);
		
		assertEquals(obj.peek(),900);
		assertEquals(obj.size(),4);
		assertEquals(obj.isEmpty(),false);
	}

}
