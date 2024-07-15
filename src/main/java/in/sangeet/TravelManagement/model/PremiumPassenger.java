package in.sangeet.TravelManagement.model;

/**
 * Class representing a premium passenger.
 */
public class PremiumPassenger extends Passenger {
    /**
     * Constructor for PremiumPassenger.
     *
     * @param name The name of the passenger.
     * @param passengerNumber The unique passenger number.
     */
    public PremiumPassenger(String name, long passengerNumber) {
        super(name, passengerNumber);
    }

    @Override
    public boolean canRegisterForActivity(Activity activity) {
        return true;
    }

    @Override
    public double getBalance() {
        return 0;
    }

    @Override
    public void deductBalance(double amount) {
        // Premium passengers do not have balance
    }

    @Override
    public void printDetails() {
        System.out.println("Passenger: " + getName());
        System.out.println("Passenger Number: " + getPassengerNumber());
        for (Activity activity : getActivities()) {
            System.out.println("Activity: " + activity.getName() +
                    ", Destination: " + activity.getDestination().getName() +
                    ", Cost: " + activity.getCost());
        }
    }
}