package Exercise3;

public class Mulit2 implements Runnable {
    public void run(){
        System.out.println("Thread is running" + this.getClass().getName());
    }

    public static void main(String[] args){
        Mulit2 mulit1 = new Mulit2();
        Mulit2 mulit2 = new Mulit2();
        Mulit2 mulit3 = new Mulit2();

        Thread thread1 = new Thread(mulit1);
        Thread thread2 = new Thread(mulit2);
        Thread thread3 = new Thread(mulit3);

        thread1.start();
        thread2.start();
        thread3.start();
    }
}
