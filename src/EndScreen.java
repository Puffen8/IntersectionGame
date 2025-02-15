
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class is used to represent the end screen of the game.
 * The class implements the Screen interface and builds a
 * JLayeredPane that the class showing the EndScreen can obtain
 * through the EndScreens getter.
 */
public class EndScreen implements Screen
{
    private JLayeredPane layeredPane;
    private JButton playButton;
    private JButton exitButton;
    private JFrame frame;
    /**
     * The width of the game name text.
     */
    public static final int GAME_NAME_WIDTH = 800;
    /**
     * The height of the game name text.
     */
    public static final int GAME_NAME_HEIGHT = 100;
    /**
     * The font size of the game name text.
     */
    public static final int GAME_NAME_FONT_SIZE = 100;
    /**
     * The width of the score text.
     */
    public static final int SCORE_TEXT_WIDTH = 800;
    /**
     * The height of the score text.
     */
    public static final int SCORE_TEXT_HEIGHT = 100;
    /**
     * The font size of the score text.
     */
    public static final int SCORE_TEXT_FONT_SIZE = 70;
    /**
     * The width of the play button text.
     */
    public static final int PLAY_BUTTON_WIDTH = 400;
    /**
     * The height of the play button text.
     */
    public static final int PLAY_BUTTON_HEIGHT = 100;
    /**
     * The font size of the play button text.
     */
    public static final int PLAY_BUTTON_FONT_SIZE = 70;
    /**
     * The width of the exit button text.
     */
    public static final int EXIT_BUTTON_WIDTH = 400;
    /**
     * The height of the exit button text.
     */
    public static final int EXIT_BUTTON_HEIGHT = 100;
    /**
     * The font size of the exit button text.
     */
    public static final int EXIT_BUTTON_FONT_SIZE = 70;

    /**
     * The constructor which creates the EndScreen by
     * creating all of the necessary objects, setting their
     * sizes and their positions, and adding everything to
     * the JLayeredPane that can be obtained by the getter.
     * @param viewer The Viewer that controls the graphics
     *               of the game, including the JFrame of
     *               the game.
     */
    public EndScreen(Viewer viewer) {
	this.frame = viewer.getFrame();
	this.layeredPane = new JLayeredPane();

	JLabel gameName = new JLabel("Intersection", JLabel.CENTER);
	gameName.setFont(new Font("Default", Font.BOLD, GAME_NAME_FONT_SIZE));
	gameName.setBounds(frame.getWidth() / 2 - GAME_NAME_WIDTH / 2,
			   OBJECT_MARGIN,
			   GAME_NAME_WIDTH,
			   GAME_NAME_HEIGHT);
	layeredPane.add(gameName);

	JLabel scoreText = new JLabel("Your score: " + viewer.getBoard().getScore(), JLabel.CENTER);
	scoreText.setFont(new Font("Default", Font.PLAIN, SCORE_TEXT_FONT_SIZE));
	scoreText.setBounds(frame.getWidth() / 2 - SCORE_TEXT_WIDTH / 2,
			    gameName.getY() + gameName.getHeight() + OBJECT_MARGIN,
			    SCORE_TEXT_WIDTH,
			    SCORE_TEXT_HEIGHT);
	layeredPane.add(scoreText);

	this.playButton = new JButton("Play again");
	playButton.setFont(new Font("Default", Font.PLAIN, PLAY_BUTTON_FONT_SIZE));
	playButton.setBounds(
		frame.getWidth() / 2 - PLAY_BUTTON_WIDTH / 2,
		scoreText.getY() + scoreText.getHeight() + OBJECT_MARGIN,
		PLAY_BUTTON_WIDTH,
		PLAY_BUTTON_HEIGHT);
	layeredPane.add(playButton);

	this.exitButton = new JButton("Exit");
	exitButton.setFont(new Font("Default", Font.PLAIN, EXIT_BUTTON_FONT_SIZE));
	exitButton.setBounds(
		frame.getWidth() / 2 - EXIT_BUTTON_WIDTH / 2,
		playButton.getY() + playButton.getHeight() + OBJECT_MARGIN,
		EXIT_BUTTON_WIDTH,
		EXIT_BUTTON_HEIGHT);
	layeredPane.add(exitButton);

	playButton.addActionListener(new ActionListener()
	{
	    @Override public void actionPerformed(final ActionEvent e) {
		frame.remove(viewer.getActivePanel());
		viewer.initGameScreen();
		viewer.showFrame();
		viewer.startGameClock();
	    }
	});
	exitButton.addActionListener(new ActionListener()
	{
	    @Override public void actionPerformed(final ActionEvent e) {
		frame.dispose();
	    }
	});
    }

    @Override
    public JLayeredPane getLayeredPane() {
	return layeredPane;
    }
}
