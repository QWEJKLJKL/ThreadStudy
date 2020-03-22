package PrintTask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

//使用ExecutorService去执行runnables接口
public class TaskExecutor {
    public static void main(String[] args) throws InterruptedException {
        //create and name each runnable
        PrintTask task1 = new PrintTask("task1");
        PrintTask task2 = new PrintTask("task2");
        PrintTask task3 = new PrintTask("task3");

        System.out.println("Starting Executor");

        //Create ExecutorService to manage threads 新建一个缓存线程池
        ExecutorService executorService = Executors.newCachedThreadPool();

        // 开始3个线程
        executorService.execute(task1);
        executorService.execute(task2);
        executorService.execute(task3);
        System.out.println("Main Thread continue");

        executorService.shutdown();
        //当主程序终止后, 程序还会运行直到所有线程全部完成.
        executorService.awaitTermination(1, TimeUnit.MINUTES);
    }
}
