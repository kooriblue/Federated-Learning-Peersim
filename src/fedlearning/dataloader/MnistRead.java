package fedlearning.dataloader;

import fedlearning.util.Matrix;

import javax.imageio.ImageIO;
import javax.print.DocFlavor;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Timer;

public class MnistRead {

    public static final String TRAIN_IMAGES_FILE = "src/fedlearning/data/mnist/train-images-idx3-ubyte";
    public static final String TRAIN_LABELS_FILE = "src/fedlearning/data/mnist/train-labels-idx1-ubyte";
    public static final String TEST_IMAGES_FILE = "src/fedlearning/data/mnist/t10k-images-idx3-ubyte";
    public static final String TEST_LABELS_FILE = "src/fedlearning/data/mnist/t10k-labels-idx1-ubyte";


    /**
     * change bytes into a hex string.
     *
     * @param bytes bytes
     * @return the returned hex string
     */
    public static String bytesToHex(byte[] bytes) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(bytes[i] & 0xFF);
            if (hex.length() < 2) {
                sb.append(0);
            }
            sb.append(hex);
        }
        return sb.toString();
    }

    /**
     * get images of 'train' or 'test'
     *
     * @param fileName the file of 'train' or 'test' about image
     * @return one row show a `picture`
     */
    public static double[][] getImages(String fileName) {
        double[][] x = null;
        try (BufferedInputStream bin = new BufferedInputStream(new FileInputStream(fileName))) {
            byte[] bytes = new byte[4];
            bin.read(bytes, 0, 4);


            File file = new File("src/fedlearning/data/mnist/train.txt");
            file.createNewFile();
            FileOutputStream outputStream = new FileOutputStream(file);


            if (!"00000803".equals(bytesToHex(bytes))) {                        // 读取魔数
                throw new RuntimeException("Please select the correct file!");
            } else {
                bin.read(bytes, 0, 4);
                int number = Integer.parseInt(bytesToHex(bytes), 16);           // 读取样本总数
                bin.read(bytes, 0, 4);
                int xPixel = Integer.parseInt(bytesToHex(bytes), 16);           // 读取每行所含像素点数
                bin.read(bytes, 0, 4);
                int yPixel = Integer.parseInt(bytesToHex(bytes), 16);           // 读取每列所含像素点数
                x = new double[number][xPixel * yPixel];
                for (int i = 0; i < number; i++) {
                    double[] element = new double[xPixel * yPixel];
                    for (int j = 0; j < xPixel * yPixel; j++) {
                        element[j] = bin.read();                                // 逐一读取像素值
                        // normalization
                        element[j] = bin.read() / 255.0;
                        outputStream.write((element[j] + " ").getBytes());
                    }
                    outputStream.write("\n".getBytes());
                    x[i] = element;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return x;
    }

    /**
     * get labels of `train` or `test`
     *
     * @param fileName the file of 'train' or 'test' about label
     * @return
     */
    public static double[] getLabels(String fileName) {
        double[] y = null;
        try (BufferedInputStream bin = new BufferedInputStream(new FileInputStream(fileName))) {
            byte[] bytes = new byte[4];
            bin.read(bytes, 0, 4);
            if (!"00000801".equals(bytesToHex(bytes))) {
                throw new RuntimeException("Please select the correct file!");
            } else {
                bin.read(bytes, 0, 4);
                int number = Integer.parseInt(bytesToHex(bytes), 16);
                y = new double[number];
                for (int i = 0; i < number; i++) {
                    y[i] = bin.read();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return y;
    }

    /**
     * Return MNIST training dataset
     * @return the Train dataset
     */
    public static double[][] loadFromTrain() {
        File file = new File("src/fedlearning/data/mnist/train.txt");
        double[][] res = new double[60000][784];

        long start = System.currentTimeMillis();

        if (file.isFile() && file.exists()) {
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                String line;
                int row = 0;

                while ((line = bufferedReader.readLine()) != null) {
                    String[] values = line.split(" ");
                    for (int i = 0; i < values.length; i++) {
                        res[row][i] = Double.parseDouble(values[i]);
                    }
                    row++;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        long end = System.currentTimeMillis();
        System.err.println("Loading data: " + (end-start) + " ms");
        return res;
    }


    public static void drawGrayPicture(int[] pixelValues, int width, int high, String fileName) throws IOException {
        BufferedImage bufferedImage = new BufferedImage(width, high, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < high; j++) {
                int pixel = 255 - pixelValues[i * high + j];
                int value = pixel + (pixel << 8) + (pixel << 16);   // r = g = b 时，正好为灰度
                bufferedImage.setRGB(j, i, value);
            }
        }
        ImageIO.write(bufferedImage, "JPEG", new File(fileName));
    }

    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
//        double[][] images = getImages(TRAIN_IMAGES_FILE);
        double[][] images = loadFromTrain();
        long end = System.currentTimeMillis();
        System.out.println(images.length + " " + images[0].length);

        Matrix x = new Matrix(images[0], true);
        Matrix y = new Matrix(images[1], false);
        Matrix res = x.mul(y);

        System.out.println("程序运行时间：" + (end - start) + "ms");
        System.out.println(res);

        /**
        double[] labels = getLabels(TRAIN_LABELS_FILE);

        double[][] test_images = getImages(TEST_IMAGES_FILE);
        double[] test_labels = getLabels(TEST_LABELS_FILE);

        // show image
        int[] img = new int[images[10].length];
        for (int i = 0; i < 28*28; i++) {
            img[i] = (int) images[10][i];
        }
        try {
            drawGrayPicture(img, 28, 28, "test.jpeg");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(images.length);
         */
    }
}
