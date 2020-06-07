import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.Console;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

/** Właściwa klasa realizująca przebieg gry */
public class GamePanel extends JPanel implements KeyListener {

    private static final long serialVersionUID = 1L;

    /** Dlugość i szerokość planszy */
    public static final int WIDTH = 600 , HEIGHT = 600 ;

    /** Wątek dla węża */
    private SnakeThread snakeThread;

    /** Wątek dla sprawdzania kolizji */
    private ColisionCheckThread colisionThread;

    /** Wątek dla jabłka */
    private AppleThread appleThread;

    /** Wątek dla funkcji odświeżającej planszę */
    private RepaintThread repaintThread;

    /** Zmienna typu bool, która ma wartość True, dopuki trwa rozgrywka */
    private boolean run ;

    /** Zmienne typu boool, potrzebne do sprawdzania, który przycisk jest w danej chwili wciśnięty */
    private boolean right = true;
    private boolean left = false;
    private boolean up = false;
    private boolean down = false;

    /** Tworzenie obiektu typu ReentrantLock dla klasy */
    public ReentrantLock lock = new ReentrantLock();

    /** Wąż na planszy tworzony z obiektów typu Body */
    private Body body;
    private ArrayList<Body> snakeBody;

    /** Jabłko na planszy tworzone z obiektu typu Apple */
    private Apple apple;
    private ArrayList <Apple> apples;

    /** Zmienna potrzebna do generowania randomowych miejsc pojawiania się jabłek */
    private Random random;

    /** Początkowe koordynaty i wielkość ogonu węża */
    private int xCor = 10;
    private int yCor = 10;
    private int size = 1;

    /** Początkowa prędkość węża */
    private int speed = 500;

    /** Metoda zwracająca aktualną prędkość węża */
    public int returnSpeed()
    {
        return speed;
    }

    /** Konstruktor klasy */
    public GamePanel ()
    {
        // Ustawianie odpowiednich parametrów dla game panelu
        setFocusable(true);
        addKeyListener(this);
        setPreferredSize(new Dimension(WIDTH,HEIGHT));

        // Inicjalizacja węża, jabłka oraz nowej liczby pseudolosowej
        snakeBody = new ArrayList<Body>();
        apples = new ArrayList<Apple>();
        random = new Random();

        // Inicjalizacja wątku dla węża
        snakeThread = new SnakeThread(this);
        snakeThread.start();

        // ... dla funkcji kolizji
        colisionThread = new ColisionCheckThread(this);
        colisionThread.start();

        // ... dla jabłka
        appleThread = new AppleThread(this);
        appleThread.start();

        // ... dla funkcji odświeżającej planszę
        repaintThread = new RepaintThread(this);
        repaintThread.start();

        // start gry
        start();

    }

    /** Metoda uruchamiająca grę */
    public void start ()
    {
        run = true;

    }

