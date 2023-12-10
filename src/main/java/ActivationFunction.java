import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class ActivationFunction {
    public abstract List<Double> forward(List<Double> inputs);
}

class SoftmaxActivationFunction extends ActivationFunction {
    public List<Double> forward(List<Double> inputs) {
        List<Double> outputs = new ArrayList<>();

        for (int i = 0; i < inputs.size(); i++) {
            outputs.add(inputs.get(i));
        }

//        cari nilai terbesar di dalam output
        var maxValue = Double.MIN_VALUE;
        for (int i = 0; i < outputs.size(); i++) {
            var cv = outputs.get(i);
            if (cv > maxValue) {
                maxValue = cv;
            }
        }

//        update nilai di dalam output menggunakan maxvalue
        for (int i = 0; i < outputs.size(); i++) {
            var nv = outputs.get(i) - maxValue;
            nv = Math.exp(nv);
            outputs.set(i, nv);
        }

//        menghitung normalized output

        double sum_values = 0;
        for (int i = 0; i < outputs.size(); i++) {
            sum_values += outputs.get(i);
        }

        for (int i = 0; i < outputs.size(); i++) {
            var val = outputs.get(i);
            outputs.set(i, val / sum_values);
        }

        return outputs;
    }
}
