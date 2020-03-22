package ProducerAndConsumer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SynchronizedBufferAdvanced implements Buffer {
    //Lock to control synchronization with this buffer
    /**
     * Lock可以分为三种： 允许被打断，不允许被打断，到点中断。
     * 每类锁都允许多条件，抢占/非抢占
     */
    private final Lock accessLock = new ReentrantLock();
    //conditions to control reading and writing
    private final Condition canWrite = accessLock.newCondition();
    private final Condition canRead =accessLock.newCondition();

    private int buffer = -1;//shared by producer and consumer threads
    private boolean occupied = false;

    //place int value into buffer
    public void blockingPut(int value) throws InterruptedException{
        accessLock.lock();  //lock this object

        //output thread information and buffer information, then wait
        try{
            while(occupied){
                System.out.println("Producer tries to write");
                displayState("Buffer full. Producer waits");
                canWrite.await();
            }

            buffer = value;//set new buffer value

            //indicate producer cannot store another value
            //until consumer retrieves current buffer value
            occupied = true;

            displayState("Producer wrrites " + buffer);

            //signal any threads waiting to read from buffer
            canRead.signalAll();
        }
        finally{
            accessLock.unlock();//unlock this object
        }
    }

    public int blockingGet() throws InterruptedException{
        int readValue = 0; //initialize value read from buffer
        accessLock.lock();//lock this object

        //output thread information and buffer information, then wait
        try {
            //if there is no data to read, place thread in waiting state
            while(!occupied){
                System.out.println("Consumer tries to read.");
                displayState("Buffer empty. Consumer waits.");
                canRead.await();
            }
            //indicate that producer can store another value
            //beacuse consumer just retrieve buffer value
            occupied = false;

            canWrite.signalAll();
            readValue = buffer;//retrieve value from buffer
            displayState("Consumer reads " + readValue);
        }
        finally {
            accessLock.unlock();//unlock this object
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
