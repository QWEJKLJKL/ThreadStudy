package ProducerAndConsumer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CircularBufferTest {
    public static void main(String[] args) throws InterruptedException{
        ExecutorService executorService = Executors.newCachedThreadPool();

        //Create CircularBuffer to store ints
        CircularBuffer shareLocation = new CircularBuffer();

        //display the initial state
        shareLocation.displayState("Initial State");

        //Execute the Producer and Consumer Tasks
        executorService.execute(new Producer(shareLocation));
        executorService.execute(new Consumer(shareLocation));

        executorService.shutdown();
        /**
         * Blocks until all tasks have completed execution after a shutdown
         * request, or the timeout occurs, or the current thread is
         * interrupted, whichever happens first.
         * **/
        executorService.awaitTermination(1, TimeUnit.MINUTES);
    }
}
