public class InstanceNode implements Comparable {
    private double nodesLabel;
    private double[] attributes;
    private double[] inputRow;
    private double distance;
    private int positionInFeatures;

    public InstanceNode(double[] attributes, double[] inputRow, double nodesLabel, int position) {
        this.nodesLabel = nodesLabel;
        this.attributes = attributes;
        this.inputRow = inputRow;
        distance = 0;
        positionInFeatures = position;
        calcEuclidean();
    }

    private void calcEuclidean() {

        double tempResult = 0;

        for (int i = 0; i < inputRow.length; i++) {
            tempResult += Math.pow(attributes[i] - inputRow[i], 2.0);
        }
        distance = Math.sqrt(tempResult);
    }

    private void calcOverlap() {
        double tempResult = 0;

        for (int i = 0; i < inputRow.length; i++) {

        }
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }


    @Override
    public int compareTo(Object o) {
        InstanceNode temp = (InstanceNode) o;
        //this way it stores the smallest distances first in the list
        if (temp.getDistance() < distance) return 1;
        else if (temp.getDistance() > distance) return -1;
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String toString() {
        return "" + distance;
    }

    public double getLabel() {
        return nodesLabel;
    }

    public int getPositionInFeatures() {
        return positionInFeatures;
    }
}
