/** Klasa wątku sprawdzania kolizji */
public class ColisionCheckThread extends Thread {
    private final GamePanel game;

    public ColisionCheckThread(GamePanel game) {
        this.game = game;
    }

    public void run() {
        try {
            for (; ; ) {
                game.lock.lock();
                // obsługa metody sprawdzającej kolizję
                game.colisionCheck();
                game.lock.unlock();
                Thread.sleep(1);
            }
        } catch (InterruptedException e) {
            System.out.println("sleep interrupted");
        }
    }

}
