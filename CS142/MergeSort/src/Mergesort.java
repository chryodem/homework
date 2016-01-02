import java.util.ArrayList;


public class Mergesort {
	public static void main(String[] args) {
		int [] unsorted = {9,5,2,6,3,9};
		int [] sorted = merge_sort(unsorted);
		for(int element : sorted){
			System.out.println(element + " ");
		}
		System.out.println();
	}
//	function merge_sort(m)
	public static int[] merge_sort(int[] unsorted){
//		if length(m) <=1
		if (unsorted.length <=1){
			return unsorted;
		}
//	    var integer middle = length(m) / 2
		int middle = unsorted.length/2;
		
//	    var list left, right, result
		int [] left = new int[middle];
		int [] right = new int[unsorted.length-middle];
		int [] result = new int[unsorted.length];
		
//	    for each x in m up to middle
//	         add x to left
		for(int i=0; i<middle; i++){
			left[i] = unsorted[i];
		}
//	    for each x in m after middle
//	         add x to right
		for(int i=middle; i<unsorted.length; i++){
			right[i-middle] = unsorted[i];
	}
		
		
	    left = merge_sort(left);
	    right = merge_sort(right);
		result = merge(left, right);
	    return result;
	}
//		function merge(left,right)
	public static int[] merge(int[] left, int[] right) {
		int[] result = new int[left.length + right.length];
		int left_idx = 0;
		int right_idx = 0;
		int result_idx = 0;
		
		for(int i=0; i<result.length; i++){
			if (right_idx == right.length){
				result[result_idx]= left[left_idx];
				left_idx++;
				result_idx++;
			}
			else if (left_idx == left.length){
				result[result_idx]= right[right_idx];
				right_idx++;
				result_idx++;
			}
			else if (left[left_idx]<right[right_idx]){
				result[result_idx]= left[left_idx];
				left_idx++;
				result_idx++;
			}
			else{result[result_idx]=right[right_idx];
				right_idx++;
				result_idx++;
				
			}
		}
		
		return result;
	}
		
		
}
