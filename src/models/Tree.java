package models;

public class Tree extends Model {
	
	public Tree(double fieldPosX, double fieldPosY) {
		super(fieldPosX, fieldPosY);
		setPath("./files/tree/Tree low.obj");
		this.scale = 0.07;
	}
}
