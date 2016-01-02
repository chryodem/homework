package ShapeHeirachy;

public class Oval extends Shape {
	
	public Oval (int width,int height, int x, int y){
		super(width , height , x, y);
		
	}
	public double findArea(){
		
		double Area = width*height*Math.PI;
		
		return Area;
		
	}

}