    /** Metoda zatrzymująca grę */
    public void stop ()
    {
        // Zmiana wartosci zmiennej run na false = koniec gry
        run = false;

        // Czekanie na zakończenie wszystkich wątków przed zatrzymaniem wątku głównego
        try {
            repaintThread.join();
            appleThread.join();
            snakeThread.join();
            colisionThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /** Metoda sprawdzająca kolizję */
    public void colisionCheck()
    {
        // Sprawdzanie czy wąż nie wyszedł za planszę
        if(xCor < 0 || xCor > 29 || yCor < 0 || yCor > 29)
        {
            System.out.println("Colision");

            // uruchomienie metody konczacej gre
            stop();
        }

        // Sprawdzanie, czy wąż nie uderzył sam w siebie
        for(int i=0;i<snakeBody.size();i++)
        {
            if(xCor == snakeBody.get(i).getxCor() && yCor == snakeBody.get(i).getyCor() )
            {
                if(i != snakeBody.size()-1)
                {
                    System.out.println("Colision");

                    // uruchomienie metody konczacej gre
                    stop();
                }
            }
        }
    }

    /** Metoda obsługująca jabłko */
    public void apple()
    {
        // Jeśli  lista z jabłkami jest pusta wylosuj miejsce spawnu dla jabłka
        if(apples.size()==0)
        {
            int xCor = random.nextInt(23);
            int yCor = random.nextInt(23);

            // Jeśli jabłko zespawnuje się na ciele węża , wykonaj funkcje apple() jeszcze raz
            for(int i=0;i<size;i++)
            {
                if(xCor==snakeBody.get(i).getxCor() && yCor==snakeBody.get(i).getyCor())
                {
                    apple();
                }
            }

            // Stworzenie nowego jabłka
            apple = new Apple(xCor,yCor,20);
            apples.add(apple);
        }

        // Obsługa zjedzenia jabłka przez węża
        for(int i=0;i<apples.size();i++)
        {
            if(xCor==apples.get(i).getxCor()&&yCor==apples.get(i).getyCor())
            {
                size ++;

                System.out.print("Size : ");
                System.out.println(size);

                System.out.print("Speed : ");
                System.out.println(speed);

                apples.remove(i);

                // Zwiększanie rozmiaru węża w zależności od tego, w jakim kierunku poruszał się zjadając jabłko

                if(right) body = new Body(xCor + 1 ,yCor, 20);
                if(left)  body = new Body(xCor - 1 ,yCor, 20);

                if(down)  body = new Body(xCor,yCor - 1 , 20);
                if(up)    body = new Body(xCor,yCor + 1 , 20);

                snakeBody.add(0,body);
                i++;
            }
        }
    }

    /** Metoda obsługująca węża */
    public void runSnake ()
    {
        // Jeśli lista z elementami węża jest pusta to dodaj pierwszy element ciała węża w miejscu startu gry
        if(snakeBody.size()==0)
        {
            for(int i=1;i<=size;i++)
            {
                body = new Body(xCor+i, yCor, 20);
                snakeBody.add(body);
            }
        }

        // Poruszanie się w odpowiednim kierunku , zależnym od wciśniętego przycisku na klawiaturze
        if (right) xCor++;
        if (left) xCor--;
        if (up) yCor--;
        if (down) yCor++;

        // Przemieszczanie się węża jako dodawanie i usuwanie odpowienich elementów jego ciała
        body = new Body(xCor, yCor, 20);
        snakeBody.add(body);
        snakeBody.remove(0);

        // Switch manipulujący prędkością poruszania się węża w zależności od jego wielkości ( czyli w sumie od zdobytych punktów )
        switch(size){
            case 5:
                speed=450;
                break;
            case 15:
                speed=400;
                break;
            case 20:
                speed=350;
                break;
            case 30:
                speed=300;
                break;
            case 35:
                speed=250;
                break;
            case 40:
                speed=200;
                break;

            default:
               break;
        }



    }

    /** Metoda rysująca planszę oraz węża i jabłko */
    public void paint (Graphics g)
    {
        g.clearRect(0,0,WIDTH,HEIGHT);
        g.setColor(Color.BLACK);
        g.fillRect(0,0,WIDTH,HEIGHT);

        // plansza jeden kierunek linii
        for(int i=0;i<WIDTH/10;i++)
        {
            g.drawLine(i*10,0,i*10,HEIGHT);
        }

        // plansza drugi kierunek linii
        for(int i=0;i<HEIGHT/10;i++)
        {
            g.drawLine(0,i*10,HEIGHT,i*10);
        }

        // wąż
        for(int i=size-1;i>=0;i--)
        {

            snakeBody.get(i).draw(g);
        }

        // jabłko
        for(int i=0;i<apples.size();i++)
        {
            apples.get(i).draw(g);
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    /** Adnotacja potrzebna do obsługi klawiszy uzywanych do sterowania wężem, przyciski góra , dół, lewo, prawo  */
    @Override
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        // wcisniety klawisz w prawo, ruch weza w prawo
        if(key==KeyEvent.VK_RIGHT && !left)
        {
            right = true;
            up = false ;
            down = false;
        }

        // wcisniety klawisz w lewo, ruch weza w lewo
        if(key==KeyEvent.VK_LEFT && !right)
        {
            left = true;
            up = false ;
            down = false;
        }

        // wcisniety klawisz do góry, ruch weza do góry
        if(key==KeyEvent.VK_UP && !down)
        {
            up = true;
            left = false ;
            right = false;
        }

        // wcisniety klawisz w dół, ruch weza w dół
        if(key==KeyEvent.VK_DOWN && !up)
        {
            down = true;
            left = false ;
            right = false;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
