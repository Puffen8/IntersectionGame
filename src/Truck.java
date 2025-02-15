import java.util.EnumMap;

/**
 * This class is used to represent a truck. The class extends to the
 * AbstractVehicle class that  creates the base model for
 * every Truck object. The Truck class then has some unique properties
 * that is special for the Truck VehicleType.
 */
public class Truck extends AbstractVehicle
{
    /**
     * The speed of the Truck.
     */
    private static final double SPEED = 1;
    /**
     * The score the Truck is worth.
     */
    private static final int SCORE_WORTH = 3;
    /**
     * The Enum map containing all the Truck image paths.
     */
    private static final EnumMap<VehicleColor,String> TRUCK_IMAGE_NAME_MAP = GraphicsAsset.createTruckUrlMap(); // False positive in inspection, used as a constant
    /**
     * The constructor of a Truck that creates a Truck object
     * by inheriting properties from the super class.
     * @param color The color of the Truck.
     * @param direction The Direction the Truck will be facing.
     */
    public Truck(final VehicleColor color, final Direction direction) {
	super(direction, SPEED, SCORE_WORTH, TRUCK_IMAGE_NAME_MAP.get(color));
    }
}
