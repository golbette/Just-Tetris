/*
 * TCSS 305
 * Assignment 6 - Tetris
 */
package view;

/**
 * Driver class for a Tetris application.
 * @author sander68
 * @version 1.0
 */

public final class TetrisMain {

    /**
     * Private constructor to prevent instantiation.
     */
    private TetrisMain() {
        
    }
    
    /**
     * Sets up and starts a new Tetris application window.
     * @param theArgs The arguments given to this application. Unused.
     */
    public static void main(final String[] theArgs) {
        new TetrisFrame().setVisible(true);
    }

}
