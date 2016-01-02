import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class DecisionTree extends SupervisedLearner {
    private Matrix features;
    private Matrix labels;
    private Map<Double, Integer> startingEntropy;
    DecisionNode root;

    public DecisionTree() {

    }

    @Override
    public void train(Matrix features, Matrix labels) throws Exception {
        this.features = features;
        this.labels = labels;
        //this should calculate the starting entropy first, go throuw all of the features and see how many there are,
        //the using a hash map calculate it.
        root = new DecisionNode(features, labels);
        //figure out the entropy for the root node
        root.setEntropy(calculateEntropy());
        //calculates individual entropy and also goes through each of the columns
        root.calculateIndividualGain(null);

        //gets the index of the feature on which we are going to split
        int index = root.getHighestGain();

        root.setSplittingFeature(index);

        //now need to build a recursive function
        //right now this returned a feature on which the split will be made
        DecisionNode temp = root.getColumn(index);
        temp.buildChildren();
        System.out.println();


    }


    private double calculateEntropy() {
        startingEntropy = new HashMap<Double, Integer>();
        for (int i = 0; i < labels.rows(); i++) {
            double tempFeature = labels.get(i, 0);
            Integer result = startingEntropy.get(tempFeature);
            if (result == null) {
                startingEntropy.put(tempFeature, 1);
            } else {
                startingEntropy.put(tempFeature, result + 1);
            }
        }

        Iterator<Double> startingKeys = startingEntropy.keySet().iterator();
        double tempEntropy = 0;
        while (startingKeys.hasNext()) {
            double tempKey = startingKeys.next();
            double keysValue = (double) startingEntropy.get(tempKey);
            double temp = keysValue / (double) features.rows();
            tempEntropy -= (temp) * (Math.log(temp) / Math.log(2));
        }
        //System.out.println(tempEntropy);
        return tempEntropy;

    }


    @Override
    public void predict(double[] features, double[] labels) throws Exception {
        int splittingFeat = root.getSplittingFeature();


        DecisionNode temp = root.getColumn(splittingFeat);
        double label = 0;
        while (temp != null) {
            splittingFeat = temp.getIndexOfFeature();
            double firstFeature = features[splittingFeat];
            Map<Double, DecisionNode> actualChildren = temp.getActualChildren();
            temp = temp.getRightChild(firstFeature);
            if (temp != null) {
                label = temp.getLabel();
            }
            //temp = temp.getActualChildren().get(firstFeature);
        }
        labels[0] = label;


    }
}
