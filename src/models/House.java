package models;

import javafx.scene.Group;

public class House extends Model{
	
	public House(double fieldPosX, double fieldPosY, Group root) {
		super(fieldPosX, fieldPosY, root);
		setPath("./files/Japanese_Temple_Model/Model/Japanese_Temple.obj");
		this.scale = 0.3;
		this.translateY = 0;
	}

}
