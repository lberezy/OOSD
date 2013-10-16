public class Point2D {
	// represents a 2D point in game space, used for passing around data

	// note: this is deprecated and apparently slick already has a class like
	// this anyway
	public double x, y;

	public Point2D(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public Point2D(Point2D point) {
		this.x = point.x;
		this.y = point.y;
	}
}
