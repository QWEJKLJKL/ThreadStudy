package PrintTask;//PrintTask.PrintTask class sleeps for a random time from 0 to 5 seconds
import  java.security.SecureRandom; //真随机数 并且线程同步

public class PrintTask implements Runnable {
    private static final SecureRandom generator = new SecureRandom();
    private final int sleepTime;//random sleep time for thread
    private final String taskName;

    //构造器
    public PrintTask(String taskName){
        this.taskName = taskName;
        //pick random sleep time between 0 and 5 seconds
        sleepTime = generator.nextInt(5000);
    }

    //method run contains the code that a thread will execute
    public void run(){
        try{    //将一个线程睡眠一段时间
            System.out.printf("%s going to sleep for %d millisecondes. %n", taskName, sleepTime);
            Thread.sleep(sleepTime);
        }catch (InterruptedException exception){
            exception.printStackTrace();
            Thread.currentThread().interrupt(); //重新中断线程
            /*
                阻塞方法可能因为等不到所等的事件而无法终止, 因此令阻塞方法可取消就非常有用.
                这里的Thread.currentThread().interrupt()是为了方便让当前进程处理interrupt
             */
        }

        //print task name
        System.out.printf("%s done sleeping%n", taskName);
    }


}
