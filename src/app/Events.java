package app;

import java.util.List;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Box;
import models.Model;
import models.Platform;
import models.TransformationParameters;

public class Events {
	private Scene scene;
	private int selectedObject = 0;
	private List<Model> models;
	private Platform platform;
	
	public Events(Scene scene, List<Model> models,  Platform platform) {
		this.scene = scene;
		this.platform = platform;
		this.models = models;
		listen();
	}
	
	public void listen() {
		scene.setOnMouseClicked(e -> {
			Node node = e.getPickResult().getIntersectedNode();
			if (node instanceof Box) {
				Platform.fieldSelected(node);
			}
		});
		
		scene.setOnKeyPressed(e -> {
			String character = e.getText();
			
			if(e.getCode().equals(KeyCode.ENTER)) {
				if(selectedObject > 0 && platform.isSelected()) {
					TransformationParameters tp = platform.getSelectedFieldTransforms();
					models.get(selectedObject - 1).addNode(tp.getX(), 0, tp.getZ(), tp.getRot());
				}
			} else if (character.equals("1") || character.equals("2")) {
				int newSelectedObject = Integer.parseInt(character);
				if(selectedObject == newSelectedObject) {
					selectedObject = 0;
				} else {
					selectedObject = newSelectedObject;
				}
			}
		});
	}
}
