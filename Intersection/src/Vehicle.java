import javax.swing.*;
import java.awt.geom.Point2D;
/**
 * This Interface is used to represent the methods that every
 * vehicle should to have. Every vehicle class should implement
 * this interface.
 */
public interface Vehicle
{
    public void moveVehicle(); // False positive in inspection
    public boolean hasReachedEnd(); // False positive in inspection
    public Point2D.Double getPosition(); // False positive in inspection
    public JLabel getImageLabel(); // False positive in inspection
    public ImageIcon getImageIcon(); // False positive in inspection

}
