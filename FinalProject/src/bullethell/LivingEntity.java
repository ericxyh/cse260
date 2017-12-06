package bullethell;

public abstract class LivingEntity extends Entity {

	private int health;

	public int getHealth() {
		return health;
	}

	public void setHealth(int h) {
		health = h;
	}

	public abstract Bullet fire();

	public abstract void move();

	public void loseHealth() {
		health -= 1;
		if (getHealth() <= 0) {
			die();
		}
	}

}
