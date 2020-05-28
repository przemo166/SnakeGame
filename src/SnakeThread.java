public class SnakeThread extends Thread {
    private final GamePanel game;

    public SnakeThread(GamePanel game) {
        this.game = game;
    }

    public void run() {
        try {
            for (; ; ) {
                game.lock.lock();
                game.runSnake();
                game.lock.unlock();
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            System.out.println("sleep interrupted");
        }
    }

}