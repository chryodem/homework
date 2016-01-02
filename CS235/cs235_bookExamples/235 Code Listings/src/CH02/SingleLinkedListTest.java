package CH02;

public class SingleLinkedListTest {

	public static void main(String[] args) {
		SingleLinkedListTest me = new SingleLinkedListTest();
		me.run();
	}
	
	public void run() {
		test1();
		test2();
		test3();
		test4();
		test5();
		test6();
		test7();
		test8();
		test9();
		test10();
		test11();
		test12();
		test13();
		test14();
	}

    private SingleLinkedList testInstance;


    /** Test to see that the initial list is empty */
	public void test1() {
	SingleLinkedList testInstance = new SingleLinkedList();
	assert 0 == testInstance.size();
    }

    /** Test to see that the get method throws an
	exception on an empty list
    */
	public void test2() {
	try {
		SingleLinkedList testInstance = new SingleLinkedList();
		assert null == testInstance.get(0);
		assert false;
	} catch (IndexOutOfBoundsException ex) {
		assert true;
    }
	 }

    /** Test to see that add method inserts an item */
	public void test3() {
	SingleLinkedList<String> testInstance = new SingleLinkedList<String>();
	testInstance.add("tom");
	assert (1 ==testInstance.size());
	assert ("[tom]".equals(testInstance.toString()));
    }

    /** Test to see that add method inserts three items */
	public void test4() {
	SingleLinkedList<String> testInstance = new SingleLinkedList<String>();
	testInstance.add("tom");
	testInstance.add("dick");
	testInstance.add("harry");
	assert (3 == testInstance.size());
	assert ("[tom ==> dick ==> harry]".equals(testInstance.toString()));
    }

    /** Test the get method on a list of three items */
	public void test5() {
	SingleLinkedList<String> testInstance = new SingleLinkedList<String>();
	testInstance.add("tom");
	testInstance.add("dick");
	testInstance.add("harry");
	assert ("tom".equals(testInstance.get(0)));
	assert ("dick".equals(testInstance.get(1)));
	assert ("harry".equals(testInstance.get(2)));
    }

    /** Test the remove method on an empty list */
	public void test6() {
	SingleLinkedList<String> testInstance = new SingleLinkedList<String>();
	try {
			assert("tom".equals(testInstance.remove(0)));
			assert false;
   	 } catch (IndexOutOfBoundsException ex) {
	 		assert true;
		}
	}

    /** Test the remove method on a list with only one item */
	public void test7() {
	SingleLinkedList<String> testInstance = new SingleLinkedList<String>();
	testInstance.add("tom");
	assert("tom".equals(testInstance.remove(0)));
	assert(0 == testInstance.size());
    }

    /** Test the remove method by removing the first item */
	public void test8() {
	SingleLinkedList<String> testInstance = new SingleLinkedList<String>();
	testInstance.add("tom");
	testInstance.add("dick");
	testInstance.add("harry");
	assert("tom".equals(testInstance.remove(0)));
	assert(2 == testInstance.size());
	assert("[dick ==> harry]".equals(testInstance.toString()));
    }

    /** Test the remove method by removing the middle item */
	public void test9() {
	SingleLinkedList<String> testInstance = new SingleLinkedList<String>();
	testInstance.add("tom");
	testInstance.add("dick");
	testInstance.add("harry");
	String result = testInstance.remove(1);
	assert("dick".equals(result));
	assert(2 == testInstance.size());
	assert("[tom ==> harry]".equals(testInstance.toString()));
    }
    
    /** Test the remove method by removing the last item */
	public void test10() {
	SingleLinkedList<String> testInstance = new SingleLinkedList<String>();
	testInstance.add("tom");
	testInstance.add("dick");
	testInstance.add("harry");
	String result = testInstance.remove(2);
	assert("harry".equals(result));
	assert(2 == testInstance.size());
	assert("[tom ==> dick]".equals(testInstance.toString()));
    }

    /** Test the add method to see if it adds in the middle */
	public void test11() {
	SingleLinkedList<String> testInstance = new SingleLinkedList<String>();
	testInstance.add("tom");
	testInstance.add("dick");
	testInstance.add("harry");
	testInstance.add(1, "sam");
	assert(4 == testInstance.size());
	assert("[tom ==> sam ==> dick ==> harry]".equals(testInstance.toString()));
    }

    /** Test the add method to see if it adds in the middle */
	public void test12() {
	SingleLinkedList<String> testInstance = new SingleLinkedList<String>();
	testInstance.add("tom");
	testInstance.add("dick");
	testInstance.add("harry");
	testInstance.add(2, "sam");
	assert(4 == testInstance.size());
	assert("[tom ==> dick ==> sam ==> harry]".equals(testInstance.toString()));
    }

    /** Test the add method to see if it adds at the beginning */
	public void test13() {
	SingleLinkedList<String> testInstance = new SingleLinkedList<String>();
	testInstance.add("tom");
	testInstance.add("dick");
	testInstance.add("harry");
	testInstance.add(0, "sam");
	assert(4 == testInstance.size());
	assert("[sam ==> tom ==> dick ==> harry]".equals(testInstance.toString()));
    }
    /** Test the add method to see if it adds at the end */
	public void test14() {
	SingleLinkedList<String> testInstance = new SingleLinkedList<String>();
	testInstance.add("tom");
	testInstance.add("dick");
	testInstance.add("harry");
	testInstance.add(3, "sam");
	assert(4 == testInstance.size());
	assert("[tom ==> dick ==> harry ==> sam]".equals(testInstance.toString()));
    }

}

	

    
