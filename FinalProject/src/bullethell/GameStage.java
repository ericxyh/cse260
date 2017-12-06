package bullethell;

import java.util.HashSet;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GameStage extends Application {

	private Pane gameScreen;
	private BorderPane statusScreen;
	private Player play;
	private Text health;

	private boolean gameOver = false;
	private boolean finished = false;
	private HashSet<Enemy> en = new HashSet<>();
	private HashSet<PlayerBullet> plb = new HashSet<>();
	private HashSet<EnemyBullet> enb = new HashSet<>();

	private Timeline shootPlayerBullet;
	private Timeline shootEnemyBullet;
	private Thread moveThings;
	private Thread gameScript;

	public void start(Stage primaryStage) throws Exception {
		setup();
		Scene scene = new Scene(statusScreen, 1000, 600);
		primaryStage.setScene(scene);
		primaryStage.show();

		shootPlayerBullet = new Timeline(new KeyFrame(Duration.millis(50), e -> {
			Bullet bul = play.fire();
			gameScreen.getChildren().add(bul.getShape());
			plb.add((PlayerBullet) bul);
			if (play.isDead()) {
				gameOver();
			}
		}));
		shootPlayerBullet.setCycleCount(Timeline.INDEFINITE);
		shootPlayerBullet.play();

		moveThings = new Thread(() -> {

			Timeline mE = new Timeline(new KeyFrame(Duration.millis(50), e -> {
				for (Enemy enemy : en) {
					if (!enemy.isDead()) {
						enemy.move();
					} else {
						if (enemy.getShape() != null) {
							gameScreen.getChildren().remove(enemy.getShape());
							enemy.setShape(null);
						}
					}
				}
			}));
			mE.setCycleCount(Timeline.INDEFINITE);
			mE.play();

			Timeline mB = new Timeline(new KeyFrame(Duration.millis(5), e -> {
				for (PlayerBullet playerBullet : plb) {
					if (!playerBullet.isDead()) {
						playerBullet.move();
						for (Enemy enemy : en) {
							if (!enemy.isDead()) {
								playerBullet.hit(enemy);
							}
						}
					} else {
						if (playerBullet.getShape() != null) {
							gameScreen.getChildren().remove(playerBullet.getShape());
							playerBullet.setShape(null);
						}
					}
				}
			}));
			mB.setCycleCount(Timeline.INDEFINITE);
			mB.play();

			Timeline mEB = new Timeline(new KeyFrame(Duration.millis(50), e -> {
				for (EnemyBullet enemyBullet : enb) {
					if (!enemyBullet.isDead()) {
						enemyBullet.move();
						enemyBullet.hit(play);
					} else {
						if (enemyBullet.getShape() != null) {
							gameScreen.getChildren().remove(enemyBullet.getShape());
							enemyBullet.setShape(null);
						}
					}
				}
			}));
			mEB.setCycleCount(Timeline.INDEFINITE);
			mEB.play();

			if (finished) {
				mE.stop();
				mB.stop();
				mEB.stop();
			}

		});
		moveThings.start();

		gameScript = new Thread(() -> {
			int firingInterval = 200;

			Platform.runLater(() -> {
				Enemy enemy = new Enemy1();
				en.add(enemy);
				gameScreen.getChildren().add(enemy.getShape());
			});
			shootEnemyBullet = new Timeline(new KeyFrame(Duration.millis(firingInterval), e -> {
				for (Enemy enemy : en) {
					if (!enemy.isDead() && enemy.canFire()) {
						HashSet<EnemyBullet> temp = enemy.fireAll();
						for (EnemyBullet enemyBullet : temp) {
							gameScreen.getChildren().add(enemyBullet.getShape());
						}
						enb.addAll(temp);
					}
				}
			}));
			shootEnemyBullet.setCycleCount(Timeline.INDEFINITE);
			shootEnemyBullet.play();

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			while (!allEnemyDefeated()) {
			}
			shootEnemyBullet.stop();
			en = new HashSet<>();
			Platform.runLater(() -> {
				finished();
			});
		});
		gameScript.start();
	}

	private boolean allEnemyDefeated() {
		for (Enemy enemy : en) {
			if (!enemy.isDead()) {
				return false;
			}
		}
		for (Enemy enemy : en) {
			if (enemy.getShape() != null) {
				Platform.runLater(() -> {
					gameScreen.getChildren().remove(enemy.getShape());
				});
			}

		}
		return true;
	}

	private void gameOver() {
		gameOver = true;
		shootEnemyBullet.stop();
		finished();
	}
	
	private void finished() {
		finished = true;
		shootPlayerBullet.stop();
		for (Enemy enemy : en) {
			if (!enemy.isDead()) {
				gameScreen.getChildren().remove(enemy.getShape());
			}
		}
		for (PlayerBullet playerBullet : plb) {
			if (!playerBullet.isDead()) {
				gameScreen.getChildren().remove(playerBullet.getShape());
			}
		}
		for (EnemyBullet enemyBullet : enb) {
			if (!enemyBullet.isDead()) {
				gameScreen.getChildren().remove(enemyBullet.getShape());
			}
		}
		gameScreen.getChildren().removeAll(play.getShape(), play.getSprite());
		if (gameOver) {
			gameScreen.getChildren().add(new Text(500, 300, "Game Over"));
		} else {
			gameScreen.getChildren().add(new Text(500, 300, "You Win!"));
		}
	}

	private void setup() {
		statusScreen = new BorderPane();
		gameScreen = new Pane();
		statusScreen.setCenter(gameScreen);
		play = new Player();
		health = new Text();
		statusScreen.setBottom(health);
		gameScreen.getChildren().addAll(play.getSprite(), play.getShape());
		gameScreen.setOnMouseMoved(e -> {
			play.setXY(e.getX(), e.getY());
			health.setText("Health: " + play.getHealth());
		});
	}

	public static void main(String[] args) {
		launch(args);
	}

}
