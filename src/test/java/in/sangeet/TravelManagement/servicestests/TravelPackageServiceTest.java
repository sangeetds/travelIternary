package in.sangeet.TravelManagement.servicestests;

import in.sangeet.TravelManagement.exceptions.TravelPackageFullException;
import in.sangeet.TravelManagement.model.*;
import in.sangeet.TravelManagement.services.TravelPackageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static in.sangeet.TravelManagement.TestConstants.*;
import static org.junit.jupiter.api.Assertions.*;

class TravelPackageServiceTest {

    private TravelPackageService travelPackageService;

    @BeforeEach
    void setUp() {
        travelPackageService = new TravelPackageService();
    }

    @Test
    void createTravelPackage_happyCase() {
        TravelPackage sampleTravelPackage = getSampleTravelPackage(SAMPLE_ID);
        travelPackageService.createTravelPackage(sampleTravelPackage);

        TravelPackage activity = travelPackageService.getTravelPackage(sampleTravelPackage.getId());

        assertEquals(sampleTravelPackage.getId(), activity.getId());
        assertEquals(sampleTravelPackage.getPassengers().size(), activity.getPassengers().size());
        assertEquals(sampleTravelPackage.getName(), activity.getName());
        assertEquals(sampleTravelPackage.getPassengerCapacity(), activity.getPassengerCapacity());
        assertEquals(sampleTravelPackage.getItinerary().size(), activity.getItinerary().size());
    }

    @Test
    void createTravelPackage_whenTravelPackageByIdAlreadyPresent() {
        TravelPackage sampleTravelPackage = getSampleTravelPackage(SAMPLE_ID);
        travelPackageService.createTravelPackage(sampleTravelPackage);
        assertThrows(IllegalArgumentException.class, () -> travelPackageService.createTravelPackage(sampleTravelPackage));
    }

    @Test
    void removeDestination_whenDestinationExists() {
        travelPackageService.createTravelPackage(getSampleTravelPackage(SAMPLE_ID));
        Destination destination = getSampleDestination(SAMPLE_ID);
        travelPackageService.addDestination(SAMPLE_ID, destination);

        travelPackageService.removeDestination(SAMPLE_ID, destination);

        TravelPackage travelPackage = travelPackageService.getTravelPackage(SAMPLE_ID);
        assertTrue(travelPackage.getItinerary().isEmpty());
    }

    @Test
    void removePassenger_whenPassengerExists() {
        travelPackageService.createTravelPackage(getSampleTravelPackage(SAMPLE_ID));
        Passenger passenger = getStandardPassenger(SAMPLE_ID);
        travelPackageService.addPassenger(SAMPLE_ID, passenger);

        travelPackageService.removePassenger(SAMPLE_ID, passenger);

        TravelPackage travelPackage = travelPackageService.getTravelPackage(SAMPLE_ID);
        assertTrue(travelPackage.getPassengers().isEmpty());
    }

    @Test
    void removePassenger_whenNoPassengerPresent() {
        assertThrows(IllegalArgumentException.class,
                () ->travelPackageService.removePassenger(SAMPLE_ID, getStandardPassenger(SAMPLE_ID)));
        assertThrows(IllegalArgumentException.class,
                () ->travelPackageService.removePassenger(SAMPLE_ID, null));
        assertThrows(IllegalArgumentException.class,
                () ->travelPackageService.removePassenger(2, null));
    }

    @Test
    void removeDestination_whenNoDestinationPresent() {
        assertThrows(IllegalArgumentException.class,
                () ->travelPackageService.removeDestination(SAMPLE_ID, getSampleDestination(SAMPLE_ID)));
    }

    @Test
    void addDestination_whenValidInputs_destinationAddedToItinerary() {
        Destination destination = getSampleDestination(SAMPLE_ID);
        travelPackageService.createTravelPackage(getSampleTravelPackage(SAMPLE_ID));

        travelPackageService.addDestination(SAMPLE_ID, destination);

        TravelPackage travelPackage = travelPackageService.getTravelPackage(SAMPLE_ID);

        List<Destination> itinerary = travelPackage.getItinerary().values().stream().toList();

        assertEquals(1, itinerary.size());
        assertEquals(destination, itinerary.get(0));
    }

    @Test
    void signUpPassenger_whenValidInputs_passengerAddedToTravelPackage() {
        Passenger passenger = new StandardPassenger("John Doe", 1, 100);

        travelPackageService.createTravelPackage(getSampleTravelPackage(SAMPLE_ID));

        travelPackageService.addPassenger(SAMPLE_ID, passenger);

        TravelPackage travelPackage = travelPackageService.getTravelPackage(SAMPLE_ID);

        List<Passenger> passengers = travelPackage.getPassengers().values().stream().toList();
        assertEquals(1, passengers.size());
        assertEquals(passenger, passengers.get(0));
    }


    @Test
    void signUpPassenger_whenNullTravelPackage_throwsIllegalArgumentException() {
        Passenger passenger = getStandardPassenger(SAMPLE_ID);

        assertThrows(IllegalArgumentException.class, () -> travelPackageService.addPassenger(9, passenger));

        travelPackageService.createTravelPackage(getSampleTravelPackage(SAMPLE_ID));
        assertThrows(IllegalArgumentException.class, () -> travelPackageService.addPassenger(SAMPLE_ID, null));
    }

    @Test
    void signUpPassenger_whenTravelPackageIsFull() {
        travelPackageService.createTravelPackage(getSampleTravelPackage(SAMPLE_ID));
        travelPackageService.addPassenger(SAMPLE_ID, getStandardPassenger(SAMPLE_ID));
        travelPackageService.addPassenger(SAMPLE_ID, getPremiumPassenger(2));

        assertThrows(TravelPackageFullException.class,
                () -> travelPackageService.addPassenger(SAMPLE_ID, getGoldPassenger(3)));
    }
}
