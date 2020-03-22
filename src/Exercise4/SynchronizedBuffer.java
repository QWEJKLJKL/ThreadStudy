package Exercise4;

public class SynchronizedBuffer implements Buffer{
    private int buffer = -1;
    private boolean occupied = false;

    @Override
    public synchronized void blockingPut(int value) throws InterruptedException{
        while(occupied){
            wait();
        }

        buffer = value;
        occupied = true;
        notifyAll();
    }

    @Override
    public synchronized int blockingGet() throws InterruptedException{
        while (!occupied){
            wait();
        }
        int value = buffer;
        occupied = false;
        notifyAll();

        return value;
    }
}
