import java.util.HashSet;

class AutoPlayer {
    private Tile[][] board;
    private HashSet<Tile> clicked = new HashSet<>();

    AutoPlayer(Tile[][] board){
        this.board=board;
    }

    /*
    returns the number of blocked squares next to a certain point
     */
    private int getBlocked(Point p){
        int count = 0;
        for(int i=-1; i<2; i++){
            for(int j=-1; j<2; j++){
                if(p.getY()+j>-1 && p.getY()+j<board[0].length && p.getX()+i>-1 && p.getX()+i<board.length){
                    if(board[p.getX()+i][p.getY()+j].isNotClicked()){
                        count++;
                    }
                }
            }
        }
        return count;
    }

    /*
    returns the number of flagged squares next to a certain point
     */
    private int getFlagged(Point p){
        int count = 0;
        for(int i=-1; i<2; i++){
            for(int j=-1; j<2; j++){
                if(p.getY()+j>-1 && p.getY()+j<board[0].length && p.getX()+i>-1 && p.getX()+i<board.length){
                    if(board[p.getX()+i][p.getY()+j].isFlagged()){
                        count++;
                    }
                }
            }
        }
        if(board[p.getX()][p.getY()].isFlagged()){
            count--;
        }
        return count;
    }

    public boolean moveClick() {
        //click first, if number of flags around equal number of tile, then click the rest of the un-clicked tiles
        for (Tile[] row : board) {
            for (Tile tile : row) {
                if(!tile.isNotClicked()) {
                    if (getFlagged(tile.getLoc()) == tile.getNum()) {

                        //for loop in a 3x3 square around block
                        for (int i = -1; i < 2; i++) {
                            for (int j = -1; j < 2; j++) {
                                if (tile.getLoc().getY() + j > -1 && tile.getLoc().getY() + j < board[0].length
                                        && tile.getLoc().getX() + i > -1 && tile.getLoc().getX() + i < board.length) {
                                    if (board[tile.getLoc().getX() + i][tile.getLoc().getY() + j].isNotClicked()) {
                                        clickTile(board[tile.getLoc().getX() + i][tile.getLoc().getY() + j]);
                                    }
                                }
                            }
                        }
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean moveFlag() {
        //flag after, iterate through the board and if neighbors == number of the tile, then flag all neighbors
        for (Tile[] row : board) {
            for (Tile tile : row) {
                if(!tile.isNotClicked()) {
                    if (getBlocked(tile.getLoc()) == tile.getNum()) {

                        //for loop in a 3x3 square around block
                        for (int i = -1; i < 2; i++) {
                            for (int j = -1; j < 2; j++) {
                                if (tile.getLoc().getY() + j > -1 && tile.getLoc().getY() + j < board[0].length
                                        && tile.getLoc().getX() + i > -1 && tile.getLoc().getX() + i < board.length) {
                                    if (board[tile.getLoc().getX() + i][tile.getLoc().getY() + j].isNotClicked()) {
                                        board[tile.getLoc().getX() + i][tile.getLoc().getY() + j].flag();
                                    }
                                }
                            }
                        }
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private void clickTile(Tile t){
        if(t.getNum()!=0){
            t.click();
        }else if(!t.isFlagged()) {
            if (clicked.add(t)) {
                t.click();
                for (int i = -1; i < 2; i++) {
                    for (int j = -1; j < 2; j++) {

                        if (t.getLoc().getX() + i > -1 && t.getLoc().getX() + i < board[0].length
                                && t.getLoc().getY() + j > -1 && t.getLoc().getY() + j < board.length) {
                            if (i != 0 && j != 0) clickTile(board[t.getLoc().getX() + i][t.getLoc().getY() + j]);
                        }

                    }
                }
            }
        }
    }
}
