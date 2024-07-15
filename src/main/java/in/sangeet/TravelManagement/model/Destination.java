package in.sangeet.TravelManagement.model;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * Class representing a destination in a travel package.
 */
@Getter
public class Destination {

    private final long                id;
    private final String              name;
    private final Map<Long, Activity> activities;

    /**
     * Constructor for Destination.
     *
     * @param name The name of the destination.
     */
    public Destination(long id, String name) {
        this.id = id;
        this.name = name;
        activities = new HashMap<>();
    }
}
