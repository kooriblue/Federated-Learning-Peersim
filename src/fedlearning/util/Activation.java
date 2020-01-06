package fedlearning.util;

public class Activation {
    public static Matrix sigmoid(Matrix x) {
        for (int i = 0; i < x.getNumberOfRows(); i++) {
            for (int j = 0; j < x.getNumberOfColumns(); j++) {
                double val = 1 / ( 1 + Math.exp( x.getValue(i, j) ) );
                x.setValue(i, j, val);
            }
        }
        return x;
    }

    public static Matrix relu(Matrix x) {
        for (int i = 0; i < x.getNumberOfRows(); i++) {
            for (int j = 0; j < x.getNumberOfColumns(); j++) {
                x.setValue(i, j, Math.max(x.getValue(i, j), 0.0));
            }
        }
        return x;
    }


    /**
     * Returns the result of softmax
     * @param x it's not a row vector!
     * @return matrix with softmax
     */
    public static Matrix softmax(Matrix x) {

        for (int i = 0; i < x.getNumberOfColumns(); i++) {
            double temp = 0.0;
            double[] classes = new double[x.getNumberOfRows()];

            for (int j = 0; j < x.getNumberOfRows(); j++) {
                classes[j] = Math.exp(x.getValue(j, i));
                temp += classes[j];
            }

            for (int j = 0; j < x.getNumberOfRows(); j++) {
                x.setValue(j, i, classes[j] / temp);
            }
        }
        return x;
    }

    public static void main(String[] args) {
        double[] test = {1, 2, 3};
        Matrix x = new Matrix(test, true);
        softmax(x);
    }
}
