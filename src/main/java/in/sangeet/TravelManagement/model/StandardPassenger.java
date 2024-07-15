package in.sangeet.TravelManagement.model;

/**
 * Class representing a standard passenger.
 */
public class StandardPassenger extends Passenger {

    private double balance;

    /**
     * Constructor for StandardPassenger.
     *
     * @param name The name of the passenger.
     * @param passengerNumber The unique passenger number.
     * @param balance The initial balance of the passenger.
     */
    public StandardPassenger(String name, long passengerNumber, double balance) {
        super(name, passengerNumber);
        this.balance = balance;
    }

    @Override
    public boolean canRegisterForActivity(Activity activity) {
        return activity.getCost() <= balance;
    }

    @Override
    public double getBalance() {
        return balance;
    }

    @Override
    public void deductBalance(double amount) {
        this.balance -= amount;
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
