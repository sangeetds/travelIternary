package in.sangeet.TravelManagement.model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class representing a passenger.
 */
@Getter
public abstract class Passenger {

    private final String         name;
    private final long           passengerNumber;
    private final List<Activity> activities;

    /**
     * Constructor for Passenger.
     *
     * @param name The name of the passenger.
     * @param passengerNumber The unique passenger number.
     */
    public Passenger(String name, long passengerNumber) {
        this.name = name;
        this.passengerNumber = passengerNumber;
        this.activities = new ArrayList<>();
    }

    public void addActivity(Activity activity) {
        this.activities.add(activity);
    }

    /**
     * Registers the passenger for an activity.
     *
     * @param activity The activity to register for.
     * @return true if registration was successful, false otherwise.
     */
    public abstract boolean canRegisterForActivity(Activity activity);

    public abstract double getBalance();

    public abstract void deductBalance(double amount);

    /**
     * Prints the details of the passenger.
     */
    public abstract void printDetails();
}
