/*
 * TCSS 305
 * Assignment 6 - Tetris
 */
package resources;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Helper class containing images for Tetris.
 * @author sander68
 * @version 1.0
 */
public class TetrisImages {
    /**
     * The image used for individual tetris squares.
     */
    public static final Image BLOCK_IMAGE = 
                    new TetrisImages().loadImage("block2.png");
    
    /**
     * The image used for the board background.
     */
    public static final Image BG_IMAGE = 
                    new TetrisImages().loadImage("bg.png");
    
    /**
     * The image used for the "next piece" background.
     */
    public static final Image BG_IMAGE_SMALL = 
                    new TetrisImages().loadImage("bgSmall.png");
    
    /**
     * Helper method that attempts to load a buffered image.
     * @param thePath the image's file path.
     * @return The BufferedImage corresponding to this file path.
     */
    private Image loadImage(final String thePath) {
        Image result = null;
        try {
            result = ImageIO.read(getClass().getResource(thePath));
        } catch (final IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
