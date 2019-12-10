package models;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;

public class Balloon extends Model {
	private static final double heightBeg = 10;
	private static final double heightEnd = 20;
	private static final double flightTime = 10;
	private double startTime;
	private Node balloon;
	private Group root;
	
	public Balloon(double fieldPosX, double fieldPosY, Group root) {
		super(fieldPosX, fieldPosY, root);
		this.root = root;
		setPath("./files/Hot Air Balloon Iridesium/Air_Balloon.obj");
		this.scale = 0.2;
		balloon = loadModel();
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
		if(!root.getChildren().contains(balloon)) {
			root.getChildren().addAll(getBalloon());
			setTime(time);
		} else {
			update(time);	
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
		return balloon;
	}
	
	public void remove() {
		root.getChildren().remove(balloon);
	}
	
	@Override
	public void addNode(TransformationParameters tp) {
		balloon.getTransforms().clear();
		balloon.getTransforms().addAll(new Translate(tp.getX(), tp.getY() + translateY, tp.getZ()),
				new Scale(tp.getScale(), tp.getScale(), tp.getScale()),
				new Rotate(90 * tp.getRot()));
		root.getChildren().add(balloon);
	}
}
