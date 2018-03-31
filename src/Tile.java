import java.awt.*;

public class Tile {
    private boolean flagged, bomb, clicked;
    private int num;
    private Point loc;
    private Image one, two, three, four, five, six, seven, eight, block, flag, zero, Bomb;

    Tile(boolean bomb, Point loc, Image[] pics){
        super();
        this.bomb = bomb;
        this.flagged = false;
        this.clicked = false;
        this.loc = loc;
        one = pics[0];
        two = pics[1];
        three = pics[2];
        four = pics[3];
        five = pics[4];
        six = pics[5];
        seven = pics[6];
        eight = pics[7];
        block = pics[8];
        flag = pics[9];
        zero = pics[10];
        Bomb = pics[11];
    }

    public void click(){
        if(!flagged) {
            this.clicked = true;
        }
    }
    public void flag(){
        if(!clicked) this.flagged = !flagged;
    }

    public boolean isBomb(){
        return bomb;
    }
    public boolean isFlagged(){
        return flagged;
    }
    public int getNum(){return num;}

    public void setNum(int num){
        this.num = num;
    }

    public void draw(Graphics g, int offset){
        if(offset<0) offset = 0;
        if (flagged) {
            g.drawImage(flag, loc.getY()*20+offset, loc.getX()*20+50, 20, 20, null);
        }else if(!clicked){
            g.drawImage(block, loc.getY()*20+offset, loc.getX()*20+50, 20, 20, null);
        }else if(bomb){
            g.drawImage(Bomb, loc.getY()*20+offset, loc.getX()*20+50, 20, 20, null);
        }else{
            switch (num){
                case 0:
                    g.drawImage(zero, loc.getY()*20+offset, loc.getX()*20+50, 20, 20, null);
                    break;
                case 1:
                    g.drawImage(one, loc.getY()*20+offset, loc.getX()*20+50, 20, 20, null);
                    break;
                case 2:
                    g.drawImage(two, loc.getY()*20+offset, loc.getX()*20+50, 20, 20, null);
                    break;
                case 3:
                    g.drawImage(three, loc.getY()*20+offset, loc.getX()*20+50, 20, 20, null);
                    break;
                case 4:
                    g.drawImage(four, loc.getY()*20+offset, loc.getX()*20+50, 20, 20, null);
                    break;
                case 5:
                    g.drawImage(five, loc.getY()*20+offset, loc.getX()*20+50, 20, 20, null);
                    break;
                case 6:
                    g.drawImage(six, loc.getY()*20+offset, loc.getX()*20+50, 20, 20, null);
                    break;
                case 7:
                    g.drawImage(seven, loc.getY()*20+offset, loc.getX()*20+50, 20, 20, null);
                    break;
                case 8:
                    g.drawImage(eight, loc.getY()*20+offset, loc.getX()*20+50, 20, 20, null);
                    break;
            }
        }
    }

    public Point getLoc(){
        return this.loc;
    }
}
