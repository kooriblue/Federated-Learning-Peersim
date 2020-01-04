package fedlearning.model;

import fedlearning.util.Matrix;
import fedlearning.util.SparseVector;
import peersim.config.Configuration;
import peersim.core.CommonState;

import java.util.Arrays;
import java.util.TreeMap;

public class ANN {

    protected static final String PAR_HIDDEN = "ann.hiddenLayers";
    protected static final String PAR_GAMMA = "ann.gammas";
    protected static final String PAR_LAMBDA = "ann.lambda";
    protected static final String PAR_LR = "ann.learningRate";

    private Matrix[] thetas = null;
    private double[] gammas = null;
    private TreeMap<Integer,Integer> sparseDimMap = null;  // inputDim -> matrixDim
    private int numberOfClasses = 2;
    private double lambda = 0.001;
    private double age = 0;
    private double learningRate = 0.001;

    public ANN() {
        sparseDimMap = new TreeMap<Integer,Integer>();
    }
    // copy constructor (e.g. it is used in clone)
    public ANN(ANN a) {
    }

    @Override
    public Object clone() {
        return new ANN(this);
    }

}
