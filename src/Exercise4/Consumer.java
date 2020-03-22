package Exercise4;

import java.security.SecureRandom;

public class Consumer implements Runnable {
    private final SecureRandom generator = new SecureRandom();
    private Buffer sharedLocation;

    public Consumer(Buffer sharedLocation){
        this.sharedLocation = sharedLocation;
    }

    public void run(){
        int sum = 0;
        try{
            for(int count = 1; count <= 10; count++){
                Thread.sleep(3000);
                sum += sharedLocation.blockingGet();
            }
        }catch (InterruptedException exception){
            Thread.currentThread().interrupt();
        }

        System.out.println("Consumer read: " + sum);
    }
}
