package bullethell;

import javafx.scene.shape.Shape;

public abstract class Bullet extends Entity {

	public abstract void move();
	
	public void hit(LivingEntity en) {
		Shape temp = Shape.intersect(getShape(), en.getShape());
		if (temp.getBoundsInLocal().getWidth() != -1) {
			en.loseHealth();
			die();
		}
	}

}
