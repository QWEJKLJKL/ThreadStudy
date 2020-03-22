package CreateThread;

import Exercise1.Buffer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SynchronizedBufferAdvanced implements Buffer {
    /**
     * Lock可以分为三种：允许被打断，不允许被打断，计时中断。
     * 每类的锁都允许多条件，抢占/非抢占
     */
    private final Lock accessLock = new ReentrantLock();
    private final Condition canWrite = accessLock.newCondition();
    private final Condition canRead = accessLock.newCondition();

    private int buffer = -1;
    private boolean occupied = false;

    //place int value into buffer

    @Override
    public void blockingPut(int value) throws InterruptedException {
        accessLock.lock();//lock this object

        try {
            while(occupied){
                System.out.println("Producer tries to write");
                displayState("Buffer full. Producer waits");
                canWrite.await();
            }

            buffer = value;
            occupied = true;

            displayState("Producer wrrites " + buffer);

            canRead.signalAll();
        }finally {
            accessLock.unlock();
        }
    }

    @Override
    public int blockingGet() throws InterruptedException {
        accessLock.lock();
        int readValue = 0;

        try{
            while(!occupied){
                System.out.println("Consumer tries to read.");
                displayState("Buffer empty. Consumer waits.");
                canRead.await();
            }

            occupied = false;
            readValue = buffer;
            displayState("Consumer reads " + readValue);
        }
        finally {
            accessLock.unlock();
        }
        return readValue;

    }
    public void displayState(String operation){
        try{
            accessLock.lock();//lock this object
            System.out.printf("%-40s%d\t\t%b%n%n", operation,buffer,occupied);
        }
        finally {
            accessLock.unlock();//unlock this objects
        }
    }
}
