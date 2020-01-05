package fedlearning.init;

import fedlearning.dataloader.MnistRead;
import fedlearning.protocol.FederatedAverage;
import fedlearning.util.Matrix;
import peersim.config.Configuration;
import peersim.core.Control;
import peersim.core.Network;

import java.util.Arrays;
import java.util.Random;

/**
 * Allocate data to node in network
 */
public class DataAllocation implements Control {

    /**
     * Network size
     *
     * @config
     */
    private static final String PAR_SIZE = "size";

    /**
     * The protocol to operate on.
     *
     * @config
     */
    private static final String PAR_PROT = "protocol";

    /** Network size */
    private final int size;

    /** Protocol identifier; obtained from config property {@link #PAR_PROT}. */
    private final int pid;

    public DataAllocation(String prefix) {
        size = Configuration.getInt(prefix + "." + PAR_SIZE);
        pid = Configuration.getPid(prefix + "." + PAR_PROT);
    }

    @Override
    public boolean execute() {

        double[][] datasets = MnistRead.loadFromTrain();

        int examples = datasets.length / size;

        double[][] tempData = new double[examples][datasets[0].length];

        /** Sample randomly data from datasets */
        for (int i = 0; i < size; i++) {
            FederatedAverage prot = (FederatedAverage) Network.get(i).getProtocol(pid);
            int[] temp = randomArray(0, datasets.length, examples);

            for (int j = 0; j < temp.length; j++)  tempData[i] = datasets[temp[i]];

            prot.setData(new Matrix(tempData));
        }

        return false;
    }

    /**
     * Returns N unduplicated numbers in a randomly specified range
     * @param max max value in specified range
     * @param min min value in specified range
     * @param n Random number
     * @return int[] Result set of random number
     */
    private static int[] randomArray(int min,int max,int n) {
        int len = max-min+1;

        if(max < min || n > len){
            return null;
        }

        //初始化给定范围的待选数组
        int[] source = new int[len];
        for (int i = min; i < min+len; i++){
            source[i-min] = i;
        }

        int[] result = new int[n];
        Random rd = new Random();
        int index = 0;
        for (int i = 0; i < result.length; i++) {
            index = rd.nextInt(len-i);
            result[i] = source[index];

            int temp = source[index];
            source[index] = source[len-1-i];
            source[len-1-i] = temp;
        }
        return result;
    }

    public static void main(String[] args) {
        DataAllocation.randomArray(4, 10, 5);
    }
}
