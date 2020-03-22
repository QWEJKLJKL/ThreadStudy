package SharedArray;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SharedArrayTest {
    public static void main(String[] args){
        //建立共享object
        SimpleArray shareSimpleArray = new SimpleArray(6);

        //创建两个拥有run方法的task去改写共享array
        ArrayWriter writer1 = new ArrayWriter(1, shareSimpleArray);
        ArrayWriter writer2 = new ArrayWriter(11, shareSimpleArray);

        //用Execute执行线程. ExecutorService是一个interface, 创建interface实例需要用实现它的一个
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(writer1);
        executorService.execute(writer2);

        //结束线程池
        executorService.shutdown();

        try{
            // awaitTermination: 在线程池关闭后,等待其他线程结束. 如果其他线程在指定时间内没有关闭返回false.
            //这个方法是为了避免线程还没结束就将array打印出来.
            boolean tasksEnded =
                    executorService.awaitTermination(1, TimeUnit.MINUTES);
            if(tasksEnded){
                System.out.printf("%nContents of SharedArray.SimpleArray: %n");
                System.out.println(shareSimpleArray);
            }else
                System.out.println("Timed out while waiting for tasks to finish");
        }catch(InterruptedException ex){
            ex.printStackTrace();
        }
        /**
         * 总结: 1. 当一个程序存在共享数据时, 不能允许两个以上的线程同时改写它.
         *       2. 有锁的时候不能call sleep
         *       3. run方法里运行时间要越少越好,不然被阻塞的线程时间会很长
         */
    }
}
