package Exercise3;

import java.security.SecureRandom;

public class Producer implements Runnable {
    private static final SecureRandom generator = new SecureRandom();
    private final Buffer sharedLocation;

    public Producer(Buffer sharedLocation){
        this.sharedLocation = sharedLocation;
    }

    public void run(){
        int sum = 0;
        for(int count = 1; count <= 10; count++){
            try{
                Thread.sleep(generator.nextInt(3000));
                sharedLocation.blockingPut(count);
                sum += count;
                System.out.printf("\tProducer:%d%n",sum);
            }catch(InterruptedException exception){
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("Producer done");
    }
}
