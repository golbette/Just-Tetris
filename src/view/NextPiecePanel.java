/*
 * TCSS 305
 * Assignment 6 - Tetris
 */
package view;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.Observable;
import java.util.Observer;
import model.MovableTetrisPiece;
import resources.TetrisImages;

/**
 * The window pane that shows the next piece.
 * @author sander68
 * @version 1.0
 */

public class NextPiecePanel extends AbstractDrawPanel implements Observer {

    /**
     * 
     */
    private static final long serialVersionUID = 168426035370641970L;
    
    /**
     * Creates a new NextPiecePanel, using the background and foreground image
     * constants.
     */
    public NextPiecePanel() {
        super(TetrisImages.BG_IMAGE_SMALL, TetrisImages.BLOCK_IMAGE);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void update(final Observable theObservable, final Object theData) {

        if (theData instanceof MovableTetrisPiece) {
            this.clearShapes();
            final MovableTetrisPiece next = (MovableTetrisPiece) theData;
            final String pieceString = next.toString();

            for (int i = 0; i < pieceString.length(); i++) {
                if (pieceString.charAt(i) != ' ' && pieceString.charAt(i) != '\n') {
                    this.addShape(new Rectangle((i % BoardPanel.BORDER_OFFSET) 
                                                * BoardPanel.BLOCK_DIM 
                                                + (BoardPanel.BLOCK_DIM),  
                                                +(i / BoardPanel.BORDER_OFFSET) 
                                                * BoardPanel.BLOCK_DIM
                                                + BoardPanel.BLOCK_DIM, 
                                                BoardPanel.BLOCK_DIM, BoardPanel.BLOCK_DIM));
                }
            }

        }
        this.addString("Next Piece", new Point(BoardPanel.BLOCK_DIM 
                                               + BoardPanel.BLOCK_DIM / 2, 
                                               this.getHeight() 
                                               - BoardPanel.BLOCK_DIM / 2));
        this.repaint();
    }

}
