package CH02;
import org.junit.*;
import static org.junit.Assert.*;

public class TestSingleLinkedList {

    private SingleLinkedList testInstance;


    /** Test to see that the initial list is empty */
    @Test
	public void test1() {
	SingleLinkedList testInstance = new SingleLinkedList();
	assertEquals(0, testInstance.size());
    }

    /** Test to see that the get method throws an
	exception on an empty list
    */
    @Test(expected = IndexOutOfBoundsException.class)
	public void test2() {
	SingleLinkedList testInstance = new SingleLinkedList();
	assertNull(testInstance.get(0));
    }

    /** Test to see that add method inserts an item */
    @Test
	public void test3() {
	SingleLinkedList<String> testInstance = new SingleLinkedList<String>();
	testInstance.add("tom");
	assertEquals(1, testInstance.size());
	assertEquals("[tom]", testInstance.toString());
    }

    /** Test to see that add method inserts three items */
    @Test
	public void test4() {
	SingleLinkedList<String> testInstance = new SingleLinkedList<String>();
	testInstance.add("tom");
	testInstance.add("dick");
	testInstance.add("harry");
	assertEquals(3, testInstance.size());
	assertEquals("[tom ==> dick ==> harry]", testInstance.toString());
    }

    /** Test the get method on a list of three items */
    @Test
	public void test5() {
	SingleLinkedList<String> testInstance = new SingleLinkedList<String>();
	testInstance.add("tom");
	testInstance.add("dick");
	testInstance.add("harry");
	assertEquals("tom", testInstance.get(0));
	assertEquals("dick", testInstance.get(1));
	assertEquals("harry", testInstance.get(2));
    }

    /** Test the remove method on an empty list */
    @Test(expected = IndexOutOfBoundsException.class)
	public void test6() {
	SingleLinkedList<String> testInstance = new SingleLinkedList<String>();
	assertEquals("tom", testInstance.remove(0));
    }

    /** Test the remove method on a list with only one item */
    @Test
	public void test7() {
	SingleLinkedList<String> testInstance = new SingleLinkedList<String>();
	testInstance.add("tom");
	assertEquals("tom", testInstance.remove(0));
	assertEquals(0, testInstance.size());
    }

    /** Test the remove method by removing the first item */
    @Test
	public void test8() {
	SingleLinkedList<String> testInstance = new SingleLinkedList<String>();
	testInstance.add("tom");
	testInstance.add("dick");
	testInstance.add("harry");
	assertEquals("tom", testInstance.remove(0));
	assertEquals(2, testInstance.size());
	assertEquals("[dick ==> harry]", testInstance.toString());
    }

    /** Test the remove method by removing the middle item */
    @Test
	public void test9() {
	SingleLinkedList<String> testInstance = new SingleLinkedList<String>();
	testInstance.add("tom");
	testInstance.add("dick");
	testInstance.add("harry");
	assertEquals("dick", testInstance.remove(1));
	assertEquals(2, testInstance.size());
	assertEquals("[tom ==> harry]", testInstance.toString());
    }
    
    /** Test the remove method by removing the last item */
    @Test
	public void test10() {
	SingleLinkedList<String> testInstance = new SingleLinkedList<String>();
	testInstance.add("tom");
	testInstance.add("dick");
	testInstance.add("harry");
	assertEquals("harry", testInstance.remove(2));
	assertEquals(2, testInstance.size());
	assertEquals("[tom ==> dick]", testInstance.toString());
    }

    /** Test the add method to see if it adds in the middle */
    @Test
	public void test11() {
	SingleLinkedList<String> testInstance = new SingleLinkedList<String>();
	testInstance.add("tom");
	testInstance.add("dick");
	testInstance.add("harry");
	testInstance.add(1, "sam");
	assertEquals(4, testInstance.size());
	assertEquals("[tom ==> sam ==> dick ==> harry]", 
		     testInstance.toString());
    }

    /** Test the add method to see if it adds in the middle */
    @Test
	public void test12() {
	SingleLinkedList<String> testInstance = new SingleLinkedList<String>();
	testInstance.add("tom");
	testInstance.add("dick");
	testInstance.add("harry");
	testInstance.add(2, "sam");
	assertEquals(4, testInstance.size());
	assertEquals("[tom ==> dick ==> sam ==> harry]", 
		     testInstance.toString());
    }

    /** Test the add method to see if it adds at the beginning */
    @Test
	public void test13() {
	SingleLinkedList<String> testInstance = new SingleLinkedList<String>();
	testInstance.add("tom");
	testInstance.add("dick");
	testInstance.add("harry");
	testInstance.add(0, "sam");
	assertEquals(4, testInstance.size());
	assertEquals("[sam ==> tom ==> dick ==> harry]", 
		     testInstance.toString());
    }
    /** Test the add method to see if it adds at the end */
    @Test
	public void test14() {
	SingleLinkedList<String> testInstance = new SingleLinkedList<String>();
	testInstance.add("tom");
	testInstance.add("dick");
	testInstance.add("harry");
	testInstance.add(3, "sam");
	assertEquals(4, testInstance.size());
	assertEquals("[tom ==> dick ==> harry ==> sam]", 
		     testInstance.toString());
    }

