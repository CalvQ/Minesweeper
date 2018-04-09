import java.awt.*;

public class DrawNums {
    Graphics g;

    public DrawNums(Graphics g){
        super();
        this.g = g;
    }

    public void drawVert(Point p){
        Color temp = g.getColor();
        g.setColor(Color.RED);

    }
    public void drawHoriz(Point p){
        Color temp = g.getColor();
        g.setColor(Color.RED);
    }
}
