package in.sangeet.TravelManagement.model;

import lombok.Getter;

import java.util.*;

/**
 * Class representing a travel package.
 */
@Getter
public class TravelPackage{


    private final long                             id;
    private final String                           name;
    private final int                              passengerCapacity;
    private final LinkedHashMap<Long, Destination> itinerary;
    private final Map<Long, Passenger>             passengers;

    /**
     * Constructor for TravelPackage.
     *
     * @param name The name of the travel package.
     * @param passengerCapacity The maximum number of passengers.
     */
    public TravelPackage(long id, String name, int passengerCapacity) {
        this.id = id;
        this.name = name;
        this.passengerCapacity = passengerCapacity;
        this.itinerary = new LinkedHashMap<>();
        this.passengers = new HashMap<>();
    }

    /**
     * Checks if the travel package is full and cannot accommodate more participants.
     *
     * @return True if the activity is full, false otherwise.
     */
    public boolean isFull() {
        return passengerCapacity == passengers.size();
    }

    /**
     * Prints the itinerary of the travel package.
     */
    public void printItinerary() {
        System.out.println("Travel Package: " + name);
        for (Destination destination : itinerary.values()) {
            System.out.println("Destination: " + destination.getName());
            for (Activity act : destination.getActivities().values()) {
                System.out.println("\tActivity: " + act.getName() +
                        ", Cost: " + act.getCost() +
                        ", Capacity: " + act.getCapacity() +
                        ", Description: " + act.getDescription());
            }
        }
    }

    /**
     * Prints the details of activities.
     */
    public void printPassengerList() {
        System.out.println("Travel Package: " + name);
        System.out.println("Passenger Capacity: " + passengerCapacity);
        System.out.println("Current Passengers: " + passengers.size());
        for (Passenger passenger : passengers.values()) {
            System.out.println("Passenger: " + passenger.getName() +
                    ", Number: " + passenger.getPassengerNumber());
        }
    }

    /**
     * Prints the details of activities that still have available spaces.
     */
    public void printAvailableActivities() {
        for (Destination destination : itinerary.values()) {
            for (Activity act : destination.getActivities().values()) {
                if (act.getAvailableCapacity() > 0) {
                    System.out.println("Activity: " + act.getName() +
                            ", Available Spots: " + act.getAvailableCapacity());
                }
            }
        }
    }
}