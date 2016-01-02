package ShapeHeirachy;

import java.util.Random;

public abstract class Shape {
	protected int width;
	protected int height;
	protected int x;
	protected int y;
	
	public Shape(int w, int h, int x, int y){
		
		width = w;
		height = h;
		x = this.x;
		y = this.y;
	}
	public Shape(){
		Random rand = new Random();
		
		width = rand.nextInt(191)+10;
		height = rand.nextInt(191)+10;
		x = rand.nextInt(501);
		y = rand.nextInt(501);	
		
	}
	public void Translate(int x, int y){
		x = this.x;
		y = this.y;
	}
	public abstract double findArea();//{
		//double Area = (width)
		
	//	return height;
		
		
	//}
	public int getWidth(){
		return width;
	}
	public int getHeight(){
		return height;
		}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}

}
