package CreateThread;

public class Mulit3 implements Runnable {
    public void run(){
        System.out.println("Thread is Running");
    }

    public static void main(String[] args){
        Mulit3 mulit3 = new Mulit3();
        Thread thread = new Thread(mulit3);
        thread.start();
    }
}
