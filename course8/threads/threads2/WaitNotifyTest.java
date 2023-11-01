package ro.ase.course8.threads.threads2;

public class WaitNotifyTest {

    public static void main(String[] args) {
        Message msg = new Message("... process all the work!");

        Waiter waiter1 = new Waiter(msg);
        new Thread(waiter1,"WAITER 1").start();
        
        Waiter waiter2 = new Waiter(msg);
        new Thread(waiter2, "WAITER 2").start();
        
        Notifier notifier = new Notifier(msg);
        new Thread(notifier, "NOTIFIER 1").start();
        System.out.println("All the threads are started");
    }

}
