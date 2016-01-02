import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: andreirybin
 * Date: 3/26/13
 * Time: 1:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class CentroidNode implements Comparable<CentroidNode> {
    private double[] row; //a row from which this centroid was created
    private double distance;
    private Set<Integer> positions;

    public CentroidNode(double[] row) {
        this.row = row;
        positions = new HashSet<Integer>();
    }

    public Set<Integer> getPositions() {
        return positions;
    }

    public void addPosition(int position) {
        positions.add(position);
    }


    public void calcDistance(double[] otherRow, Matrix features) {

        distance = 0;
        for (int i = 1; i < otherRow.length; i++) {
            double x = row[i];
            double y = otherRow[i];
            //this is a real value
            if (features.valueCount(i) == 0) {
                //this is an unknown value either in my row or in the passed in row
                if (x == Double.MAX_VALUE || y == Double.MAX_VALUE) {
                    distance += 1;
                } else {
                    distance += Math.pow((x - y), 2); // (x+y)^2
                }
                //this is a nominal input
            } else {
                if (x == Double.MAX_VALUE || y == Double.MAX_VALUE) {
                    distance += 1;
                } else if (x != y) {
                    distance += 1;
                }
            }
        }

        distance = Math.sqrt(distance);
    }

    public double getDistance() {
        return distance;
    }

    @Override
    public int compareTo(CentroidNode o) {
        //this is written in a way that it would return the node with the lowest distance
        if (distance > o.getDistance()) return 1;
        else if (distance < o.getDistance()) return -1;
        return 0;
    }
}
