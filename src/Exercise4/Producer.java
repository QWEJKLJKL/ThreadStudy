package Exercise4;

import java.security.SecureRandom;

public class Producer implements Runnable {
    private final SecureRandom generator = new SecureRandom();
    private Buffer sharedLocation;

    public Producer(Buffer sharedLocation){
        this.sharedLocation = sharedLocation;
    }

    public void run(){
        int sum = 0;
        try{
            for(int count = 1; count <= 10; count++){
                Thread.sleep(generator.nextInt(3000));
                sharedLocation.blockingPut(count);
                sum += count;
            }
        }catch (InterruptedException exception){
            Thread.currentThread().interrupt();
        }

        System.out.println("Producer write: " + sum);
    }
}
