
public class Student {
	
	private String name;
	
	//constructor
	//called when we say "new Student ();"
	
//	public Student (){
//		
//		name = "Brian";
//		
//	}
	
	
	public Student(String name){
		
		this.name = name;
		//this makes
	}
	
	
	//will be called  (or invoked)
	
	//when we say student1.printName();
	
	public void printName() {
		System.out.println(name);
		
		
	}
	//this is a getter, getting info from some class
	public String getName(){
		//camelcase, getter
		
		
		
		return name;
		
	}
	//this is a setter, sets name, gives info to a class
	public void setName(String name){
		this.name= name;
		
		
		
		
	}



}
