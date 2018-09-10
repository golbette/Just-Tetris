/*
 * TCSS 305
 * Assignment 6 - Tetris
 */
package view;
import controller.GameKeys;
import controller.TetrisKeyHandler;
import controller.TetrisTimerEvent;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.Timer;

import model.Board;


/**
 * A JFrame with a timer to contain a game interface.
 * @author sander68
 * @version 1.0
 */


public class TetrisFrame extends JFrame implements Observer {
    /**
     * 
     */
    private static final long serialVersionUID = -1471806197094678788L;

    /**
     * Minimum size factor for the board panel.
     */
    private static final int MIN_FACTOR = 6;

    /**
     * Maximum size factor for the board panel.
     */

    private static final int MAX_FACTOR = 9;
    /**
     * The game board used by the game.
     */
    private Board myBoard = new Board();

    /**
     * The game timer.
     */
    private Timer myGameTimer;

    /**
     * The key handler for this game of tetris.
     */
    private TetrisKeyHandler myKeyHandler;

    /**
     * The Information panel, which stores this game's scoring and level
     * information.
     */
    private InfoPanel myGameInfo = new InfoPanel();

    /**
     * The "New Game" menu item.
     */
    private JMenuItem myNewGameOption;
    
    /**
     * The panel responsible for drawing the board.
     */
    private BoardPanel myBoardPanel;
    
    /**
     * Whether or not a game is currently being played.
     */
    private boolean myGameRunning;

    /**
     * Creates and sets up a new Tetris game window.
     * @author sander68
     */
    public TetrisFrame() {
        super();
        myGameRunning = false;
        myKeyHandler = new TetrisKeyHandler(myBoard);
        myKeyHandler.setMovable(false);
        myGameTimer = new Timer(TetrisConstants.BASE_TIMER_INTERVAL, 
                               new TetrisTimerEvent(myBoard));
        makeFrame();
        this.addKeyListener(new PauseKeyListener());
    }

    /**
     * Creates a new Tetris game window.
     */
    private void makeFrame() {
        this.getContentPane().removeAll();
      
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("TCSS 305 - Tetris");
        this.setJMenuBar(new JMenuBar());
        
        myNewGameOption = new JMenuItem("New game...");
        
        final JMenuItem[] gameMenuItems = {myNewGameOption, new JMenuItem("End game")};
        final ActionListener[] gameMenuActions 
        = {new StartGameListener(), new StopGameListener()};
        this.addMenu(new JMenu("Game"), gameMenuItems, gameMenuActions);
        
        
        final JMenuItem[] helpMenuItems = {new JMenuItem("Controls...")};
        final ActionListener[] helpMenuActions 
        = {new HelpMenuListener()};
        this.addMenu(new JMenu("Help"), helpMenuItems, helpMenuActions);
        
        myBoardPanel = new BoardPanel();
        final NextPiecePanel next = new NextPiecePanel();
        myGameInfo = new InfoPanel();
        
        this.add(myBoardPanel, BorderLayout.WEST);
        this.add(myGameInfo, BorderLayout.EAST);
        myGameInfo.add(next);
        myGameInfo.createLabels();
        
        myBoardPanel.setPreferredSize(new Dimension(BoardPanel.BLOCK_DIM  
                                  * myBoard.getWidth() + BoardPanel.BLOCK_DIM, 
                                  BoardPanel.BLOCK_DIM * (myBoard.getHeight() + 1)));
        
        next.setPreferredSize(new Dimension(BoardPanel.BLOCK_DIM * MIN_FACTOR,
                                            BoardPanel.BLOCK_DIM * MIN_FACTOR));
        next.setMaximumSize(new Dimension(BoardPanel.BLOCK_DIM * MAX_FACTOR,
                                          BoardPanel.BLOCK_DIM * MIN_FACTOR));
        
        this.pack();
        this.setResizable(false);

        final GraphicsEnvironment currentEnvironment = 
                        GraphicsEnvironment.getLocalGraphicsEnvironment();
        this.setLocation(new Point((int) currentEnvironment.getCenterPoint().getX() 
                                   - this.getWidth() / 2,
                                   (int) currentEnvironment.getCenterPoint().getY() 
                                   - this.getHeight() / 2));

        myBoard.addObserver(this);
        myBoard.addObserver(myGameInfo);
        myBoard.addObserver(myBoardPanel);
        this.addKeyListener(myKeyHandler);
        myBoard.addObserver(next);

    }
    /**
     * Start a new game of Tetris with the given width and height.
     * @param theWidth The width of the game board.
     * @param theHeight The height of the game board.
     */
    private void startNewGame(final int theWidth, final int theHeight) {
        myBoard.deleteObservers();
        myBoard = new Board(theWidth, theHeight);
        myGameTimer = new Timer(TetrisConstants.BASE_TIMER_INTERVAL, 
                               new TetrisTimerEvent(myBoard));
        myKeyHandler = new TetrisKeyHandler(myBoard);
        this.makeFrame(); //remake the frame so it's the correct size for the game board
        myGameTimer.restart();
        myBoard.newGame();
        myKeyHandler.setMovable(true);
        myNewGameOption.setEnabled(false);
        myGameRunning = true;
    }
    
