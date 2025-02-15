
import javax.swing.*;
/**
 * This Interface is used to represent the methods that every
 * screen should to have. Every screen class should implement
 * this interface.
 */
public interface Screen
{
    /**
     * Used as a standard margin size between object and
     * the frame edges. Can be used to always have the same
     * distance between the objects.
     */
    public static final int OBJECT_MARGIN = 50;

    /**
     * Every screen should set up a JLayeredPane containing
     * everything in the screen. When the screen will be used
     * it's JLayeredPane can be obtained through this method.
     * @return The JLayeredPane that the screen class has built up
     */
    public JLayeredPane getLayeredPane(); // False positive in inspection

}
