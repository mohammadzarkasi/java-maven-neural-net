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

    public static double meanSquareLoss2(List<Integer> labels, List<Integer> predictionLabels, List<Double> predictionValues) {
        double sumSquare = 0;
        for(var i = 0; i < labels.size(); i++)
        {
            var label = labels.get(i);
            var predictLabel = predictionLabels.get(i);
            var predictVal = predictionValues.get(i);

            if(label == predictLabel)
            {
                double error = 1 - predictVal;
                sumSquare += error * error;
            }
            else
            {
                double error = 20 - predictVal;
                sumSquare += error * error;
            }
        }
        return sumSquare / labels.size();
    }
}
