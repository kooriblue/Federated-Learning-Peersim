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

    public static void main(String[] args) {
        Matrix x = new Matrix(5, 5, -10);
        sigmoid(x);
        System.out.println(x);

    }
}
