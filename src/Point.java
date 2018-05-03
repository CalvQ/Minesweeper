public class Point {
    private int x;
    private int y;

    Point(int x, int y){
        this.x = x;
        this.y = y;
    }

    int getX(){
        return this.x;
    }
    int getY(){
        return this.y;
    }

    void setY(int y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Point && this.x == ((Point) obj).getX() && this.y == ((Point) obj).getY();
    }
}
