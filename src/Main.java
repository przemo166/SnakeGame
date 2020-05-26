import javax.swing.*;
import java.awt.*;

public class Main {

    public Main ()
    {
        JFrame frame = new JFrame ();
        GamePanel gamePanel = new GamePanel();

        frame.add(gamePanel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("SnakeGame");
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.pack();

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);

        frame.setVisible(true);
    }

    public static void  main (String[] args)
    {
        new Main ();
    }

}
