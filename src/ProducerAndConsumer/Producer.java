package ProducerAndConsumer;

import java.security.SecureRandom;

public class Producer implements Runnable {
    private static final SecureRandom generator = new SecureRandom();
    private final Buffer sharedLocation;//引用共享对象

    public Producer(Buffer sharedLocation){
        this.sharedLocation = sharedLocation;
    }

    //储存1-10的值在sharedLocation
    public void run(){
        int sum = 0;
        for (int count = 1; count<=10; count++){
            try{
                Thread.sleep(generator.nextInt(3000));//random sleep
                sharedLocation.blockingPut(count);
                sum += count;
                System.out.printf("\t%2d%n", sum);
            }catch (InterruptedException exception){
                Thread.currentThread().interrupt();
            }
        }

        System.out.printf(
                "Producer done producing%nTerminating Producer%n"
        );
    }
}
