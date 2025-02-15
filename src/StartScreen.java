
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * This class is used to represent the start screen of the game.
 * The class implements the Screen interface and builds a
 * JLayeredPane that the class showing the GameScreen can obtain
 * through the StartScreen getter.
 */
public class StartScreen implements Screen
{
    private JLayeredPane layeredPane;
    private JButton playButton;
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
     * The width of the play button.
     */
    public static final int PLAY_BUTTON_WIDTH = 400;
    /**
     * The height of the play button.
     */
    public static final int PLAY_BUTTON_HEIGHT = 100;
    /**
     * The font size of the play button text.
     */
    public static final int PLAY_BUTTON_TEXT_FONT_SIZE = 70;

    /**
     * The constructor which creates the StartScreen by
     * creating all of the necessary objects, setting their
     * sizes and their positions, and adding everything to
     * the JLayeredPane that can be obtained by the getter.
     * @param viewer The Viewer that controls the graphics
     *               of the game, including the JFrame of
     *               the game.
     */
    public StartScreen(Viewer viewer) {
	this.frame = viewer.getFrame();
	this.layeredPane = new JLayeredPane();

	JLabel gameName = new JLabel("Intersection", JLabel.CENTER);
	gameName.setFont(new Font("Default", Font.BOLD, GAME_NAME_FONT_SIZE));
	gameName.setBounds(frame.getWidth() / 2 - GAME_NAME_WIDTH / 2, OBJECT_MARGIN,
			   GAME_NAME_WIDTH,
			   GAME_NAME_HEIGHT);
	layeredPane.add(gameName);

	this.playButton = new JButton("Play");
	playButton.setFont(new Font("Default", Font.PLAIN,PLAY_BUTTON_TEXT_FONT_SIZE));
	playButton.setBounds(
		frame.getWidth() / 2 - PLAY_BUTTON_WIDTH / 2,
		frame.getHeight() / 2,
		PLAY_BUTTON_WIDTH,
		PLAY_BUTTON_HEIGHT);

	layeredPane.add(playButton);

	playButton.addActionListener(new ActionListener()
	{
	    @Override public void actionPerformed(final ActionEvent e) {
		frame.remove(viewer.getActivePanel());
		viewer.setActivePanel(viewer.getGameScreen().getLayeredPane());
		viewer.showFrame();
		viewer.startGameClock();
	    }
	});
    }

    @Override
    public JLayeredPane getLayeredPane() {
	return layeredPane;
    }
}
