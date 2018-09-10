/*
 * TCSS 305
 * Assignment 6 - Tetris
 */
package view;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.Observable;
import java.util.Observer;

import model.Board;
import resources.TetrisImages;

/**
 * A panel containing the visual representation of a game of Tetris.
 * 
 * @author sander68
 * @version 1.0
 */

public class BoardPanel extends AbstractDrawPanel implements Observer {
    
    /**
     * The width and height of a single Tetris square, in pixels.
     */
    public static final int BLOCK_DIM = 20;
    
    /**
     * The width and height of a single Tetris square, in pixels.
     */
    public static final int BORDER_OFFSET = 5;
    
    /**
     * 
     */
    private static final long serialVersionUID = 402964245097451548L;
    
    /**
     * Create a new BoardPanel.
     */
    public BoardPanel() {
        super(TetrisImages.BG_IMAGE, TetrisImages.BLOCK_IMAGE);
        
    }

    @Override
    public void update(final Observable theBoard, final Object theInfo) {
        
        if (theInfo instanceof String) {
            final String currentBoard = (String) theInfo;
            //currentBoard = currentBoard.replaceAll("|", "");
            //currentBoard = currentBoard.replaceAll("-", "");
            final Board gameBoard = (Board) theBoard;
            //final int height = gameBoard.getHeight();
            final int width = gameBoard.getWidth() + 3;
            this.clearShapes();
            for (int i = BORDER_OFFSET * width; i < currentBoard.length(); i++) {
                if (currentBoard.charAt(i) != ' ' && currentBoard.charAt(i) != '|'
                                && currentBoard.charAt(i) != '-' 
                                && currentBoard.charAt(i) != '\n') {
                    this.addShape(new Rectangle((i % width) * BLOCK_DIM + BLOCK_DIM,  
                                                (i / width) * BLOCK_DIM - BLOCK_DIM 
                                                * BORDER_OFFSET, 
                                                BLOCK_DIM, BLOCK_DIM));
                }
            }
            this.repaint();
        }

    }
    
    /**
     * Hides the board, displaying the given message.
     * @param theMessage The message to display while paused.
     */
    public void hideBoard(final String theMessage) {
        this.setForeActive(false);
        this.addString(theMessage, new Point(this.getWidth() / 2, this.getHeight() / 2));
        this.repaint();
    }
    
    /**
     * Re-shows the board, removing any text messages set.
     */
    public void showBoard() {
        this.setForeActive(true);
        this.clearStrings();
        this.repaint();
    }

}
