package models;

public class PlatformNet {
	private int dimension;
	private boolean[][] takenSpots;
	private int selectedX = dimension;
	private int selectedY = dimension;
	
	public PlatformNet(int dimension) {
		this.dimension = dimension;
		takenSpots = new boolean[2 * dimension][2 * dimension];
	}
	
	public void update(int scale) {
		int updatedDimension = dimension * scale;
		boolean[][] updatedFreeSpot = new boolean[updatedDimension][updatedDimension];
		for(int i = dimension; i < 2 * dimension; i++) {
			for(int j = dimension; j < 2 * dimension; j++) {
				if(takenSpots[i - dimension][j - dimension]) updatedFreeSpot[i][j] = true; 
			}
		}
		
		dimension = updatedDimension;
		takenSpots = updatedFreeSpot;
	}
	
	public boolean isFree(int i, int j) {
		return !takenSpots[i + dimension][j + dimension];
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
	
	public void occupy(int x, int y) {
		takenSpots[x + dimension][y + dimension] = true;
	}
}
