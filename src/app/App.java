package app;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Point3D;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import models.House;
import models.Platform;
import models.Tree;
import camera.CameraController;

public class App extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		/* ====== Scene =========================================================================== */

		Group root = new Group();
		Platform platform = new Platform(30);
		root.getChildren().add(platform.getNode());
		
		Tree trees = new Tree();
		for(int i = 0; i < 3; i++) {
			trees.addNode(i*3, 0, i*3, 0);
		}
		root.getChildren().addAll(trees.getObjects());
		
		House houses = new House();
		houses.addNode(-5, 0, -5, 0);
		root.getChildren().addAll(houses.getObjects());
		
		/* ====== Camera ========================================================================== */

		PerspectiveCamera camera = new PerspectiveCamera(true);
		
		camera.setRotationAxis(Rotate.X_AXIS);
		camera.setRotate(-28);

		camera.setTranslateY(-20);
		camera.setTranslateZ(-30);

		camera.setFieldOfView(45);
		
		/* ====== Scene ========================================================================== */
		
		Scene scene = new Scene(root, 800, 600, true, SceneAntialiasing.BALANCED);
		
		scene.setFill(Color.hsb(210, 0.4, 1));
		scene.setCamera(camera);
		scene.setCursor(Cursor.HAND);
		
		/* ====== Stage ========================================================================== */
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("Sky Island");
		primaryStage.show();

		new CameraController(scene, camera, root);
	}
	
	
	public static void main(String[] args) {
		Application.launch();
	}

}
