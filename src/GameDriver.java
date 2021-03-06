import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class GameDriver {
    private JFrame frame = new JFrame("Minesweeper");
    private JPanel panel;
    private Tile[][] board;
    private Image[] pics = new Image[13];
    private HashSet<Point> clicked = new HashSet<>();
    private boolean firstClick = true;
    private boolean gameOver;
    private boolean victory;
    private int flags=100;
    private int time=0;
    private Timer t;

    public static void main(String[] args){
        new GameDriver().start();
    }

    private void start() {
        genImages();
        board = new Tile[20][30];
        genTiles();
        firstClick=true;
        gameOver = false;
        victory=false;

        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        panel = new JPanel(){
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawBoard(g);
                drawGame(g);
            }
        };
        t = new Timer(1000, e -> tick());
        panel.setPreferredSize(new Dimension(700, 500));
        panel.setBackground(Color.LIGHT_GRAY);
        setPanel();
        frame.add(panel);
        frame.pack();
        panel.requestFocusInWindow();
    }

    private void genImages() {
        try{
            pics[0] = ImageIO.read(new File("src/pics/one.gif"));
            pics[1] = ImageIO.read(new File("src/pics/two.gif"));
            pics[2] = ImageIO.read(new File("src/pics/three.gif"));
            pics[3] = ImageIO.read(new File("src/pics/four.gif"));
            pics[4] = ImageIO.read(new File("src/pics/five.gif"));
            pics[5] = ImageIO.read(new File("src/pics/six.gif"));
            pics[6] = ImageIO.read(new File("src/pics/seven.gif"));
            pics[7] = ImageIO.read(new File("src/pics/eight.gif"));
            pics[8] = ImageIO.read(new File("src/pics/block.gif"));
            pics[9] = ImageIO.read(new File("src/pics/flag.gif"));
            pics[10] = ImageIO.read(new File("src/pics/zero.gif"));
            pics[11] = ImageIO.read(new File("src/pics/bomb.gif"));
            pics[12] = ImageIO.read(new File("src/pics/redB.gif"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void tick(){
        time++;
        panel.repaint();
    }

    private void genTiles() {
        HashSet<Integer> bombs = new HashSet<>();
        while(bombs.size()<100){//100 is number of bombs
            bombs.add((int) (Math.random() * board.length * board[0].length));
        }
        int[] numbs = new int[100];
        int index=0;
        for(Integer num:bombs){
            numbs[index] = num;
            index++;
        }

        Arrays.sort(numbs);
        index=0;

        for(int r=0; r<board.length; r++){
            for(int c=0; c<board[r].length; c++){
                if(index!=100 && numbs[index]/board[0].length == r && numbs[index]%board[0].length == c) {
                    board[r][c] = new Tile(true, new Point(r, c), pics);
                    index++;
                }else{
                    board[r][c] = new Tile(false, new Point(r,c), pics);
                }
            }
        }

        setNumbs();
    }

    private void setNumbs() {
        int counter;
        for(int r=0; r<board.length; r++){
            for(int c=0; c<board[r].length; c++){

                counter=0;
                for(int i=-1; i<2; i++){
                    for(int j=-1; j<2; j++){
                        if(r+i>-1 && r+i < board.length && c+j>-1 && c+j<board[0].length){
                            if(board[r+i][c+j].isBomb()) {
                                counter++;
                            }
                        }
                    }
                }
                board[r][c].setNum(counter);
            }
        }
    }

    private void drawGame(Graphics g){
        for(Tile[] row:board){
            for(Tile tile:row){
                tile.draw(g, (panel.getWidth()-600)/2);
            }
        }
    }
    private void drawBoard(Graphics g){
        g.setColor(Color.WHITE);
        g.fillRect((panel.getWidth()-600)/2 - 3, 47, 606, 3);
        g.fillRect((panel.getWidth()-600)/2 - 3, 47, 3, 406);

        g.setColor(Color.GRAY);
        g.fillRect((panel.getWidth()-600)/2 - 3, 450, 606, 3);
        g.fillRect((panel.getWidth()-600)/2 +600 , 47, 3, 406);

        g.drawRect((panel.getWidth()-600)/2 - 13, 10, 626, 456);

        g.setColor(Color.BLACK);
        g.fillRect((panel.getWidth()-600)/2, 13, 70, 30);
        g.fillRect(panel.getWidth() - ((panel.getWidth()-600)/2)-70, 13, 70, 30);

        try{
            Image pic;
            if(gameOver){
                pic = ImageIO.read(new File("src/pics/sad.gif"));
            }else if(victory) {
                pic = ImageIO.read(new File("src/pics/cool.gif"));
            }else {
                pic = ImageIO.read(new File("src/pics/smile.gif"));
            }
            g.drawImage(pic, (panel.getWidth() / 2) - 15, 13, 30, 30, null);
        }catch (IOException e){
            e.printStackTrace();
        }

        drawValue(new Point((panel.getWidth()-600)/2,13), g, flags);
        drawValue(new Point(panel.getWidth() - ((panel.getWidth()-600)/2)-70, 13), g, time);
    }

    private void drawValue(Point p, Graphics g, int num){
        DrawNums drawNums = new DrawNums(g);
        char[] chars = (num+"").toCharArray();
        ArrayList<Character> draw = new ArrayList<>();
        for(int i=3-chars.length; i>0; i--){
            draw.add('0');
        }
        for(char c:chars){
            draw.add(c);
        }

        for(int i=0; i<draw.size(); i++){
            switch (draw.get(i)){
                case '0':
                    drawNums.drawZero(new Point(p.getX()+(5 * (i+1)) + (10 * i), p.getY()));
                    break;
                case '1':
                    drawNums.drawOne(new Point(p.getX()+(5 * (i+1)) + (10 * i), p.getY()));
                    break;
                case '2':
                    drawNums.drawTwo(new Point(p.getX()+(5 * (i+1)) + (10 * i), p.getY()));
                    break;
                case '3':
                    drawNums.drawThree(new Point(p.getX()+(5 * (i+1)) + (10 * i), p.getY()));
                    break;
                case '4':
                    drawNums.drawFour(new Point(p.getX()+(5 * (i+1)) + (10 * i), p.getY()));
                    break;
                case '5':
                    drawNums.drawFive(new Point(p.getX()+(5 * (i+1)) + (10 * i), p.getY()));
                    break;
                case '6':
                    drawNums.drawSix(new Point(p.getX()+(5 * (i+1)) + (10 * i), p.getY()));
                    break;
                case '7':
                    drawNums.drawSeven(new Point(p.getX()+(5 * (i+1)) + (10 * i), p.getY()));
                    break;
                case '8':
                    drawNums.drawEight(new Point(p.getX()+(5 * (i+1)) + (10 * i), p.getY()));
                    break;
                case '9':
                    drawNums.drawNine(new Point(p.getX()+(5 * (i+1)) + (10 * i), p.getY()));
                    break;
            }
        }
    }

    private void setPanel(){
        panel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int offset = (panel.getWidth()-600)/2;
                int row = (e.getX()-offset)/20;
                int col = (e.getY()-50)/20;
                if(!gameOver && row<board[0].length && col<board.length && e.getX()-offset>=0 && e.getY()-50>=0) {
                    if (e.getButton() == 1) {
                        revealClick(col, row);
                        if(board[col][row].isBomb()){
                            board[col][row].setRedBomb(true);
                            gameOver();
                        }
                    }
                    if(e.getButton()==3 && flags>0){
                        board[col][row].flag();
                        if(board[col][row].isFlagged()){
                            flags--;
                        }else{
                            flags++;
                        }
                    }
                    panel.repaint();
                }
                if(e.getX() > (panel.getWidth()/2)-15 && e.getX() < (panel.getWidth()/2)+15 &&
                        e.getY() > 13 && e.getY() < 43){
                    reset();
                }
                gameWin();
            }

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        });
    }

    private void gameWin(){
        boolean out = true;
        for(Tile[] row:board){
            for(Tile tile:row){
                if(tile.isBomb() && !tile.isFlagged()){
                    out=false;
                }
            }
        }
        if(out){
            victory=true;
            t.stop();
        }
    }

    private void gameOver(){
        gameOver = true;
        for(Tile[] row:board){
            for(Tile tile:row){
                if(tile.isBomb()){
                    tile.click();
                }
            }
        }
        t.stop();
    }

    private void reset(){
        genTiles();
        firstClick=true;
        gameOver = false;
        panel.repaint();
        flags=100;
        time=0;
        t.stop();
    }

    private void revealClick(int col, int row){
        if(firstClick){
            while(board[col][row].getNum()!= 0 || board[col][row].isBomb()){
                genTiles();
            }
            firstClick=false;
            t.start();
            revealClick(col, row);
        }else if(board[col][row].getNum()!=0){
            board[col][row].click();
        }else if(!board[col][row].isFlagged()) {
            if (clicked.add(board[col][row].getLoc())) {
                board[col][row].click();
                for(int i=-1; i<2; i++){
                    for(int j=-1; j<2; j++){

                        if(row+i>-1 && row+i < board[0].length && col+j>-1 && col+j<board.length){
                            if(board[col+j][row+i].getNum()==0){
                                revealClick(col+j, row+i);
                            }else{
                                board[col+j][row+i].click();
                            }
                        }

                    }
                }
            }
        }
    }
}
