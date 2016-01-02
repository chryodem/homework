package ShapeHeirachy;

public class Rectangle extends Shape {
	
	public Rectangle (int width,int height, int x, int y){
		super(width , height , x, y);
		
	}
	public double findArea(){
		
		double Area = width*height;
		
		return Area;
		
	}


}
