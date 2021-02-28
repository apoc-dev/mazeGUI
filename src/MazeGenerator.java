import java.util.*;

public class MazeGenerator{

    int size;
    Random random = new Random();
    ArrayList<Cell> list = new ArrayList<>();

    public MazeGenerator(int size){

        this.size = size;
        generate();
    }
    public int getSize() {
        return size;
    }
    public ArrayList<Cell> getList() {
        return list;
    }

    private void generate(){
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                list.add(new Cell(x, y));
            }
            
        }
    }

    public List<Cell> getNeighbors(Cell current){

        List<Cell> neighbors = new ArrayList<>();

        for (Cell cell : list) {
            
            if(current.getX() == cell.getX()-1 && current.getY() == cell.getY() || current.getX() == cell.getX()+1 && current.getY() == cell.getY()){
                neighbors.add(cell);
            }

            if(current.getY() == cell.getY()-1 && current.getX() == cell.getX() || current.getY() == cell.getY()+1 && current.getX() == cell.getX()){
                neighbors.add(cell);
            }
        }

        return neighbors;

    }

    public void dfs(){
        Stack<Cell> stack = new Stack();
        stack.push(list.get(0));
        
        while (!stack.isEmpty()){

            Cell current = stack.pop();
            List<Cell> neighbors = getNeighbors(current);
            boolean flag = false;


            for (Cell neighbor : neighbors) {
                if(!neighbor.getVisit()){
                    neighbor.breakWalls(current.getX(), current.getY());
                    current.breakWalls(neighbor.getX(), neighbor.getY());
                    neighbor.setVisit(true);
                    flag = true;
                }
            }

            while (!neighbors.isEmpty() && flag) {
                int r = random.ints(0, neighbors.size()).findFirst().getAsInt();
                stack.push(neighbors.get(r));
                neighbors.remove(r);
            }

        }

    }

}