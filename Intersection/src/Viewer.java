
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.EnumMap;

/**
 * This class is used to represent the viewer that handles and
 * displays all of the game graphics. The class contains methods
 * for image handling; rotate for rotating a image depending on the
 * direction, getPrefferedSize for getting the size of the frame and
 * show that add the backgrounds image to the frame, displays the frame
 * and starts the main timer clock that runs the game.
 * <p> </p>
 * The timer clock updates all of the graphics on the screen to their
 * right positions, removes vehicles that has made it to their end Point,
 * adds new vehicles that has been created to the frame.
 */
public class Viewer
{
    private JFrame frame = new JFrame("Intersection");
    private Board board;
    private ImageIcon intersectionIcon;
    /**
     * The amount of game ticks and frames per second.
     */
    private final static int GAME_TICK_RATE = 60;
    private JLayeredPane activePanel;
    private JLabel vehicleLabel = null;
    /**
     * The layer for the vehicles in the JLayeredPane.
     */
    private static final int VEHICLE_LAYER = 1;
    private StartScreen startScreen;
    private GameScreen gameScreen;
    private EndScreen endScreen;
    /**
     * The rotaion angle for the Direction NORTH, in degrees.
     */
    private static final int NORTH_ANGLE = 0;
    /**
     * The rotaion angle for the Direction SOUTH, in degrees.
     */
    private static final int SOUTH_ANGLE = 180;
    /**
     * The rotaion angle for the Direction EAST, in degrees.
     */
    private static final int EAST_ANGLE = 90;
    /**
     * The rotaion angle for the Direction WEST, in degrees.
     */
    private static final int WEST_ANGLE = -90;
    /**
     * The delay between the game ending and the display
     * of the EndScreen, in milliseconds.
     */
    private static final int END_SCREEN_DELAY = 3000;
    private static final EnumMap<Direction,Integer> DIRECTION_ROTATION_ANGLE_MAP = createDirectionAngleMap(); // False positive in inspection, used as constant

    /**
     * The constructor of the class ehich initializes the frame
     * and adds the startScreen to the activePanel.
     * @param board The game board.
     */
    public Viewer(Board board) {
	this.board = board;

	this.intersectionIcon = board.getBackgroundImage();

	frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	frame.setSize(getPreferredSize());

	this.startScreen = new StartScreen(this);
	this.gameScreen = new GameScreen(this);
	this.endScreen = new EndScreen(this);

	/*this.activePanel = new StartScreen(this).getLayeredPane();*/
	initStartScreen();

    }

    /**
     * Creates a static EnumMap for the relation between a Direction
     * and it's rotation angle in degrees.
     * @return The created EnumMap
     */
    public static EnumMap<Direction,Integer> createDirectionAngleMap() {
	EnumMap<Direction,Integer> directionAngleMap = new EnumMap<>(Direction.class);
	directionAngleMap.put(Direction.NORTH, NORTH_ANGLE);
	directionAngleMap.put(Direction.SOUTH, SOUTH_ANGLE);
	directionAngleMap.put(Direction.EAST, EAST_ANGLE);
	directionAngleMap.put(Direction.WEST, WEST_ANGLE);

	return directionAngleMap;
    }

    /**
     * Gets the rotation angle from the DIRECTION_ROTATION_ANGLE_MAP
     * @param direction The Direction that will be passed to the EnumMap
     * @return The rotation angle corresponding to the Direction parameter
     */
    public static int getRotationAngle(Direction direction) {
	return DIRECTION_ROTATION_ANGLE_MAP.get(direction);
    }

    /**
     * Rotatates a BufferedImage and it's Dimension to the wanted rotation angle.
     * @param image The image to be rotated
     * @param degreeAngle The rotation the imag ewill be rotated to
     * @return The rotated image
     */
    public static BufferedImage rotate(BufferedImage image, int degreeAngle) {
	double radianAngle = Math.toRadians(degreeAngle);
	int width = image.getWidth();
	int height = image.getHeight();

	Rectangle border = new Rectangle(0, 0, width, height);
	AffineTransform transform = new AffineTransform();
	transform.rotate(radianAngle, (double)width / 2, (double)height / 2);
	Shape transformed = transform.createTransformedShape(border);
	int newWidth = transformed.getBounds().width;
	int newHeight = transformed.getBounds().height;

	BufferedImage rotationImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
	Graphics2D g = rotationImage.createGraphics();
	g.translate((double)(newWidth - width) / 2, (double)(newHeight - height) / 2);
	g.rotate(radianAngle, (double)width / 2, (double)height / 2);
	g.drawRenderedImage(image, null);
	g.dispose();

	return rotationImage;
    }

