package in.sangeet.TravelManagement.servicestests;

import in.sangeet.TravelManagement.exceptions.ActivityFullException;
import in.sangeet.TravelManagement.exceptions.NotEnoughBalanceException;
import in.sangeet.TravelManagement.model.Activity;
import in.sangeet.TravelManagement.model.Destination;
import in.sangeet.TravelManagement.model.Passenger;
import in.sangeet.TravelManagement.services.ActivityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static in.sangeet.TravelManagement.TestConstants.*;
import static org.junit.jupiter.api.Assertions.*;

public class ActivityServiceTest {

    private ActivityService activityService;

    @BeforeEach
    void setUp() {
        activityService = new ActivityService();
    }

    @Test
    void createActivity_happyCase() {
        Activity sampleActivity = getSampleActivity(SAMPLE_ID);
        activityService.createActivity(sampleActivity);

        Activity activity = activityService.getActivity(sampleActivity.getId());

        assertEquals(sampleActivity.getId(), activity.getId());
        assertEquals(sampleActivity.getName(), activity.getName());
        assertEquals(sampleActivity.getCapacity(), activity.getCapacity());
        assertEquals(sampleActivity.getDescription(), activity.getDescription());
        assertEquals(sampleActivity.getDestination().getId(), activity.getDestination().getId());
        assertEquals(sampleActivity.getCost(), activity.getCost());
        assertEquals(sampleActivity.getEnrolledPassengers(), activity.getEnrolledPassengers());
    }

    @Test
    void createActivity_whenActivityByIdAlreadyPresent() {
        Activity sampleActivity = getSampleActivity(SAMPLE_ID);
        activityService.createActivity(sampleActivity);
        assertThrows(IllegalArgumentException.class, () -> activityService.createActivity(sampleActivity));
    }

        @Test
    void registerForActivity_happyCase() {
        Passenger passenger = getStandardPassenger(SAMPLE_ID);
        Destination destination = getSampleDestination(SAMPLE_ID);

        double baseBalance = passenger.getBalance();

        activityService.createActivity(getSampleActivity(SAMPLE_ID));

        activityService.registerForActivity(getSampleActivity(SAMPLE_ID).getId(), passenger, destination);

        Activity activity = activityService.getActivity(getSampleActivity(SAMPLE_ID).getId());

        assertEquals(passenger.getBalance(), baseBalance - activity.getCost());
        assertFalse(passenger.getActivities().isEmpty());
        assertFalse(activity.isFull());
        assertTrue(activity.getEnrolledPassengers() > 0);
        assertTrue(passenger.getActivities().contains(activity));
    }

    @Test
    void registerForActivity_whenBalanceIsNotEnough() {
        Passenger passenger = getStandardPassenger(SAMPLE_ID);
        Destination destination = getSampleDestination(SAMPLE_ID);

        activityService.createActivity(getExpensiveActivity());

        assertThrows(NotEnoughBalanceException.class,
                () -> activityService.registerForActivity(getExpensiveActivity().getId(), passenger, destination));

    }

    @Test
    void registerForActivity_invalidArguments() {
        assertThrows(IllegalArgumentException.class,
                () -> activityService.registerForActivity(SAMPLE_ID, null, null));

        activityService.createActivity(getSampleActivity(SAMPLE_ID));

        assertThrows(IllegalArgumentException.class,
                () -> activityService.registerForActivity(SAMPLE_ID, null, getSampleDestination(SAMPLE_ID)));
        assertThrows(IllegalArgumentException.class,
                () -> activityService.registerForActivity(SAMPLE_ID, getPremiumPassenger(SAMPLE_ID), null));
    }

    @Test
    void registerForActivity_whenActivityIsFull() {
        Destination destination = getSampleDestination(SAMPLE_ID);

        activityService.createActivity(getSampleActivity(SAMPLE_ID));

        activityService.registerForActivity(SAMPLE_ID, getPremiumPassenger(SAMPLE_ID), destination);
        activityService.registerForActivity(SAMPLE_ID, getPremiumPassenger(SAMPLE_ID), destination);
        activityService.registerForActivity(SAMPLE_ID, getPremiumPassenger(SAMPLE_ID), destination);
        activityService.registerForActivity(SAMPLE_ID, getPremiumPassenger(SAMPLE_ID), destination);

        assertThrows(ActivityFullException.class,
                () -> activityService.registerForActivity(SAMPLE_ID, getPremiumPassenger(SAMPLE_ID), destination));

    }
}

