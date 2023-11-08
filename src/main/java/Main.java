import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("hello");

        Network mynetwork = new Network();
        double prediction = mynetwork.predict(115, 66);
        System.out.println("prediction: " + prediction);


        List<List<Integer>> data = new ArrayList<>();
        data.add(Arrays.asList(115, 66));
        data.add(Arrays.asList(175, 78));
        data.add(Arrays.asList(205, 72));
        data.add(Arrays.asList(120, 67));
        List<Double> answers = Arrays.asList(1.0,0.0,0.0,1.0);

        mynetwork.train(1000, 1, data, answers);

        System.out.println("");
        System.out.println(String.format("  male, 167, 73: network500: %.10f | network1000: %.10f", mynetwork.predict(167, 73), mynetwork.predict(167, 73)));
        System.out.println(String.format("female, 105, 67: network500: %.10f | network1000: %.10f", mynetwork.predict(105, 67), mynetwork.predict(105, 67)));
        System.out.println(String.format("female, 120, 72: network500: %.10f | network1000: %.10f", mynetwork.predict(120, 72), mynetwork.predict(120, 72)));
        System.out.println(String.format("  male, 143, 67: network500: %.10f | network1000: %.10f", mynetwork.predict(143, 67), mynetwork.predict(120, 72)));
        System.out.println(String.format("  male, 130, 66: network500: %.10f | network1000: %.10f", mynetwork.predict(130, 66), mynetwork.predict(130, 66)));

    }
}
