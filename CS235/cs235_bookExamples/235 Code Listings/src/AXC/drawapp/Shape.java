/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package AXC.drawapp;

/**
 * Abstract class for a geometric shape
 * @author Paul Wolfgang
 */
public abstract class Shape implements ShapeInt {

    /** The name of the shape */
    private String shapeName = "";

    /** Initializes the shapeName.
     *  @param shapeName the kind of shape
     */
    public Shape(String shapeName) {
        this.shapeName = shapeName;
    }

    /** Get the kind of shape.
     *  @return the shapeName
     */
    public String getShapeName() { return shapeName; }

    @Override
    public String toString() {return "Shape is a " + shapeName;}

}
