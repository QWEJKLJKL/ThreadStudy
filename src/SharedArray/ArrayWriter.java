package SharedArray;

//添加一个整数到一个共享array里(与其他Runnables共享的)
public class ArrayWriter  implements  Runnable{
    private final SimpleArray shareSimpleArray;
    private final int startValue;

    public ArrayWriter(int value, SimpleArray array){
        startValue = value;
        shareSimpleArray = array;
    }

    public void run(){
        for (int i = startValue; i <startValue + 3; i++){
            shareSimpleArray.add(i);
        }
    }
}
