// ----------------------------------------------------------------
// The contents of this file are distributed under the CC0 license.
// See http://creativecommons.org/publicdomain/zero/1.0/
// ----------------------------------------------------------------

import java.util.Random;

public class MLSystemManager {

    private StringBuilder sb;
    private boolean printConfusionMatrix = false; // for confusion matrix
    private boolean normalize = false; // to normalize data
    private String fileName = "";
    private String learnerName = "";
    private String evalMethod = "";
    private String evalParameter = "";
    private String testFilePath = "";

    public void setVerbose(boolean verbose) {
        printConfusionMatrix = verbose;
    }

    public void setNormalize(boolean normalize) {
        this.normalize = normalize;
    }

    public void setFilePath(String fileName) {
        this.fileName = fileName;
    }

    public void setLearnerName(String learnerName) {
        this.learnerName = learnerName;
    }

    public void setEvalMethod(String evalMethod) {
        this.evalMethod = evalMethod;
    }

    public void setEvalParameter(String evalParameter) {
        this.evalParameter = evalParameter;
    }

    public void setTestFilePath(String path) {
        testFilePath = path;
    }

    /**
     * When you make a new learning algorithm, you should add a line for it to
     * this method.
     */
    public SupervisedLearner getLearner(String model, Random rand)
            throws Exception {
        if (model.equals("baseline")) {
            return new BaselineLearner();

        } else if (model.equals("perceptron")) {
            return new Perceptron(rand);
        } else if (model.equals("backprop")) {
            return new Backprop(rand);
        } else if (model.equals("decisiontree")) {
            return new DecisionTree();
        } else if (model.equals("knn")) {
            return new InstanceBasedLearner();
        } else if (model.equals("clustering")) {
            return new ClusteringModel();
        }
        // else if (model.equals("neuralnet")) return new NeuralNet(rand);
        // else if (model.equals("knn")) return new InstanceBasedLearner();
        else
            throw new Exception("Unrecognized model: " + model);
    }

