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
		String result = countit.resolve("[5,?]");
		assertTrue(result.equalsIgnoreCase("YES"));
		result = countit.resolve("[[5,?],?]");
		assertTrue(result.equalsIgnoreCase("YES"));
		result = countit.resolve("5");
		assertTrue(result.equalsIgnoreCase("NO"));
		result = countit.resolve("[3,?]");
		assertTrue(result.equalsIgnoreCase("YES"));
		result = countit.resolve("[?,[?,?]]");
		assertTrue(result.equalsIgnoreCase("NO"));
		result = countit.resolve("[[[?,?],5],?]");
		assertTrue(result.equalsIgnoreCase("NO"));
		result = countit.resolve("[2,[3,?]]");
		assertTrue(result.equalsIgnoreCase("NO"));
		result = countit.resolve("[[[5,?],10],[?,?]]");
		assertTrue(result.equalsIgnoreCase("YES"));
	}
	
	@Test
	public void testCommaPos() {
		CountIt countit = new CountIt();
		int commaPos = countit.getCommaPos("[5,?]", 0);
		assertTrue(commaPos == 2);
		commaPos = countit.getCommaPos("[[5,?],6]", 3);
		assertTrue("Received value " + commaPos, commaPos == 6);
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
		reduc = countit.reduce("[20,10]");
		assertTrue(reduc.equalsIgnoreCase("NO"));
		reduc = countit.reduce("[10,????]");
		assertTrue(reduc.equalsIgnoreCase("NO"));
		reduc = countit.reduce("[????,10]");
		assertTrue(reduc.equalsIgnoreCase("NO"));
		reduc = countit.reduce("[????,20]");
		assertTrue(reduc.equalsIgnoreCase("40"));
	}
	
	@Test
	public void testRange() {
		CountIt countit = new CountIt();
		String range = countit.range("[5,?]", 2);
		assertTrue("Received range was " + range, range.equalsIgnoreCase("[5,?]"));
		range = countit.range("[[5,?],?]", 3);
		assertTrue(range.equalsIgnoreCase("[5,?]"));
		range = countit.range("[[5,?],?]", 6);
		assertTrue(range == null);
		range = countit.range("[[10,?],?]", 3);
		assertTrue(range.equalsIgnoreCase("[10,?]"));
		range = countit.range("[[10,???],?]", 3);
		assertTrue("Received range was " + range, range.equalsIgnoreCase("[10,???]"));
	}
	
	@Test
	public void testNextRange() {
		CountIt countit = new CountIt();
		String range = countit.nextRange("[5,?]", 0);
		assertTrue("Received range was ", range.equalsIgnoreCase("[5,?]"));
		range = countit.nextRange("[[5,?],?]", 0);
		assertTrue("Received range was ", range.equalsIgnoreCase("[5,?]"));
		range = countit.nextRange("[[5,?],?]", 3);
		assertTrue("Received range was ", range == null);
	}
	
	@Test
	public void testReplace() {
		CountIt countit = new CountIt();
		String replaced = countit.replace("[[5,?],?]", "[5,?]", "10");
		assertTrue("Received replaced " + replaced, replaced.equalsIgnoreCase("[10,?]"));
		replaced = countit.replace("[[?,[5,?]],?]", "[5,?]", "10");
		assertTrue("Received replaced " + replaced, replaced.equalsIgnoreCase("[[?,10],?]"));
	}
	
	@Test
	public void testOneRun() {
		CountIt countit = new CountIt();
		String result = countit.oneRun("[[5,?],?]");
		assertTrue("Received resilt " + result, result.equalsIgnoreCase("[10,?]"));
		result = countit.oneRun("[10,?]");
		assertTrue("Received resilt " + result, result.equalsIgnoreCase("YES"));
		result = countit.oneRun("[[5,2],?]");
		assertTrue("Received resilt " + result, result.equalsIgnoreCase("NO"));
	}
}
