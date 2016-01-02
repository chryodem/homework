import java.util.ArrayList;

public class QSTest implements QSTestInterface {

	// int[] test1 = {45,0,1,23,45,6,7,8,9,10};
	// int[] test2 = {106,5,71,2,6,9,2,19};
	// int[] test3 = {101,51,5};
	// int[] test4 = {};
	//

	@Override
	public boolean testSort(QSInterface test) {

		try {
			boolean compare = false;
			int[] test1 = { 47, 0, 1, 23, 45, 6, 7 };
			int[] correctTest1 = { 0, 1, 6, 7, 23, 45, 47 };
			int[] test4 = null;
			int[] correctTest4 = null;

			test.sort(test1);
			compare = compareArray(test1, correctTest1);

			if (compare == false) {
				return false;
			}
			test.sort(test4);

			if (test4 != correctTest4) {
				return false;
			}
		} catch (Exception e) {
			return false;
		}

		return true;

	}

	private boolean compareArray(int[] test, int[] correctTest) {
		

		for (int i = 0; i < test.length; i++) {
			if (test[i] != correctTest[i]) {
				return false;
			}
		}
		if (test.length != correctTest.length) {
			return false;
		}

		return true;
	}

	@Override
	public boolean testMedianOfThree(QSInterface test) {
		int[] test1 = { 45, 0, 1, 23, 45, 6, 7, 8, 9, 10 };
		int[] correctTest1 = { 0, 1, 45, 23, 45, 6, 7, 8, 9, 10 };
		int[] test2 = { 3, 2, 1 };
		int[] CorrectTest2 = { 1, 2, 3 };
		int[] test3 = { 2, 1 };
		int[] CorrectTest3 = { 1, 2 };
		int[] test4 = null;
		int[] test5 = { 4, 3, 2, 1 };
		int[] correctTest5 = { 4, 3, 2, 1 };
		int[] test6 = { 4, 3, 2, 1 };
		int[] correctTest6 = { 4, 3, 2, 1 };
		int[] test7 = { 4, 3, 2, 1 };
		int[] correctTest7 = { 4, 3, 2, 1 };

		try {

			// testing correct array
			test.medianOfThree(test1, 0, 2);
			if (compareArray(test1, correctTest1) == false) {
				return false;
			}
			// testing correct array
			test.medianOfThree(test2, 0, 2);
			if (compareArray(test2, CorrectTest2) == false) {
				return false;
			}
			// testing array of 2 values
			test.medianOfThree(test3, 0, 1);
			if (compareArray(test3, CorrectTest3) == false) {
				return false;
			}
			// testing the null
			test.medianOfThree(test4, 0, 2);
			if(test4!=null){
				return false;
			}
			// testing out of bounds on the left
			test.medianOfThree(test5, -2, 1);
			if (compareArray(test5, correctTest5) == false) {
				
				return false;
			}
			// testing out of bounds on the right
			test.medianOfThree(test6, 1, 13);
			if (compareArray(test6, correctTest6) == false) {
				return false;
			}
			// testing left value is more than the right value
			test.medianOfThree(test7, 3, 1);
			if (compareArray(test7, correctTest7) == false) {
				return false;
			}

		} // end of try
		catch (Exception e) {
			//System.out.println("returning false here!------------------");
			return false;
		}

		return true;
	}

