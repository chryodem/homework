import java.util.*;

public class DecisionNode {
    // this is used when the is created from the column with the highest
    // enthropy
    private Set<Integer> columnsToIgnore;
    private List<DecisionNode> columns;
    private double entropy;
    private double gain;
    private Map<Double, ArrayList<Integer>> correspondingIndeces;
    private Matrix features, labels;
    // its individual entropy
    private Map<Double, HashMap<Double, Integer>> individualFeatures;
    // this map is used to calculate each of the feautres individual counter
    private Map<Double, Integer> individualFeaturesCounter;

    private Map<Double, DecisionNode> actualChildren; // this is where the
    // children will be
    // stored

    private int indexOfFeature;// individual feature of that index
    private double label;

    // given a label for a specific input, return the node and the value
    // associated with this node

    private int splittingFeature;

    public DecisionNode(Matrix features, Matrix labels) {
        this.features = features;
        this.labels = labels;
        setEntropy(0.0);
        setGain(0.0);
        columns = new ArrayList<DecisionNode>();
        columnsToIgnore = new HashSet<Integer>();
        correspondingIndeces = new HashMap<Double, ArrayList<Integer>>();
        individualFeatures = new HashMap<Double, HashMap<Double, Integer>>();
        individualFeaturesCounter = new HashMap<Double, Integer>();
        actualChildren = new HashMap<Double, DecisionNode>();
    }

    public int getSplittingFeature() {
        return splittingFeature;
    }

    public void setSplittingFeature(int splittingFeature) {
        this.splittingFeature = splittingFeature;
    }

    // creates a deep copy of the map
    public void copyIndeces(Map<Double, ArrayList<Integer>> external) {
        Iterator<Double> iter = external.keySet().iterator();

        while (iter.hasNext()) {
            double key = iter.next();
            correspondingIndeces.put(key, external.get(key));
        }
    }

    public Map<Double, ArrayList<Integer>> getCorIndeces() {
        return correspondingIndeces;
    }

    public Set<Integer> getColumnsToIgnore() {
        return columnsToIgnore;
    }

    public void addToIgnoredColumns(int column) {
        columnsToIgnore.add(column);
    }

    public void setEntropy(double enthropy) {
        this.entropy = enthropy;
    }

    public double getEntropy() {
        return entropy;
    }

    public List<DecisionNode> getChildren() {
        return columns;
    }

    public void setChildren(List<DecisionNode> children) {
        this.columns = children;
    }

    public void setGain(double gain) {
        this.gain = gain;
    }

    public double getGain() {
        return gain;
    }

    public void addNode(DecisionNode node) {
        columns.add(node);
    }

    public DecisionNode getColumn(int index) {
        if (!columns.isEmpty()) {
            return columns.get(index);
        } else {
            return null;
        }
    }

    /**
     * @return child's index with the highest gain for this root
     */
    public int getHighestGain() {
        int index = 0;
        for (int i = 0; i < columns.size(); i++) {
            if (i == 0) {
                index = i;
            } else if (columns.get(i).getGain() > columns.get(index).getGain()) {
                index = i;
            }
        }
        columnsToIgnore.add(index);
        return index;
    }

    public void calculateEntropy() {

    }

    private double calculateEntropy(Map<Double, Integer> numberOfIndivLabels,
                                    int size) {

        if (numberOfIndivLabels.size() == 1) {
            return 0.0;
        }

        Iterator<Double> iter = numberOfIndivLabels.keySet().iterator();
        double tempEntropy = 0;
        while (iter.hasNext()) {
            double key = iter.next();
            double keysValue = (double) numberOfIndivLabels.get(key);
            double temp = keysValue / (double) size;
            tempEntropy -= (temp) * (Math.log(temp) / Math.log(2));

        }
        return tempEntropy;

    }

    public boolean containsColumn(int i) {
        return columnsToIgnore.contains(i);
    }

    public void copyIndivFeatCounter(
            Map<Double, Integer> individualFeaturesCounter) {
        Iterator<Double> iter = individualFeaturesCounter.keySet().iterator();
        while (iter.hasNext()) {
            double key = iter.next();
            this.individualFeaturesCounter.put(key,
                    individualFeaturesCounter.get(key));

        }

    }

