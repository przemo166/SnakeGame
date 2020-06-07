import javax.swing.*;
import java.awt.*;
import java.util.concurrent.locks.ReentrantLock;

/** Klasa główna */
public class Main {

    /** Konstruktor klasy */
    public Main ()
    {
        // tworzenie i inicjalizacja nowego obiektu typu JFrame
        JFrame frame = new JFrame ();

        // tworzenie i inicjalizacja nowego obiektu typu GamePanel
        GamePanel gamePanel = new GamePanel();

        // dodanie panelu gry do obiektu frame
        frame.add(gamePanel);

        // ustawienie odpowienich parametrów dla okna gry
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("SnakeGame");
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.pack();

        // ustawienie pojawiania sie okienka w centrum ekranu
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);

        // ustawienie widoczności obiektu typu frame
        frame.setVisible(true);
    }

    /** Metoda tworząca okno z grą i pośrednio uruchamiająca metody z klasy GamePanel */
    public static void  main (String[] args)
    {
        new Main ();

    }


    }


