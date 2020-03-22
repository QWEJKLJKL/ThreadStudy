package Exercise3;

public class SynchronizedBuffer implements Buffer{
    private int buffer = -1;
    private boolean occupied = false;

    @Override
    public synchronized void blockingPut(int value) throws InterruptedException {
        while(occupied){
            wait();
        }

        occupied = true;
        buffer = value;
        notifyAll();
    }

    @Override
    public synchronized int blockingGet() throws InterruptedException {
        while (!occupied){
            wait();
        }

        occupied = false;
        int value = buffer;
        notifyAll();

        return value;
    }

    private synchronized void displayState(String operation){
        System.out.printf("%-40s%d\t\t%b%n%n",operation,buffer,occupied);
    }

}
