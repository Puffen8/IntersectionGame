import javax.swing.*;
import java.util.EnumMap;

/**
 * This class is only used to store the data from the image paths.
 * Any class that is loading in a image can get the image or the
 * image URL from the enum maps and getters in this class.
 */
public class GraphicsAsset
{
    private static final ImageIcon BACKGROUND_IMAGE = new ImageIcon(ClassLoader.getSystemResource("Intersection.png"));

    public static EnumMap<VehicleColor,String> createCarUrlMap() {
	EnumMap<VehicleColor,String> carImagePathMap = new EnumMap<>(VehicleColor.class);
	carImagePathMap.put(VehicleColor.BLUE, "CarBlue.png");
	carImagePathMap.put(VehicleColor.GREEN, "CarGreen.png");
	carImagePathMap.put(VehicleColor.RED, "CarRed.png");

	return carImagePathMap;
    }
    public static EnumMap<VehicleColor,String> createTruckUrlMap() {
	EnumMap<VehicleColor,String> truckImagePathMap = new EnumMap<>(VehicleColor.class);
	truckImagePathMap.put(VehicleColor.BLUE, "TruckBlue.png");
	truckImagePathMap.put(VehicleColor.GREEN, "TruckGreen.png");
	truckImagePathMap.put(VehicleColor.RED, "TruckRed.png");

	return truckImagePathMap;
    }


    public static ImageIcon getBackgroundImage() {
	return BACKGROUND_IMAGE;
    }
}
