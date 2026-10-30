import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Abstract base class preventing direct instantiation of generic staff member
abstract class Staff {
    protected String name;

    public Staff(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract double calculatePay();
}

// Full-time staff: fixed weekly salary
class FullTimeStaff extends Staff {
    private double weeklySalary;

    public FullTimeStaff(String name, double weeklySalary) {
        super(name);
        this.weeklySalary = weeklySalary;
    }

    @Override
    public double calculatePay() {
        return weeklySalary;
    }
}

// Hourly staff: rate for first 40 hours, 1.5 * rate for hours above 40
class HourlyStaff extends Staff {
    private double hours;
    private double rate;

    public HourlyStaff(String name, double hours, double rate) {
        super(name);
        this.hours = hours;
        this.rate = rate;
    }

    @Override
    public double calculatePay() {
        if (hours <= 40) {
            return hours * rate;
        } else {
            return (40 * rate) + ((hours - 40) * rate * 1.5);
        }
    }
}

// Intern: fixed stipend
class InternStaff extends Staff {
    private double stipend;

    public InternStaff(String name, double stipend) {
        super(name);
        this.stipend = stipend;
    }

    @Override
    public double calculatePay() {
        return stipend;
    }
}

public class WeeklyStaffPay {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextInt()) {
            return;
        }
        int n = scanner.nextInt();
        List<Staff> staffList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String type = scanner.next();
            String name = scanner.next();
            if ("FULLTIME".equalsIgnoreCase(type)) {
                double weeklySalary = scanner.nextDouble();
                staffList.add(new FullTimeStaff(name, weeklySalary));
            } else if ("HOURLY".equalsIgnoreCase(type)) {
                double hours = scanner.nextDouble();
                double rate = scanner.nextDouble();
                staffList.add(new HourlyStaff(name, hours, rate));
            } else if ("INTERN".equalsIgnoreCase(type)) {
                double stipend = scanner.nextDouble();
                staffList.add(new InternStaff(name, stipend));
            }
        }

        double totalPayroll = 0.0;
        for (Staff staff : staffList) {
            double pay = staff.calculatePay();
            totalPayroll += pay;
            System.out.printf("%s: %.2f\n", staff.getName(), pay);
        }
        System.out.printf("Total Payroll: %.2f\n", totalPayroll);

        scanner.close();
    }
}