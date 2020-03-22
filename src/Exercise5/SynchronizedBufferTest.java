package Exercise5;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SynchronizedBufferTest {
    public static void main(String[] args) throws InterruptedException {
        Buffer sharedLocation = new SynchronizedBuffer();
        ExecutorService executorService = Executors.newCachedThreadPool();

        executorService.execute(new Producer(sharedLocation));
        executorService.execute(new Consumer(sharedLocation));

        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.MINUTES);

    }
}
