import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * This class is used to represent a vehicle. It implements the interface Vehicle
 * and contains fields and methods that every vehicle object in the game has.
 * <p>
 * The class implements a MouseListener to handle the mouse event of a object
 * being pressed.
 * <p>
 * The class also implements a interface Vehicle that contains common methods
 * that every vehicle object has. Some of these methods are overridden in the
 * separete Car and Truck classes since they look different depending on the
 * class.
 * <p>
 * The class also contains getters for all of the fields specified in the class.
 */
public abstract class AbstractVehicle extends MouseAdapter implements Vehicle
{
    protected Point2D.Double position;
    protected Point endPos;
    protected final Direction direction;
    protected double speed;
    protected int scoreWorth;
    protected ImageIcon imageIcon;
    protected JLabel imageLabel;
    protected boolean isDriving = true;
    protected boolean endReached = false;
    /**
     * A logger for the image reading and error catching.
     */
    private static final Logger LOGGER = Logger.getLogger(AbstractVehicle.class.getName());
    /**
     * The scale the vehicle images will be scaled to.
     */
    private static final double VEHICLE_IMAGE_SCALE = 0.125;

    /**
     * The constructor of the class.
     * @param direction The Direction the vehicle will be facing.
     * @param speed The speed of the vehicle.
     * @param scoreWorth The score the vehicle will be worth.
     * @param imageName The name of the image of teh vehicle.
     */

    protected AbstractVehicle(final Direction direction, final double speed,
			      final int scoreWorth, final String imageName) {
	this.direction = direction;
	this.speed = speed;
	this.scoreWorth = scoreWorth;
	Point startPosition = BoardBackground.getStartPosMap().get(direction);
	this.position = new Point2D.Double(startPosition.x, startPosition.y);
	this.endPos = new Point(BoardBackground.getEndPosMap().get(direction));

	BufferedImage vehicleBufferedImage = null;

	try {
	    vehicleBufferedImage = ImageIO.read(ClassLoader.getSystemResource(imageName)); // Reading the image from the path
	} catch (IOException e) { // False positive in inspection
	    LOGGER.severe("Failed to read image file: " + e.getMessage());
	    e.printStackTrace();
	}
	// False positive in inspection
	vehicleBufferedImage = Viewer.rotate(vehicleBufferedImage, Viewer.getRotationAngle(direction)); // Rotating the image

	Image vehicleScaledImage = Viewer.scaleImage(vehicleBufferedImage, VEHICLE_IMAGE_SCALE); // Scaling the image

	this.imageIcon = new ImageIcon(vehicleScaledImage);
	this.imageLabel = new JLabel(imageIcon);

	imageLabel.addMouseListener(this);
    }

    @Override
    public void mousePressed(MouseEvent e) {
	if (isDriving) {
	    isDriving = false;
	} else {isDriving = true;}
    }

    @Override
    public void moveVehicle() {
	switch (this.direction) {
	    case NORTH:
		this.position.y -= speed;
		break;
	    case SOUTH:
		this.position.y += speed;
		break;
	    case EAST:
		this.position.x += speed;
		break;
	    case WEST:
		this.position.x -= speed;
		break;
	}
    }
    @Override
    public boolean hasReachedEnd() {
	switch (direction) {
	    case NORTH:
		return this.position.y <= this.endPos.y;
	    case SOUTH:
		return this.position.y >= this.endPos.y;
	    case EAST:
		return this.position.x >= this.endPos.x;
	    case WEST:
		return this.position.x <= this.endPos.x;
	    default: return false;

	}
    }

    @Override
    public Point2D.Double getPosition() {
	return position;
    }

    @Override
    public ImageIcon getImageIcon() {
	return imageIcon;
    }

    @Override
    public JLabel getImageLabel() {
	return imageLabel;
    }

    public boolean isDriving() {
	return isDriving;
    }

    public boolean endIsReached() {
	return endReached;
    } // False positive in inspection
    public int getScoreWorth() {
	return scoreWorth;
    }
}

