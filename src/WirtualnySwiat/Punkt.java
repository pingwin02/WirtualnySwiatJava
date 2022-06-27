package WirtualnySwiat;

public class Punkt {
    private final int x, y;

    public Punkt(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Punkt(Punkt p) {
        this.x = p.getX();
        this.y = p.getY();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ')';
    }
}