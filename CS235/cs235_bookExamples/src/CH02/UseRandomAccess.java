package CH02;

import java.util.RandomAccess;
import java.util.List;
import java.util.ArrayList;
import java.util.ListIterator;


public class UseRandomAccess {

    public static <E> void someMethod(List<E> aList) {
	List<E> workingCopy = aList;
	if (!(aList instanceof RandomAccess)) {
	    workingCopy = new ArrayList<E>(aList);
	}
	// Perform random access work on working copy
	if (!(aList instanceof RandomAccess)) {
	    ListIterator<E> itr = aList.listIterator();
	    for (int i = 0; i < aList.size(); i++) {
		itr.next();
		itr.set(workingCopy.get(i));
	    }
	}
    }
}

