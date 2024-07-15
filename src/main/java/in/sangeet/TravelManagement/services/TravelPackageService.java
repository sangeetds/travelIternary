package in.sangeet.TravelManagement.services;

import in.sangeet.TravelManagement.exceptions.TravelPackageFullException;
import in.sangeet.TravelManagement.model.Activity;
import in.sangeet.TravelManagement.model.Destination;
import in.sangeet.TravelManagement.model.Passenger;
import in.sangeet.TravelManagement.model.TravelPackage;
import lombok.Getter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Service for managing destinations.
 */
public class TravelPackageService {

    private final Map<Long, TravelPackage> travelPackages;

    public TravelPackageService() {
        this.travelPackages = new HashMap<>();
    }

    /**
     * Creates a new travel package
     * @param travelPackage The destination to be created.
     */
    public void createTravelPackage(TravelPackage travelPackage) {
        if (travelPackages.containsKey(travelPackage.getId())) {
            throw new IllegalArgumentException("Package with travelPackage id " + travelPackage.getId() + " already exists");
        }

        travelPackages.put(travelPackage.getId(), travelPackage);
    }

    /**
     * Gets the activity by activity id.
     *
     * @param id The activity to find.
     * @return The activity with given activityId
     */
    public TravelPackage getTravelPackage(long id) {
        if (!travelPackages.containsKey(id)) {
            throw new IllegalArgumentException("TravelPackage does not exists.");
        }

        return travelPackages.get(id);
    }

    /**
     * Adds a new destination to a travel package.
     *
     * @param travelPackageId The travel package to which the destination will be added.
     * @param destination The destination to add.
     */
    public void addDestination(long travelPackageId, Destination destination) {
        if (destination == null || !travelPackages.containsKey(travelPackageId)) {
            throw new IllegalArgumentException("TravelPackage or Destination cannot be null.");
        }

        travelPackages.get(travelPackageId).getItinerary().put(destination.getId(), destination);
    }

    /**
     * Removes a destination from a travel package.
     *
     * @param travelPackageId The travel package to which the destination will be removed.
     * @param destination The destination to remove.
     */
    public void removeDestination(long travelPackageId, Destination destination) {
        if (destination == null || !travelPackages.containsKey(travelPackageId)) {
            throw new IllegalArgumentException("TravelPackage or Destination cannot be null.");
        }

        travelPackages.get(travelPackageId).getItinerary().remove(destination.getId());
    }

    /**
     * Adds a passenger to a travel package.
     *
     * @param travelPackageId The travel package to which the passenger will be added.
     * @param passenger The passenger to add.
     * @throws TravelPackageFullException when the travel package is full.
     */
    public void addPassenger(long travelPackageId, Passenger passenger) throws TravelPackageFullException {
        if (!travelPackages.containsKey(travelPackageId) || passenger == null) {
            throw new IllegalArgumentException("TravelPackage or Passenger cannot be null.");
        }

        if (travelPackages.get(travelPackageId).getPassengers().containsKey(passenger.getPassengerNumber())) {
            throw new IllegalArgumentException("Passenger has already been added to the package.");
        }

        if (travelPackages.get(travelPackageId).isFull()) {
            throw new TravelPackageFullException();
        }

        travelPackages.get(travelPackageId).getPassengers().put(passenger.getPassengerNumber(), passenger);
    }

    /**
     * Removes a passenger from a travel package.
     *
     * @param travelPackageId The travel package to which the passenger will be removed.
     * @param passenger The passenger to remove. Have not created any coupling between services and could have been fetched by the other service
     */
    public void removePassenger(long travelPackageId, Passenger passenger) {
        if (!travelPackages.containsKey(travelPackageId) || passenger == null) {
            throw new IllegalArgumentException("TravelPackage or Passenger cannot be null.");
        }

        if (!travelPackages.get(travelPackageId).getPassengers().containsKey(passenger.getPassengerNumber())) {
            throw new IllegalArgumentException("Passenger is not present in the package.");
        }

        travelPackages.get(travelPackageId).getPassengers().remove(passenger.getPassengerNumber());
    }
}
