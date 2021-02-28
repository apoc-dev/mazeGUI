public class Cell {
    
    //position
    int x;
    int y;

    //walls if true they exist
    boolean wallNorth = true;
    boolean wallSouth = true; 
    boolean wallWest = true;
    boolean wallEast = true;

    //is cell visited
    boolean visit = false;

    Cell(int x, int y){

        this.x = x;
        this.y = y;

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setVisit(boolean visit) {
        this.visit = visit;
    }

    public boolean getVisit(){
        return this.visit;
    }

    public boolean[] getWalls(){
        return new boolean[] {wallNorth, wallSouth, wallWest, wallEast};
    }

    public void setEast(boolean east) {
        this.wallEast = east;
    }

    public void setNorth(boolean north) {
        this.wallNorth = north;
    }

    public void setSouth(boolean south) {
        this.wallSouth = south;
    }

    public void setWest(boolean west) {
        this.wallWest = west;
    }

    public void breakWalls(int x, int y){

        if(this.x == x){
            if(this.y > y){
                wallNorth = false;
            }else{
                wallSouth = false;
            }
        }

        if(this.y == y){
            if(this.x > x){
                wallWest = false;
            }else{
                wallEast = false;
            }
        }

    }

}
