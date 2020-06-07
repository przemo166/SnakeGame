import java.awt.*;

/** Klasa, w której znajdują się elementy do tworzenia węża */
public class Body {

    /** Współrzedne x oraz y bloku oraz jego długość i szerokość */
    private int  xCor, yCor, width, height ;

    /** Konstruktor klasy */
    public Body (int xCor, int yCor, int tileSize)
    {
        this.xCor = xCor;
        this.yCor = yCor;

        width = tileSize;
        height = tileSize;
    }

    /** Metoda rysująca dany blok */
    public void  draw (Graphics g)
    {
        g.setColor(Color.BLUE);
        g.fillRect(xCor*width,yCor*height,width,height);
    }

    /** xCor getter */
    public int getxCor ()
    {
        return xCor;
    }

    /** yCor getter */
    public int getyCor ()
    {
        return yCor;
    }

    /** xCor setter */
    public void setxCor (int xCor)
    {
        this.xCor = xCor;
    }

    /** yCor setter */
    public void  setyCor (int yCor)
    {
        this.yCor = yCor;
    }
}
