package models;

import javafx.scene.Group;
import javafx.scene.Node;

public class Balloon extends Model {
	private static final double heightBeg = 10;
	private static final double heightEnd = 20;
	private static final double flightTime = 10;
	private double startTime;
	
	public Balloon(double fieldPosX, double fieldPosY) {
		super(fieldPosX, fieldPosY);
		setPath("./files/Hot Air Balloon Iridesium/Air_Balloon.obj");
		this.scale = 0.2;
	}
	
	private double calculateHeight(double time) {
		double t = (time - startTime)/flightTime;
		if(t < 1) {
			return heightBeg + t * (heightEnd - heightBeg);
		} else {
			return 0;
		}
	}
	
	public void update(double time, Group root) {
		boolean flying = false;

		Node balloon = getObjects().get(0);
		if(time % 60.0 < 10.0) {
			if(!root.getChildren().contains(balloon)) {
				flying = true;
				root.getChildren().addAll(getObjects());
				setTime(time);
			} else {
				update(time);	
			}
		} else {
			if(flying) {
				root.getChildren().remove(balloon);
			}
			flying = false;
		}
	}
	
	public void update(double time) {
		double h = -calculateHeight(time);
		getBalloon().setTranslateY(h);
	}
	
	public void setTime(double time) {
		this.startTime = time;
	}
	
	public Node getBalloon() {
		return getObjects().get(0);
	}
}
