package CreateThread;

import ProducerAndConsumer.Buffer;
import ProducerAndConsumer.Consumer;
import ProducerAndConsumer.SynchronizedBuffer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class sharedBufferTest {
    public static void main(String[] args) throws  InterruptedException{
        ExecutorService executorService = Executors.newCachedThreadPool();
        Buffer sharedLocation = new SynchronizedBuffer();
        System.out.printf("%-40s%s\t\t%s%n%-40s%s%n%n","Operation","Buffer","Occupied",
                "----------","--------\t\t--------");

        executorService.execute(new Producer(sharedLocation));
        executorService.execute(new Consumer(sharedLocation));
    }
}
