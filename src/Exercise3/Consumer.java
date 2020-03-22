package Exercise3;

import java.security.SecureRandom;

public class Consumer implements Runnable {
    private static SecureRandom generator = new SecureRandom();
    private final Buffer sharedLocation;

    public Consumer(Buffer sharedLocation){
        this.sharedLocation = sharedLocation;
    }

    public void run(){
        int sum = 0;
        try {
            for (int count = 1; count <= 10; count++) {
                Thread.sleep(generator.nextInt(3000));
                int value = sharedLocation.blockingGet();
                sum += value;

                System.out.printf("\tConsumer: %d%n", sum);
            }

            System.out.println("Consumer done!");
        }catch (InterruptedException exception){
            Thread.currentThread().interrupt();
        }
    }
}
