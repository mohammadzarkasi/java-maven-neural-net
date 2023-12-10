import java.util.ArrayList;
import java.util.List;

public class Neuron2 {
    private double bias;
    private double newBias;
    private boolean historyEnabled;
    private final int numberOfInput;
    private List<Double> weights;

    //    private double oldBias = 0;
//    private double errorValue = 0;
//    private double lastComputeResult = 0;
//    private List<Double> oldWeights = new ArrayList<>();
    private List<Double> newWeights;

    private List<Double> historyDeltaPredictions;
    //  arr[x][y], x adalah nomor riwayat, y adalah index input
    private List<List<Double>> historyInputs;

    public Neuron2(int numberOfInput) {
        historyEnabled = true;
        this.numberOfInput = numberOfInput;
        historyDeltaPredictions = new ArrayList<>();
        historyInputs = new ArrayList<>();
        weights = new ArrayList<>();
        newWeights = new ArrayList<>();

        bias = Util.getRandomDouble(-1, 1);
//        oldBias = bias;
        for (int i = 0; i < numberOfInput; i++) {
            double randomVal = Util.getRandomDouble(-1, 1);
            weights.add(randomVal);
//            oldWeights.add(randomVal);
        }
    }

    private int id = -1;

    public Neuron2(int numberOfInput, int id) {
        this(numberOfInput);
        this.id = id;
    }

    public void enableHistory(boolean b) {
        historyEnabled = b;
    }

    public boolean isHistoryEnabled() {
        return historyEnabled;
    }


//    public void setErrorValue(double predicted, double expected) {
//        var diff = predicted - expected;
////        errorValue = diff * diff;
//    }

//    public double getErrorValue() {
//        return errorValue;
//    }

    public double forward(List<Double> inputs) {
        double jumlah = bias;
        if (isHistoryEnabled() == true) {
            historyInputs.add(inputs);
        }
        for (int i = 0; i < numberOfInput; i++) {
            var weight = weights.get(i);
            var input = inputs.get(i);
            jumlah += (weight * input);
        }
        return jumlah;
//        double output = Util.sigmoid(jumlah);
//        setLastComputeResult(output);
//        return output;
    }

//    private void setLastComputeResult(double v) {
//        lastComputeResult = v;
//    }

//    public double getLastComputeResult() {
//        return lastComputeResult;
//    }

//    public double forward(List<Double> inputs) {
//        return forward(inputs, true);
//    }

    //https://www.youtube.com/watch?v=-zI1bldB8to
//    public void backprop(List<Double> lastInputs, double prediction, double expected) {
//        var delta_cost = 2 * (prediction - expected);
//        var bias2 = delta_cost;
//        for (var i = 0; i < weights.size(); i++) {
//            var weight2 = delta_cost * lastInputs.get(i);
//        }
//    }

//    public void mutateNeuron(double learningFactor) {
//        int propertyToChange = Util.getRandomInt(0, 2);
//        int mutateDirection = Util.getRandomInt(0, 2);
//        double learningFactor2 = mutateDirection == 0 ? learningFactor : (0 - learningFactor);
//        if (propertyToChange == 0) {
////            oldBias = bias;
//            bias += learningFactor2;
//        } else {
//            int weightIndexToChange = Util.getRandomInt(0, numberOfInput);
//            for (int i = 0; i < numberOfInput; i++) {
//                oldWeights.set(i, weights.get(i));
//            }
//            weights.set(weightIndexToChange, weights.get(weightIndexToChange) + learningFactor2);
//        }
//    }

//    public void forgetMutation() {
//        bias = oldBias;
//        for (int i = 0; i < numberOfInput; i++) {
//            weights.set(i, oldWeights.get(i));
//        }
//    }
//
//    public void rememberMutation() {
//        oldBias = bias;
//        for (int i = 0; i < numberOfInput; i++) {
//            oldWeights.set(i, weights.get(i));
//        }
//    }

    public void addHistoryDeltaPrediction(double v) {
        historyDeltaPredictions.add(v);
    }

    public List<Double> getHistoryDeltaPredictions() {
        return historyDeltaPredictions;
    }

    public double getMeanDeltaPredictions() {
        var sum = 0.0;
        for (var i = 0; i < historyDeltaPredictions.size(); i++) {
            sum += historyDeltaPredictions.get(i);
        }
        var result = sum / historyDeltaPredictions.size();
        return result;
    }

//    https://www.youtube.com/watch?v=-zI1bldB8to&ab_channel=BevanSmith2
    public void calculateDerivative(double learningRate) {
        var deltaPrediction = getMeanDeltaPredictions() * 2;
        newWeights.clear();
        newBias = bias - (learningRate * deltaPrediction);
        for (var i = 0; i < numberOfInput; i++) {
//            var listDeltaWeight = new ArrayList<Double>();
            var sumDeltaWeight = 0.0;
            for (var j = 0; j < historyInputs.size(); j++) {
                var inputs = historyInputs.get(j);
                var input = inputs.get(i);
                var dw = input * deltaPrediction;
//                listDeltaWeight.add(deltaWeight);
                sumDeltaWeight += dw;
            }

            var deltaWeight = sumDeltaWeight / historyInputs.size();
            newWeights.add(weights.get(i) - (learningRate * deltaWeight));
        }
    }
}