    public void run() throws Exception {

        sb = new StringBuilder();

        // args = new String[]{"-L", "baseline", "-A", "data/iris.arff", "-E",
        // "cross", "10", "-N"};

        Random rand = new Random(1234); // Use a seed for deterministic
        // results (makes debugging easier)
        //Random rand = new Random(); // No seed for non-deterministic results

        // Load the model
        SupervisedLearner learner = getLearner(learnerName, rand);

        // Load the ARFF file
        Matrix data = new Matrix();
        data.loadArff(fileName);
        data.mostCommonValue(0);

        if (data.errorsReading()) {
            sb.append(data.errorMessage());
            return;
        }
        if (normalize) {
            // System.out.println("Using normalized data\n");
            sb.append("Using normalized data\n");
            data.normalize();
        }

        // Print some stats

        sb.append("\n ");
        sb.append("Dataset name: " + fileName + "\n");
        sb.append("Number of instances: " + data.rows() + "\n");
        sb.append("Number of attributes: " + data.cols() + "\n");
        sb.append("Learning algorithm: " + learnerName + "\n");
        sb.append("Evaluation method: " + evalMethod + "\n");
        sb.append("\n ");

        if (evalMethod.equals("training")) {
            sb.append("Calculating accuracy on training set...\n");
            Matrix features = new Matrix(data, 0, 0, data.rows(),
                    data.cols() - 1);
            Matrix labels = new Matrix(data, 0, data.cols() - 1, data.rows(), 1);
            Matrix confusion = new Matrix();
            double startTime = System.currentTimeMillis();
            learner.train(features, labels);
            double elapsedTime = System.currentTimeMillis() - startTime;
            sb.append("Time to train (in seconds): " + elapsedTime / 1000.0
                    + "\n");
            double accuracy = learner.measureAccuracy(features, labels,
                    confusion);
            sb.append("Training set accuracy: " + accuracy);
            if (printConfusionMatrix) {
                sb.append("\nConfusion matrix: (Row=target value, Col=predicted value)\n");
                sb.append(confusion.print());
                sb.append("\n \n");
            }
        } else if (evalMethod.equals("static")) {
            Matrix testData = new Matrix();
            testData.loadArff(testFilePath);
            if (normalize) {
                testData.normalize(); // BUG! This may normalize differently
                // from the training data. It should use
                // the same ranges for normalization!
            }

            sb.append("\n Calculating accuracy on separate test set...\n");
            sb.append("Test set name" + evalParameter + "\n");
            sb.append("Number of test instances: " + testData.rows() + "\n");
            Matrix features = new Matrix(data, 0, 0, data.rows(),
                    data.cols() - 1);
            Matrix labels = new Matrix(data, 0, data.cols() - 1, data.rows(), 1);
            double startTime = System.currentTimeMillis();
            learner.train(features, labels);
            double elapsedTime = System.currentTimeMillis() - startTime;
            sb.append("Time to train (in seconds): " + elapsedTime / 1000.0
                    + "\n");
            double trainAccuracy = learner.measureAccuracy(features, labels, null);
            sb.append("Training set accuracy: " + trainAccuracy + "\n");
            Matrix testFeatures = new Matrix(testData, 0, 0, testData.rows(),
                    testData.cols() - 1);
            Matrix testLabels = new Matrix(testData, 0, testData.cols() - 1,
                    testData.rows(), 1);
            Matrix confusion = new Matrix();
            double testAccuracy = learner.measureAccuracy(testFeatures,
                    testLabels, confusion);
            sb.append("Test set accuracy: " + testAccuracy + "\n");
            if (printConfusionMatrix) {
                sb.append("\nConfusion matrix: (Row=target value, Col=predicted value)\n");
                sb.append(confusion.print());
                sb.append("\n\n");
            }
        } else if (evalMethod.equals("random")) {
            sb.append("\n Calculating accuracy on a random hold-out set...\n");
            double trainPercent = 1 - Double.parseDouble(evalParameter);
            if (trainPercent < 0 || trainPercent > 1)
                throw new Exception(
                        "Percentage for random evaluation must be between 0 and 1");
            sb.append("Percentage used for training: " + trainPercent + "\n");
            sb.append("Percentage used for testing: "
                    + Double.parseDouble(evalParameter) + "\n");
            data.shuffle(rand);
            int trainSize = (int) (trainPercent * data.rows());
            Matrix trainFeatures = new Matrix(data, 0, 0, trainSize,
                    data.cols() - 1);
            Matrix trainLabels = new Matrix(data, 0, data.cols() - 1,
                    trainSize, 1);
            Matrix testFeatures = new Matrix(data, trainSize, 0, data.rows()
                    - trainSize, data.cols() - 1);
            Matrix testLabels = new Matrix(data, trainSize, data.cols() - 1,
                    data.rows() - trainSize, 1);
            double startTime = System.currentTimeMillis();
            learner.train(trainFeatures, trainLabels);
            double elapsedTime = System.currentTimeMillis() - startTime;
            sb.append("Time to train (in seconds): " + elapsedTime / 1000.0
                    + "\n");
            double trainAccuracy = learner.measureAccuracy(trainFeatures,
                    trainLabels, null);
            sb.append("Training set accuracy: " + trainAccuracy + "\n");
            Matrix confusion = new Matrix();
            double testAccuracy = learner.measureAccuracy(testFeatures,
                    testLabels, confusion);
            sb.append("Test set accuracy: " + testAccuracy + "\n");
            if (printConfusionMatrix) {
                sb.append("\nConfusion matrix: (Row=target value, Col=predicted value)\n");
                sb.append(confusion.print());
                sb.append("\n\n");
            }
        } else if (evalMethod.equals("cross")) {
            sb.append("\n Calculating accuracy using cross-validation...\n");
            int folds = Integer.parseInt(evalParameter);
            if (folds <= 0)
                throw new Exception("Number of folds must be greater than 0");
            sb.append("Number of folds: " + folds + "\n");
            int reps = 1;
            double sumAccuracy = 0.0;
            double elapsedTime = 0.0;
            for (int j = 0; j < reps; j++) {
                data.shuffle(rand);
                for (int i = 0; i < folds; i++) {
                    int begin = i * data.rows() / folds;
                    int end = (i + 1) * data.rows() / folds;
                    Matrix trainFeatures = new Matrix(data, 0, 0, begin,
                            data.cols() - 1);
                    Matrix trainLabels = new Matrix(data, 0, data.cols() - 1,
                            begin, 1);
                    Matrix testFeatures = new Matrix(data, begin, 0, end
                            - begin, data.cols() - 1);
                    Matrix testLabels = new Matrix(data, begin,
                            data.cols() - 1, end - begin, 1);
                    trainFeatures.add(data, end, 0, data.rows() - end);
                    trainLabels.add(data, end, data.cols() - 1, data.rows()
                            - end);
                    double startTime = System.currentTimeMillis();
                    learner.train(trainFeatures, trainLabels);
                    elapsedTime += System.currentTimeMillis() - startTime;
                    double accuracy = learner.measureAccuracy(testFeatures,
                            testLabels, null);
                    sumAccuracy += accuracy;
                    sb.append("Rep=" + j + ", Fold=" + i + ", Accuracy="
                            + accuracy + "\n");
                }
            }
            elapsedTime /= (reps * folds);
            sb.append("Average time to train (in seconds): " + elapsedTime
                    / 1000.0 + "\n");
            sb.append("Mean accuracy=" + (sumAccuracy / (reps * folds)) + "\n");
        }
    }

    /**
     * Class for parsing out the command line arguments
     */
    public void resetStringBuilder() {
        sb = new StringBuilder();
    }

    public String getResults() {
        return sb.toString();
    }

}
