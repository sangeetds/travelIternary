package in.sangeet.TravelManagement;

import in.sangeet.TravelManagement.services.ActivityService;
import in.sangeet.TravelManagement.services.DestinationService;
import in.sangeet.TravelManagement.services.TravelPackageService;

/**
 * Driver app
 */
public class TravelAgencyItineraryManagementApplication {
    ActivityService activityService;
    DestinationService destinationService;
    TravelPackageService travelPackageService;


    public TravelAgencyItineraryManagementApplication() {
        this.activityService = new ActivityService();
        this.destinationService = new DestinationService();
        this.travelPackageService = new TravelPackageService();
    }

    public static void main(String[] args) {

    }
}