    /** Test the remove(item) method on an empty list */
    @Test
	public void test15() {
	SingleLinkedList<String> testInstance = new SingleLinkedList<String>();
	assertFalse(testInstance.remove("tom"));
    }

    /** Test the remove(item) method on a list with one item */
    @Test
	public void test16() {
	SingleLinkedList<String> testInstance = new SingleLinkedList<String>();
	testInstance.add("tom");
	assertTrue(testInstance.remove("tom"));
	assertEquals(0, testInstance.size());
    }

    /** Test the remove(item) from the first item of a list */
    @Test
	public void test17() {
	SingleLinkedList<String> testInstance = new SingleLinkedList<String>();
	testInstance.add("tom");
	testInstance.add("dick");
	testInstance.add("harry");
	testInstance.add("sam");
	assertTrue(testInstance.remove("tom"));
	assertEquals(3, testInstance.size());
	assertEquals("[dick ==> harry ==> sam]", testInstance.toString());
    }

    /** Test the remove(item) from the second item of a list */
    @Test
	public void test18() {
	SingleLinkedList<String> testInstance = new SingleLinkedList<String>();
	testInstance.add("tom");
	testInstance.add("dick");
	testInstance.add("harry");
	testInstance.add("sam");
	assertTrue(testInstance.remove("dick"));
	assertEquals(3, testInstance.size());
	assertEquals("[tom ==> harry ==> sam]", testInstance.toString());
    }

    /** Test the remove(item) from the third item of a list */
    @Test
	public void test19() {
	SingleLinkedList<String> testInstance = new SingleLinkedList<String>();
	testInstance.add("tom");
	testInstance.add("dick");
	testInstance.add("harry");
	testInstance.add("sam");
	assertTrue(testInstance.remove("harry"));
	assertEquals(3, testInstance.size());
	assertEquals("[tom ==> dick ==> sam]", testInstance.toString());
    }

    /** Test the remove(item) from the last item of a list */
    @Test
	public void test20() {
	SingleLinkedList<String> testInstance = new SingleLinkedList<String>();
	testInstance.add("tom");
	testInstance.add("dick");
	testInstance.add("harry");
	testInstance.add("sam");
	assertTrue(testInstance.remove("sam"));
	assertEquals(3, testInstance.size());
	assertEquals("[tom ==> dick ==> harry]", testInstance.toString());
    }

    /** Test the add2 method to see if it adds in the middle */
    @Test
	public void test21() {
	SingleLinkedList<String> testInstance = new SingleLinkedList<String>();
	testInstance.add("tom");
	testInstance.add("dick");
	testInstance.add("harry");
	testInstance.add2(1, "sam");
	assertEquals(4, testInstance.size());
	assertEquals("[tom ==> sam ==> dick ==> harry]", 
		     testInstance.toString());
    }

    /** Test the add2 method to see if it adds in the middle */
    @Test
	public void test22() {
	SingleLinkedList<String> testInstance = new SingleLinkedList<String>();
	testInstance.add("tom");
	testInstance.add("dick");
	testInstance.add("harry");
	testInstance.add2(2, "sam");
	assertEquals(4, testInstance.size());
	assertEquals("[tom ==> dick ==> sam ==> harry]", 
		     testInstance.toString());
    }

    /** Test the add2 method to see if it adds at the beginning */
    @Test
	public void test23() {
	SingleLinkedList<String> testInstance = new SingleLinkedList<String>();
	testInstance.add("tom");
	testInstance.add("dick");
	testInstance.add("harry");
	testInstance.add2(0, "sam");
	assertEquals(4, testInstance.size());
	assertEquals("[sam ==> tom ==> dick ==> harry]", 
		     testInstance.toString());
    }

    /** Test the add2 method to see if it adds at the end */
    @Test
	public void test24() {
	SingleLinkedList<String> testInstance = new SingleLinkedList<String>();
	testInstance.add("tom");
	testInstance.add("dick");
	testInstance.add("harry");
	testInstance.add2(3, "sam");
	assertEquals(4, testInstance.size());
	assertEquals("[tom ==> dick ==> harry ==> sam]", 
		     testInstance.toString());
    }

    /** Test the add2 method to see if throws an exception for -1 */
    @Test(expected=java.lang.IndexOutOfBoundsException.class)
	public void test25() {
	SingleLinkedList<String> testInstance = new SingleLinkedList<String>();
	testInstance.add("tom");
	testInstance.add("dick");
	testInstance.add("harry");
	testInstance.add2(-1, "sam");
	assertEquals(4, testInstance.size());
	assertEquals("[tom ==> dick ==> harry ==> sam]", 
		     testInstance.toString());
    }

    /** Test the add2 method to see if it throws an exception for 4 */
    @Test(expected=java.lang.IndexOutOfBoundsException.class)
	public void test26() {
	SingleLinkedList<String> testInstance = new SingleLinkedList<String>();
	testInstance.add("tom");
	testInstance.add("dick");
	testInstance.add("harry");
	testInstance.add2(4, "sam");
	assertEquals(4, testInstance.size());
	assertEquals("[tom ==> dick ==> harry ==> sam]", 
		     testInstance.toString());
    }

}


    
