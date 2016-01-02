/*<exercise chapter="C" section="7" type="programming" number="2">*/
package AXC.drawapp;

import java.util.Scanner;

/**
 * Class to represent a rombus (diamond) enclosed within a rectangle.
 * The points of the rombus are the mid-points of the rectangle.
 * @author Paul Wolfgang
 */
public class Rombus extends Shape implements ShapeInt {
    
    // Data Fields
    private int width;
    private int height;

    // Methods
    // Constructors
    public Rombus() {
        super("Rombus");
    }

    public Rombus(int wid, int hei) {
        super("Rombus");
        width = wid;
        height = hei;
    }

    // Accessors:
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public double computeArea() {
        return width * height / 2.0;
    }

    public double computePerimeter() {
        double halfWidth = (double)width/2.0;
        double halfHeight = (double)height/2.0;
        double side = Math.sqrt(halfWidth*halfWidth + halfHeight*halfHeight);
        return 4*side;
    }

    // Mutators
    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    /** Read the parameters of the rectangle */
    public void readShapeData() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the width of the bounding Rectangle:");
        width = scan.nextInt();
        System.out.println("Enter the height of the bounding Rectangle:");
        height = scan.nextInt();
    }

    @Override
    public String toString() {
        return super.toString() + ": width is " 
                + width + ", height is " + height;
    }
}
/*</exercise>*/
