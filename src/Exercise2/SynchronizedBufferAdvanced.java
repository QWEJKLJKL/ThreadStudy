package Exercise2;

import java.security.SecureRandom;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SynchronizedBufferAdvanced implements Buffer {
    private int buffer = -1;
    private final SecureRandom generator = new SecureRandom();
    private final Lock accessLock = new ReentrantLock();

    private final Condition canWrite = accessLock.newCondition();
    private final Condition canRead = accessLock.newCondition();

    private boolean occupied = false;

    @Override
    public int blockingGet() throws InterruptedException {
        int readValue = 0;
        accessLock.lock();

        try {
            while (!occupied) {
                canRead.await();
            }

            readValue = buffer;
            occupied = false;
            canWrite.signalAll();
            displayState("Consumer reads " + readValue);
        }finally {
            accessLock.unlock();
        }

        return readValue;
    }

    @Override
    public void blockingPut(int value) throws InterruptedException{
        accessLock.lock();

        try{
            while (occupied){
                System.out.println("Producer tries to write");
                displayState("Buffer full. Producer waits");
                canWrite.await();
            }

            occupied = true;
            buffer = value;
            displayState("Producer wrrites " + buffer);
            canRead.notifyAll();
        }finally {
            accessLock.unlock();
        }
    }


 public void displayState(String operation) {
    try {
        accessLock.lock();//lock this object
        System.out.printf("%-40s%d\t\t%b%n%n", operation, buffer, occupied);
    } finally {
        accessLock.unlock();//unlock this objects
    }
}
}
