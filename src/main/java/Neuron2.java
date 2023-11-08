import java.util.ArrayList;
import java.util.List;

public class Neuron2 {
    private List<Double> weights = new ArrayList<>();
    private double bias = Util.getRandomDouble(-1,1);
    private int numberOfInput = 1;

    private double oldBias = 0;
    private List<Double> oldWeights = new ArrayList<>();

    public Neuron2(int numberOfInput)
    {
        this.numberOfInput = numberOfInput;
        oldBias = bias;
        for(int i = 0; i < numberOfInput; i++)
        {
            double randomVal =Util.getRandomDouble(-1,1);
            weights.add(randomVal);
            oldWeights.add(randomVal);
        }
    }

    private int id = -1;
    public Neuron2(int numberOfInput, int id)
    {
        this(numberOfInput);
        this.id = id;
    }

    public double compute(List<Double> inputs)
    {
//        assert inputs.size() == weights.size();

        double jumlah = bias;
        for(int i = 0; i < numberOfInput; i++)
        {
            jumlah += (weights.get(i) * inputs.get(i));
        }
        double ouput = Util.sigmoid(jumlah);

        return ouput;
    }

    public void mutateNeuron(double learningFactor)
    {
        int propertyToChange = Util.getRandomInt(0,2);
        int mutateDirection = Util.getRandomInt(0,2);
        double learningFactor2 =mutateDirection == 0 ? learningFactor : (0 - learningFactor);
        if(propertyToChange == 0)
        {
            oldBias = bias;
            bias += learningFactor2;
        }
        else
        {
            int weightIndexToChange = Util.getRandomInt(0, numberOfInput);
            for(int i = 0; i < numberOfInput; i++)
            {
                oldWeights.set(i, weights.get(i));
            }
            weights.set(weightIndexToChange, weights.get(weightIndexToChange) + learningFactor2);
        }
    }

    public void forgetMutation()
    {
        bias = oldBias;
        for(int i = 0; i < numberOfInput; i++)
        {
            weights.set(i, oldWeights.get(i));
        }
    }

    public void rememberMutation()
    {
        oldBias = bias;
        for(int i = 0; i < numberOfInput; i++)
        {
            oldWeights.set(i, weights.get(i));
        }
    }
}
