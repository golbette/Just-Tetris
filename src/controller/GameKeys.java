/*
 * TCSS 305
 * Assignment 6 - Tetris
 */
package controller;


import java.awt.event.KeyEvent;

/**
 * An enumeration type for video game key controls.
 * @author sander68
 * @version 1.0
 */
public enum GameKeys {

    /**
     * Left movement key.
     */
    LEFT_KEY(new int[]{KeyEvent.VK_LEFT, KeyEvent.VK_A}), 
    
    /**
     * Up movement key.
     */
    UP_KEY(new int[]{KeyEvent.VK_UP, KeyEvent.VK_W}),

    /**
     * Right movement key.
     */
    RIGHT_KEY(new int[]{KeyEvent.VK_RIGHT, KeyEvent.VK_D}), 

    /**
     * Action key.
     */
    ACTION_KEY(new int[]{KeyEvent.VK_SPACE}), 
    
    /**
     * Down key.
     */
    
    DOWN_KEY(new int[]{KeyEvent.VK_S, KeyEvent.VK_DOWN}),
    
    /**
     * Pause key.
     */
    PAUSE_KEY(new int[]{KeyEvent.VK_TAB, KeyEvent.VK_P});

    /**
     * The keys corresponding to this key press enumeration.
     */
    private final int[] myKeys;

    /**
     * Creates a key GameKey enumeration.
     * @param theCodes The KeyCode values associated with this enum.
     */
    GameKeys(final int[] theCodes) {
        myKeys = theCodes.clone();
    }
    
    /**
     * Returns whether or not the given integer key code is represented by this enumeration.
     * @param theKeyCode The key code to check.
     * @return Whether or not the given integer key code is represented by this enumeration.
     */
    public boolean isKey(final int theKeyCode) {
        boolean result = false;
        for (int i = 0; i < myKeys.length; i++) {
            if (theKeyCode == myKeys[i]) {
                result = true;
            }
        }
        return result;
    }

}
