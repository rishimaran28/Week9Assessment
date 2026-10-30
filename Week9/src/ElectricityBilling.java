import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Base class for connections
abstract class Connection {
    protected String type;
    protected double units;

    public Connection(String type, double units) {
        this.type = type;
        this.units = units;
    }

    public String getType() {
        return type;
    }

    public abstract double calculateBill();
}

// Home: 5 per unit for first 100 units, 7 per unit after that
class HomeConnection extends Connection {
    public HomeConnection(double units) {
        super("HOME", units);
    }

    @Override
    public double calculateBill() {
        if (units <= 100) {
            return units * 5.0;
        } else {
            return (100 * 5.0) + ((units - 100) * 7.0);
        }
    }
}

// Shop: 8 per unit plus fixed charge of 100
class ShopConnection extends Connection {
    public ShopConnection(double units) {
        super("SHOP", units);
    }

    @Override
    public double calculateBill() {
        return (units * 8.0) + 100.0;
    }
}

// Factory: 6 per unit, minimum bill of 1000
class FactoryConnection extends Connection {
    public FactoryConnection(double units) {
        super("FACTORY", units);
    }

    @Override
    public double calculateBill() {
        return Math.max(units * 6.0, 1000.0);
    }
}

public class ElectricityBilling {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextInt()) {
            return;
        }
        int n = scanner.nextInt();
        List<Connection> connections = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String type = scanner.next();
            double units = scanner.nextDouble();
            if ("HOME".equalsIgnoreCase(type)) {
                connections.add(new HomeConnection(units));
            } else if ("SHOP".equalsIgnoreCase(type)) {
                connections.add(new ShopConnection(units));
            } else if ("FACTORY".equalsIgnoreCase(type)) {
                connections.add(new FactoryConnection(units));
            }
        }

        double total = 0.0;
        for (Connection conn : connections) {
            double bill = conn.calculateBill();
            total += bill;
            System.out.printf("%s: %.2f\n", conn.getType(), bill);
        }
        System.out.printf("Total: %.2f\n", total);

        scanner.close();
    }
}