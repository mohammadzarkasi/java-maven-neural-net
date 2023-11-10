import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Network2 {
    List<List<Neuron2>> neurons = new ArrayList<>();
    public Network2(List<Integer> layers)
    {
        System.out.println("constructor network2, creating network...");
        for(int i = 0; i < layers.size(); i++) {
            int numOfNeuron = layers.get(i);
            List<Neuron2> neuron2s = new ArrayList<>();
            if (i == 0) {
                for (int j = 0; j < numOfNeuron; j++) {
                    var neuron2 = new Neuron2(1);
                    neuron2s.add(neuron2);
                }
            } else if (i > 0) {
                int numOfNeuronFromPrevLayer = layers.get(i - 1);
                for (int j = 0; j < numOfNeuron; j++) {
                    var neuron2 = new Neuron2(numOfNeuronFromPrevLayer);
                    neuron2s.add(neuron2);
                }
            }

            neurons.add(neuron2s);
        }
    }

    public List<Double> predict(List<Double> inputs)
    {
        return predict(inputs, 0);
    }

    private List<Double> predict(List<Double> inputs, int indexLayer)
    {
        List<Double> outputs = new ArrayList<>();
        var neuronsThisLayer = neurons.get(indexLayer);
        for(var i = 0; i < neuronsThisLayer.size(); i++)
        {
            var neuron = neuronsThisLayer.get(i);
            if(indexLayer == 0)
            {
                var output = neuron.compute(Arrays.asList(inputs.get(i)));
                outputs.add(output);
            }
            else if(indexLayer == neurons.size()-1)
            {
                var output = neuron.compute(inputs, false);
                outputs.add(output);
            }
            else
            {
                var output = neuron.compute(inputs);
                outputs.add(output);
            }
        }
        if(indexLayer < neurons.size()-1)
        {
            return predict(outputs, indexLayer+1);
        }
        return outputs;

    }

    public void backprop(List<Double> predictions, Integer expectedLabel)
    {
        var layerSize = neurons.size();
        List<Double> losses = new ArrayList<>();
        for(var i = layerSize-1; i>0; i--)
        {
            var neuronsInCurrentLayer = neurons.get(i);
            var neuronsInLeftLayer = neurons.get(i-1);
            if(i == layerSize)
            {
                for(var j = 0; j < neuronsInCurrentLayer.size(); j++)
                {
                    var n = neuronsInCurrentLayer.get(j);
                    var inputs = neuronsInLeftLayer.stream().map(n2 -> n2.getLastComputeResult()).toList();
                    n.backprop(inputs, predictions.get(j), expectedLabel.doubleValue());
                }
            }
        }

    }

    private Random myrandom = new Random();
    private Neuron2 pickRandomNeuron()
    {
        int indexLayer = myrandom.nextInt(neurons.size());
        var selectedLayer = neurons.get(indexLayer);
        var indexNeuron = myrandom.nextInt(selectedLayer.size());
        return selectedLayer.get(indexNeuron);
    }

    private MyPair<Integer, Double> getMaxValIndex(List<Double> data)
    {
        int pos = 0;
        MyPair<Integer, Double> result = new MyPair<>();
        for(var i = 0; i < data.size(); i++)
        {
            if(data.get(pos) < data.get(i))
            {
                pos = i;
            }
        }
        result.left = pos;
        result.right = data.get(pos);
        return result;
    }

    public void trains(int epochs, int learningFactor, List<List<Double>> data, List<Integer> labels)
    {
        double bestEpochLoss = Double.MAX_VALUE;
        for (int epoch = 0; epoch < epochs; epoch++)
        {
//            var neuron = pickRandomNeuron();
//            neuron.mutateNeuron(learningFactor);

//            List<Integer> predictionLabels = new ArrayList<>();
//            List<Double> predictionValues = new ArrayList<>();

            for(int i = 0; i < data.size(); i++)
            {
                var input = data.get(i);
                var expectedLabel = labels.get(i);

                var prediction = predict(input);
                System.out.println("inputs     : " + Arrays.toString(input.stream().map(x -> x.doubleValue()).toArray()));
                System.out.println("predictions: " + Arrays.toString(prediction.stream().map(x -> x.doubleValue()).toArray()));
                var prediction2 = getMaxValIndex(prediction);
//                predictionLabels.add(prediction2.left);
//                predictionValues.add(prediction2.right);
                System.out.println("predicted  : " + prediction2.left + ", answer: " + labels.get(i));

                var loss = Util.meanSquareLoss3(prediction, prediction2.left, expectedLabel);

                if(bestEpochLoss > loss)
                {
                    bestEpochLoss = loss;
                }

                backprop(prediction, expectedLabel);
            }
            System.out.println("=====");

//            double epochLoss = Util.meanSquareLoss2(labels, predictionLabels, predictionValues);

//            if (epoch % 10 == 9) {
//                System.out.println(String.format("Epoch: %s | bestEpochLoss: %.15f | thisEpochLoss: %.15f", epoch, bestEpochLoss, epochLoss));
//            }

//            if(bestEpochLoss > epochLoss)
//            {
//                bestEpochLoss = epochLoss;
//                neuron.rememberMutation();
//            }
//            else
//            {
//                neuron.forgetMutation();
//            }
        }
    }
}


class MyPair<T1,T2>{
    public T1 left;
    public T2 right;
    public MyPair(){

    }
    public MyPair(T1 t1, T2 t2)
    {
        left = t1;
        right = t2;
    }
}