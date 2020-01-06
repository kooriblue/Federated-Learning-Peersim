package fedlearning.model;

import fedlearning.util.Activation;
import fedlearning.util.Init;
import fedlearning.util.Matrix;

public class Layer {

    private int inputSize;
    private int outputSize;

    private Matrix weight;
    private Matrix bias;

    public Layer(int inputSize, int outputSize) {
        this.inputSize = inputSize;
        this.outputSize = outputSize;

        weight = new Matrix(outputSize, inputSize);
        bias = new Matrix(outputSize, false);

        weight = Init.randn(weight);
        bias = Init.randn(bias);
    }

    /**
     * Return current layer's output
     * @param x
     * @param activation relu, sigmoid
     * @return
     */
    public Matrix activate(Matrix x, String activation) {
        switch (activation) {
            case "sigmoid":
                return Activation.sigmoid( weight.mul(x).add(bias) );
            case "relu":
                return Activation.relu( weight.mul(x).add(bias) );
            case "softmax":
                return Activation.softmax( weight.mul(x).add(bias) );
            default:
                throw new RuntimeException("Unexpected activation name!");
        }
    }

    @Override
    public String toString() {
        return "Layer{" +
                "inputSize=" + inputSize +
                ", outputSize=" + outputSize +
                ", weight=" + weight +
                ", bias=" + bias +
                '}';
    }
}
