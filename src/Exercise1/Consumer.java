package Exercise1;

import java.security.SecureRandom;

public class Consumer implements Runnable {
    private static final SecureRandom generator = new SecureRandom();
    private final Buffer sharedLocation;//应用共享对象

    public Consumer(Buffer sharedLocation){
        this.sharedLocation = sharedLocation;
    }

    //从共享对象读取10次然后相加
    public void run(){
        int sum = 0;
        for (int count = 1; count <= 10; count++){
            try{
                Thread.sleep(generator.nextInt(3000));
                sum += sharedLocation.blockingGet();
                System.out.printf("\t\t\t%2d%n",sum);
            }catch (InterruptedException exception){
                Thread.currentThread().interrupt();//自己打断自己
            }

            System.out.printf("%n%s %d%n%s%n",
                    "Consumer read values totaling", sum, "Terminating Consumer");
        }
    }

}
