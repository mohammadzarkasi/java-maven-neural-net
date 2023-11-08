public class Neuron {
    //private Random random = new Random();
    private double bias = Util.getRandomDouble(-1,1);
    private double weight1 = Util.getRandomDouble(-1,1);
    private double weight2 = Util.getRandomDouble(-1,1);

    private double oldBias, oldWeight1, oldWeight2;
    public Neuron(){
        System.out.println("constructor for neuron, bias: " + bias + ", weight1: " + weight1 +", weight2: " + weight2);
        oldBias = bias;
        oldWeight1 = weight1;
        oldWeight2 = weight2;
    }

    private int id = -1;
    public Neuron(int id)
    {
        this();
        this.id = id;
    }

    public double compute(double input1, double input2)
    {
        double preActivation = (weight1 * input1) + (weight2 * input2) + bias;
        double output = Util.sigmoid(preActivation);
        return output;
    }

    public void mutate(double learningFactor)
    {
        int propertyToChange = Util.getRandomInt(0, 3);
        double changeFactor = 0;
//        changeFactor = Util.getRandomDouble(-1,1);
        changeFactor = learningFactor;

        if(propertyToChange == 0)
        {
            oldBias = bias;
            bias += changeFactor;
            System.out.println("Neuron " + id +", mutate bias to: " + bias);
        }
        else if (propertyToChange == 1)
        {
            oldWeight1 = weight1;
            weight1 += changeFactor;
            System.out.println("Neuron " + id +", mutate weight1 to: " + weight1);
        }
        else
        {
            oldWeight2 = weight2;
            weight2 += changeFactor;
            System.out.println("Neuron " + id +", mutate weight2 to: " + weight2);
        }
    }

    public void forgetMutation()
    {
        bias = oldBias;
        weight1 = oldWeight1;
        weight2 = oldWeight2;
        System.out.println("Neuron " + id +", forget mutation");
    }

    public void rememberMutation()
    {
        oldBias = bias;
        oldWeight1 = weight1;
        oldWeight2 = weight2;
        System.out.println("Neuron " + id +", remember mutation");
    }
}
