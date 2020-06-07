/** Klasa wątku odświeżania planszy */
public class RepaintThread extends Thread {
        private final GamePanel game;

        public RepaintThread(GamePanel game) {
            this.game = game;
        }

        public void run() {
            try {
                for (; ; ) {
                    game.lock.lock();
                    // obsługa odświeżania planszy
                    game.repaint();
                    game.lock.unlock();
                    Thread.sleep(5);
                }
            } catch (InterruptedException e) {
                System.out.println("sleep interrupted");
            }
        }

}



