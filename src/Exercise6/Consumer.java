package Exercise6;

import java.security.SecureRandom;

public class Consumer implements Runnable {
    private final SecureRandom generator = new SecureRandom();
    private final Buffer sharedLocation;

    public Consumer(Buffer sharedLocation){
        this.sharedLocation =sharedLocation;
    }

    public void run(){
        int sum = 0;

        for (int i =0; i<10; i++){
            try{
                Thread.sleep(generator.nextInt(3000));
                sum += sharedLocation.blockingGet();
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }
    }
}