    public void copyIndivFeat(
            Map<Double, HashMap<Double, Integer>> individualFeatures) {
        Iterator<Double> iter = individualFeatures.keySet().iterator();
        while (iter.hasNext()) {
            double key = iter.next();
            this.individualFeatures.put(key, individualFeatures.get(key));

        }

    }

    public void calculateIndividualGain(ArrayList<Integer> tList) {
        // now go through each of the columns in the features and figure out
        // its individual entropy
        individualFeatures = new HashMap<Double, HashMap<Double, Integer>>();

        // this map is used to calculate each of the feautres individual counter
        individualFeaturesCounter = new HashMap<Double, Integer>();

        // this map stores each individual features counter in the array list so
        // they could be found easier
        Map<Double, ArrayList<Integer>> correspondingIdeces = new HashMap<Double, ArrayList<Integer>>();
        // this map in the end should have for each key as many array list as
        // there should be children
        for (int i = 0; i < features.cols(); i++) {
            // for each individual item check if it's in the map
            if (this.containsColumn(i)) {
                continue;
            }
            HashMap<Double, Integer> tempMap;
            Integer tempValue;
            Integer counterResult;
            ArrayList<Integer> tempList;
            // if it's null then go through this one
            if (tList == null) {
                for (int j = 0; j < features.rows(); j++) {
                    double fromFeaturesList = features.get(j, i);
                    double associatedLabel = labels.get(j, 0);

                    tempMap = individualFeatures.get(fromFeaturesList);
                    if (tempMap == null) {// this feature is not in the list of
                        // maps
                        // yet
                        tempMap = new HashMap<Double, Integer>();
                        tempMap.put(associatedLabel, 1);
                        individualFeatures.put(fromFeaturesList, tempMap);
                    } else {// meaning the map already exists
                        // check the map to see if there's anything for that
                        // value
                        tempValue = tempMap.get(associatedLabel);
                        if (tempValue == null) {
                            tempMap.put(associatedLabel, 1);
                        } else {
                            tempMap.put(associatedLabel, tempValue + 1);
                        }
                        individualFeatures.put(fromFeaturesList, tempMap);
                    }

                    counterResult = individualFeaturesCounter
                            .get(fromFeaturesList);
                    if (counterResult == null) {
                        individualFeaturesCounter.put(fromFeaturesList, 1);
                    } else {
                        individualFeaturesCounter.put(fromFeaturesList,
                                counterResult + 1);
                    }

                    tempList = correspondingIdeces.get(fromFeaturesList);
                    if (tempList == null) {
                        tempList = new ArrayList<Integer>();
                        tempList.add(j);
                        correspondingIdeces.put(fromFeaturesList, tempList);
                    } else {
                        tempList.add(j);
                        correspondingIdeces.put(fromFeaturesList, tempList);
                    }

                }

            } else {
                for (int j = 0; j < tList.size(); j++) {
                    double fromFeaturesList = features.get(tList.get(j), i);
                    double associatedLabel = labels.get(tList.get(j), 0);

                    tempMap = individualFeatures.get(fromFeaturesList);
                    if (tempMap == null) {// this feature is not in the list of
                        // maps
                        // yet
                        tempMap = new HashMap<Double, Integer>();
                        tempMap.put(associatedLabel, 1);
                        individualFeatures.put(fromFeaturesList, tempMap);
                    } else {// meaning the map already exists
                        // check the map to see if there's anything for that
                        // value
                        tempValue = tempMap.get(associatedLabel);
                        if (tempValue == null) {
                            tempMap.put(associatedLabel, 1);
                        } else {
                            tempMap.put(associatedLabel, tempValue + 1);
                        }
                        individualFeatures.put(fromFeaturesList, tempMap);
                    }

                    counterResult = individualFeaturesCounter
                            .get(fromFeaturesList);
                    if (counterResult == null) {
                        individualFeaturesCounter.put(fromFeaturesList, 1);
                    } else {
                        individualFeaturesCounter.put(fromFeaturesList,
                                counterResult + 1);
                    }

                    tempList = correspondingIdeces.get(fromFeaturesList);
                    if (tempList == null) {
                        tempList = new ArrayList<Integer>();
                        tempList.add(j);
                        correspondingIdeces.put(fromFeaturesList, tempList);
                    } else {
                        tempList.add(j);
                        correspondingIdeces.put(fromFeaturesList, tempList);
                    }

                }

            }
            // now before each iteration need calculate gain and inner entropy
            double columnsGain = calculateGain(individualFeatures,
                    individualFeaturesCounter, this);
            DecisionNode node = new DecisionNode(features, labels);
            node.copyIndivFeatCounter(individualFeaturesCounter);
            node.copyIndivFeat(individualFeatures);
            node.setGain(columnsGain);
            node.copyIndeces(correspondingIdeces);
            node.setIndexOfFeature(i);
            this.addNode(node);

            individualFeatures = new HashMap<Double, HashMap<Double, Integer>>();
            individualFeaturesCounter = new HashMap<Double, Integer>();
            correspondingIdeces = new HashMap<Double, ArrayList<Integer>>();

        }

    }

