package Exercise6;

public class Mulit extends Thread{
    public void run(){
        System.out.println(getName() + "Running");
    }

    public static void main(String[] args){
        Thread thread =new Mulit();
        thread.start();

        System.out.println(Thread.currentThread().getName() + "Running");
    }
}
