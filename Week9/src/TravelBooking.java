import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Abstract base class handling common fee in one place
abstract class TravelBookingClass {
    protected static final double BOOKING_FEE = 50.0;
    protected String mode;
    protected double distanceKm;

    public TravelBookingClass(String mode, double distanceKm) {
        this.mode = mode;
        this.distanceKm = distanceKm;
    }

    public String getMode() {
        return mode;
    }

    // Subclasses implement unique base fare calculation
    public abstract double calculateBaseFare();

    // Final total fare adds the common booking fee (only defined in this one place)
    public double calculateTotalFare() {
        return calculateBaseFare() + BOOKING_FEE;
    }
}

// Bus: 2 per km
class BusBooking extends TravelBookingClass {
    public BusBooking(double distanceKm) {
        super("BUS", distanceKm);
    }

    @Override
    public double calculateBaseFare() {
        return distanceKm * 2.0;
    }
}

// Train: 1.5 per km
class TrainBooking extends TravelBookingClass {
    public TrainBooking(double distanceKm) {
        super("TRAIN", distanceKm);
    }

    @Override
    public double calculateBaseFare() {
        return distanceKm * 1.5;
    }
}

// Flight: 2500 plus 4 per km
class FlightBooking extends TravelBookingClass {
    public FlightBooking(double distanceKm) {
        super("FLIGHT", distanceKm);
    }

    @Override
    public double calculateBaseFare() {
        return 2500.0 + (distanceKm * 4.0);
    }
}

public class TravelBooking {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextInt()) {
            return;
        }
        int n = scanner.nextInt();
        List<TravelBookingClass> bookings = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String mode = scanner.next();
            double distanceKm = scanner.nextDouble();
            if ("BUS".equalsIgnoreCase(mode)) {
                bookings.add(new BusBooking(distanceKm));
            } else if ("TRAIN".equalsIgnoreCase(mode)) {
                bookings.add(new TrainBooking(distanceKm));
            } else if ("FLIGHT".equalsIgnoreCase(mode)) {
                bookings.add(new FlightBooking(distanceKm));
            }
        }

        for (TravelBookingClass booking : bookings) {
            System.out.printf("%s: %.2f\n", booking.getMode(), booking.calculateTotalFare());
        }

        scanner.close();
    }
}