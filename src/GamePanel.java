import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class GamePanel extends JPanel implements  Runnable , KeyListener {

    private static final long serialVersionUID = 1L;

    public static final int WIDTH = 600 , HEIGHT = 600 ;

    private Thread thread ;

    private boolean run ;

    private boolean right = true;
    private boolean left = false;
    private boolean up = false;
    private boolean down = false;


    private Body body;
    private ArrayList<Body> snakeBody;

    private Apple apple;
    private ArrayList <Apple> apples;

    private Random random;

    private int xCor = 10;
    private int yCor = 10;
    private int size = 1;

    public GamePanel ()
    {
        setFocusable(true);
        addKeyListener(this);
        setPreferredSize(new Dimension(WIDTH,HEIGHT));
        snakeBody = new ArrayList<Body>();
        apples = new ArrayList<Apple>();
        random = new Random();
        start();
    }

    public void start ()
    {
        run = true;
        thread = new Thread(this);
        thread.start();
    }

    public void stop ()
    {
        run = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void tick ()
    {
        if(snakeBody.size()==0)
        {
            for(int i=1;i<=size;i++)
            {
                body = new Body(xCor+i, yCor, 20);
                snakeBody.add(body);
            }
        }

        try {
            TimeUnit.MICROSECONDS.sleep(500000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (right) xCor++;
        if (left) xCor--;
        if (up) yCor--;
        if (down) yCor++;

        body = new Body(xCor, yCor, 20);
        snakeBody.add(body);
        snakeBody.remove(0);

        if(apples.size()==0)
        {
            int xCor = random.nextInt(23);
            int yCor = random.nextInt(23);

            apple = new Apple(xCor,yCor,20);
            apples.add(apple);
        }


        for(int i=0;i<apples.size();i++)
        {
            if(xCor==apples.get(i).getxCor()&&yCor==apples.get(i).getyCor())
            {
                size ++;
                apples.remove(i);

                if(right) body = new Body(xCor + 1 ,yCor, 20);
                if(left)  body = new Body(xCor - 1 ,yCor, 20);

                if(down)  body = new Body(xCor,yCor - 1 , 20);
                if(up)    body = new Body(xCor,yCor + 1 , 20);

                snakeBody.add(0,body);
                i++;
            }
        }

        

    }

    public void paint (Graphics g)
    {
        g.clearRect(0,0,WIDTH,HEIGHT);
        g.setColor(Color.BLACK);
        g.fillRect(0,0,WIDTH,HEIGHT);

        for(int i=0;i<WIDTH/10;i++)
        {
            g.drawLine(i*10,0,i*10,HEIGHT);
        }

        for(int i=0;i<HEIGHT/10;i++)
        {
            g.drawLine(0,i*10,HEIGHT,i*10);
        }

        for(int i=size-1;i>=0;i--)
        {
            snakeBody.get(i).draw(g);
        }

        for(int i=0;i<apples.size();i++)
        {
            apples.get(i).draw(g);
        }

    }

    @Override
    public void run() {

        while (run)
        {
            tick();
            repaint();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if(key==KeyEvent.VK_RIGHT && !left)
        {
            right = true;
            up = false ;
            down = false;
        }

        if(key==KeyEvent.VK_LEFT && !right)
        {
            left = true;
            up = false ;
            down = false;
        }

        if(key==KeyEvent.VK_UP && !down)
        {
            up = true;
            left = false ;
            right = false;
        }

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
