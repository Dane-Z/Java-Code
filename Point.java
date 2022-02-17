package inAndOut;

public class Point {
	private double x;
	private double y;

	// Constructor
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}

	// Get x
	public double x() {
		return x;
	}

	// Get y
	public double y() {
		return y;
	}


	public String toString() {
		return x + "," + y;
	}

}
