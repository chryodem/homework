import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: andreirybin
 * Date: 3/24/13
 * Time: 7:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class ClusteringModel extends SupervisedLearner {
    private Matrix _features, _labels;
    private final int k = 5;

    @Override
    public void train(Matrix features, Matrix labels) throws Exception {
        _features = features;
        _labels = labels;

        trainKMeans();

    }

    private void trainKMeans() {

        //list where the centroids will be stored
        List<CentroidNode> centroidNodeList = new ArrayList<CentroidNode>();
        for (int i = 0; i < k; i++) {
            centroidNodeList.add(new CentroidNode(_features.row(i)));
        }

        for (int i = 0; i < _features.rows(); i++) {
            double[] row = _features.row(i);
            //this is the first iteration when assigning rows to centroids
            calculateIndivCentroids(row, centroidNodeList, i);
        }

        //verified that the first part works correctly
        //now need to implement the centroid
    }

    private void calculateIndivCentroids(double[] row, List<CentroidNode> centroidNodeList, int i) {
        FibonacciHeap<CentroidNode> heap = new FibonacciHeap<CentroidNode>();
        for (CentroidNode cNode : centroidNodeList) {
            cNode.calcDistance(row, _features);
            double key = cNode.getDistance();
            Node<CentroidNode> tNode = new Node<CentroidNode>(key, cNode);
            heap.insert(tNode);
        }

        CentroidNode pointer = heap.extractMin().getData();
        pointer.addPosition(i);
        //then add this specific row to this centroid

        //sum squared distance is the sum of the distances of each of the rows to its centroid
        //new centroid is calculated by calculating the average distance of each of the individual
        //columns of each of the centroids, then this new value is assigned to the centroid's row
        //keep doing this process until the centroid doesn't move anymore.
        //have each centroid have 2 individual double[] and then compare to see if there was any imporvement made

    }

    @Override
    public void predict(double[] features, double[] labels) throws Exception {

    }
}
