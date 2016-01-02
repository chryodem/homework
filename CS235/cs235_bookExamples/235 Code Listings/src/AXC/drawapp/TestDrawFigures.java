/** TestDrawFigures.java    Authors: Koffman & Wolfgang
 * Draws a collection of drawable objects stored in an array.
 * Uses DrawableRectangle, DrawableCircle,  
 * DrawableTriangle, and awt.
 */
package AXC.drawapp;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class TestDrawFigures extends JPanel {

    public static void main(String[] args) {
        // Create a frame to display the figures
        JFrame frame = new JFrame("Test Draw Figures");
        frame.setSize(400, 450);
        frame.setContentPane(new TestDrawFigures());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    @Override
    public void paintComponent(Graphics g) {
        // Paint the background
        super.paintComponent(g);
        // Create an array of type GeoFigure
        Object[] drawableShapes = {
            new DrawableCircle(50, new Point(50, 50),
            Color.blue, Color.green),
            new DrawableRectangle(100, 200, new Point(100, 100),
            Color.red, Color.yellow),
            new DrawableTriangle(50, 100, new Point(250, 300),
            Color.black, Color.red),
            new DrawableRectangle(60, 50, new Point(300, 200),
            Color.orange, Color.gray),
            new DrawableCircle(30,
            new Point(350, 350),
            Color.black, Color.magenta)
        };

        // Draw the drawable objects and display their attributes.
        for (int i = 0; i < drawableShapes.length; i++) {
            ((Drawable) drawableShapes[i]).drawMe(g);
            System.out.println("\n" + drawableShapes[i]);
            System.out.println("Area: "
                    + ((ShapeInt) drawableShapes[i]).computeArea()
                    + ", perimeter: "
                    + ((ShapeInt) drawableShapes[i]).computePerimeter());
        }
    }
}
