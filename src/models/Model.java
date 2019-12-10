package models;

import java.util.ArrayList;
import java.util.List;

import com.interactivemesh.jfx.importer.obj.ObjModelImporter;

import javafx.scene.Group;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;

public abstract class Model {
	private String path;
	protected double scale;
	protected double translateY = 0;
	private double fieldPositionX, fieldPositionY;
	private Group root;
	
	public Model(double x, double y, Group root) {
		this.fieldPositionX = x;
		this.fieldPositionY = y;
		this.root = root;
	}
	
	public void setPath(String path) {
		this.path = path;
	}
	public Group loadModel() {
		ObjModelImporter importer = new ObjModelImporter();
		importer.read(path);
		Group objModel = new Group();

		objModel.getChildren().addAll(importer.getImport());
		return objModel;
	}
	
	public void addNode(double x, double y, double z, int rot) {
		addNode(new TransformationParameters(x, y, z, scale, rot));
	}
	
	public void addNode(TransformationParameters tp) {
		Group newNode = loadModel();
		newNode.getTransforms().addAll(new Translate(tp.getX(), tp.getY() + translateY, tp.getZ()),
				new Scale(tp.getScale(), tp.getScale(), tp.getScale()),
				new Rotate(90 * tp.getRot()));
		root.getChildren().add(newNode);
	}

	public double getFieldPositionX() {
		return fieldPositionX;
	}

	public void setFieldPositionX(double fieldPositionX) {
		this.fieldPositionX = fieldPositionX;
	}

	public double getFieldPositionY() {
		return fieldPositionY;
	}

	public void setFieldPositionY(double fieldPositionY) {
		this.fieldPositionY = fieldPositionY;
	}
}
