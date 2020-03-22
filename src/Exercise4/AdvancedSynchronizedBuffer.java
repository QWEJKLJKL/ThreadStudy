package Exercise4;

import java.security.SecureRandom;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AdvancedSynchronizedBuffer implements Buffer {
    private int buffer = -1;
    private boolean occupied = false;

    private Lock accessLock = new ReentrantLock();
    private Condition canRead = accessLock.newCondition();
    private Condition canWrite = accessLock.newCondition();

    @Override
    public void blockingPut(int value) throws InterruptedException {
        accessLock.lock();
        try {
            while (occupied) {
                canWrite.await();
            }

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
        accessLock.toString();
        int value = 0;
        try {
            while (!occupied){
                canRead.await();
            }

            value = buffer;
            occupied = false;
            canWrite.signalAll();

        }
        finally {
            accessLock.unlock();
            return value;
        }
    }
}
