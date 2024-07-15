package in.sangeet.TravelManagement.services;

import in.sangeet.TravelManagement.exceptions.ActivityFullException;
import in.sangeet.TravelManagement.exceptions.NotEnoughBalanceException;
import in.sangeet.TravelManagement.model.Activity;
import in.sangeet.TravelManagement.model.Destination;
import in.sangeet.TravelManagement.model.Passenger;

import java.util.HashMap;
import java.util.Map;

/**
 * Service for managing activities.
 */
public class ActivityService {

    private final Map<Long, Activity> activities;

    public ActivityService() {
        this.activities = new HashMap<>();
    }

    /**
     * Creates a new activity
     * @param activity The activity to be created.
     */
    public void createActivity(Activity activity) {
        if (activities.containsKey(activity.getId())) {
            throw new IllegalArgumentException("Activity with activity id " + activity.getId() + " already exists");
        }

        activities.put(activity.getId(), activity);
    }

    /**
     * Gets the activity by activity id.
     *
     * @param activityId The activity to find.
     * @return The activity with given activityId
     */
    public Activity getActivity(long activityId) {
        if (!activities.containsKey(activityId)) {
            throw new IllegalArgumentException("Activity does not exists.");
        }

        return activities.get(activityId);
    }

    /**
     * Registers a passenger for an activity.
     *
     * @param passenger The passenger who wants to register for the activity.
     * @param activityId The activity for which the passenger wants to register.
     * @param destination The destination where the activity is located.
     */
    public void registerForActivity(long activityId, Passenger passenger, Destination destination) throws NotEnoughBalanceException {
        if (passenger == null || !activities.containsKey(activityId) || destination == null) {
            throw new IllegalArgumentException("Passenger, Activity, or Destination cannot be null.");
        }

        Activity activity = activities.get(activityId);
        if (!passenger.canRegisterForActivity(activity)) {
            throw new NotEnoughBalanceException("Passenger " + passenger.getName() + " does not have enough balance .");
        }

        if (activity.isFull()) {
            throw new ActivityFullException("Activity " + activity.getName() + " is full");
        }

        passenger.deductBalance(activity.getCost());
        passenger.addActivity(activity);
        activity.enrollPassenger();
    }
}