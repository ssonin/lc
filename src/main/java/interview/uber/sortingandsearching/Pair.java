package interview.uber.sortingandsearching;

import static java.lang.String.format;

public class Pair {

    public final int row;
    public final int col;

    public Pair(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public String toString() {
        return format("p(%d, %d)", row, col);
    }
}
