import java.util.*;

public class MazeProcessor {
    
    List<Cell> list;
    int size;
    int[][] listBinary;
    MazeProcessor (List<Cell> list, int size){
        this.list = list;
        this.size = size;
    }

    public void toBinary(){
        listBinary = new int[size*3][size*3];
        for (int y = 0; y < listBinary.length; y++) {
            for (int x = 0; x < listBinary.length; x++) {
                listBinary[x][y] = 1;
            }   
        }

        for (Cell cell : list) {
            
            int x = convertPosition(cell.getX());
            int y = convertPosition(cell.getY());

            boolean[] cellWalls = cell.getWalls();

            listBinary[x][y] = 0;
            
            if(!cellWalls[0]){
                listBinary[x][y-1] = 0;
            }
            if(!cellWalls[1]){
                listBinary[x][y+1] = 0;
            }
            if(!cellWalls[2]){
                listBinary[x-1][y] = 0;
            }
            if(!cellWalls[3]){
                listBinary[x+1][y] = 0;
            }

        }
    }

    private int convertPosition(int orgPos){
        return 1+(orgPos*3);
    }

    public void print(){
        for (int y = 0; y < listBinary.length; y++) {
            
            for (int x = 0; x < listBinary.length; x++) {
                System.out.print(listBinary[x][y]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    public int[][] getListBinary() {
        return listBinary;
    }

}
