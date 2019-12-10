package models;

public class PlatformNet {
	private int dimension;
	private boolean[][] freeSpot;
	private int selectedX, selectedY;
	
	public PlatformNet(int dimension) {
		this.dimension = dimension;
		freeSpot = new boolean[dimension][dimension];
	}
	
	public void update(int scale) {
		int updatedDimension = dimension * scale;
		boolean[][] updatedFreeSpot = new boolean[updatedDimension][updatedDimension];
		for(int i = dimension; i < 2 * dimension; i++) {
			for(int j = dimension; j < 2 * dimension; j++) {
				if(freeSpot[i - dimension][j - dimension]) updatedFreeSpot[i][j] = true; 
			}
		}
		
		dimension = updatedDimension;
		freeSpot = updatedFreeSpot;
	}
	
	public boolean isFree(int i, int j) {
		return freeSpot[i][j];
	}
	
	public void deselect() {
		this.selectedX = dimension;
		this.selectedY = dimension;
	}
	
	public void select(int x, int y) {
		this.selectedX = x;
		this.selectedY = y;
	}

	public int getDimension() {
		return dimension;
	}

	public int getSelectedX() {
		return selectedX;
	}

	public int getSelectedY() {
		return selectedY;
	}
	
	public boolean isSelected() {
		return selectedX != dimension;
	}
}
