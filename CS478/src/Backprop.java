import java.util.*;

public class Backprop extends SupervisedLearner {

    private Random rand;
    private List<Integer> shuffledIndexes;
    private List<BackpropNode> inputNodes;
    private List<BackpropNode> hiddenNodes;
    private List<BackpropNode> outputNodes;
    private Matrix features;
    private Matrix labels;
    private int trainSize = 0;
    private int predictSize = 0;
    private double trainPercent = 0.8;
    private Set<Double> namesOfLabels;
    private int hiddenNodeMultiplier = 1;
    private double startWeight = 1;
    private int uniqueID = 1;

    private int gotRight = 0;

    public Backprop(Random rand) {
        this.rand = rand;
        shuffledIndexes = new ArrayList<>();
        // used for creating a list of input nodes
        inputNodes = new ArrayList<>();
        // list of hidden nodes
        hiddenNodes = new ArrayList<>();
        // list of output nodes
        outputNodes = new ArrayList<>();
        // names of labels of the output nodes
        // size of this list will be how many output nodes will be created
        namesOfLabels = new LinkedHashSet<>();
    }

    @Override
    public void train(Matrix features, Matrix labels) throws Exception {
        this.features = features;
        this.labels = labels;

        createShuffledIndex();
        populateNamesSet();
        // now create the input,hidden and output nodes
        createOutputNodes();
        createHiddenNodes();
        createInputNodes();

        connectNodes();

        startTraining();

        //tryVerify();

    }

    private void tryVerify() {
        int test = shuffledIndexes.get(predictSize + 24);
        double row[] = features.row(test);
        double target = labels.get(test, 0);

        setOutputForInput(row);
        //setTargetsForOutput(target);
        outputsToHidden();
        outputsOut();
        System.out.println("target is :" + target);
        for (BackpropNode node : outputNodes) {
            System.out.println("outputNode + has output: " + node.getOutput());
        }


    }

    private void startTraining() {

        inputNodes.get(inputNodes.size() - 1).setOutput(1.0);
        hiddenNodes.get(hiddenNodes.size() - 1).setOutput(1.0);

        gotRight = 0;
        int epochs = 0;
        do {
            //for(int i =0; i< features.rows();i++){
            for (int i = 0; i < features.rows(); i++) {
                // get the input row
                //int whichToGrab = shuffledIndexes.get(i);
                double[] row = features.row(i);
                // target that I am training towards for this specific row
                double target = labels.get(i, 0);

                //	printInputsAndLabel(row, target);
                // now go through each of the features and add it to the input nodes
                setOutputForInput(row);
                setTargetsForOutput(target);
                outputsToHidden();
                outputsOut();
                // ---------------------
                // end of forward prop
                // ---------------------
                outputsErrors();// calculate the errors and save them
                // calculate weight changes from hidden to output
                calculateWeightChangesFromHidden();
                // now calculate changes from input
                calculateWeightChangesFromInput();
                // now that all of the errors had been calculated and saved, update
                // to new weights
                updateWeights();
                updateGotRight(target);


            }
//			System.out.println("stop");
            epochs++;

            //}while(gotRight());
        } while (epochs < 2);
        //while(epochs<100){
        // this is one epoch
        //for(int i =0;i<features.rows();i++){
        //	epochs++;

        //	}

    }

    private void printInputsAndLabel(double[] row, double target) {
        for (int j = 0; j < row.length; j++) {
            System.out.print("\t" + row[j]);
        }
        System.out.print("\t" + target);
        System.out.println("");
    }

    private void printOutputNodes() {
        for (BackpropNode node : outputNodes) {
            System.out.println("output for node: " + node.getID() + " output is: " + node.getOutput());
        }
        System.out.println("");

    }

    private void updateGotRight(double target) {
        int highestOuput = 0;
        BackpropNode temp;
        for (int i = 0; i < outputNodes.size(); i++) {
            if (i == 0) {
                highestOuput = 0;
            } else {
                temp = outputNodes.get(i);
                if (temp.getOutput() > outputNodes.get(highestOuput).getOutput()) {
                    highestOuput = i;
                }
            }
        }

        if (target == inputNodes.get(highestOuput).getNodesLabel()) {
            gotRight++;
        }

    }

    private boolean gotRight() {
        double t = ((double) gotRight / trainSize) * 100;
        gotRight = 0;
        System.out.println(t);
        return t >= 0.8;

    }

