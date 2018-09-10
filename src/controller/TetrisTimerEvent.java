/*
 * TCSS 305
 * Assignment 6 - Tetris
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Board;
/**
 * A Swing timer event handler for Tetris.
 * @author sander68
 * @version 1.0
 */
public class TetrisTimerEvent implements ActionListener {

    /**
     * The Board object that this Timer Event should talk to.
     */
    private final Board myBoard;
    
    /**
     * Create a new Tetris timer event.
     * @param theBoard The Tetris board object this timer should talk to.
     */
    public TetrisTimerEvent(final Board theBoard) {
        myBoard = theBoard;
    }

    @Override
    public void actionPerformed(final ActionEvent theEvent) {
        myBoard.down();
    }

}
