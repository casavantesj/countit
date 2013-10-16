package ie.alvarop.sample.count.test;

import ie.alvarop.sample.count.CountIt;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CountTest {

	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		CountIt countit = new CountIt();
		String result = countit.resolve("[]");
		assert result.equalsIgnoreCase("YES");
		result = countit.resolve("[5,?]");
		assert result.equalsIgnoreCase("YES");
	}
	
	@Test
	public void test2() {
		CountIt countit = new CountIt();
		int commaPos = countit.getCommaPos("[5,?]", 0);
		assert commaPos == 2;
	}

	@Test
	public void test3() {
		CountIt countit = new CountIt();
		String reduc = countit.reduce("[5,?]");
		assert reduc.equalsIgnoreCase("10");
		reduc = countit.reduce("[6,?]");
		assert reduc.equalsIgnoreCase("12");
		reduc = countit.reduce("[?,6]");
		assert reduc.equalsIgnoreCase("12");
		reduc = countit.reduce("[?,?]");
		assert reduc.equalsIgnoreCase("??");
	}
}
