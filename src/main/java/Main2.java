import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main2 {
    public static void main(String[] args) {
//        var n2 = new Neuron2(4);
//        double predict = n2.compute(Arrays.asList(1.0,2.0,3.0));
//        System.out.println("predict: " + predict);

        var network2 = new Network2(Arrays.asList(3, 2));

        List<List<Integer>> data = new ArrayList<>();
        data.add(Arrays.asList(3, 1, 22));
        data.add(Arrays.asList(1, 2, 38));
        data.add(Arrays.asList(3, 2, 26));
        data.add(Arrays.asList(1, 2, 35));
        data.add(Arrays.asList(2, 2, 14));
        data.add(Arrays.asList(1, 1, 54));
        data.add(Arrays.asList(3, 1, 35));
        List<Integer> answers = Arrays.asList(0, 1, 1, 1, 1, 0, 0);


//        List<List<Double>> data2 = new ArrayList<>();
//        for(var i = 0; i < data.size(); i++) {
//            List<Double> data22=new ArrayList<>();
//            for (var j = 0; j < data.get(i).size(); j++) {
//                data22.add(data.get(i).get(j)+0.0);
//            }
//            data2.add(data22);
//        }

        network2.trains(100, 1,
                data
                        .stream()
                        .map(
                                x -> x
                                        .stream()
                                        .map(
                                                y -> y.doubleValue()
                                        )
                                        .toList()
                        )
                        .toList(), answers);

    }
}
