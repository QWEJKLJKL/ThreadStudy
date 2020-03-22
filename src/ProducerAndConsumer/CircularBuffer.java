package ProducerAndConsumer;

public class CircularBuffer implements Buffer {

    private final int[] buffer = {-1,-1,-1};//共享缓存

    private int occupiedCells = 0;
    private int writeIndex =0;
    private int readIndex =0;

    //place value into buffer

    public synchronized void blockingPut(int value)
        throws InterruptedException
    {
        while(occupiedCells == buffer.length){
            System.out.printf("Buffer is full. Producer waits.%n");
            wait();
        }
        buffer[writeIndex] = value;//set new buffer value

        writeIndex = (writeIndex + 1) % buffer.length;
        ++ occupiedCells;   //one more buffer cell is full
        displayState("Producer writes " + value);
        notifyAll();//notify threads waiting to read from buffer
    }

    public synchronized int blockingGet() throws InterruptedException{
        while(occupiedCells == 0){
            System.out.println("Buffer is empty. Consumer waits. %n");
            wait();
        }

        int readValue = buffer[readIndex]; //read value from buffer

        //update circular read index
        readIndex = (readIndex + 1) % buffer.length;

        --occupiedCells;
        displayState("Consumer reads" + readValue);
        notifyAll();
        return  readValue;
    }

    public void displayState(String operation) {
        // output operation and number of occupied buffer cells
        System.out.printf("%s%s%d)%n%s", operation,
                " (buffer cells occupied: ", occupiedCells, "buffer cells: ");

        for (int value : buffer)
            System.out.printf(" %2d ", value); // output values in buffer

        System.out.printf("%n ");

        for (int i = 0; i < buffer.length; i++)
            System.out.print("---- ");

        System.out.printf("%n ");

        for (int i = 0; i < buffer.length; i++) {
            if (i == writeIndex && i == readIndex)
                System.out.print(" WR"); // both write and read index
            else if (i == writeIndex)
                System.out.print(" W "); // just write index
            else if (i == readIndex)
                System.out.print(" R "); // just read index
            else
                System.out.print(" "); // neither index
        }
    }
}
