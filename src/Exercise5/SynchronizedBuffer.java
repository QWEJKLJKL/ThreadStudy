package Exercise5;

public class SynchronizedBuffer implements Buffer{
    private boolean occupied;
    private int buffer;

    @Override
    public synchronized int blockingGet() throws InterruptedException {

        while(!occupied)
            wait();

        int value = buffer;
        occupied = false;
        notifyAll();
        return value;
    }


    @Override
    public synchronized void blockingPut(int value) throws InterruptedException {
        while (occupied)
            wait();

        buffer = value;
        occupied = true;
        notifyAll();
    }
}
