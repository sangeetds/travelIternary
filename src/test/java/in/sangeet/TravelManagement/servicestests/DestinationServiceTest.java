package in.sangeet.TravelManagement.servicestests;

import in.sangeet.TravelManagement.exceptions.ActivityAlreadyPresentInDestinationException;
import in.sangeet.TravelManagement.model.Activity;
import in.sangeet.TravelManagement.model.Destination;
import in.sangeet.TravelManagement.services.DestinationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static in.sangeet.TravelManagement.TestConstants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DestinationServiceTest {

    private DestinationService destinationService;

    @BeforeEach
    void setUp() {
        destinationService = new DestinationService();
    }

    @Test
    void createDestination_happyCase() {
        Destination sampleDestination = getSampleDestination(SAMPLE_ID);
        destinationService.createDestination(sampleDestination);

        Destination destination = destinationService.getDestination(sampleDestination.getId());

        assertEquals(sampleDestination.getId(), destination.getId());
        assertEquals(sampleDestination.getName(), destination.getName());
        assertEquals(sampleDestination.getActivities().size(), destination.getActivities().size());
    }

    @Test
    void createDestination_whenDestinationByIdAlreadyPresent() {
        Destination sampleDestination = getSampleDestination(SAMPLE_ID);
        destinationService.createDestination(sampleDestination);
        assertThrows(IllegalArgumentException.class, () -> destinationService.createDestination(sampleDestination));
    }

    @Test
    void testAddActivity_ToDestination_ValidInputs_ActivitiesAdded() throws ActivityAlreadyPresentInDestinationException {
        destinationService.createDestination(getSampleDestination(SAMPLE_ID));

        Activity activity1 = getSampleActivity(SAMPLE_ID);
        Activity activity2 = getSampleActivity(SAMPLE_ID_2);

        destinationService.addActivityToDestination(SAMPLE_ID, activity1);
        destinationService.addActivityToDestination(SAMPLE_ID, activity2);

        Destination destination = destinationService.getDestination(SAMPLE_ID);

        List<Activity> activities = destination.getActivities().values().stream().toList();
        assertEquals(2, activities.size());
    }

    @Test
    void registerForDestination_invalidArguments() {
        assertThrows(IllegalArgumentException.class,
                () -> destinationService.addActivityToDestination(SAMPLE_ID, null));

        destinationService.createDestination(getSampleDestination(SAMPLE_ID));

        assertThrows(IllegalArgumentException.class,
                () -> destinationService.addActivityToDestination(SAMPLE_ID, null));
    }

    @Test
    void testAddActivity_whenActivityIsAlreadyPresent() {
        destinationService.createDestination(getSampleDestination(SAMPLE_ID));

        Destination destination = getSampleDestination(SAMPLE_ID);
        Activity activity = getSampleActivity(SAMPLE_ID);

        destinationService.addActivityToDestination(destination.getId(), activity);
        assertThrows(ActivityAlreadyPresentInDestinationException.class,
            () -> destinationService.addActivityToDestination(destination.getId(), activity));
    }
}
