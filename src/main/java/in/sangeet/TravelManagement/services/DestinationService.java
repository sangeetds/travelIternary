package in.sangeet.TravelManagement.services;

import in.sangeet.TravelManagement.exceptions.ActivityAlreadyPresentInDestinationException;
import in.sangeet.TravelManagement.model.Activity;
import in.sangeet.TravelManagement.model.Destination;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Service class for managing destinations.
 *
 */
public class DestinationService {

    private final Map<Long, Destination> destinations;

    public DestinationService() {
        this.destinations = new HashMap<>();
    }

    /**
     * Creates a new destination
     * @param destination The destination to be created.
     */
    public void createDestination(Destination destination) {
        if (destinations.containsKey(destination.getId())) {
            throw new IllegalArgumentException("Destination with destination id " + destination.getId() + " already exists");
        }

        destinations.put(destination.getId(), destination);
    }

    /**
     * Gets the particualr destination
     * @param destinationId The activity to find.
     * @return The activity with given activityId
     */
    public Destination getDestination(long destinationId) {
        if (!destinations.containsKey(destinationId)) {
            throw new IllegalArgumentException("Destination does not exists.");
        }

        return destinations.get(destinationId);
    }

    /**
     * Adds a new activity to a destination.
     *
     * @param destinationId The destination to which the activity will be added.
     * @param activity The activity to add.
     * @throws ActivityAlreadyPresentInDestinationException when activity is already a part of another destination
     */
    public void addActivityToDestination(long destinationId, Activity activity)
            throws ActivityAlreadyPresentInDestinationException {
        if (!destinations.containsKey(destinationId) || activity == null) {
            throw new IllegalArgumentException("Destination or Activity cannot be null.");
        }

        boolean isActivityAPartOfAnotherDestination = destinations.values()
                .stream()
                .anyMatch(dest -> dest.getActivities().containsKey(activity.getId()));
        if (isActivityAPartOfAnotherDestination) {
            throw new ActivityAlreadyPresentInDestinationException();
        }

        destinations.get(destinationId).getActivities().put(activity.getId(), activity);
    }

    /**
     * Gets the list of destinations in a travel package.
     *
     * @param destinationId The destinations in which activities are present
     * @return The list of destinations.
     */
    public List<Activity> getActivitiesInDestination(int destinationId) {
        if (!destinations.containsKey(destinationId)) {
            throw new IllegalArgumentException("Destination with id " + destinationId + "not found");
        }

        return destinations.get(destinationId).getActivities().values().stream().toList();
    }
}
