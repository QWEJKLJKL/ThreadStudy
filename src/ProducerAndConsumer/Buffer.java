package ProducerAndConsumer;
//Buffer接口规定了被Producer和Consumer调用的方法
public interface Buffer {

    //将一个整数放入到Buffer
    public void blockingPut(int value) throws InterruptedException;

    //从Buffer返回一个整数
    public int blockingGet() throws InterruptedException;

}