    /**
     * this method is responsible for calculating the gain of each individual
     * column against some other column
     */
    private double calculateGain(Map<Double, HashMap<Double, Integer>> map,
                                 Map<Double, Integer> counterMap, DecisionNode node) {

        int totalEntries = 0;
        Iterator<Double> i = counterMap.keySet().iterator();
        while (i.hasNext()) {
            totalEntries += counterMap.get(i.next());
        }
        Iterator<Double> iter = map.keySet().iterator();

        double gain = 0;
        while (iter.hasNext()) {
            double mapKey = iter.next();
            HashMap<Double, Integer> innerMap = map.get(mapKey);
            int counter = counterMap.get(mapKey);

            Iterator<Double> innerIter = innerMap.keySet().iterator();
            double innerEntropy = 0;
            while (innerIter.hasNext()) {
                double key = innerIter.next();

                innerEntropy -= calculateInnerEntropy(
                        (double) innerMap.get(key), (double) counter);
            }
            gain += (double) counter / (double) totalEntries * innerEntropy;
        }
        return node.getEntropy() - gain;

    }

    private double calculateInnerEntropy(double integer, double denominator) {

        return (integer / denominator)
                * (Math.log(integer / denominator) / Math.log(2));
    }

    // this is where the recursive call will be made
    public void buildChildren() {
        // individual features map holds the information regarding how many
        // children it needs to have
//		DecisionNode root = new DecisionNode(features, labels);
        Iterator<Double> iter = individualFeatures.keySet().iterator();
        while (iter.hasNext()) {
            double key = iter.next();
            HashMap<Double, Integer> tMap = individualFeatures.get(key);
            int size = individualFeaturesCounter.get(key);// this should be the
            // counter for that
            // specific node
            double tEntropy = calculateEntropy(tMap, size);
            ArrayList<Integer> tList = correspondingIndeces.get(key);

            DecisionNode temp = new DecisionNode(features, labels);
            if (tEntropy == 0) {
                temp.setEntropy(tEntropy);
                temp.addToIgnoredColumns(this.getSplittingFeature());
                temp.setLabel(key);

                // return;

            } else {
                temp.setEntropy(tEntropy);
                temp.addToIgnoredColumns(this.getSplittingFeature());
                temp.calculateIndividualGain(tList);
                temp.setSplittingFeature(temp.getHighestGain());
            }

            this.addNode(temp);
            // int index = root.getHighestGain();
            // root.getColumn(index).buildChildren();
            actualChildren.put(key, temp);

        }
        Iterator<Double> keyT = actualChildren.keySet().iterator();
        while (keyT.hasNext()) {
            double key = keyT.next();
            DecisionNode featureNode = actualChildren.get(key);
            int highestCol = featureNode.getHighestGain();
            DecisionNode t = featureNode.getColumn(highestCol);
            if (t == null) {
                continue;
            } else {
                t.buildChildren();
            }
        }

    }

    public Map<Double, DecisionNode> getActualChildren() {
        return actualChildren;
    }

    private void setLabel(double key) {
        label = key;

    }

    public double getLabel() {
        return label;
    }

    public int getIndexOfFeature() {
        return indexOfFeature;
    }

    public void setIndexOfFeature(int indexOfFeature) {
        this.indexOfFeature = indexOfFeature;
    }

    public DecisionNode getRightChild(double index) {
        return actualChildren.get(index);
    }
}
