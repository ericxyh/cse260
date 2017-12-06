package bullethell;

public abstract class EnemyBullet extends Bullet {
	

	@Override
	public abstract void move();

	@Override
	public void hit(LivingEntity en) {
		if (en instanceof Player) {
			super.hit(en);
		}
	}

}
