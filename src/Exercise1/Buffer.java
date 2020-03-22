package Exercise1;
//Buffer接口规定了被Producer和Consumer调用的方法

public interface Buffer {
    public void blockingPut(int value) throws InterruptedException;

    public int blockingGet() throws InterruptedException;
}
