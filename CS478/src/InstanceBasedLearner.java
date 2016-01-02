import java.util.*;

public class InstanceBasedLearner extends SupervisedLearner {
    private Matrix _features;
    private Matrix _labels;
    private FibonacciHeap<InstanceNode> heap;
    private int k = 15;
    private boolean isRegression = false;
    private boolean isWeighted = false;
    private Map<Double, HashSet<Integer>> reducedSet;
    private Map<Double, Boolean> usingReduced;
    private boolean useHashSet = false;


    @Override
    public void train(Matrix features, Matrix labels) throws Exception {
        _features = features;
        _labels = labels;
        reducedSet = new HashMap<Double, HashSet<Integer>>();
        usingReduced = new HashMap<Double, Boolean>();

    }

    @Override
    public void predict(double[] features, double[] labels) throws Exception {
        heap = new FibonacciHeap<InstanceNode>();
        List<InstanceNode> hList = new ArrayList<InstanceNode>();

        double proposedLabel = populateHeap(features, hList);

        populateSet(hList, proposedLabel);


        labels[0] = proposedLabel;


    }

    private double populateHeap(double[] features, List<InstanceNode> hList) {
        for (int i = 0; i < _features.rows(); i++) {
            InstanceNode node = new InstanceNode(_features.row(i), features, _labels.get(i, 0), i);
            Node<InstanceNode> tNode = new Node<InstanceNode>(node.getDistance(), node);
            heap.insert(tNode);
        }

        for (int i = 0; i < k; i++) {
            hList.add(heap.extractMin().getData());
        }
        double proposedLabel = 0;
        //if this is a regression try to calculate the actual value for label
        if (isRegression) {
            if (isWeighted) {
                proposedLabel = calcWeightedRegression(hList);
            } else {
                proposedLabel = calcUnweightedRegression(hList);
            }

            //or just use the voting to determine which one is the closest
        } else {
            if (isWeighted) {
                proposedLabel = calcWeightedClass(hList);
            } else {
                proposedLabel = calcUnweightClass(hList);
            }
        }
        return proposedLabel;
    }

    private void populateSet(List<InstanceNode> hList, double proposedLabel) {
        Boolean startUsing = usingReduced.get(proposedLabel);
        if (startUsing == null) {
            usingReduced.put(proposedLabel, false);
            startUsing = false;
        }

        if (!startUsing) {
            HashSet<Integer> positionSet = reducedSet.get(proposedLabel);

            if (positionSet == null) {
                positionSet = new HashSet<Integer>();
            }

            if (positionSet.isEmpty() || positionSet.size() < 100) {
                for (int i = 0; i < hList.size(); i++) {
                    InstanceNode tNode = hList.get(i);
                    positionSet.add(tNode.getPositionInFeatures());
                }
                for (int i = 0; i < 100; i++) {
                    InstanceNode t = heap.extractMin().getData();
                    positionSet.add(t.getPositionInFeatures());

                }
            } else {
                useHashSet = true;
            }
            reducedSet.put(proposedLabel, positionSet);

        }

    }

    //TODO verified: regressions, classifications
    //classification unweighted
    private double calcUnweightClass(List<InstanceNode> hList) {
        Map<InstanceNode, IBNodeCounter> tMap = new HashMap<InstanceNode, IBNodeCounter>();
        for (int i = 0; i < hList.size(); i++) {
            InstanceNode node = hList.get(i);
            if (node.getDistance() == 0) {
                return node.getLabel();
            }
            IBNodeCounter count = tMap.get(node);
            if (count == null) {
                count = new IBNodeCounter(node.getLabel());
                count.addPosition(i);
                tMap.put(node, count);
            } else {
                count.addPosition(i);
                count.increaseCount();
                tMap.put(node, count);
            }
        }

        Deque<IBNodeCounter> voteQ = new LinkedList<IBNodeCounter>();
        Iterator<InstanceNode> iter = tMap.keySet().iterator();
        while (iter.hasNext()) {
            //just need the
            IBNodeCounter count = tMap.get(iter.next());

            count.setWeight((double) count.getCounter() / (double) hList.size());

            //now figure out which one is the node with the highest weight
            if (voteQ.isEmpty()) {
                voteQ.addFirst(count);
            } else if (voteQ.peekFirst().getWeight() < count.getWeight()) {
                voteQ.addFirst(count);
            }

        }

        return voteQ.peekFirst().getLabel();
    }

