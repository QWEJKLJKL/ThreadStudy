package ProducerAndConsumer;

public class SynchronizedBuffer implements  Buffer {
    private int buffer = -1; //共享数据
    private boolean occupied = false;
    //将值放入buffer
    public synchronized void blockingPut(int value) throws InterruptedException{
        while(occupied){
            System.out.println("Producer tries to write");
            displayState("Buffer full. Producer waits");
            wait();
        }

        buffer = value;
        occupied = true;
        displayState("Producer writes" + buffer);

        notifyAll();//唤醒等待的线程来进入runnable state
    }

    public synchronized int blockingGet() throws InterruptedException{
        while(!occupied){
            System.out.println("Consumer tries to read");
            displayState("Buffer empty. Consumer waits");
            wait();
        }

        occupied = false;
        displayState("Consumer reads" + buffer);
        notifyAll();//这里相当于唤醒put,因为本程序只有两个线程
        return buffer;
    }

    private synchronized void displayState(String operation){
        System.out.printf("%-40s%d\t\t%b%n%n", operation,buffer,occupied);
    }
}
