package models;

public class TransformationParameters {
	private double x, y, z, scale;
	private int rot;
	
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getZ() {
		return z;
	}

	public void setZ(double z) {
		this.z = z;
	}

	public double getScale() {
		return scale;
	}

	public void setScale(double scale) {
		this.scale = scale;
	}

	public int getRot() {
		return rot;
	}

	public void setRot(int rot) {
		this.rot = rot;
	}

	public TransformationParameters(double x, double y, double z, double scale, int rot) {
		super();
		this.x = x;
		this.y = y;
		this.z = z;
		this.scale = scale;
		this.rot = rot;
	}
}
