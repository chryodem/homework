package AXC.drawapp;

import javax.swing.*;
import java.awt.*;
/** The DrawableFigureIcon implements the Icon interface by
    providing an icon that contains a figure that extends
    the Drawable class.
    @author Koffman and Wolfgang
*/
public class DrawableFigureIcon implements Icon {
    /** Reference to the Drawable figure */
    private Drawable theFig;
    /** The relative x coordinate of the origin  */
    int x0;
    /** The relative y coordinate of the origin */
    int y0;

    // Constructor
    /** Construct a DrawableFigureIcon for a specified DrawabelInt
	figure.
	@param f The Drawable figure
    */
    public DrawableFigureIcon(Drawable f) {
        theFig = f;
        x0 = f.getPoint().x;
        y0 = f.getPoint().y;
    }

    /** Paint the figure for the icon
	@param c The component in which this icon is being drawn
	@param g The graphics environment
	@param x The x-coordinate of the upper left corner of the
	icon
	@param y The y-coordinte of the upper left corner of the 
	icont
    */
    public void paintIcon(Component c, Graphics g, int x, int y) {
        theFig.setPoint(x + x0, y + y0);
        theFig.drawMe(g);
    }

    /** Get the width of the icon 
	@return the width of the icon 
    */
    public int getIconWidth() {
        return theFig.getWidth();
    }

    /** Get the height of the icon
	@return the height of the icon
    */
    public int getIconHeight() {
        return theFig.getHeight();
    }

}