    /**
     * Add a new menu, specifying its menu items and listeners.
     * @param theMenu The menu to add.
     * @param theMenuItems The menu items to add in the menu.
     * @param theListeners The listeners which correspond to the menu items to add.
     */
    private void addMenu(final JMenu theMenu, final JMenuItem[] theMenuItems,
                         final ActionListener[] theListeners) {
        this.getJMenuBar().add(theMenu);
        for (int i = 0; i < theMenuItems.length; i++) {
            addMenuItem(theMenu, theMenuItems[i], theListeners[i]);
        }
    }

    /**
     * Adds a menu item to the specified menu and attaches a listener.
     * 
     * @param theMenu The menu to add the item to.
     * @param theMenuItem The menu item to add.
     * @param theListener The listener to attach to the menu item.
     */
    private void addMenuItem(final JMenu theMenu, final JMenuItem theMenuItem,
                             final ActionListener theListener) {
        theMenu.add(theMenuItem);
        theMenuItem.addActionListener(theListener);
    }

    /**
     * Performs window-level game update operations. This mainly checks for 'game over'
     * states.
     */
    @Override
    public void update(final Observable theObservable, final Object theInfo) {
        if (theInfo instanceof Boolean) {
            JOptionPane.showMessageDialog(this, "Game Over!");
            myNewGameOption.setEnabled(true);
            myGameRunning = false;
        }
        if (theInfo.getClass().isArray() && myGameInfo.getLevel() 
                        <= TetrisConstants.MAX_LEVEL) {
            myGameTimer.setDelay(TetrisConstants.BASE_TIMER_INTERVAL 
                                - (myGameInfo.getLevel() - 1) 
                                * TetrisConstants.LEVEL_SPEED_MOD);
        }        
    }

    /**
     * A menu listener that displays the controls for the game.
     * @author sander68
     * @version 1.0
     */
    private class HelpMenuListener implements ActionListener {

        @Override
        public void actionPerformed(final ActionEvent theAction) {
            JOptionPane.showMessageDialog(null,
                                          "TCSS 305 W2017 - Tetris by Sam Anderson \n"
                                           + "(Tetris backend model by TCSS 305 Instructors)\n"
                                           + "Controls: \n" 
                                           + "A/D or Left/Right Arrows: Move Piece \n"
                                           + "W or Up Arrow: Rotate Piece\n" 
                                           + "S or Down Arrow: Move Piece down\n" 
                                           + "Space Bar: Drop Piece\n"
                                           + "P or Tab: Pause/Unpause Game");

        }

    }

    /**
     * A menu listener that stops the game when triggered.
     * @author sander68
     * @version 1.0
     */
    private class StopGameListener implements ActionListener {

        @Override
        public void actionPerformed(final ActionEvent theAction) {
            myGameTimer.stop();
            myKeyHandler.setMovable(false);
            myNewGameOption.setEnabled(true);
            myGameRunning = false;
        }

    }

    /**
     * A menu listener that restarts the game when triggered.
     * @author sander68
     * @version 1.0
     */
    private class StartGameListener implements ActionListener {

        @Override
        public void actionPerformed(final ActionEvent theAction) {
            final JPanel newGamePanel = new JPanel();
            final ButtonGroup sizeOptions = new ButtonGroup();
            final JRadioButton btnSmall = new JRadioButton("Small (5 x 15)");
            final JRadioButton btnMed = new JRadioButton("Standard (10 x 20)");
            final JRadioButton btnLarge = new JRadioButton("Large (20 x 30)");
            newGamePanel.add(btnSmall);
            newGamePanel.add(btnMed);
            newGamePanel.add(btnLarge);
            sizeOptions.add(btnSmall);
            sizeOptions.add(btnMed);
            sizeOptions.add(btnLarge);
            btnMed.setSelected(true);
            final String[] options = {"Start Game", "Cancel"};
            if (JOptionPane.showOptionDialog(null, newGamePanel, "Game Options", 
                                         JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, 
                                         null, options, null) == JOptionPane.YES_OPTION) {
                if (btnSmall.isSelected()) {
                    startNewGame(TetrisConstants.SMALL_X, TetrisConstants.SMALL_Y);
                }
                if (btnMed.isSelected()) {
                    startNewGame(TetrisConstants.MED_X, TetrisConstants.MED_Y);
                }
                if (btnLarge.isSelected()) {
                    startNewGame(TetrisConstants.LARGE_X, TetrisConstants.LARGE_Y);
                }
            }
        }

    }

    /**
     * Key listener for "Pause game" input.
     * @author sander68
     * @version 1.0
     */
    private class PauseKeyListener implements KeyListener {
        
        @Override
        public void keyReleased(final KeyEvent theKey) {
            if (GameKeys.PAUSE_KEY.isKey(theKey.getKeyCode())) {
                if (myGameTimer.isRunning() && myGameRunning) {
                    myGameTimer.stop();
                    myBoardPanel.hideBoard("PAUSE");
                    myKeyHandler.setMovable(false);
                } else if (myGameRunning) {
                    myGameTimer.start();
                    myBoardPanel.showBoard();
                    myKeyHandler.setMovable(true);
                }
            }
        }

        @Override
        public void keyPressed(final KeyEvent theKey) {
        }

        @Override
        public void keyTyped(final KeyEvent theKey) {
        }
    }

}
