package fedlearning.model;

import fedlearning.util.Matrix;
import peersim.config.Configuration;

import java.util.Arrays;

public class ANN {

    protected static final String PAR_HIDDEN = "ann.hiddenLayers";
    protected static final String PAR_GAMMA = "ann.gammas";
    protected static final String PAR_LAMBDA = "ann.lambda";
    protected static final String PAR_LR = "ann.learningRate";

    private Layer[] layers = null;

    private double[] gammas = null;

    private int numberOfClasses = 10;
    private double lambda = 0.001;
    private double learningRate = 0.001;

    public ANN(ANN a) { }

    @Override
    public Object clone() {
        return new ANN(this);
    }

    public ANN(String prefix) {
        layers = new Layer[3];
        layers[0] = new Layer(784, 200);
        layers[1] = new Layer(200, 200);
        layers[2] = new Layer(200, 10);
    }

    private Matrix forward(Matrix x) {
        x = layers[0].activate(x, "relu");
        x = layers[1].activate(x, "relu");
        x = layers[2].activate(x, "softmax");
        System.out.println(x);
        return x;
    }

    public static void main(String[] args) {
        ANN test = new ANN("test");
        Matrix x = new Matrix(784, 1, 0);

        test.forward(x);

//        System.out.println(test.forward(x));

    }

}
