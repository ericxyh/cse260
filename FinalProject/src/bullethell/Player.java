package bullethell;

import java.util.Timer;
import java.util.TimerTask;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;

public class Player extends LivingEntity {

	private Ellipse sprite;
	private boolean invincible;
	private boolean fireLeft = true;

	public Player() {
		setShape(new Circle(3));
		sprite = new Ellipse(9,15);
		sprite.setFill(Color.RED);
		setXY(0, 0);
		setHealth(4);
	}
	
	public Ellipse getSprite() {
		return sprite;
	}
	
	public void setXY(double x, double y) {
		super.setXY(x, y);
		getSprite().setLayoutX(getX());
		getSprite().setLayoutY(getY());
	}

	public boolean isInvincible() {
		return invincible;
	}

	@Override
	public PlayerBullet fire() {
		PlayerBullet bul = new PlayerBullet();
		if (fireLeft) {
			bul.setXY(getX() - 10, getY() - 10);
			fireLeft = false;
		} else {
			bul.setXY(getX() + 5, getY() - 10);
			fireLeft = true;
		}
		return bul;
	}

	@Override
	public void loseHealth() {
		if (!isInvincible()) {
			super.loseHealth();
			invincible = true;
			Timer invincibleTime = new Timer();
			getSprite().setFill(Color.YELLOW);
			invincibleTime.schedule(new TimerTask() {
				
				@Override
				public void run() {
					invincible = false;
					getSprite().setFill(Color.RED);
				}
			}, 5000);
		}
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
	}

}
