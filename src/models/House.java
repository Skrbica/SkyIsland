package models;

public class House extends Model{
	
	public House(double fieldPosX, double fieldPosY) {
		super(fieldPosX, fieldPosY);
		//setPath("./files/Japanese_Temple_Model/Model/Japanese_Temple.obj");
		setPath("./files/Japanese_Temple_Model/Model/Japanese_Temple.obj");
		this.scale = 0.3;
		this.translateY = 0;
	}

}
