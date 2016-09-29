public class Circle extends GeometricObject {

	private double radius;
	
	public Circle() {
	}

	public Circle(double radius) {
		super();
		this.radius = radius;
	}

	public Circle(String color, boolean filled, double radius) {
		super(color, filled);
		this.radius = radius;
		//setColor(color);
		//setFilled(filled);
	}
	
	//Return radius
	public double getRadius() {
		return radius;
	}

	//Set a new radius
	public void setRadius(double radius) {
		this.radius = radius;
	}

	//Return area
	public double getArea() {
		return Math.round((radius * radius * Math.PI)*100.00)/100.00;
	}

	//Return diameter
	public double getDiameter() {
		return 2 * radius;
	}

	//Return perimeter
	public double getPerimeter() {
		return Math.round((2 * radius * Math.PI)*100.00)/100.00;
	}

	public String toString() {
		return super.toString() + "\n" + "Circle [ radius=" + getRadius() + " area=" + getArea() + " Perimeter=" + getPerimeter() + " ]";
	}


}