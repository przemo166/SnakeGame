import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyThread extends Thread {
    private final GamePanel game;

    public MyThread(GamePanel game) {
        this.game = game;
    }

    public void run() {
        try {
            for (; ; ) {
                game.lock.lock();
                game.repaint();
                game.lock.unlock();
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            System.out.println("sleep interrupted");
        }
    }

}