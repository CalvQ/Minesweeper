public class Point {
    private int x;
    private int y;

    Point(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }

    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Point && this.x == ((Point) obj).getX() && this.y == ((Point) obj).getY();
    }
}
