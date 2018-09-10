/*
 * TCSS 305
 * Assignment 6 - Tetris
 */
package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

/**
 * A Panel that draws a collection of shapes.
 * @author sander68
 * @version 1.0
 */
public abstract class AbstractDrawPanel extends JPanel {

    /**
     * 
     */
    private static final long serialVersionUID = -1400796701786039705L;

    /**
     * A list of all shapes for this panel to draw.
     */
    private final List<Shape> myShapes = new ArrayList<Shape>();

    /**
     * A list of all strings for this panel to draw.
     */
    private final List<String> myDrawStrings = new ArrayList<String>();

    /**
     * A list of all points corresponding to the strings for this panel to draw.
     */
    private final List<Point> myStringPoints = new ArrayList<Point>();

    /**
     * The background image for this draw panel.
     */
    private final Image myBgImg;

    /**
     * The foreground image for this draw panel to draw.
     */
    private final Image myForeImg;

    /**
     * Whether or not this DrawPanel should draw its foreground.
     */
    private boolean myActiveForegroundState;

    /**
     * Create a new Abstract draw panel, initializing its foreground and background.
     * @param theBgImage The background image to draw with.
     * @param theForeImage The foreground image to draw with.
     */
    public AbstractDrawPanel(final Image theBgImage, final Image theForeImage) {
        super();
        myBgImg = theBgImage;
        myForeImg = theForeImage;
        myActiveForegroundState = true;
    }

    /**
     * Paint all shapes contained in this Draw Panel.
     * @param theGraphics The Graphics object this panel paints with.
     */
    @Override
    public void paint(final Graphics theGraphics) {
        final Graphics2D g = (Graphics2D) theGraphics;
        g.setColor(this.getBackground());
        g.clearRect(0, 0, this.getWidth(), this.getHeight());
        g.fill(new Rectangle(0, 0, 
                             this.getWidth(), this.getHeight()));
        g.drawImage(myBgImg, BoardPanel.BLOCK_DIM, BoardPanel.BLOCK_DIM, 
                    this.getWidth(), this.getHeight() - BoardPanel.BLOCK_DIM * 2, null);
        //g.drawImage(bgImg, null, 0, 0);
        //g.setColor(Color.BLACK);
        //g.drawImage(bgImg, new Rectangle(BoardPanel.BLOCK_DIM, BoardPanel.BLOCK_DIM, 
        //                     this.getWidth(), this.getHeight() - BoardPanel.BLOCK_DIM * 2));

        if (myActiveForegroundState) {
            for (int i = 0; i < myShapes.size(); i++) {
                g.setColor(Color.GRAY);
                g.drawImage(myForeImg, (int) myShapes.get(i).getBounds2D().getX(),
                            (int) myShapes.get(i).getBounds2D().getY(), 
                            (int) myShapes.get(i).getBounds2D().getWidth(), 
                            (int) myShapes.get(i).getBounds2D().getHeight(), null);
            }
        }

        g.setColor(Color.BLACK);
        for (int i = 0; i < myDrawStrings.size(); i++) {
            g.drawString(myDrawStrings.get(i), (int) myStringPoints.get(i).getX(),
                         (int) myStringPoints.get(i).getY());
        }
    }

    /**
     * Add a shape to the list of shapes in this DrawPanel.
     * @param theShape The shape to add.
     */
    protected void addShape(final Shape theShape) {
        myShapes.add(theShape);
    }

    /**
     * Add a string and its corresponding point
     *  to the list of strings in this DrawPanel.
     * @param  theString The string to be drawn.
     * @param thePosition The position where the string should be drawn.
     */
    protected void addString(final String theString, final Point thePosition) {
        myDrawStrings.add(theString);
        myStringPoints.add(thePosition);
    }

    /**
     * Clears all objects in this DrawPanel.
     */
    protected void clearShapes() {
        myShapes.clear();
        myDrawStrings.clear();
        myStringPoints.clear();
    }
    
    /**
     * Removes all strings from this draw panel.
     */
    protected void clearStrings() {
        myDrawStrings.clear();
    }
    
    /**
     * Sets whether or not foreground drawing should be active.
     * @param theActiveState True if the foreground should be drawn, false if not.
     */
    protected void setForeActive(final boolean theActiveState) {
        this.myActiveForegroundState = theActiveState;
    }

}
