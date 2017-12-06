package bullethell;

public class EnemyBullet1 extends EnemyBullet {
	
	private boolean warped = true;

	@Override
	public void move() {
		double rotate = (getShape().getRotate() - ANGLE_DIFF) / 180 * Math.PI;
		setXY(getX() + 5* Math.cos(rotate), getY() + 5* Math.sin(rotate));
		if (getX() >= SCREEN_WIDTH) {
			if (warped) {
				die();
			} else {
				setXY(0,getY());
				warped = true;
			}
		}
		if (getX() < 0 ) {
			if (warped) {
				die();
			} else {
				setXY(SCREEN_WIDTH,getY());
				warped = true;
			}
		}
		if (getY() > SCREEN_WIDTH || getY() < 0) {
			die();
		}
	}

}
