package dojo.dp.flyweight;

public class Point {
    private static Point[][] CACHED;

    static {
        CACHED = new Point[100][];
        for (int i = 0; i < 100; i++) {
            CACHED[i] = new Point[100];
            for (int j = 0; j < 100; j++) {
                CACHED[i][j] = new Point(i, j);
            }

        }
    }

    int x;
    int y;

    private Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    static Point makePoint(int x, int y) {
        if (x >= 0 && x < 100 && y >= 0 && y < 100) {
            return CACHED[x][y];
        } else {
            return new Point(x, y);
        }
    }
}
