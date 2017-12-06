package bullethell;

import java.util.HashSet;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;

public class Enemy1 extends Enemy {

	
	//finish this
	private boolean inPosition = false;
	private final double CENTER_X = SCREEN_WIDTH/ 2;
	private final double CENTER_Y = SCREEN_HEIGHT / 2 - 50;
	private final double CENTER_RADIUS = 160;
	
	private double rotation = 180;

	public Enemy1() {
		Circle sha = new Circle(20);
		sha.setFill(Color.GREEN);
		sha.setStroke(Color.BLACK);
		sha.setRotate(rotation + ANGLE_DIFF);
		setShape(sha);
		setHealth(200);
		setXY(CENTER_X - CENTER_RADIUS, 0);
	}

	@Override
	public EnemyBullet1 fire() {

			Ellipse bulletShape = new Ellipse(4, 8);
			bulletShape.setFill(Color.AQUA);
			bulletShape.setRotate(rotation + ANGLE_DIFF - 180);
			bulletShape.setStroke(Color.BLACK);
			EnemyBullet1 bul = new EnemyBullet1();
			bul.setShape(bulletShape);
			double x = CENTER_X + CENTER_RADIUS * Math.cos(rotation / 180 * Math.PI);
			double y = CENTER_Y + CENTER_RADIUS * Math.sin(rotation / 180 * Math.PI);
			bul.setXY(x,y);
			return bul;
	}
	
	public HashSet<EnemyBullet> fireAll(){
		HashSet<EnemyBullet> ans = new HashSet<>();
		for (int i = 0 ; i < 12; i++) {
			EnemyBullet1 bul = fire();
			bul.getShape().setRotate(bul.getShape().getRotate() + 30* i);
			ans.add(bul);
		}
		return ans;
	}
	
	public boolean canFire() {
		return inPosition;
	}

	@Override
	public void move() {
		if (! canFire()) {
			setXY(getX(), getY() + 20);
			if (getY() >= CENTER_Y) {
				inPosition = true;
			};
		} else {
			rotation -= 10;
			if (rotation < 0) {
				rotation += 360;
			}
			getShape().setRotate(rotation +ANGLE_DIFF);
			double x = CENTER_X + CENTER_RADIUS * Math.cos(rotation / 180 * Math.PI);
			double y = CENTER_Y + CENTER_RADIUS * Math.sin(rotation / 180 * Math.PI);
			setXY(x, y);
		}
	}
}
