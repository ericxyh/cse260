package bullethell;

import javafx.scene.shape.Shape;

public abstract class Entity{

	private double x;
	private double y;
	private Shape shape;
	private boolean dead = false;
	protected static final int SCREEN_WIDTH = 1000;
	protected static final int SCREEN_HEIGHT = 600;
	protected final double ANGLE_DIFF = 90;
	
	
	public boolean isDead() {
		return dead;
	}
	
	public void die() {
		dead = true;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public Shape getShape(){
		return shape;
	}
	
	public abstract void move();
	
	public void setXY(double x, double y) {
		this.x = x;
		this.y = y;
		getShape().setLayoutX(getX());
		getShape().setLayoutY(getY());
	}
	
	public void setShape(Shape sha) {
		shape = sha;
	}
	
}
