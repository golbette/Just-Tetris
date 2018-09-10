/*
 * TCSS 305
 * Assignment 6 - Tetris
 */
package view;



/**
 * Utility class containing constants for the Tetris GUI.
 * @author sander68
 * @version 1.0
 */
public final class TetrisConstants {
    
    /**
     * Line clear interval between levels.
     */
    public static final int LEVEL_INTERVAL = 5;
    
    /**
     * 'Small' board X Value.
     */
    public static final int SMALL_X = 5;
    
    /**
     * 'Small' board Y Value.
     */
    public static final int SMALL_Y = 15;
    
    /**
     * 'Standard' board X Value.
     */
    public static final int MED_X = 10;
    
    /**
     * 'Standard' board X Value.
     */
    public static final int MED_Y = 20;
    
    /**
     * 'Large' board X Value.
     */
    public static final int LARGE_X = 20;
    
    /**
     * 'Large' board X Value.
     */
    public static final int LARGE_Y = 30;
    
    /**
     * Base score added per line clear.
     */
    public static final int SCORE_BASE = 100;
    
    /**
     * The starting board timer, in milliseconds. 
     */
    public static final int BASE_TIMER_INTERVAL = 1000;
    
    /**
     * The amount the timer decreases per game level, in milliseconds.
     */
    public static final int LEVEL_SPEED_MOD = 50;
    
    /**
     * The game level at which speed stops increasing.
     */
    public static final int MAX_LEVEL = 20;
    
    
    
    /**
     * Private constructor to prevent instantiation.
     */
    private TetrisConstants() {
        
    }
    
    
}
