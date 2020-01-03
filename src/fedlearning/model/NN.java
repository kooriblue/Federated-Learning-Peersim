package fedlearning.model;

public class NN {

    /**
     * The learning parameter is 0.0001 by default.
     */
    protected static final String PAR_LAMBDA = "NN.lambda";
    protected double lambda = 0.0001;

    protected double bias;

    public NN() {}
}
