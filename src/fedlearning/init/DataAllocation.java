package fedlearning.init;

import peersim.config.Configuration;
import peersim.core.Control;

import java.util.Arrays;
import java.util.Random;

public class DataAllocation implements Control {

    /**
     * Network size
     */
    private static final String PAR_SIZE = "size";

    private final int size;

    public DataAllocation(String prefix) {
        size = Configuration.getInt(prefix + "." + PAR_SIZE);
    }

    @Override
    public boolean execute() {
        
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
        System.out.println(Arrays.toString(result));
        return result;
    }

    public static void main(String[] args) {
        DataAllocation.randomArray(4, 10, 5);
    }
}
