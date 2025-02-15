import javax.swing.*;
import java.awt.*;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * This class is used to represent a board. The board has a list
 * that contains all of the vehicles that currently is in the game.
 * This list i constantly updated; vehicles are added when they are created
 * and vehicles are removed when they have reached their end position.
 * <p></p>
 * The class contains methods for creating a new vehicle, checking if a
 * vehicle has reached it's end point, the tick method that is called every
 * game tick and also getters for all of the fields.
 */
public class Board
{
    private BoardBackground background;
    private ImageIcon backgroundImage;
    private List<AbstractVehicle> vehicles;
    private Direction[] startDirections;
    private List<Direction> validStartDirections;
    private final static Random RND = new Random();
    private int score = 0;
    /**
     * The chance of a spawned vehicle being a Truck, in percent.
     */
    private final static int TRUCK_SPAWN_CHANCE = 25;
    /**
     * A constant for the total spawn chance of a vehicle, in percent.
     */
    private static final int TOTAL_SPAWN_CHANCE = 100;
    /**
     * The time between the vehicle spawns, in milliseconds.
     */
    private final static int TIME_BETWEEN_SPAWNS_MS = 2000;
    private boolean gameRunning = true;
    private Instant anchorTime = null;

    /**
     * The constructor that creates a Board. Initializes everything
     * for the board and set the validStartDirections List to
     * contain everyDirection.
     * @param background The BoardBackground that contains the
     *                   background image and start and end Points
     *                   for the car paths.
     */
    public Board(BoardBackground background) {
	this.background = background;
	this.backgroundImage = background.getBackgroundImage();
	this.vehicles = new ArrayList<>();
	this.startDirections = Direction.values();
	this.validStartDirections = new ArrayList<>();
	this.validStartDirections.addAll(Arrays.asList(startDirections));
    }

    /**
     * Removes a Direction from the validStartDirections List.
     *
     * @param direction The Direction to be removed from the List.
     */
    public void removeStartDirection(Direction direction) {
	validStartDirections.remove(direction);
    }

    /**
     * Resets the validStartDirections List to how it
     * was when the Board was constructed.
     */
    public void resetStartDirections() {
	this.validStartDirections.clear();
	this.validStartDirections.addAll(Arrays.asList(startDirections));
    }

    /**
     * Creates a new vehicle with a random VehicleColor, Direction
     * and VehicleType. The method also calls the
     * resetStartDirections method to reset the validStartDirections
     * List and then calls the removeStartDirection to remove the
     * direction of the created vehicle from the validStartDirections
     * List.
     */
    public void createVehicle() {
	VehicleColor color = VehicleColor.values()[RND.nextInt(VehicleColor.values().length)];
	Direction direction = validStartDirections.get(RND.nextInt(validStartDirections.size()));

	int typeIndex = RND.nextInt(TOTAL_SPAWN_CHANCE);

	if (typeIndex < TRUCK_SPAWN_CHANCE) {
	    Truck truck = new Truck(color, direction); // Create a TRUCK
	    this.vehicles.add(truck);
	} else {
	    Car car = new Car(color, direction); // Create a CAR
	    this.vehicles.add(car);
	}

	resetStartDirections();
	removeStartDirection(direction);
    }

    /**
     * Checks if any of the vehicles in the vehicles List
     * has collided with each other.
     * @return True if a collision has happened, otherwise false.
     */
    public boolean hasCollided() {
	for (int i = 0; i < this.vehicles.size(); i++) {
	    Rectangle hitbox1 = this.vehicles.get(i).getImageLabel().getBounds();
	    for (int j = i + 1; j < this.vehicles.size(); j++) {
		Rectangle hitbox2 = this.vehicles.get(j).getImageLabel().getBounds();
		if (hitbox1.intersects(hitbox2)) {
		    return true;
		}
	    }
	}
	return false;
    }

    /**
     * Updates everything that happens in the game. This
     * method is called every tick of the game and creates
     * vehicles, moves vehicles,checks if a vehicle has
     * made it to it's end Point or if any vehicle has
     * collided, which sets the gameRunning varaible to
     * false.
     */
    public void tick() {
	if (anchorTime == null) {
	    anchorTime = Instant.now();

	}
	if (Duration.between(anchorTime, Instant.now()).toMillis() >= TIME_BETWEEN_SPAWNS_MS) {
	    anchorTime = Instant.now();
	    createVehicle();
	}
	for (AbstractVehicle vehicle : this.vehicles) {
	    if (vehicle.isDriving()) {
		vehicle.moveVehicle();
	    }
	    if (vehicle.hasReachedEnd()) {
		score += vehicle.getScoreWorth();
	    }
	}
	if (hasCollided()) {
	    gameRunning = false;
	}
    }

    public List<AbstractVehicle> getVehicles() {
	return vehicles;
    }

    public int getScore() {
	return score;
    }

    public boolean isGameRunning() {
	return gameRunning;
    }

    public ImageIcon getBackgroundImage() {
	return backgroundImage;
    }

    public BoardBackground getBackground() {
	return background;
    }
}