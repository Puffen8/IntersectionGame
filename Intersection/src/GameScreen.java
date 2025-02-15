import javax.swing.*;
import java.awt.*;

/**
 * This class is used to represent the game screen of the game.
 * The class implements the Screen interface and builds a
 * JLayeredPane that the class showing the GameScreen can obtain
 * through the GameScreen getter.
 */
public class GameScreen implements Screen
{
    private JLayeredPane layeredPane;
    private JLabel intersectionLabel;
    private JLabel scoreLabel;
    /**
     * The width of the score text.
     */
    public static final int SCORE_TEXT_WIDTH = 300;
    /**
     * The height of the score text.
     */
    public static final int SCORE_TEXT_HEIGHT = 50;
    /**
     * The font size of the score text.
     */
    public static final int SCORE_TEXT_FONT_SIZE = 50;

    /**
     * The constructor which creates the GameScreen by
     * creating all of the necessary objects, setting their
     * sizes and their positions, and adding everything to
     * the JLayeredPane that can be obtained by the getter.
     * @param viewer The Viewer that controls the graphics
     *               of the game, including the JFrame of
     *               the game.
     */
    public GameScreen(Viewer viewer) {
	this.layeredPane = new JLayeredPane();

	this.intersectionLabel = new JLabel(viewer.getIntersectionIcon());
	intersectionLabel.setBounds(0, 0, viewer.getIntersectionIcon().getIconWidth(), viewer.getIntersectionIcon().getIconHeight());
	layeredPane.add(intersectionLabel);

	this.scoreLabel = new JLabel("Score: " + viewer.getBoard().getScore());
	scoreLabel.setFont(new Font("Default", Font.BOLD, SCORE_TEXT_FONT_SIZE));
	layeredPane.add(scoreLabel);
	scoreLabel.setBounds(OBJECT_MARGIN,
			     OBJECT_MARGIN,
			     SCORE_TEXT_WIDTH * 2,
			     SCORE_TEXT_HEIGHT * 2);
    }

    @Override
    public JLayeredPane getLayeredPane() {
	return layeredPane;
    }

    public JLabel getScoreLabel() {
	return scoreLabel;
    }
}
