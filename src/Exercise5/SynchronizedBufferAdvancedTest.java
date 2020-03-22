package Exercise5;

public class SynchronizedBufferAdvancedTest {
    public static void main(String[] args) throws InterruptedException {
        Buffer sharedLocation = new SynchronizedBufferAdvanced();

        new Thread(new Producer(sharedLocation)).start();
        Thread t = new Thread(new Consumer(sharedLocation));
        t.start();

        t.join();
        System.out.println("main program done!");

    }
}
