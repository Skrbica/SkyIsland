package models;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;

public class Platform {
	private double dimension;
	protected static final double height = 1;
	private Group platformNode;

	public void setupPlatform() {
		Box top = getPlatformPart(true, Color.hsb(120, 0.4, 1));
		Box bot = getPlatformPart(false, Color.hsb(0, 0.4, 1));		

		platformNode =  new Group(top, bot);
	}
	
	public Box getPlatformPart(boolean top, Color cDiff) {
		Box platformPart = new Box(dimension, height, dimension);
		double k = top ? -1 : 1;
		platformPart.setTranslateY(height * k / 2);
		PhongMaterial pm = new PhongMaterial();
		pm.setDiffuseColor(cDiff);
		platformPart.setMaterial(pm);
		return platformPart;
	}
	
	public Platform(double dimension) {
		this.dimension = dimension;
		setupPlatform();
	}
	
	public Platform() {
		new Platform(25);
	}
	
	public Group getNode() {
		return platformNode;
	}
}
