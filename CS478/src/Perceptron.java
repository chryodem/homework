import java.util.*;

//net value = features doted wit the weight

/**
 * The Class Perceptron.
 */
public class Perceptron extends SupervisedLearner {

    private List<PerceptronNode> listOfPerceptrons;
    private Matrix _features, _labels;
    //random matrix is used for the 70/30 experiments

    /**
     * The rand.
     */
    private Random rand;

    /**
     * Instantiates a new perceptron.
     *
     * @param rand the rand
     */
    public Perceptron(Random rand) {
        listOfPerceptrons = new ArrayList<PerceptronNode>();
        this.rand = rand;

    }

    @Override
    public void train(Matrix features, Matrix labels) throws Exception {
        _features = features;
        _labels = labels;

        listOfPerceptrons.clear();

        Set<Double> namesOfLabels = new HashSet<Double>();

        for (int i = 0; i < labels.rows(); i++) {
            Double d = labels.row(i)[0];
            namesOfLabels.add(d);
        }

//		printRandomMatrix();
//		randomMatrix.shuffle(rand);
//		printRandomMatrix();

        int numberOfLabels = labels.valueCount(0);
        for (double name : namesOfLabels) {
            listOfPerceptrons.add(new PerceptronNode(name, features, 0.1));
        }
        //System.out.println("Pattern and bias(5)  Target(1) WeightVector(5) Net(1) Output(1) ChangeInWeight(5) ");
        for (int j = 0; j < 5000; j++) {
            //used these methods for shuffling data


            for (int i = 0; i < _features.rows(); i++) {
                double[] inputRow = _features.row(i);
                double target = _labels.row(i)[0];
                for (PerceptronNode p : listOfPerceptrons) {
                    p.train(inputRow, target);
                }
                //System.out.println(" ");
            }

        }
    }


    @Override
    public void predict(double[] features, double[] labels) throws Exception {
        PerceptronNode max = null;
        for (PerceptronNode p : listOfPerceptrons) {

            p.calculateValue(features);
            double result = p.getPredValue();

            if (max == null) {
                max = p;
            } else if (result > max.getPredValue()) {
                max = p;
            }
        }
        labels[0] = max.getLabel();

    }
}
