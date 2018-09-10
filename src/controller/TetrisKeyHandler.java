/*
 * TCSS 305
 * Assignment 6 - Tetris
 */
package controller;


import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import model.Board;
/**
 * A Key Adapter for the game Tetris.
 * @author sander68
 * @version 1.0
 */
public class TetrisKeyHandler extends KeyAdapter implements KeyListener {

    /**
     * The current game board.
     */
    private final Board myBoard;
    
    /**
     * Whether or not pieces are allowed to be moved.
     */
    private boolean myMoveState = true;
    
    /**
     * Creates a new TetrisKeyHandler object.
     * @param theBoard The Board object this key handler should talk to.
     */
    public TetrisKeyHandler(final Board theBoard) {
        super();
        myBoard = theBoard;
    }
    
    @Override
    public void keyPressed(final KeyEvent theEvent) {
        final int key = theEvent.getKeyCode();

        if (myMoveState) {
            if (GameKeys.LEFT_KEY.isKey(key)) {
                myBoard.left();
            }

            if (GameKeys.RIGHT_KEY.isKey(key)) {
                myBoard.right();
            }
            
            if (GameKeys.UP_KEY.isKey(key)) {
                myBoard.rotate();
            }

            if (GameKeys.ACTION_KEY.isKey(key)) {
                myBoard.drop();
            }
            
            if (GameKeys.DOWN_KEY.isKey(key)) {
                myBoard.down();
            }
        }
        
       // if (GameKeys.PAUSE_KEY.isKey(key)) {
        //    myMoveState = !myMoveState;
       // }
        
    }
    
    /**
     * Sets whether or not pieces should be movable by this controller.
     * @param theMoveState Whether or not pieces should be movable by this controller.
     */
    public void setMovable(final boolean theMoveState) {
        myMoveState = theMoveState;
    }
}
