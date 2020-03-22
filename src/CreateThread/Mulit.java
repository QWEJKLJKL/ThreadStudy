package CreateThread;

public class Mulit extends Thread{
    public void run(){
        System.out.println("Thread is running");
    }

    public static void main(String[] args){
        Mulit mulit = new Mulit();
        mulit.start();
    }
}
