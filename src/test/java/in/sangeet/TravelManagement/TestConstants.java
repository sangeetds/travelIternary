package in.sangeet.TravelManagement;


import in.sangeet.TravelManagement.model.*;

import java.util.LinkedList;

public class TestConstants {
     public static final long   SAMPLE_ID            = 1;
     public static final long   SAMPLE_ID_2          = 2;
     public static final String DESTINATION_NAME     = "Bangalore";
     public static final String ACTIVITY_NAME        = "Pub crawling";
     public static final String ACTIVITY_DESCRIPTION = "Visit many pubs";
     public static final double ACTIVITY_COST        = 100;
     public static final double HIGH_ACTIVITY_COST   = 10000;
     public static final double BALANCE              = 200;
     public static final String TRAVEL_PACKAGE       = "Bangalore Tour";
     public static final String PASSENGER_NAME       = "Sangeet";
     public static final int    CAPACITY             = 4;
     public static final int    LOW_CAPACITY         = 2;


    public static Activity getSampleActivity(long id) {
        return new Activity(
                id,
                ACTIVITY_NAME,
                ACTIVITY_DESCRIPTION,
                ACTIVITY_COST,
                CAPACITY,
                getSampleDestination(SAMPLE_ID)
        );
    }

    public static Activity getExpensiveActivity() {
        return new Activity(
                SAMPLE_ID_2,
                ACTIVITY_NAME,
                ACTIVITY_DESCRIPTION,
                HIGH_ACTIVITY_COST,
                CAPACITY,
                getSampleDestination(SAMPLE_ID)
        );
    }

    public static Destination getSampleDestination(long id) {
        return new Destination(
                id,
                DESTINATION_NAME
        );
    }

    public static TravelPackage getSampleTravelPackage(long travelId) {
        return new TravelPackage(
                travelId,
                TRAVEL_PACKAGE,
                LOW_CAPACITY
        );
    }

    public static Passenger getStandardPassenger(long id) {
        return new StandardPassenger(PASSENGER_NAME, id, BALANCE);
    }

    public static Passenger getGoldPassenger(long id) {
        return new GoldPassenger(PASSENGER_NAME, id, BALANCE);
    }

    public static Passenger getPremiumPassenger(long id) {
        return new PremiumPassenger(PASSENGER_NAME, id);
    }
}
