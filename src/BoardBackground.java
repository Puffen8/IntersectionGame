import javax.swing.*;
import java.awt.*;
import java.util.EnumMap;

/**
 * This class is used to store the pathing data of the image
 * used as the background in the game. The class contains starting
 * and ending locations for the vehicles. If another image is used
 * as the background in the game. The start and end Points in this
 * class can be adjusted accordingly.
 */
public class BoardBackground
{
    /**
     * The start Point of a vehicle starting in the bottom.
     */
    private static final Point BOTTOM_START = new Point(710, 750); // False positive in inspection, used as a constant
    /**
     * The start Point of a vehicle starting in the top.
     */
    private static final Point UPPER_START = new Point(570, -113); // False positive in inspection, used as a constant
    /**
     * The start Point of a vehicle starting to the left.
     */
    private static final Point LEFT_START = new Point(-113, 416); // False positive in inspection, used as a constant
    /**
     * The start Point of a vehicle starting to the right.
     */
    private static final Point RIGHT_START = new Point(1334, 276); // False positive in inspection, used as a constant
    /**
     * The end Point of a vehicle ending in the top.
     */
    private static final Point UPPER_END = new Point(710, -100); // False positive in inspection, used as a constant
    /**
     * The end Point of a vehicle ending in the bottom.
     */
    private static final Point BOTTOM_END = new Point(570, 750); // False positive in inspection, used as a constant
    /**
     * The end Point of a vehicle ending to the right.
     */
    private static final Point RIGHT_END = new Point(1334, 416); // False positive in inspection, used as a constant
    /**
     * The end Point of a vehicle ending to the left.
     */
    private static final Point LEFT_END = new Point(-100, 276); // False positive in inspection, used as a constant
    /**
     * A Enum map containing all of the vehicle start positions.
     */
    private static final EnumMap<Direction,Point> START_POS_MAP = createStartPosMap(); // False positive in inspection, used as a constant
    /**
     * A Enum map containing all of the vehicle end positions.
     */
    private static final EnumMap<Direction,Point> END_POS_MAP = createEndPosMap(); // False positive in inspection, used as a constant
    private ImageIcon backgroundImage;

    public BoardBackground(final ImageIcon backgroundImage) {
	this.backgroundImage = backgroundImage;
    }

    public static EnumMap<Direction,Point> createStartPosMap() {
	EnumMap<Direction,Point> startPosMap = createDirPointMap();
	startPosMap.put(Direction.NORTH, BOTTOM_START);
	startPosMap.put(Direction.SOUTH, UPPER_START);
	startPosMap.put(Direction.EAST, LEFT_START);
	startPosMap.put(Direction.WEST, RIGHT_START);

	return startPosMap;
    }

    public static EnumMap<Direction,Point> createEndPosMap() {
	EnumMap<Direction,Point> endPosMap = createDirPointMap();
	endPosMap.put(Direction.NORTH, UPPER_END);
	endPosMap.put(Direction.SOUTH, BOTTOM_END);
	endPosMap.put(Direction.EAST, RIGHT_END);
	endPosMap.put(Direction.WEST, LEFT_END);

	return endPosMap;
    }
    public static EnumMap<Direction,Point> createDirPointMap() {
	return new EnumMap<>(Direction.class);
    }

    public ImageIcon getBackgroundImage() {
	return backgroundImage;
    }

    public static EnumMap<Direction, Point> getStartPosMap() {
	return START_POS_MAP;
    }

    public static EnumMap<Direction, Point> getEndPosMap() {
	return END_POS_MAP;
    }
}
