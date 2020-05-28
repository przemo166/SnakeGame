import java.awt.*;

public class Body {

    private int  xCor, yCor, width, height ;

    public Body (int xCor, int yCor, int tileSize)
    {
        this.xCor = xCor;
        this.yCor = yCor;

        width = tileSize;
        height = tileSize;
    }

    public void  draw (Graphics g)
    {
        g.setColor(Color.BLUE);
        g.fillRect(xCor*width,yCor*height,width,height);
    }

    public int getxCor ()
    {
        return xCor;
    }

    public int getyCor ()
    {
        return yCor;
    }

    public void setxCor (int xCor)
    {
        this.xCor = xCor;
    }

    public void  setyCor (int yCor)
    {
        this.yCor = yCor;
    }
}
