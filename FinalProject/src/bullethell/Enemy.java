package bullethell;

import java.util.HashSet;

public abstract class Enemy extends LivingEntity {

	@Override
	public abstract Bullet fire();
	
	public abstract HashSet<EnemyBullet> fireAll();

	@Override
	public abstract void move();
	
	public abstract boolean canFire();
	

}
