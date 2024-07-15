package in.sangeet.TravelManagement.model;

import lombok.Getter;

/**
 * Class representing an activity at a destination.
 */
@Getter
public class Activity {

    private final long        id;

    private final String      name;

    private final String      description;

    private final double      cost;

    private final int         capacity;

    private final Destination destination;

    private int               enrolledPassengers;

    /**
     * Constructor for Activity.
     *
     * @param name The name of the activity.
     * @param description The description of the activity.
     * @param cost The cost of the activity.
     * @param capacity The maximum capacity of the activity.
     * @param destination The destination of the activity.
     */
    public Activity(long id, String name, String description, double cost, int capacity, Destination destination) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.capacity = capacity;
        this.destination = destination;
        this.enrolledPassengers = 0;
    }

    /**
     * Checks if the activity is full.
     *
     * @return True if the activity is full, false otherwise.
     */
    public boolean isFull() {
        return capacity == enrolledPassengers;
    }

    /**
     * Gets the available capacity for the activity.
     *
     * @return The number of available spots.
     */
    public int getAvailableCapacity() {
        return capacity - enrolledPassengers;
    }

    /**
     * Add one more passenger to the activity
     */
    public void enrollPassenger() {
        enrolledPassengers++;
    }

    /**
     * Prints the details of an activity.
     *
     * @param activity The activity whose details are to be printed.
     */
    public void printActivityDetails(Activity activity) {
        if (activity == null) {
            throw new IllegalArgumentException("Activity cannot be null.");
        }
        System.out.println("Activity: " + activity.getName() +
                ", Cost: " + activity.getCost() +
                ", Capacity: " + activity.getCapacity() +
                ", Description: " + activity.getDescription() +
                ", Available Spots: " + (activity.getCapacity() - activity.getEnrolledPassengers()));
    }
}
