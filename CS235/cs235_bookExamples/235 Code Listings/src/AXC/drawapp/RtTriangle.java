/** RtTriangle.java        Authors: Koffman & Wolfgang 
 *  Represents a right triangle.
 *  Implements ShapeInt.
 */
package AXC.drawapp;

import javax.swing.*;

public class RtTriangle implements ShapeInt {

  // Data Fields
  private int base;
  private int height;

  // Methods
  // Constructors
  public RtTriangle() {}

  public RtTriangle(int bas, int hei) {
    base = bas;
    height = hei;
  }

  // Accessors:
  public int getBase() {
    return base;
  }

  public int getHeight() {
    return height;
  }

  public double computeArea() {
    return 0.5* height * base;
  }

  public double computePerimeter() {
      double hypotenuse = Math.sqrt(height*height + base*base);
      return hypotenuse + base + height;
  }

    // Mutators
    public void setBase(int base) {
	this.base = base;
    }

    public void setHeight(int height) {
	this.height = height;
    }

  /** Read the parameters of the right triangle */
  public void readShapeData() {
    String baseStr = JOptionPane.showInputDialog(
            "Enter the base of the Right Triangle");
    base = Integer.parseInt(baseStr);
    String heightStr = JOptionPane.showInputDialog(
            "Enter the height of the Right Triangle");
    height = Integer.parseInt(heightStr);
  }


  public String toString() {
    return "Right Triangle: base is " + base + ", height is " + height;
  }

}

