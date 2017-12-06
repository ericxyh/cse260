package bullethell;

import javafx.scene.shape.Rectangle;

public class PlayerBullet extends Bullet {

	public PlayerBullet() {
		setShape( new Rectangle(7, 7));
	}
	
	@Override
	public void move() {
		setXY(getX(), getY() - 5);
		if (getY() < 0)
			die();
	}

	@Override
	public void hit(LivingEntity en) {
		if (en instanceof Enemy) {
			super.hit(en);
		}
	}

}
