package fedlearning.util;

public class Matrix {
    private double[][] matrix;

    private int numberOfRows;
    private int numberOfColumns;

    /**
     * Constructs a matrix with the specified number of rows and cols and fills with
     * the default vlaue 0.0 .
     * @param numberOfRows  number of rows
     * @param numberOfColumns number of cols
     */
    public Matrix(int numberOfRows, int numberOfColumns) {
        this(numberOfRows, numberOfColumns, 0.0);
    }

    /**
     * Constructs a matrix with the specified number of rows and cols and fills with
     * the specified value.
     * @param numberOfRows number of rows
     * @param numberOfColumns number of cols
     * @param value value to fill
     */
    public Matrix(int numberOfRows, int numberOfColumns, double value) {
        this.numberOfRows = numberOfRows;
        this.numberOfColumns = numberOfColumns;

        matrix = new double[numberOfRows][numberOfColumns];

        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfColumns; j++) {
                matrix[i][j] = value;
            }
        }
    }

    /**
     * Constructs a matrix based on the specified array (wrapping).
     * @param matrix matrix to wrap
     */
    public Matrix(double[][] matrix) {
        this.numberOfRows = matrix.length;
        this.numberOfColumns = matrix[0].length;

        this.matrix = new double[numberOfRows][numberOfColumns];

        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfColumns; j++) {
                this.matrix[i][j] = matrix[i][j];
            }
        }
    }

    /**
     * Constructs a matrix from the specified vector. The one of the dimension is the
     * length of the specified vector and the other dimension is 1. The order of dimensions
     * is specified by type of the vector, specified at the second parameter.
     * @param vector vector to convert matrix
     * @param isRowVector the type of the vector
     */
    public Matrix(double[] vector, boolean isRowVector) {

        if (isRowVector) {
            numberOfRows = 1;
            numberOfColumns = vector.length;
            matrix = new double[numberOfRows][numberOfColumns];
            for (int i = 0; i < numberOfColumns; i++) {
                matrix[0][i] = vector[i];
            }
        } else {
            numberOfRows = vector.length;
            numberOfColumns = 1;
            matrix = new double[numberOfRows][numberOfColumns];
            for (int i = 0; i < numberOfRows; i++) {
                matrix[i][0] = vector[i];
            }
        }
    }

    public Matrix(int length, boolean isRowVector) {

        if (isRowVector) {
            numberOfRows = 1;
            numberOfColumns = length;
            matrix = new double[numberOfRows][numberOfColumns];
            for (int i = 0; i < numberOfColumns; i++) {
                matrix[0][i] = 0;
            }
        } else {
            numberOfRows = length;
            numberOfColumns = 1;
            matrix = new double[numberOfRows][numberOfColumns];
            for (int i = 0; i < numberOfRows; i++) {
                matrix[i][0] = 0;
            }
        }
    }

    /**
     * Constructs a deep copy of the specified matrix.
     * @param matrix matrix to copy
     */
    public Matrix(Matrix matrix) {
        this.numberOfRows = matrix.numberOfRows;
        this.numberOfColumns = matrix.numberOfColumns;

        this.matrix = new double[numberOfRows][numberOfColumns];

        for (int i =0 ; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfColumns; j++) {
                this.matrix[i][j] = matrix.matrix[i][j];
            }
        }
    }

    @Override
    public String toString() {
        String res = "numberOfRows: " + numberOfRows + " numberOfColumns: "
                 + numberOfColumns + "\n";

        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfColumns; j++) {
                res += matrix[i][j] + " ";
            }
            res += "\n";
        }
        return res;
    }

    /**
     * Returns the reference of the matrix which cells are filled with the specified value.
     * @param value value to fill
     * @return reference of the filled matrix
     */
    public Matrix fill(double value) {
        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfColumns; j++) {
                matrix[i][j] = value;
            }
        }
        return this;
    }

    /**
     * Returns the reference of the matrix that was multiplied by the specified constant.
     * @param value value to multiply
     * @return reference of the multiplied matrix
     */
    public Matrix mul(double value) {
        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfColumns; j++) {
                matrix[i][j] *= value;
            }
        }
        return this;
    }

    /**
     * Returns a new matrix object that is the current matrix multiplied by the
     * specified matrix.
     * @param matrix matrix to multiply
     * @return result of the multiplication as a new matrix object
     * @throws RuntimeException if the matrices cannot be multiplied
     */
    public Matrix mul(Matrix matrix) {
        if (matrix.numberOfRows == 1 && matrix.numberOfColumns == 1) {
            return new Matrix(mul(matrix.matrix[0][0]));
        }

        if (numberOfColumns != matrix.numberOfRows) {
            throw  new RuntimeException("Matrix with dimensions " + numberOfRows + "x" +
                    numberOfColumns + "cannot be multiplied by a matrix with dimensions " +
                    matrix.numberOfRows + "x" + matrix.numberOfColumns);
        }

        Matrix res = new Matrix(numberOfRows, matrix.numberOfColumns);

        for (int i = 0; i < res.numberOfRows; i++) {
            for (int j = 0; j < res.numberOfColumns; j++) {
                double sum = 0.0;
                for (int k = 0; k < numberOfColumns; k++) {
                    sum += this.matrix[i][k] * matrix.matrix[k][j];
                }
                res.matrix[i][j] = sum;
            }
        }
        return res;
    }

    /**
     * Returns a new matrix object that is the current matrix added by the
     * specified matrix.
     * @param bias matrix to add
     * @return result of the addition as a new matrix object
     */
    public Matrix add(Matrix bias) {
        if (numberOfRows != bias.getNumberOfRows()) {
            throw  new RuntimeException("Matrix with dimensions " + numberOfRows + "x" +
                    numberOfColumns + " cannot be added by a matrix with dimensions " +
                    bias.getNumberOfRows() + "x1.");
        }

        for (int i = 0; i < numberOfColumns; i++) {
            for (int j = 0; j < numberOfRows; j++) {
                matrix[j][i] += bias.getValue(j, 0);
            }
        }
        return this;
    }

    /**
     * Set matrix[i][j] with value
     * @param i row
     * @param j col
     * @param value value to set
     */
    public void setValue(int i, int j, double value) {
        this.matrix[i][j] = value;
    }

    /**
     * Return the value of matrix[i][j]
     * @param i row
     * @param j col
     * @return value of matrix[i][j]
     */
    public double getValue(int i, int j) {
        return this.matrix[i][j];
    }

    /**
     * Returns the transported matrix
     * @return the transported matrix
     */
    public Matrix T() {
        double[][] res = new double[numberOfColumns][numberOfRows];
        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfColumns; j++) {
                res[j][i] = matrix[i][j];
            }
        }
        matrix = res;
        int temp = numberOfRows;
        numberOfRows = numberOfColumns;
        numberOfColumns = temp;
        return this;
    }

    public int getNumberOfColumns() { return numberOfColumns; }

    public int getNumberOfRows() { return numberOfRows; }


    public static void main(String[] args) {
        double[][] test = {{1, 2, 3}, {4, 5, 6}};
        double[] test1 = {1, 1};
        Matrix matrix = new Matrix(test);
        Matrix bias = new Matrix(test1, true);
        System.out.println(matrix);
        System.out.println(matrix.add(bias));
        System.out.print(matrix.T());
    }
}
