public class MazeSolver {
    
    int[][] board;
    int[][] sol;

    int startX;
    int startY;
    int endX;
    int endY;

    public MazeSolver(int[][] board, int startx, int starty, int endx, int endy){
        this.sol = new int[board.length][board.length];
        
        this.startX = startx;
        this.startY = starty;
        this.endX = endx;
        this.endY = endy;

        this.sol[this.startX][this.startY] = 1;
        this.board = board;


    }

    public boolean solve(){
        if(backtrack(this.startX,this.startY, sol)){
            return true;
        }
        return false;
    }

    public boolean backtrack(int row, int col, int[][] sol){

        if(row == this.endX && col == this.endY){
            return true;
        }
        
        for (int i = 0; i < 4; i++) {
            if(i == 0 && check(row, col+1)){
                
                sol[row][col+1] = 1;
                if(backtrack(row, col+1, sol)){
                    return true;
                }
                sol[row][col+1] = 0;
                
            }
            if(i == 1 && check(row+1, col)){
             
                sol[row+1][col] = 1;
                if(backtrack(row+1, col, sol)){
                    return true;
                }
                sol[row+1][col] = 0;
                
            }
            if(i == 2 && check(row, col-1)){
               
                sol[row][col-1] = 1;
                if(backtrack(row, col-1, sol)){
                    return true;
                }
                sol[row][col-1] = 0;
            
            }
            if(i == 3 && check(row-1, col)){
              
                sol[row-1][col] = 1;
                if(backtrack(row-1, col, sol)){
                    return true;
                }
                sol[row-1][col] = 0;

            }
        }

        return false;
    }

    public boolean check(int row, int col){
        if(row < 0){
            return false;
        }

        if(col < 0){
            return false;
        }

        if(row >= board.length){
            return false;
        }

        if(col >= board.length){
            return false;
        }

        if (board[row][col] == 0 && sol[row][col] != 1){
            return true;
        }

        return false;
    }

    public void printboard(){
        System.out.println("-----------");
        System.out.println();
        for (int i = 0; i < board.length; i++) {
            //System.out.print("|");
            for (int j = 0; j < board.length; j++) {
                if (sol[i][j] == 1){
                    System.out.print(TerminalColours.ANSI_RED + board[i][j] + TerminalColours.ANSI_RESET);
                }else{
                    System.out.print(board[i][j]);
                }
                System.out.print(" ");
            }
            System.out.println();
            
        }
    }

    public int[][] getSolution(){
        return sol;
    }

}
