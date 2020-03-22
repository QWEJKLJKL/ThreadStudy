package Exercise5;

public class Mulit extends Thread{

    public void run(){
        System.out.println(getName() + "Running");
    }

    public static void main(String[] args){
        Thread Mulit = new Mulit();

        Mulit.start();
        System.out.println(Thread.currentThread().getName() + "Running");
    }
}
