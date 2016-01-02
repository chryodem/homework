package JeopardyDay;

public class ComparingValues {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		int a = 93;
		int b = 47;
		int c = 1048;

		int smallest = 0;
		int middle = 0;
		int largest = 0;
		
		if (a<b && a<c){
			smallest =a;
			    if (b>a && b<c)
			    { middle = b;
			    largest =c;
			    }
			    else {
			        middle =c;
			        largest=b;
			    }
			    System.out.println(smallest + " " + middle + " " + largest);
			}

			if (b<a && b<c){
			    smallest =b;
			        if (a>b && a<c)
			        { middle = a;
			        largest =c;
			        }
			        else {
			            middle =c;
			            largest=a;
			        }
			        System.out.println(smallest + " " + middle + " " + largest);
			    }

			if (c<a && c<b){
			    smallest =c;
			        if (a>c && a<b)
			        { middle = a;
			        largest =b;
			        }
			        else {
			            middle =b;
			            largest=a;
			        }
			        System.out.println(smallest + " " + middle + " " + largest);
			    }


	}
}
