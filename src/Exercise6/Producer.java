package Exercise6;

import java.security.SecureRandom;

public class Producer implements Runnable{
    private SecureRandom geneartor = new SecureRandom();
    private Buffer sharedLocation;

    public Producer(Buffer sharedLocation){
        this.sharedLocation = sharedLocation;
    }

    public void run(){
        int sum =0;
        try {
            for (int i = 0; i < 10; i++) {
                Thread.sleep(geneartor.nextInt(3000));
                sharedLocation.blockingPut(i);
                sum += i;
            }
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }
}
