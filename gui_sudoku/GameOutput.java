package gui_sudoku;
public interface GameOutput {
     
    /**
     * Outputs a message to user, e.g. a status update.
     * @param message the message
     */
    public void info(String message);
    /**
     * Outputs a message to user, with a style indicating it is a warning.
     * @param message the warning message
     */
    public void warning(String message);
    /**
     * Outputs a message to user, with a style indicating it is an error.
     * @param message the error message
     */
    public void error(String message);
}