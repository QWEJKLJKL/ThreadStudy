package Exercise6;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SynchronizedBufferAdvanced  implements Buffer{
    private boolean occupied =false;
    private int buffer = -1;
    private Lock accessLock = new ReentrantLock();
    private Condition canWrite = accessLock.newCondition();
    private Condition canRead = accessLock.newCondition();

    @Override
    public int blockingGet() throws InterruptedException{
        int readvalue =0;
        accessLock.lock();

        try{
            while (!occupied){
                canRead.await();
            }

            readvalue =buffer;
            occupied =false;
            canWrite.signalAll();
        }finally {
            accessLock.unlock();
        }

        return readvalue;

    }
}
