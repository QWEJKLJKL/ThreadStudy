package Exercise6;

public interface Buffer {
    void blockingPut(int value) throws InterruptedException;

    int blockingGet() throws InterruptedException;
}
