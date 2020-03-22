public class InterruptTest {
    public static void main(String[] args) {
        MyThread t = new MyThread();
        t.start();

        //Thread.currentThread().interrupt();

       // System.out.println(t.isInterrupted());
        //t.interrupt();
        while(true);
    }

    private static class MyThread extends Thread {

        private Thread parentThread;

        public MyThread() {
            parentThread = Thread.currentThread();
        }

        @Override
        public void run() {
            try {
                while (true){
                    wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //parentThread.interrupt();  // Call on main thread!!!
        }
    }
}
