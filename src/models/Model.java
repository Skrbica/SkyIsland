package models;

import java.util.ArrayList;
import java.util.List;

import com.interactivemesh.jfx.importer.obj.ObjModelImporter;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;

public abstract class Model {
	//private Group modelNode;
	private double xmin, xmax, zmin, zmax;
	private String path;
	protected double scale;
	
	//multiple objects of same type
	//idea is not to load model twice
	protected List<Node> objects = new ArrayList<>();
	
	public abstract void calculateBase();
	
	public abstract void configureModel();
	
	public void translateBase(double x, double y, double z) {
		xmin += x;
		xmax += x;
		zmin += z;
		zmax += z;
	}

	/*public Group getModelNode() {
		return this.modelNode;
	}*/
	
	public void setPath(String path) {
		this.path = path;
	}

	public double getXmin() {
		return this.xmin;
	}

	public void setXmin(double xmin) {
		this.xmin = xmin;
	}

	public double getXmax() {
		return this.xmax;
	}

	public void setXmax(double xmax) {
		this.xmax = xmax;
	}

	public double getZmin() {
		return this.zmin;
	}

	public void setZmin(double zmin) {
		this.zmin = zmin;
	}

	public double getZmax() {
		return this.zmax;
	}

	public void setZmax(double zmax) {
		this.zmax = zmax;
	}
	
	public List<Node> getObjects(){
		return objects;
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
		
		newNode.getTransforms().addAll(new Translate(tp.getX(), tp.getY(), tp.getZ()),
				new Scale(tp.getScale(), tp.getScale(), tp.getScale()),
				new Rotate(90 * tp.getRot()));
		
		objects.add(newNode);
	}
}
