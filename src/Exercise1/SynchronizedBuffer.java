package Exercise1;

public class SynchronizedBuffer implements Buffer {
    private int buffer = -1;
    private boolean occupied = false;
    @Override
    public int blockingGet() throws InterruptedException {
        while (!occupied){
            System.out.println("Producer tries to write");
            displayState("Buffer empty.Consumer waits");
            wait();
        }

        occupied = false;
        System.out.println("Consumer reads" + buffer);
        notifyAll();
        return buffer;
    }

    @Override
    public synchronized void blockingPut(int value) throws InterruptedException {
        while (occupied){
            System.out.println("Producer tries to write");
            displayState("Buffer full.Producer wait");
            wait();
        }

        buffer = value;
        occupied = true;
        displayState("Producer writes + buffer");

        notifyAll();
    }

    private  synchronized void displayState(String operation){
        System.out.printf("%-40s%d\t\t%b%n%n", operation,buffer,occupied);
    }
}