	@Override
	public boolean testPartition(QSInterface test) {

		int[] test1 = { 45, 0, 1, 23, 47, 6 };
		int[] correctTest1 = { 0, 6, 1, 23, 45, 47 };
		int[] test2 = { 3, 2, 1 };
		int[] CorrectTest2 = { 1, 2, 3 };
		int[] test3 = { 2, 1 };
		int[] CorrectTest3 = { 2, 1 };
		int[] test4 = null;
		int[] test5 = { 4, 3, 2, 1 };
		int[] CorrectTest5 = { 4, 3, 2, 1 };
		int[] test6 = { 4, 3, 2, 1 };
		int[] CorrectTest6 = { 4, 3, 2, 1 };
		int[] test7 = { 4, 3};
		int[] correctTest7 = { 3,4};
		int[] test8 = { 4};
		int[] CorrectTest8 = {4};
		int[] test9 = {1,2,4};
		int[] Correcttest9 = {1,2,4};
		
		


		
		try{
		int testing1=test.partition(test1, 0, 5);
		if(testing1!=2){
			return false;
		}
		//System.out.println(testing1);
		for (int i = 6/2; i >= 0; i--) {
			if (test1[i] > 23) {
				return false;
			}
		}
		for (int i = (6 / 2); i < correctTest1.length; i++) {
			if (test1[i] < 23) {
				return false;
			}
		}
				test.partition(test2, 0, 2);// normal testing
		
		if (compareArray(test2, CorrectTest2) == false) {
			return false;
		}
		int testing3 = test.partition(test3, -4, 1); // left side out of bounds
		
		if (testing3 != -1) {
			return false;
		}
		if (compareArray(test3, CorrectTest3) == false) {
			return false;
		}
		int testing4 = test.partition(test4, 0, 2); // testing null
		if (testing4 != -1) {
			return false;
		}
		if(test4!=null){
			return false;
		}
		
		int testing5 = test.partition(test5, 0, 7);//right side out of bounds
		if (testing5 != -1) {
			return false;
		}
		
		if (compareArray(test5, CorrectTest5) == false) {
			return false;
		}
		int testing6 = test.partition(test6, 5, 2); //left side larger than the right side 
		if(testing6!=-1){
			return false;
		}
		if (compareArray(test6, CorrectTest6) == false) {
			return false;
		}
		//testing two values
		int testing7 = test.partition(test7, 0, 1);
		if(testing7!=0){
			return false;
		}
		if(compareArray(test7,correctTest7)==false){
			return false;
		}
		//testing one value
		int testing8 =test.partition(test8, 0, 0);
		if(testing8!=-1){
			return false;
		}
		
		if(compareArray(test8,CorrectTest8)==false){
			return false;
		}
		
		int testing9 = test.partition(test9, 0, 2);
		if(testing9==-1){
			return false;
		}
		
		if(compareArray(test9, Correcttest9)==false ){
			return false;
		}
		
		
		}
		catch(Exception e){
			return false;
		}

		return true;
	}

	@Override
	public boolean testSwap(QSInterface test) {
		
		int[] test1 = { 45, 0, 1, 23, 47, 6 };
		int[] correctTest1 = {0, 45, 1, 23, 47, 6 };
		int[] test2 = { 3, 2, 1 };
		int[] CorrectTest2 = { 1, 2, 3 };
		int[] test3 = { 2, 1 };
		int[] CorrectTest3 = { 1, 2 };
		int[] test4 = null;
		int[] test5 = { 4, 3, 2, 1 };
		int[] CorrectTest5 = { 4,3 , 2, 1 };
		int[] test6 = { 4, 3, 2, 1 };
		int[] CorrectTest6 = { 4, 3, 2, 1 };
		int[] test7 = { 4, 3, 2, 1 };
		int[] correctTest7 = { 4, 3, 2, 1 };
		
		try{
			//testing normal array
			test.swap(test1, 0, 1);
			if (compareArray(test1, correctTest1) == false) {
				return false;
			}
			//testing null
			test.swap(test4, 0, 4);
			if(test4!=null){
				return false;
			}
			test.medianOfThree(test5, -2, 1);
			if (compareArray(test5, CorrectTest5) == false) {
				return false;
			}
			// testing out of bounds on the right
			test.medianOfThree(test6, 1, 13);
			if (compareArray(test6, CorrectTest6) == false) {
				return false;
			}
			
			
		}
		catch(Exception e){
			return false;
		}

		return true;
	}

}
