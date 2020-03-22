package Exercise3;

public class sharedBufferTest {
    public static void main(String[] args){
        Buffer sharedLocation = new SynchronizedBuffer();
        Thread producer = new Thread(new Producer(sharedLocation));
        Thread consumer = new Thread(new Consumer(sharedLocation));

        producer.start();
        consumer.start();

    }
}
