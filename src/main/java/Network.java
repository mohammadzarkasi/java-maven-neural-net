import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Network {
    List<Neuron> neurons = Arrays.asList(
            new Neuron(1), new Neuron(2), new Neuron(3),   // input layer
            new Neuron(4), new Neuron(5),                 // hidden layer
            new Neuron(6)                                // output
    );
    public Network()
    {
        System.out.println("constructor network");
    }

//    public double predict(int input1, int input2, boolean show)

    public double predict(int input1, int input2)
    {
        return neurons.get(5).compute(
                neurons.get(4).compute(
                        neurons.get(2).compute(input1,input2),
                        neurons.get(1).compute(input1,input2)
                ),
                neurons.get(3).compute(
                        neurons.get(1).compute(input1,input2),
                        neurons.get(0).compute(input1,input2)
                )
        );
    }

    public void train(int epochs, double learningFactor, List<List<Integer>> data, List<Double> answer)
    {
        double bestEpochLoss = Double.MAX_VALUE;
        for (int epoch = 0; epoch < epochs; epoch++)
        {
            Neuron neuron = neurons.get(epoch % 6);
            neuron.mutate(learningFactor);

            List<Double> predictions = new ArrayList<>();
            System.out.print("preditions-answer: ");
            for(int i = 0; i < data.size(); i++)
            {
                int data0 = data.get(i).get(0);
                int data1 = data.get(i).get(1);
                double prediction = predict(data0, data1);
                System.out.print(data0 + ","+data1+"-->"+prediction + "-" + answer.get(i) + ", ");
                predictions.add(prediction);
            }
            System.out.println("");

            double epochLoss = Util.meanSquareLoss(answer, predictions);

            if (epoch % 10 == 0) System.out.println(String.format("Epoch: %s | bestEpochLoss: %.15f | thisEpochLoss: %.15f", epoch, bestEpochLoss, epochLoss));

            if(bestEpochLoss > epochLoss)
            {
                bestEpochLoss = epochLoss;
                neuron.rememberMutation();
            }
            else
            {
                neuron.forgetMutation();
            }
        }
    }
}
