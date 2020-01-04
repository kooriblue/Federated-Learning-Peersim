package fedlearning.util;

import java.util.Random;

public class Init {

    /**
     * Returns the matrix with Gaussian initialization
     * @param matrix matrix to initialize
     * @return
     */
    public static Matrix randn(Matrix matrix) {
        int n = matrix.getNumberOfRows();
        int m = matrix.getNumberOfColumns();

        Random random = new Random();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrix.setValue(i, j, random.nextGaussian());
            }
        }
        return matrix;
    }
}
