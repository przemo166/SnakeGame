public class AppleThread extends Thread {
    private final GamePanel game;

    public AppleThread(GamePanel game) {
        this.game = game;
    }

    public void run() {
        try {
            for (; ; ) {
                game.lock.lock();
                game.apple();
                game.lock.unlock();
                Thread.sleep(5);
            }
        } catch (InterruptedException e) {
            System.out.println("sleep interrupted");
        }
    }

}
