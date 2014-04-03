package gui_sudoku;

import java.util.ArrayList;
import java.util.Collection;

class Sudoku implements GridProvider{

	private ArrayList<Cell> grid = new ArrayList<Cell>();
	
	/**
	 * Construct a Grid from a String describing a sudoku-problem.
	 * @param problemDefinition The initial layout of the sudoku board.
	 */
	public Sudoku(String problemDefinition) {
		
		for(int i = 0; i < problemDefinition.length(); i++){
			Cell cell = new Cell();
			
			char cellDefinition = problemDefinition.charAt(i);
			if(cellDefinition != '.'){
				cell.readOnly = true;
				cell.value = cellDefinition - '0';
			}
			
			grid.add(cell);
		}
		
		fireGridChanged();
	}
	
	public Cell getCell(int x, int y){
		return grid.get(x + y*9);
	}

	public boolean isMoveLegal(int col, int row, int val){
		return !this.getCell(col, row).readOnly;
	}

	public boolean playerMove(int col, int row, int val) {
		
		if(this.getCell(col, row).readOnly)
			return false;
		
		this.getCell(col, row).value = val;
		updateConflicts();
		fireGridChanged();
		
		return true;
	}


	private void updateConflicts() {
		
		resetConflicts();
		updateComumnConflics();
		updateRowConflicts();
		updateRegionConflics();
	}

	private void calculateConflicts(ArrayList<Cell> cells){
		
		UniquenessChecker counts = new UniquenessChecker();
		
		for(int i = 0; i < cells.size(); i++)
			counts.bump(cells.get(i).value);
		
		ArrayList<Integer> conflicts = counts.getConflicts();
		for(int i = 0; i < cells.size(); i++){
			int value = cells.get(i).value;
			if(conflicts.contains(value))
				cells.get(i).isInConflict = true;
		}
	}

	private void updateRegionConflics() {
		
		for(int region = 0; region < 9; region++){
			
			ArrayList<Cell> cells = new ArrayList<Cell>();
			
			for(int i = 0; i < 9; i++){
				int col = (region % 3) * 3 + (i % 3);
				int row = (region / 3) * 3 + (i / 3);
				
				cells.add(getCell(col, row));
			}
			
			calculateConflicts(cells);
		}
	}


	private void updateRowConflicts() {
		
		for(int row = 0; row < 9; row++){
			
			ArrayList<Cell> cells = new ArrayList<Cell>();

			for(int col = 0; col < 9; col++){
				cells.add(getCell(col, row));
			}
			
			calculateConflicts(cells);
		}
	}


	private void updateComumnConflics() {
		
		for(int col = 0; col < 9; col++){
			
			ArrayList<Cell> cells = new ArrayList<Cell>();

			for(int row = 0; row < 9; row++){
				cells.add(getCell(col, row));
			}
			
			calculateConflicts(cells);
		}
	}
	
	private void resetConflicts() {
		
		for(int i = 0; i < grid.size(); i++)
			grid.get(i).isInConflict = false;
	}
	

	@Override
	public int getGridWidth() {
		// Sudoku-boards are 9x9
		return 9;
	}

	@Override
	public int getGridHeight() {
		// Sudoku-boards are 9x9
		return 9;
	}

	@Override
	public Object getGridElement(int x, int y) {
		return getCell(x, y);
	}


	private Collection<GridListener> gridListeners = new ArrayList<GridListener>();
	
	@Override
	public void addGridListener(GridListener gridListener) {
		gridListeners.add(gridListener);
	}

	@Override
	public void removeGridListener(GridListener gridListener) {
		gridListeners.remove(gridListener);
	}
	
	private void fireGridChanged() {
		for (GridListener gridListener : gridListeners) {
			gridListener.gridChanged(this, 0, 0, getGridWidth(), getGridHeight());
		}
	}
}
