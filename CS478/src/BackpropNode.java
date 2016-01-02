import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class BackpropNode {

    public enum NodeTypes {INPUT, OUTPUT, HIDDEN}

    private boolean bias;
    private NodeTypes nodeType;

    //if this nodes is an output node, it needs a label
    private double nodesLabel;
    private int uniqueID;
    private Map<Integer, BackpropNode> outputNodes;
    private Map<Integer, BackpropNode> inputNodes;

    //this is the nodes output
    private double output;
    private double target;

    private Map<Integer, Double> weightsTo;
    private Map<Integer, Double> updateWeightsTo;

    private double net;
    private double error;

    private static final double learningRate = 0.1;
    private static final boolean isDebbuging = true;
    private static final DecimalFormat df = new DecimalFormat("#.#####");

    public BackpropNode(boolean bias, NodeTypes nodeType, int uniqueID) {
        this.bias = bias;
        this.nodeType = nodeType;
        nodesLabel = 0;
        this.uniqueID = uniqueID;
        outputNodes = new HashMap<>();
        inputNodes = new HashMap<>();
        weightsTo = new HashMap<>();
        updateWeightsTo = new HashMap<>();
    }


    public boolean isBias() {
        return bias;
    }

    public void setBias(boolean bias) {
        this.bias = bias;
    }

    public String getNodeType() {
        return nodeType.toString();
    }

    public void setNodeType(NodeTypes nodeType) {
        this.nodeType = nodeType;
    }

    public double getNodesLabel() {
        return nodesLabel;
    }



    //adds a node to the out nodes
    public void addOutNode(BackpropNode node) {
        outputNodes.put(node.getID(), node);
    }

    public void addInNode(BackpropNode node) {
        inputNodes.put(node.getID(), node);
    }

    public int getID() {
        return uniqueID;
    }

    public Set<Integer> getOutKeys() {
        return outputNodes.keySet();
    }

    public Set<Integer> getInKeys() {
        return inputNodes.keySet();
    }

    public BackpropNode getInNode(int i) {
        return inputNodes.get(i);
    }

    public BackpropNode getOutNode(int i) {
        return outputNodes.get(i);
    }

    public double getOutput() {
        return output;
    }

    public void setOutput() {
        if (bias) {
            output = 1.0;
        } else {
            output = 1 / (1 + Math.exp(-net));
            output = Double.valueOf(df.format(output));
        }

        if (isDebbuging) {
            System.out.println("node: " + uniqueID + " output=" + output);

        }
    }

    public void setOutput(double output) {
        this.output = output;
    }

    public void setTarget(double target) {
        //if the target matches the label then it should be 1
        if (target == nodesLabel) {
            this.target = 1;
        } else { //else the target is supposed to be 0
            this.target = 0;
        }
    }

    public double getTarget() {
        return target;
    }


    public void addWeightTo(BackpropNode node, double weight) {
        weightsTo.put(node.getID(), weight);

    }


    public double getWeightTo(int i) {
        return weightsTo.get(i);
    }

    public void setNet() {
        if (!bias) {
            double total = 0;
            for(BackpropNode inputNode : inputNodes.values()) {
                double weight = inputNode.getWeightTo(uniqueID);
                double output = inputNode.getOutput();

                total += weight * output;
            }
            net = total;
            if (isDebbuging) {
                System.out.println("node: " + uniqueID + " net=" + net);

            }

        }
    }

    public double getNet() {
        return net;
    }

    public double getError() {
        return error;
    }

    public void setError() {

        if (nodeType == NodeTypes.OUTPUT) {
            //(T - Z) f'(net)
            error = Double.
                    valueOf(df.format((target - output) * output * (1 - output)));//t-o * (o*(1-o))
        } else { //this node is a hidden node because this never gets called on the input nodes
            double total = 0;
            //sums them up first
            //Sum of outputNodeErrors * weight * (1 - output)
            for (BackpropNode temp : outputNodes.values()) {
                double outerNodeError = temp.getError();
                double weight = weightsTo.get(temp.getID());
                total += outerNodeError * weight;//sum up first
            }

            total *= (output * (1 - output));//then multiply the sum by f prime
            error =  Double.valueOf(df.format(total));
        }
        if (isDebbuging) {
            System.out.println("node: " + uniqueID + " error=" + error + " node's type: " + nodeType);
        }

    }

    public void addWChangeTo(BackpropNode node, double wChange) {
        updateWeightsTo.put(node.getID(), wChange);
    }

    public double getWChangeTo(BackpropNode node) {
        return updateWeightsTo.get(node.getID());
    }


    public void setChangesToOuter() {
        for (BackpropNode outer : outputNodes.values()) {
            double wChange = learningRate * outer.getError() * output;
            wChange = Double.valueOf(df.format(wChange));
            updateWeightsTo.put(outer.getID(), wChange);
            if (isDebbuging) {
                System.out.println("node: " + uniqueID + " node's type: " + nodeType + " deltaW=" + wChange);
            }
        }

    }


    public void updateWeights() {
        for (BackpropNode outer : outputNodes.values()) {
            double wChange = updateWeightsTo.get(outer.getID());//what the change should be
            double newWeight = weightsTo.get(outer.getID()) + wChange;
            //now update in the map of current weights to
            if (isDebbuging) {
                System.out.println("node: " + uniqueID + " node's type: " + nodeType + " newWeight=" + newWeight);
            }
            weightsTo.put(outer.getID(), newWeight);
        }
    }


}
