public class Rectangle extends GeometricObject {

	private double width;

	private double height;

	public Rectangle() {
	}

	public Rectangle(double width, double height) {
		this.width = width;
		this.height = height;
	}

	public Rectangle(String color, boolean filled, double width, double height) {
		this.width = width;
		this.height = height;
		setColor(color);
		setFilled(filled);
	}
	
	// Return width
	public double getWidth() {
		return width;
	}

	// Set a new width
	public void setWidth(double width) {
		this.width = width;
	}

	// Return height
	public double getHeight() {
		return height;
	}

	//Set a new height	
	public void setHeight(double height) {
		this.height = height;
	}

	// Return area
	public double getArea() {
		return width * height;
	}

	//Return parameter
	public double getPerimeter() {
		return Math.round(2 * (width + height)*100.00)/100.00;
	}

	public String toString() {
		return super.toString() + "\n" +  "Rectangle [width=" + width + ", height=" + height + ", Area=" + getArea() +", Perimeter= " + getPerimeter() + " ]";
	}

}