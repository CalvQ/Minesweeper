import java.awt.*;

public class DrawNums {
    private Graphics g;

    DrawNums(Graphics g){
        super();
        this.g = g;
    }

    //drawing components for digital-clock style
    private void drawVertical(Point p){
        Color temp = g.getColor();
        g.setColor(Color.RED);
        int[] xPoints = {p.getX(), p.getX()+1, p.getX()+2, p.getX()+2, p.getX()+1, p.getX()};
        int[] yPoints = {p.getY()+1, p.getY(), p.getY()+1, p.getY()+8, p.getY()+9, p.getY()+8};
        g.fillPolygon(xPoints, yPoints, 6);
        g.setColor(temp);
    }
    private void drawHorizontal(Point p){
        Color temp = g.getColor();
        g.setColor(Color.RED);
        int[] xPoints = {p.getX(), p.getX()+1, p.getX()+8, p.getX()+9, p.getX()+8, p.getX()+1};
        int[] yPoints = {p.getY()+1, p.getY(), p.getY(), p.getY()+1, p.getY()+2, p.getY()+2};
        g.fillPolygon(xPoints, yPoints, 6);
        g.setColor(temp);
    }

    public void drawZero(Point p){
        p.setY(p.getY()+4);//always plus four for shifting down
        drawHorizontal(p);
        drawVertical(p);
        drawVertical(new Point(p.getX(), p.getY()+10));
        drawHorizontal(new Point(p.getX(), p.getY()+18));
        drawVertical(new Point(p.getX()+7, p.getY()));
        drawVertical(new Point(p.getX()+7, p.getY()+10));
    }
    public void drawOne(Point p){
        p.setY(p.getY()+4);
        drawVertical(new Point(p.getX()+7, p.getY()));
        drawVertical(new Point(p.getX()+7, p.getY()+10));
    }
    public void drawTwo(Point p){
        p.setY(p.getY()+4);
        drawHorizontal(p);
        drawVertical(new Point(p.getX()+7, p.getY()));
        drawHorizontal(new Point(p.getX(), p.getY()+9));
        drawVertical(new Point(p.getX(), p.getY()+10));
        drawHorizontal(new Point(p.getX(), p.getY()+18));
    }
    public void drawThree(Point p){
        p.setY(p.getY()+4);
        drawHorizontal(p);
        drawVertical(new Point(p.getX()+7, p.getY()));
        drawHorizontal(new Point(p.getX(), p.getY()+9));
        drawVertical(new Point(p.getX()+7, p.getY()+10));
        drawHorizontal(new Point(p.getX(), p.getY()+18));
    }
    public void drawFour(Point p){
        p.setY(p.getY()+4);
        drawVertical(p);
        drawHorizontal(new Point(p.getX(), p.getY()+9));
        drawVertical(new Point(p.getX()+7, p.getY()));
        drawVertical(new Point(p.getX()+7, p.getY()+10));
    }
    public void drawFive(Point p){
        p.setY(p.getY()+4);
        drawHorizontal(p);
        drawVertical(p);
        drawHorizontal(new Point(p.getX(), p.getY()+9));
        drawVertical(new Point(p.getX()+7, p.getY()+10));
        drawHorizontal(new Point(p.getX(), p.getY()+18));
    }
    public void drawSix(Point p){
        p.setY(p.getY()+4);
        drawHorizontal(p);
        drawVertical(p);
        drawVertical(new Point(p.getX(), p.getY()+10));
        drawHorizontal(new Point(p.getX(), p.getY()+18));
        drawVertical(new Point(p.getX()+7, p.getY()+10));
        drawHorizontal(new Point(p.getX(), p.getY()+9));
    }
    public void drawSeven(Point p){
        p.setY(p.getY()+4);
        drawHorizontal(p);
        drawVertical(new Point(p.getX()+7, p.getY()));
        drawVertical(new Point(p.getX()+7, p.getY()+10));
    }
    public void drawEight(Point p){
        p.setY(p.getY()+4);
        drawHorizontal(p);
        drawVertical(p);
        drawVertical(new Point(p.getX(), p.getY()+10));
        drawHorizontal(new Point(p.getX(), p.getY()+18));
        drawVertical(new Point(p.getX()+7, p.getY()));
        drawVertical(new Point(p.getX()+7, p.getY()+10));
        drawHorizontal(new Point(p.getX(), p.getY()+9));
    }
    public void drawNine(Point p){
        p.setY(p.getY()+4);
        drawHorizontal(p);
        drawVertical(p);
        drawVertical(new Point(p.getX()+7, p.getY()));
        drawVertical(new Point(p.getX()+7, p.getY()+10));
        drawHorizontal(new Point(p.getX(), p.getY()+9));
    }
}
