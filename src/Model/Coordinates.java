package Model;

public class Coordinates {
    private String id;
    private int x;
    private int y;
    public Coordinates(String id, int x, int y){
        this.id = id;
        this.x = x;
        this.y = y;
    }
    public String getId() {
        return id;
    }
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