    private void updateWeights() {
        // from input nodes to hidden nodes
        for (BackpropNode node : inputNodes) {
            node.updateWeights();
        }
        // from hidden nodes to output nodes
        for (BackpropNode node : hiddenNodes) {
            node.updateWeights();
        }

    }

    private void calculateWeightChangesFromInput() {
        for (BackpropNode node : inputNodes) {
            node.setChangesToOuter();
            // no node set error? per slides
        }
    }

    private void calculateWeightChangesFromHidden() {
        for (BackpropNode node : hiddenNodes) {
            node.setChangesToOuter();
            if (!node.isBias()) {
                node.setError();
            }
        }

    }

    // calculates error for the output nodes
    private void outputsErrors() {
        for (BackpropNode node : outputNodes) {
            node.setError();
        }

    }

    private void outputsOut() {
        for (BackpropNode node : outputNodes) {
            // for each of the outputnodes set the net value
            // double dProduct = dotProduct(node);
            node.setNet();
            // then figure out what the output needs to be
            node.setOutput();
        }
    }

    // get the outputs for hidden layer
    private void outputsToHidden() {
        for (BackpropNode node : hiddenNodes) {
            // this is to set the net for hidden
            node.setNet();
            node.setOutput();
        }
    }

    // set targets for the ouput nodes
    private void setTargetsForOutput(double target) {
        for (BackpropNode node : outputNodes) {
            node.setTarget(target);
        }

    }

    // this is setting input for row
    private void setOutputForInput(double[] row) {
        for (int j = 0; j < inputNodes.size() - 1; j++) {
            inputNodes.get(j).setOutput(row[j]);
        }

    }

    // link the nodes together
    private void connectNodes() {
        // link the input nodes to the hidden layer nodes
        for (BackpropNode inputNode : inputNodes) {
            for (BackpropNode hiddenNode : hiddenNodes) {
                if (!hiddenNode.isBias()) {
                    inputNode.addOutNode(hiddenNode);//this is an out connection
                    inputNode.addWeightTo(hiddenNode, startWeight); //this is a weight to the out connection
                }

            }
        }

        // link the hidden layer node to the output nodes
        for (BackpropNode hiddenNode : hiddenNodes) {
            for (BackpropNode outputNode : outputNodes) {
                hiddenNode.addOutNode(outputNode);
                hiddenNode.addWeightTo(outputNode, startWeight);
            }
        }

        // connect
        for (BackpropNode hidden : hiddenNodes) {
            // not sure about this here...
            if (!hidden.isBias()) {
                for (BackpropNode input : inputNodes) {
                    hidden.addInNode(input);
                }
            }
        }

        for (BackpropNode outter : outputNodes) {
            for (BackpropNode hidden : hiddenNodes) {
                outter.addInNode(hidden);
            }
        }

    }

    // creates the hidden layer of nodes
    private void createHiddenNodes() {
        for (int i = 0; i < features.cols() * hiddenNodeMultiplier; i++) {
            hiddenNodes.add(new BackpropNode(false, BackpropNode.NodeTypes.HIDDEN, uniqueID++));
        }
        hiddenNodes.add(new BackpropNode(true, BackpropNode.NodeTypes.HIDDEN, uniqueID++));


    }

    // creates the output nodes
    private void createOutputNodes() {
        for (int i = 0; i < 1; i++) {
            BackpropNode node = new BackpropNode(false, BackpropNode.NodeTypes.OUTPUT, uniqueID++);
            outputNodes.add(node);
        }
    }

    // this method creates the list of the input nodes
    private void createInputNodes() {

        for (int i = 0; i < features.cols(); i++) {
            inputNodes.add(new BackpropNode(false, BackpropNode.NodeTypes.INPUT, uniqueID++));
        }
        inputNodes.add(new BackpropNode(true, BackpropNode.NodeTypes.INPUT, uniqueID++));
    }

    // takes care of creating a set of labels names for the output nodes
    private void populateNamesSet() {
        for (int i = 0; i < labels.rows(); i++) {
            Double d = labels.row(i)[0];
            namesOfLabels.add(d);
        }
    }

    // creates a shuffled list for training and verification
    private void createShuffledIndex() {

        for (int i = 0; i < features.rows(); i++) {
            shuffledIndexes.add(i);
        }

        Collections.shuffle(shuffledIndexes, rand);

        trainSize = (int) (shuffledIndexes.size() * trainPercent);
        predictSize = shuffledIndexes.size() - trainSize;

    }

    @Override
    public void predict(double[] features, double[] labels) throws Exception {

    }

}
