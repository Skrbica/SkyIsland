package models;

import javafx.scene.Group;

public class Tree extends Model {
	
	public Tree(double fieldPosX, double fieldPosY, Group root) {
		super(fieldPosX, fieldPosY, root);
		setPath("./files/tree/Tree low.obj");
		this.scale = 0.07;
	}
}
