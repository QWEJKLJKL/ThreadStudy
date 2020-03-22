package Exercise3;

public class Mulit extends Thread{
    public void run(){
        System.out.println("Thread is running" + getName());
    }

    public static void main(String[] args){
        Mulit mulit1 = new Mulit();
        Mulit mulit2 = new Mulit();
        Mulit mulit3 = new Mulit();

        mulit1.start();
        mulit2.start();
        mulit3.start();
    }
}
