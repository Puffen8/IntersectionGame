import java.util.EnumMap;

/**
 * This class is used to represent a car. The class extends to the
 * AbstractVehicle class which creates the base model for
 * every Car object. The Car class then has some unique properties
 * that is special for the Car VehicleType.
 */
public class Car extends AbstractVehicle
{
    /**
     * The speed if the Car.
     */
    private static final double SPEED = 1.5;
    /**
     * The score the Car is worth.
     */
    private static final int SCORE_WORTH = 1;
    /**
     * The Enum map containing all the Car image paths.
     */
    private static final EnumMap<VehicleColor,String> CAR_IMAGE_NAME_MAP = GraphicsAsset.createCarUrlMap(); // False positive in inspection, used as a constant

    /**
     * The constructor of a Car that creates a Car object
     * by inheriting properties from the super class.
     * @param color The color of the Car.
     * @param direction The Direction the Car will be facing.
     */
    public Car(final VehicleColor color, final Direction direction) {
	super(direction, SPEED, SCORE_WORTH, CAR_IMAGE_NAME_MAP.get(color));
    }
}