    /**
     * Scales a image to the wanted scale ratio.
     * @param image The image to be scaled
     * @param scaleRatio The ratio the image will be sacled to
     * @return THe scaled image
     */
    public static Image scaleImage(Image image, double scaleRatio) {
	return image.getScaledInstance(
		(int)(image.getWidth(null) * scaleRatio),
		(int)(image.getHeight(null) * scaleRatio),
		Image.SCALE_SMOOTH);
    }

    /**
     * Gets the Dimension of the board background.
     * @return The Dimension of the board background
     */
    public Dimension getPreferredSize() {
	Image intersectionImage = intersectionIcon.getImage();
	return new Dimension(intersectionImage.getWidth(null), intersectionImage.getHeight(null));
    }

    /**
     * Adds the activePanel to the JFrame and set the
     * frame visibility to true.
     */
    public void showFrame() {
	frame.add(activePanel);
	frame.setVisible(true);
    }

    /**
     * Starts the game timer clock.
     */

    public void startGameClock() {
	clockTimer.setCoalesce(true);
	clockTimer.start();
    }

    /**
     * Initializes the StartScreen by creating a StartScreen
     * and then adding it to the activePanel.
     */
    private void initStartScreen() {
	this.startScreen =  new StartScreen(this);
	this.activePanel = startScreen.getLayeredPane();
    }
    /**
     * Initializes the GameScreen by creating a GameScreen
     * and then adding it to the activePanel.
     */
    public void initGameScreen() {
	this.gameScreen =  new GameScreen(this);
	this.activePanel = gameScreen.getLayeredPane();
    }
    /**
     * Initializes the EndScreen by creating a EndScreen
     * and then adding it to the activePanel.
     */
    public void initEndScreen() {
	this.endScreen = new EndScreen(this);
	this.activePanel = endScreen.getLayeredPane();
    }
    private final Action doOneStep = new AbstractAction() {
	public void actionPerformed(ActionEvent e) {
	    board.tick();

	    for (AbstractVehicle vehicle: board.getVehicles()) {
		Component[] layeredPaneComponents = activePanel.getComponentsInLayer(VEHICLE_LAYER);
		boolean containsLabel = false;
		for (Component component: layeredPaneComponents) {
		    if (component.equals(vehicle.getImageLabel())) {
			containsLabel = true;
			break;
		    }
		}
		if (!containsLabel) {
		    activePanel.add(vehicle.getImageLabel(), Integer.valueOf(VEHICLE_LAYER));
		}
		if (vehicle.hasReachedEnd()) {
		    activePanel.remove(vehicle.getImageLabel());
		    activePanel.repaint();
		    gameScreen.getScoreLabel().setText("Score: " + board.getScore()); // Updates score
		}

		vehicle.getImageLabel().setBounds((int)vehicle.getPosition().x, // Updates the position for the vehicle
						  (int)vehicle.getPosition().y,
						  vehicle.getImageIcon().getIconWidth(),
						  vehicle.getImageIcon().getIconHeight());
	    }
	    board.getVehicles().removeIf(AbstractVehicle::hasReachedEnd); // Removes vehicle from the vehicles list
	    								  // if their end Point has been reached
	    if (!board.isGameRunning()) {
		Timer gameEndDelay = new Timer(0, new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent event) {
			frame.remove(activePanel);
			initEndScreen();
			board = new Board(board.getBackground());
			showFrame();
		    }
		});
		clockTimer.stop(); // Stops the game Timer

		gameEndDelay.setInitialDelay(END_SCREEN_DELAY);
		gameEndDelay.setRepeats(false);
		gameEndDelay.start();
	    }
	}
    };
    private final Timer clockTimer = new Timer(1000 / GAME_TICK_RATE, doOneStep);

    public void setActivePanel(final JLayeredPane activePanel) {
	this.activePanel = activePanel;
    }

    public JFrame getFrame() {
	return frame;
    }

    public Board getBoard() {
	return board;
    }

    public void setBoard(final Board board) {
	this.board = board;
    }

    public ImageIcon getIntersectionIcon() {
	return intersectionIcon;
    }

    public JLayeredPane getActivePanel() {
	return activePanel;
    }

    public GameScreen getGameScreen() {
	return gameScreen;
    }
    public Timer getClockTimer() {
	return clockTimer;
    }
}
