package models;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;

public class Platform {
	protected static final double height = 1;
	private static final double fieldDimension = 10;
	
	public static Box selectedField = null;
	
	private int dimension;
	private Group platformNode;
	private static PlatformNet net;
	
	public static final PhongMaterial FIELD_MATERIAL = new PhongMaterial(Color.hsb(160, 0.7, 0.8));
	public static final PhongMaterial SELECTED_FIELD_MATERIAL = new PhongMaterial(Color.hsb(200, 0.8, 0.9));

	public void setupPlatform() {
		for(int i = -dimension/2; i <= dimension/2; i++) {
			for(int j = -dimension/2; j <= dimension/2; j++) {
				Box field = new Box(fieldDimension, height, fieldDimension);
				field.setTranslateX(fieldDimension*i);
				field.setTranslateZ(fieldDimension*j);
				field.setMaterial(FIELD_MATERIAL);
				platformNode.getChildren().add(field);
			}
		}
	}
	
	public Platform(int dimension) {
		this.dimension = dimension;
		platformNode = new Group();
		net = new PlatformNet(dimension);
		setupPlatform();
	}
	
	public Platform() {
		new Platform(100);
	}
	
	public Group getNode() {
		return platformNode;
	}
	
	private static void select(int x, int y) {
		net.select(x, y);
		System.out.println("(" + x + ", " + y + ")");
	}

	private static void deselect() {
		net.deselect();
	}
	
	public static void select() {
		if(selectedField == null) {
			deselect();
		} else {
			select((int) (selectedField.getTranslateX() / fieldDimension), (int) (selectedField.getTranslateZ()/ fieldDimension));
		}
	}
	
	public static void fieldSelected(Node node) {
		Box shape = (Box) node;
		boolean deselect = false;
		boolean selected = Platform.selectedField != null;
		if(selected) {
			if(Platform.selectedField == shape) {
				shape.setMaterial(Platform.FIELD_MATERIAL);
				deselect = true;
			} else {
				Platform.selectedField.setMaterial(Platform.FIELD_MATERIAL);
				shape.setMaterial(Platform.SELECTED_FIELD_MATERIAL);
			}
		} else {
			shape.setMaterial(Platform.SELECTED_FIELD_MATERIAL);
		}
		
		if(deselect) {
			deselect();
			Platform.selectedField = null;
		} else {
			Platform.selectedField = shape;
			Platform.select();
		}
	}
}
