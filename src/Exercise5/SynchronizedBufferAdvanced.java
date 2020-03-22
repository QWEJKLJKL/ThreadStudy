package Exercise5;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SynchronizedBufferAdvanced implements Buffer {
    private boolean occupied;
    private int buffer = -1;

    private Lock accessLock = new ReentrantLock();
    private Condition canRead = accessLock.newCondition();
    private Condition caneWrite = accessLock.newCondition();

    @Override
    public void blockingPut(int value) throws InterruptedException{
        accessLock.lock();
        try{
            while (occupied)
                caneWrite.await();

            buffer = value;
            occupied = true;
            canRead.signalAll();
        }
        finally {
            accessLock.unlock();
        }
    }

    @Override
    public int blockingGet() throws InterruptedException{
        int value = 0;
        accessLock.lock();
        try{
            while (!occupied)
                canRead.await();

            value = buffer;
            occupied = false;
            caneWrite.signalAll();
        }
        finally {
            accessLock.unlock();
        }

        return value;
    }


}
