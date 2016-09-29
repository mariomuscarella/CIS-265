import java.util.Date;

public abstract class GeometricObject {

	private String color = "white";
	private boolean filled = false;
	private Date dateOfCreation;

	public GeometricObject(String color, boolean filled) {
		this.color = color;
		this.filled = filled;
		this.dateOfCreation = new Date();
	}

	public GeometricObject() {
		this.dateOfCreation = new Date();
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public boolean isFilled() {
		return filled;
	}

	public void setFilled(boolean filled) {
		this.filled = filled;
	}

	public Date getDateOfCreation() {
		return dateOfCreation;
	}

	public void setDateOfCreation(Date dateOfCreation) {
		this.dateOfCreation = dateOfCreation;
	}
	
	public abstract double getArea();
	public abstract double getPerimeter();
	
	@Override
	public String toString() {
		return "GeometricObject [color=" + color + ", filled=" + filled + ", dateOfCreation=" + dateOfCreation + "]";
	}
	
}