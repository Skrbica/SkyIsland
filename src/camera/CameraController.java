package camera;

import javafx.animation.TranslateTransition;
import javafx.geometry.Point3D;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Rotate;

public class CameraController {
	
	private Scene scene;
	private Camera camera;
	private Group root;
	private double lastXPosition;
	
	private static final double MIN_FLICK_PIXELS = 3;
    private static final double MOVEMENT = 1;
    private static final double MOVEMENT_ROT = 1.5;
    
    
    
    TranslateTransition transition = new TranslateTransition();
	
	
	public CameraController(Scene scene2, Camera camera, Group root) {
		this.scene = scene2;
		this.camera = camera;
		this.root = root;
		listen();
	}

	public void listen() {
		
		scene.setOnMouseDragged(e -> {
			double currentX = e.getSceneX();
            if (currentX - lastXPosition > MIN_FLICK_PIXELS) {
                root.getTransforms().add(new Rotate(-MOVEMENT_ROT, new Point3D(0,1,0)));
                lastXPosition = currentX;
            }

            else if (currentX - lastXPosition < -MIN_FLICK_PIXELS) {
                root.getTransforms().add(new Rotate(MOVEMENT_ROT, new Point3D(0,1,0)));
                lastXPosition = currentX;
            }
		});
		
		scene.setOnKeyPressed(e -> {
			/*String character = e.getText();
			
			if(character.equals("a")) {
                camera.setTranslateX(camera.getTranslateX() - MOVEMENT);
			} else if (character.equals("w")) {
                camera.setTranslateZ(camera.getTranslateZ() + MOVEMENT);
			} else if (character.equals("d")) {
                camera.setTranslateX(camera.getTranslateX() + MOVEMENT);
			} else if (character.equals("s")) {
                camera.setTranslateZ(camera.getTranslateZ() - MOVEMENT);
			} else if (character.equalsIgnoreCase("z")) {
				camera.setTranslateY(camera.getTranslateY() + MOVEMENT_Y);
			} else if (character.equalsIgnoreCase("x")) {
				camera.setTranslateY(camera.getTranslateY() - MOVEMENT_Y);
				
			}*/
		});
		
		scene.setOnMouseReleased(e -> {
			
		});
		
		scene.setOnScroll(e -> {
			double value = e.getDeltaY();
			int direction = value > 0 ? 1 : -1;
			camera.setTranslateY(camera.getTranslateY() + direction);
			camera.setTranslateZ(camera.getTranslateZ() + direction);
		});
		
	}
	
	
}
