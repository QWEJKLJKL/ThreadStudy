package Exercise6;

public class SynchronizedBuffer implements Buffer{
    private boolean occupied = false;
    private int buffer = -1;


    @Override
    public synchronized void blockingPut(int value) throws InterruptedException {
        while(occupied == true){
            wait();
        }

        buffer = value;
        occupied = true;
        notifyAll();
    }

    @Override
    public synchronized int blockingGet() throws InterruptedException{
        while(!occupied)
            wait();

        int value = buffer;
        occupied = false;
        notifyAll();
        return value;
    }
}
