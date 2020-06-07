/** Klasa wątku węża */
public class SnakeThread extends Thread {
    private final GamePanel game;

    public SnakeThread(GamePanel game) {
        this.game = game;
    }

    public void run() {
        try {
            for (; ; ) {
                game.lock.lock();
                // obsługa metody dla węża
                game.runSnake();
                game.lock.unlock();
                Thread.sleep(game.returnSpeed());
            }
        } catch (InterruptedException e) {
            System.out.println("sleep interrupted");
        }
    }

}