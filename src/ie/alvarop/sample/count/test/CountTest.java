package ie.alvarop.sample.count.test;

import static org.junit.Assert.assertTrue;
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
		assertTrue(result.equalsIgnoreCase("YES"));
		result = countit.resolve("[5,?]");
		assertTrue(result.equalsIgnoreCase("YES"));
		result = countit.resolve("[[5,?],?]");
		assertTrue(result.equalsIgnoreCase("10"));
	}
	
	@Test
	public void testCommaPos() {
		CountIt countit = new CountIt();
		int commaPos = countit.getCommaPos("[5,?]", 0);
		assertTrue(commaPos == 2);
	}

	@Test
	public void testReduce() {
		CountIt countit = new CountIt();
		String reduc = countit.reduce("[5,?]");
		assertTrue(reduc.equalsIgnoreCase("10"));
		reduc = countit.reduce("[6,?]");
		assertTrue(reduc.equalsIgnoreCase("12"));
		reduc = countit.reduce("[?,6]");
		assertTrue(reduc.equalsIgnoreCase("12"));
		reduc = countit.reduce("[?,?]");
		assertTrue(reduc.equalsIgnoreCase("??"));
		reduc = countit.reduce("[??,?]");
		assertTrue(reduc.equalsIgnoreCase("????"));
		reduc = countit.reduce("[?,??]");
		assertTrue(reduc.equalsIgnoreCase("????"));
		reduc = countit.reduce("[??,10]");
		assertTrue(reduc.equalsIgnoreCase("20"));
		reduc = countit.reduce("[10,??]");
		assertTrue(reduc.equalsIgnoreCase("20"));
		reduc = countit.reduce("[????,10]");
		assertTrue(reduc.equalsIgnoreCase("NO"));		
	}
	
	@Test
	public void testRange() {
		CountIt countit = new CountIt();
		String range = countit.range("[5,?]", 2);
		assertTrue(range.equalsIgnoreCase("[5,?]"));
		range = countit.range("[[5,?],?]", 3);
		assertTrue(range.equalsIgnoreCase("[5,?]"));
		range = countit.range("[[5,?],?]", 6);
		assertTrue(range == null);
	}
}
