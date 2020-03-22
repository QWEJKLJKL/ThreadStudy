package SharedArray;

import java.security.SecureRandom;
import java.util.Arrays;

public class SimpleArray {
    private static final SecureRandom generator = new SecureRandom();
    private final int[] array; //the shared integer array
    private int writeIndex = 0;// shared index of next element to write

    public SimpleArray(int size){
        array = new int[size];
    }

    //将一个值添加到共享数列里
    public synchronized void add(int value){
        int position = writeIndex;

        try{
            Thread.sleep(generator.nextInt(500));
        }catch (InterruptedException ex){
            Thread.currentThread().interrupt();//
        }

        //put value in the appropriate element
        array[position] = value;
        System.out.printf("%s wrote %2d to element %d.%n",
                Thread.currentThread().getName(),value,position);

        ++writeIndex; //increment index of element to be written next
        System.out.printf("Next wrrite index: %d%n", writeIndex);
    }

    //用来输出共享数列
    public synchronized String toString(){
        return Arrays.toString(array);
    }
}