public class Point3d extends Point2d {
    int z;
    public Point3d(int _x, int _y, int _z) {
        super(_x, _y);
        z = _z;
    }
    public Point3d(int _x, int _y) {
        this(_x, _y, 0);
    }
    public String toString() {
        return "("+ x + "," + y + "," + z + ")";
    }
}
