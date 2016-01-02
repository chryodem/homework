/** Circle.java
 *  @author: Koffman & Wolfgang
 *  Represents a circle.
 *  Implements ShapeInt.
 */
package AXC.drawapp;

import java.util.Scanner;

public class Circle extends Shape implements ShapeInt {

  // Data Fields
    private double radius;

  // Methods
  // Constructors
  public Circle() {
    super("circle");
  }

  public Circle(int rad) {
      super ("circle");
      radius = rad;
  }

  // Accessors:
  public double getRadius() {
    return radius;
  }

    // Mutator
    public void setRadius(double radius) {
	this.radius = radius;
    }

  @Override
    public double computeArea() {
    return Math.PI * radius * radius;
  }

  @Override
  public double computePerimeter() {
      return 2 * Math.PI * radius;
  }

  /** Read the parameters of the rectangle */
  public void readShapeData() {
      Scanner in = new Scanner(System.in);
      System.out.println("Enter the radius of the Circle");
    radius = in.nextDouble();
  }


  public String toString() {
    return super.toString() + ": radius is " + radius;
  }

}

