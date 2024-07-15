package in.sangeet.TravelManagement.model;

/**
 * Class representing a gold passenger.
 */
public class GoldPassenger extends Passenger {

    private double balance;

    /**
     * Constructor for GoldPassenger.
     *
     * @param name The name of the passenger.
     * @param passengerNumber The unique passenger number.
     * @param balance The initial balance of the passenger.
     */
    public GoldPassenger(String name, long passengerNumber, double balance) {
        super(name, passengerNumber);
        this.balance = balance;
    }

    @Override
    public boolean canRegisterForActivity(Activity activity) {
        double discountedCost = activity.getCost() * 0.9;
        return discountedCost <= balance;
    }

    @Override
    public double getBalance() {
        return balance;
    }

    @Override
    public void deductBalance(double amount) {
        this.balance -= amount * 0.9;
    }

    @Override
    public void printDetails() {
        System.out.println("Passenger: " + getName());
        System.out.println("Passenger Number: " + getPassengerNumber());
        System.out.println("Balance: " + getBalance());
        for (Activity activity : getActivities()) {
            System.out.println("Activity: " + activity.getName() +
                    ", Destination: " + activity.getDestination().getName() +
                    ", Cost: " + activity.getCost());
        }
    }
}