    //classification weighted
    private double calcWeightedClass(List<InstanceNode> hList) {
        double denominator = 0;
        Map<InstanceNode, IBNodeCounter> tMap = new HashMap<InstanceNode, IBNodeCounter>();
        for (int i = 0; i < hList.size(); i++) {
            InstanceNode node = hList.get(i);
            //this is to handle the case when the distance is 0
            if (node.getDistance() == 0) {
                return node.getLabel();
            }
            IBNodeCounter count = tMap.get(node);
            if (count == null) {
                count = new IBNodeCounter(node.getLabel());
                count.addPosition(i);
                tMap.put(node, count);
            } else {
                count.addPosition(i);
                count.increaseCount();
                tMap.put(node, count);
            }
            denominator += ((1.0 / (Math.pow(node.getDistance(), 2))));
        }
        //now iterate over all of the nodes and calculate
        Deque<IBNodeCounter> voteQ = new LinkedList<IBNodeCounter>();
        Iterator<InstanceNode> iter = tMap.keySet().iterator();
        while (iter.hasNext()) {
            //just need the
            IBNodeCounter count = tMap.get(iter.next());
            List<Integer> positions = count.getPositions();
            double numerator = 0;
            for (Integer position : positions) {
                numerator += 1 / (Math.pow(hList.get(position).getDistance(), 2));
            }
            count.setWeight(numerator / denominator);

            //now figure out which one is the node with the highest weight
            if (voteQ.isEmpty()) {
                voteQ.addFirst(count);
            } else if (voteQ.peekFirst().getWeight() < count.getWeight()) {
                voteQ.addFirst(count);
            }

        }

        //return the node with the highest weight label
        return hList.get(voteQ.peekFirst().getPositions().get(0)).getLabel();
    }

    //regression not weighted
    private double calcUnweightedRegression(List<InstanceNode> hList) {
        double sumOfLabels = 0;
        for (int i = 0; i < hList.size(); i++) {
            sumOfLabels += hList.get(i).getLabel();
        }
        return sumOfLabels / (double) k;
    }

    //regression weighted
    private double calcWeightedRegression(List<InstanceNode> hList) {
        double denominator = 0;
        double numerator = 0;
        for (int i = 0; i < hList.size(); i++) {
            InstanceNode node = hList.get(i);
            //This is for the case when the distance is 0, the node is itself
            if (node.getDistance() == 0) {
                return node.getLabel();
            }
            numerator += ((1.0 / (Math.pow(node.getDistance(), 2))) * node.getLabel());
            denominator += ((1.0 / (Math.pow(node.getDistance(), 2))));
        }
        return numerator / denominator;
    }

    //private class used for calculations
    private class IBNodeCounter {
        List<Integer> positions;
        private double weight;
        private double label;
        int counter = 0;

        public IBNodeCounter(double label) {
            this.label = label;
            positions = new ArrayList<Integer>();
            setWeight(0);
            increaseCount();

        }

        public void increaseCount() {
            counter++;
        }

        public int getCounter() {
            return counter;
        }

        public void addPosition(int position) {
            positions.add(position);
        }

        public List<Integer> getPositions() {
            return positions;
        }

        public void setWeight(double weight) {
            this.weight = weight;
        }

        public double getWeight() {
            return weight;
        }

        public double getLabel() {
            return label;
        }
    }
}
