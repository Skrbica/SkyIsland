package app;

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
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import models.Balloon;
import models.House;
import models.Model;
import models.Platform;
import models.Tree;

public class App extends Application {
	
	Balloon balloons;
	Group root;
	Scene scene;
	boolean flying = false;
	
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
			scene.setFill(background(time));
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
		for(int i = 0; i < 5; i++) {
			trees.addNode(i*10, 0, i*10, 0);
		}
		//root.getChildren().addAll(trees.getObjects());
		
		balloons = new Balloon(0, 0, root);
		balloons.addNode(-10, -1, -10, 0);
		
		House houses = new House(0, 0, root);
		houses.addNode(-20, 0, -20, 0);
		//root.getChildren().addAll(houses.getObjects());
		
		/* ====== Camera ========================================================================== */

		PerspectiveCamera camera = new PerspectiveCamera(true);
		
		camera.setRotationAxis(Rotate.X_AXIS);
		camera.setRotate(-30);

		camera.setTranslateY(-60);
		camera.setTranslateZ(-90);

		camera.setFieldOfView(45);
		camera.setFarClip(1000);
		
		/* ====== Scene ========================================================================== */
		
		scene = new Scene(root, 800, 600, true, SceneAntialiasing.BALANCED);
		
		scene.setFill(Color.WHITE);
		scene.setCamera(camera);
		scene.setCursor(Cursor.HAND);
		
		
		/* ====== Stage ========================================================================== */
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("Sky Island");
		primaryStage.show();

		new CameraController(scene, camera, root);
		List<Model> models = new ArrayList<Model>();
		models.add(trees);
		models.add(houses);
		new Events(scene, models, platform);
		animationTimer.start();
	}
	
	
	public static void main(String[] args) {
		Application.launch();
	}

}
