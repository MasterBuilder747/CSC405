package PhongShading.Utilities;

public class PointD {
	public double x, y;
	public PointD(double _x, double _y) {
		x = _x;
		y = _y;
	}
	
	public String toString () {
		return "<" + x + ", " + y + ">";
	}

}
