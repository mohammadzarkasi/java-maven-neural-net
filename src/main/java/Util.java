import java.util.List;
import java.util.Random;

public class Util {
    public static double sigmoid(double input)
    {
        return 1 / (1 + Math.exp(-input));
    }

    public static double getRandomDouble(double min,double max)
    {
        return min + ((max-min) * Math.random());
    }

    private  static  Random random = new Random();
    public static int getRandomInt(int min, int max)
    {
        return random.nextInt(max-min) - min;
    }

    public static double meanSquareLoss(List<Double> correctAnswers, List<Double> predictedAnswers){
        double sumSquare = 0;
        for (int i = 0; i < correctAnswers.size(); i++){
            double error = correctAnswers.get(i) - predictedAnswers.get(i);
            sumSquare += (error * error);
        }
        return sumSquare / (correctAnswers.size());
    }
}
