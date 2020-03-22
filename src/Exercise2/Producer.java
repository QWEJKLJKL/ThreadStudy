package Exercise2;

import java.security.SecureRandom;

public class Producer implements Runnable {
    private SecureRandom generator = new SecureRandom();
    private final Buffer sharedLocation;

    public Producer(Buffer sharedLocation){
        this.sharedLocation = sharedLocation;
    }

    @Override
    public void run() {
        int sum = 0;
        for (int count = 1; count <= 10; count++){
            try{
                Thread.sleep(generator.nextInt(3000));
                sum += count;
                sharedLocation.blockingPut(count);
                System.out.printf("\t%2d%n", sum);
            }catch (InterruptedException exception){
                Thread.currentThread().interrupt();
                System.out.println("qjjjjjjjjj");
            }
        }
        System.out.printf(
                "Producer done producing%nTerminating Producer%n"
        );
    }
}
