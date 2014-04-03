package gui_sudoku;

public class SudokuProgram implements GridGame {
	
	private Sudoku game;
	UndoSystem undoSystem;
	private GameOutput output;

	@Override
	public void init(String level) {
		game = new Sudoku(level);
		undoSystem = new UndoSystem(game);
	}

	@Override
	public void run(GameOutput output) {
		this.output = output;
	}

	@Override
	public Integer doCommand(String command) {
		boolean undoRedo = false;
		
		if(command.equals("undo")){
			command = undoSystem.undo();
			undoRedo = true;
		}
		
		if(command.equals("redo")){
			command = undoSystem.redo();
			undoRedo = true;
		}
		
		int row = command.charAt(0) - '1';
		int col = command.charAt(1) - 'a';
		int val = command.charAt(2) - '0';
		
		boolean isLegal = game.isMoveLegal(col, row, val);
		if(isLegal){
			if(!undoRedo)
				undoSystem.doMove(command);
			game.playerMove(col, row, val);
		}
		
		if(!isLegal)
			output.warning("Illegal move. This cell if part of the challege.");
		return null;
	}

	@Override
	public GridProvider getGridProvider() {
		return game;
	}

	@Override
	public String getTextFor(Object o) {
		return o.toString();
	}

	@Override
	public String getImageFor(Object o) {
		return null;
	}

	@Override
	public Integer gridElementInput(int x, int y) {
		int before = game.getCell(x, y).value;
		int after = (before + 1) % 10;
		
		char row = (char) (y + '1');
		char col = (char) (x + 'a');
		doCommand("" + row + col + after);
		
		return null;
	}

	@Override
	public Integer directionInput(int dx, int dy) {
		return null;
	}

	public static void main(String[] args) throws Exception {
		GridGameGUI.main(new String[]{SudokuProgram.class.getName()});
	}
}
