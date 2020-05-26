import java.awt.*;

public class Apple {

    private int xCor, yCor, width, height;

    public Apple(int xCor, int yCor, int tileSize) {
        this.xCor = xCor;
        this.yCor = yCor;

        width = tileSize;
        height = tileSize;
    }

    public void tick() {

    }

    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(xCor * width, yCor * height, width, height);
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