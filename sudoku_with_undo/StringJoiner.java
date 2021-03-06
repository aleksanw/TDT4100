package sudoku_with_undo;

class StringJoiner {
	private StringBuilder sb = new StringBuilder();
	private String separator = " ";
	
	public void append(Object str){
		
		if(sb.length() != 0)
			sb.append(separator);
		
		sb.append(str);
	}
	
	@Override
	public String toString() {
		
		return sb.toString();
	}
}
