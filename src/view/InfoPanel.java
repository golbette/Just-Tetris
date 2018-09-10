/*
 * TCSS 305
 * Assignment 6 - Tetris
 */
package view;
import java.lang.reflect.Array;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;



/**
 * A JPanel to display game info, such as score, time, level, etc. 
 * @author sander68
 * @version 1.0
 */
public class InfoPanel extends JPanel implements Observer {

    /**
     * 
     */
    private static final long serialVersionUID = 4691417946172572337L;

    /**
     * Number of lines cleared.
     */
    private int myLines;

    /**
     * Current player score.
     */
    private int myScore;

    /**
     * Current game level.
     */
    private int myLevel;

    /**
     * Number of lines to the next game level.
     */
    private int myLinesToNextLevel;

    /**
     * The line count label.
     */
    private JLabel myLineLabel;

    /**
     * The level count label.
     */
    private JLabel myLevelLabel;

    /**
     * The lines to next level label.
     */
    private JLabel myNextLevelLabel;

    /**
     * The score count label.
     */
    private JLabel myScoreLabel;

    /**
     * Creates and sets up a new game info panel.
     */
    public InfoPanel() {
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        myLines = 0;
        myScore = 0;
        myLevel = 1;
    }

    /**
     * Sets up the standard text labels for this info panel.
     */
    public void createLabels() {
        myLinesToNextLevel = TetrisConstants.LEVEL_INTERVAL;
        myLineLabel = new JLabel(Integer.toString(myLines));
        myLevelLabel = new JLabel(Integer.toString(myLevel));
        myNextLevelLabel = new JLabel(Integer.toString(myLinesToNextLevel));
        myScoreLabel = new JLabel(Integer.toString(myScore));
        
        add(new JLabel("Lines:"));
        add(myLineLabel);
        add(new JLabel("Level:"));
        add(myLevelLabel);
        add(new JLabel("To Next:"));
        add(myNextLevelLabel);
        add(new JLabel("Score:"));
        add(myScoreLabel);
        add(new JLabel("Scoring:"));
        add(new JLabel(""));
        add(new JLabel("100 per row"));
        add(new JLabel("x rows cleared"));
    }

    /**
     * Listens for updates to the game state to change relevant
     * statistics.
     */
    @Override
    public void update(final Observable theObservable, final Object theInfo) {
        if (theInfo.getClass().isArray()) {
            myLines += Array.getLength(theInfo);
            this.myScore += TetrisConstants.SCORE_BASE 
                            * Array.getLength(theInfo) * Array.getLength(theInfo);
            myLinesToNextLevel -= Array.getLength(theInfo);
            if (myLinesToNextLevel <= 0) {
                this.myLevel += 1;
                myLinesToNextLevel = TetrisConstants.LEVEL_INTERVAL;
            }
            updateLabels();
        }
    }
    
    /**
     * Updates all labels to match their corresponding values.
     */
    private void updateLabels() {
        myLineLabel.setText(Integer.toString(myLines));
        myLevelLabel.setText(Integer.toString(myLevel));
        myNextLevelLabel.setText(Integer.toString(myLinesToNextLevel));
        myScoreLabel.setText(Integer.toString(myScore));
    }

    /**
     * Returns the current game speed level.
     * @return The game speed level.
     */
    public int getLevel() {
        return this.myLevel;
    }

}
