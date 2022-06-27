package WirtualnySwiat;

public class SwiatHex extends Swiat {

    public SwiatHex(int rozmiarX, int rozmiarY) {
        super(rozmiarX, rozmiarY);
    }

    @Override
    public int getczyHex() {
        return 1;
    }

    @Override
    public int[][] getKierunki() {
        return new int[][]{{-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}};
    }
}
