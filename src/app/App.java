package app;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import camera.CameraController;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import models.Balloon;
import models.House;
import models.Model;
import models.Platform;
import models.Tree;

public class App extends Application {
	
	private Balloon balloons;
	private Group root;
	private SubScene gameScene;
	private SubScene hud;
	private boolean flying = false;
	private boolean newSec = true;
	private int lastSec = 0;

	private static final int COIN_IMG_X = 35;
	private static final int COIN_TXT_X = 95;
	private static final int COIN_Y = 40;
	private static final int COIN_IMG_DIM = 35;
	private static final int COIN_IMG_Y_DIFF = 26;
	private static final int DISTANCE_X = 75;
	private static final int DISTANCE_Y = 15;
	private static final int HUD_BG_Y = 500;
	private static final int WIDTH = 800;
	private static final int HEIGHT = 600;
	private static final int IMG_SIZE = 70;
	
	private static List<ImageView> elementImgs;
	
	AnimationTimer animationTimer = new AnimationTimer() {
		@Override
		public void handle(long now) {
			double time = now/1e9;

			//balloon
			if(time % 20.0 < 10.0) {
				if(!flying) {
					flying = true;
				}
				balloons.update(time, root);
			} else {
				if(flying) {
					balloons.remove();
					flying = false;
				}
			}
			
			//background
			gameScene.setFill(background(time));
			
			//coins
			if((int) time > lastSec + 1) {
				newSec = true;
			}
			if(newSec) {
				Scoring.update(hud);
				newSec = false;
				lastSec = (int) time;
			}
		}
	};
	
	private Color background(double time) {
		double timeParam = (time % 60.0) / 60.0;
		if((time % 120) < 60) {
			return Color.hsb(210 + 40 * timeParam, 0.4 + timeParam/10.0, 1 - 0.8 * timeParam);
		} else {
			return Color.hsb(250 - 40 * timeParam, 0.5 - timeParam/10.0, 0.2 + 0.8 * timeParam);
		}
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {

		/* ====== Scene =========================================================================== */

		root = new Group();
		Platform platform = new Platform(10);
		root.getChildren().addAll(platform.getNode());
		
		Tree trees = new Tree(0, 0, root);
		/*for(int i = 0; i < 5; i++) {
			trees.addNode(i*10, 0, i*10, 0);
		}*/
		//root.getChildren().addAll(trees.getObjects());
		
		balloons = new Balloon(0, 0, root);
		balloons.addNode(-10, -1, -10, 0);
		
		House temples = new House(0, 0, root);
		//houses.addNode(-20, 0, -20, 0);
		//root.getChildren().addAll(houses.getObjects());
		
		/* ====== Camera ========================================================================== */

		PerspectiveCamera camera = new PerspectiveCamera(true);
		
		camera.setRotationAxis(Rotate.X_AXIS);
		camera.setRotate(-30);

		camera.setTranslateY(-50);
		camera.setTranslateZ(-100);

		camera.setFieldOfView(45);
		camera.setFarClip(1000);
		
		/* ====== Scene ========================================================================== */
		
		gameScene = new SubScene(root, WIDTH, HEIGHT, true, SceneAntialiasing.BALANCED);
		
		gameScene.setFill(Color.WHITE);
		gameScene.setCamera(camera);
		gameScene.setCursor(Cursor.HAND);

		/* ====== HUD ========================================================================== */

		hud = loadHud();
		
		/* ====== Stage ========================================================================== */
		Scene scene = new Scene(new Group(gameScene, hud));
		primaryStage.setScene(scene);
		primaryStage.setTitle("Sky Island");
		primaryStage.show();

		new CameraController(scene, camera, root);
		List<Model> models = new ArrayList<Model>();
		models.add(temples);
		models.add(trees);
		new Events(scene, models, platform, elementImgs, hud);
		animationTimer.start();
	}
	
	
	public static void main(String[] args) {
		Application.launch();
	}
	
	
	public static SubScene loadHud() throws FileNotFoundException {

		Font font = new Font(30);
		Text textCoins = new Text(COIN_TXT_X, COIN_Y, "" + Scoring.getCoins());
		textCoins.setFill(Color.WHITE);
		textCoins.setId("coins");
		textCoins.setFont(font);
		
		ImageView imgCoin = new ImageView();
		imgCoin.setImage(new Image(new FileInputStream("./files/imgs/coin.png")));
		imgCoin.setX(COIN_IMG_X);
		imgCoin.setY(COIN_Y - COIN_IMG_Y_DIFF);
		imgCoin.setFitHeight(COIN_IMG_DIM);
		imgCoin.setFitWidth(COIN_IMG_DIM);
		
		elementImgs = new ArrayList<>();
		
		ImageView temple = new ImageView();
		temple.setImage((new Image(new FileInputStream("./files/imgs/temple.jpg"))));
		temple.setX(DISTANCE_X);
		temple.setY(HUD_BG_Y + DISTANCE_Y);
		temple.setFitHeight(IMG_SIZE);
		temple.setFitWidth(IMG_SIZE);
		
		ImageView tree = new ImageView();
		tree.setImage((new Image(new FileInputStream("./files/imgs/tree.jpg"))));
		tree.setX(2 * DISTANCE_X + IMG_SIZE);
		tree.setY(HUD_BG_Y + DISTANCE_Y);
		tree.setFitHeight(IMG_SIZE);
		tree.setFitWidth(IMG_SIZE);
		
		ImageView placeObject = new ImageView();
		placeObject.setImage((new Image(new FileInputStream("./files/imgs/placeObj.jpg"))));
		placeObject.setX(5 * DISTANCE_X + 4*IMG_SIZE);
		placeObject.setY(HUD_BG_Y + DISTANCE_Y);
		placeObject.setFitHeight(IMG_SIZE);
		placeObject.setFitWidth(IMG_SIZE);
		placeObject.setOpacity(0.5);
		
		elementImgs.add(temple);
		elementImgs.add(tree);
		elementImgs.add(placeObject);
		
		Group rootHud = new Group(textCoins, imgCoin, temple, tree, placeObject);		
		return new SubScene(rootHud, WIDTH, HEIGHT, false, SceneAntialiasing.BALANCED);
	}

}
