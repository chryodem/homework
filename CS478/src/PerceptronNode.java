import java.util.Collections;
import java.util.Vector;


public class PerceptronNode {
    private double predValue = 0;
    private double learningRate = 0;
    private double[] weightVector;
    ;
    private double feature;// this is the feature I care for
    private Matrix featureList;
    private Vector<Double> shuffleList;

    // all of the other features are not counting

    public PerceptronNode(Double feature, Matrix featureList, double learningRate) {
        this.feature = feature;
        this.featureList = featureList;
        this.learningRate = learningRate;
        shuffleList = new Vector<Double>();
        initializeWeight();
        initializeShuffleVector();
    }

    private void initializeWeight() {
        weightVector = new double[featureList.rows() + 1];
        for (int i = 0; i < weightVector.length; i++) {
            weightVector[i] = 0;
        }
    }

    private void initializeShuffleVector() {
        for (int i = 0; i < featureList.rows(); i++) {
            shuffleList.add((double) i);
        }
        Collections.shuffle(shuffleList);
    }

    // train perceptron
    public void train(double[] inputRow, double labelOfTarget) {
        double target = 0;
        double output = 0;

        double net = dotProduct(inputRow);
        // if this is what I am training for, then target =1
        if (labelOfTarget == feature) {
            target = 1;
        } else {
            target = 0;
        }


        if (net > 0) {
            output = 1;
        } else {
            output = 0;
        }
//		printRow(inputRow);
//		printTarget(target);
//		printWeightVector();
//		System.out.print(net+ " ");
//		System.out.print("\t");
//		System.out.print(output + " " + "\t");

        if ((target - output) != 0) {
            adjustWeight(target - output, inputRow);
        }
//		System.out.println(" ");

    }


    private void adjustWeight(double delta, double[] inputRow) {
        double[] adjWeight = new double[weightVector.length];
        for (int i = 0; i < inputRow.length; i++) {
            adjWeight[i] = delta * inputRow[i] * learningRate;

        }
        adjWeight[adjWeight.length - 1] += delta * learningRate;
//		printRow(adjWeight);

        for (int i = 0; i < adjWeight.length; i++) {
            weightVector[i] += adjWeight[i];
        }

    }

    private double dotProduct(double[] featuresList) {
        // double[] result = new double[featuresList.length];
        double sum = 0;

        for (int i = 0; i < featuresList.length; i++) {
            double x1 = featuresList[i];
            double x2 = weightVector[i];
            sum += x1 * x2;
        }
        // multiply the bias (1) times the last entry in the weight vector
        sum += weightVector[weightVector.length - 1];

        return sum;
    }

    public double[] getWeightVector() {

        return weightVector;
    }

    public void calculateValue(double[] features) {
        predValue = 0;
        predValue = dotProduct(features);
    }

    public double getPredValue() {
        return predValue;
    }

    public double getLabel() {
        return feature;
    }

    private void printWeightVector() {
        for (int i = 0; i < weightVector.length; i++) {
            System.out.print(weightVector[i] + " ");
        }
        System.out.print("\t");

    }

    private void printTarget(double target) {
        System.out.print(target + " ");
        System.out.print("\t");

    }

    private void printRow(double[] inputRow) {
        for (int i = 0; i < inputRow.length; i++) {
            System.out.print(inputRow[i] + " ");
        }
        System.out.print("\t");
    }
}
