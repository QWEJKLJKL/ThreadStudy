package Exercise3;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SynchronizedBufferAdvanced implements Buffer{

    private int buffer = -1;
    private boolean occupied = false;

    /**
     * Lock可以分为三种，允许被打断，不允许被打断，到点中断。
     * 每类锁都允许多条件，抢占/非抢占
     */
    private final Lock accessLock = new ReentrantLock();

    private final Condition canWrite = accessLock.newCondition();
    private final Condition canRead = accessLock.newCondition();

    public void blockingPut(int value) throws InterruptedException{
        accessLock.lock();  //lock this object

        try {
            while(occupied){
                canWrite.await();
            }

            buffer = value;
            occupied = true;

            canRead.signalAll();
        }
        finally {
            accessLock.unlock();//unlock this object
        }
    }

    public int blockingGet() throws InterruptedException{
        accessLock.lock();
        int readValue = 0;

        try {
            while (!occupied) {
                canRead.await();
            }

            readValue = buffer;
            occupied = false;
            canWrite.signalAll();

        }
        finally {
            accessLock.unlock();
            return readValue;
        }
    }

}
